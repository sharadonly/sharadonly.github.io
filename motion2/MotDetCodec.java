/**
 * MotDetCodec.java
 *
 * This is the motion detection effect codec, it detects motion by comparing
 * frames and when motion is detected it makes this clear by marking it
 * in the output.
 *
 * Most of the code was inspired by code from
 * http://darnok.com/programming/motion-detection/
 * by Konrad Rzeszutek.
 *
 * All the actual motion detection however was inspired by the report
 * "An Introduction to Digital Image Processing" by Frédéric Patin,
 * available from http://www.yov408.com/
 *
 * The "skeleton" of the codec was imported from TimeStampEffect.java
 * in the above package.
 */

import javax.media.*;
import javax.media.format.*;
import java.awt.*;

/**
 * The motion detection codec, an effect filter.
 * Created on den 12 september 2004, 13:09
 *
 * @author  Jan Lindblom <linjan-1@student.luth.se>
 * @version 1.3
 * 
 */
public class MotDetCodec implements Effect {
	Format inputFormat;
	Format outputFormat;
	Format[] inputFormats;
	Format[] outputFormats;
	
	private byte[] refData, inpData;
        private byte[] rData, iData;
	
	private int frameCounter = 0;
        private int frameCount = 5;
        private String inVideoMsg = "";
	private int pixStrideIn;
	private int lineStrideIn;
	
        /* settings for talking to the GUI */
        private boolean motdet = false;
        private int signMov = 0;
        private int movement_percent = 0;
        
	/**
	 * Creates a new instance of MotDetCodec
	 *
	 */
	public MotDetCodec() {
		/* set input and output formats of the codec */
		inputFormats = new Format[] {
			new RGBFormat(null,
					Format.NOT_SPECIFIED,
					Format.byteArray,
					Format.NOT_SPECIFIED,
					24,
					3, 2, 1,
					3, Format.NOT_SPECIFIED,
					Format.TRUE,
					Format.NOT_SPECIFIED)
		};
		
		outputFormats = new Format[] {
			new RGBFormat(null,
					Format.NOT_SPECIFIED,
					Format.byteArray,
					Format.NOT_SPECIFIED,
					24,
					3, 2, 1,
					3, Format.NOT_SPECIFIED,
					Format.TRUE,
					Format.NOT_SPECIFIED)
		};
	}
	
        /**
         * Enables or disables motion detection
         * @param detection motion detection enabled
         */
        public void setDetection(boolean detection) {
            motdet = detection;
        }
        
         /**
          * Sets the value at which movement is to be
          * considered significant.
          * @param significant the percentage of movement
          * which is to be considered significant.
          */
        public void setSignMovement(int significant) {
            signMov = significant;
        }
        
        /** Sets the number of frames between the reference and the input frame
         * @param frames number of frames between input and reference
         */        
        public void setFrameSpace(int frames) {
            frameCount = frames;
        }
        /** Set the message String to appear in the video.
         * @param msg the message to display
         */        
        public void setVideoMsg(String msg) {
            inVideoMsg = msg;
        }
        /**
         * Returns the percentage of current movement.
         * @return integer percentage of current movement.
         */
         public int getMovementPercentage() {
             return movement_percent;
         }
        
	/** 
	 * getControl(String str) - get a Control Object for this codec
	 * 
	 * @param str Control Object to be returned
	 * @return null - since there are no Controls for this codec (yet)
	 */
	public Object getControl(String str) {
		return null;
	}
	
	/** 
	 * getControls() - get the Controls related to this codec
	 * 
	 * @return null - since there are no Controls for with this codec
	 */
	public Object[] getControls() {
		return null;
	}
	
	/** 
	 * getName() - get the name of this codec
	 * 
	 * @return the name of this codec
	 */
	public String getName() {
		return "Motion Detection Codec";
	}
	
	/** 
	 * getSupportedInputFormats() - get the supported input formats of
	 * this codec.
	 * 
	 * @return the supported input formats of this codec
	 */
	public javax.media.Format[] getSupportedInputFormats() {
		return inputFormats;
	}
	
	/** 
	 * getSupportedOutputFormats(Format input) - get the supported output
	 * formats of this codec.
	 * 
	 * @param input Format to intesect with existing output formats
	 * @return the Format[] array of supported output formats
	 */
	public Format [] getSupportedOutputFormats(Format input) {
		if (input == null) {
			return outputFormats;
		}
		
		if (matches(input, inputFormats) != null) {
			return new Format[] { outputFormats[0].
				intersects(input) };
		}
		else {
			return new Format[0];
		}
	}
	
	/**
         * open() - does nothing in this implementation.
         * @throws javax.media.ResourceUnavailableException on exception
         */
	public void open() throws javax.media.ResourceUnavailableException {
	}
	
	/** 
	 * process(Buffer in, Buffer out) - this is where the work is done by
	 * the codec.
	 * 
	 * @param inBuffer Input Buffer for this codec
	 * @param outBuffer Output Buffer for this codec
	 * @return status of operation on the buffers
	 */
	public int process(Buffer inBuffer, Buffer outBuffer) {
		int col=0; // colors, b=0, g=1, r=2
		int rpos = 0, gpos = 0, bpos = 0;
		/* java lacks unsigned byte and int so we use char */
		char rIn = 0, gIn = 0, bIn = 0, hIn = 0; 
		char rRef = 0, gRef = 0, bRef = 0, hRef = 0;
		char hDiff = 0;
		/* "movement steps" coloring of movement is based on these.
                 * These _might_ be moved to the GUI in a later implementation
		 */
		int step0=2720, step1=2800, step2=2900, step3=3000;
		int avg_movement = 0, arne = 0;
		
		int outputDataLength = ((VideoFormat)outputFormat).
			getMaxDataLength();
		validateByteArraySize(outBuffer, outputDataLength);
		
		outBuffer.setLength(outputDataLength);
		outBuffer.setFormat(outputFormat);
		outBuffer.setFlags(inBuffer.getFlags());
		
		byte [] inData = (byte[]) inBuffer.getData();
		byte [] outData = (byte[]) outBuffer.getData();
		
		RGBFormat vfIn = (RGBFormat) inBuffer.getFormat();
		Dimension sizeIn = vfIn.getSize();

                /* these are to be used in later implementations */
		pixStrideIn = vfIn.getPixelStride();
		lineStrideIn = vfIn.getLineStride();
		
		if ( outData.length < sizeIn.width*sizeIn.height*3 ) {
			System.out.println("the buffer is not full");
			return BUFFER_PROCESSED_FAILED;
		}
		
		if (refData == null)
			refData = new byte[outputDataLength];
		
                inpData = new byte[outputDataLength];
                
		System.arraycopy(inData,0,outData,0,inData.length);
		
		for (int ii=0;ii < outputDataLength;ii++) {
			switch (col) {
				case 0: // get the blue value
					bpos = ii;
					rIn = (char)outData[bpos];
					rRef = (char)refData[bpos];
					col = 1;
					break;
				case 1: // get the green value
					gpos = ii;
					gIn = (char)outData[gpos];
					gRef = (char)refData[gpos];
					col = 2;
					break;
				case 2: // get the red value
					rpos = ii;
					bIn = (char)outData[rpos];
					bRef = (char)refData[rpos];
					col = 0;
					break;
			}
			
			/* now we have the bytes for the colors of the current
			 * pixel and the positions of these in the byteArray */
			/* time for grayscale conversion */
			
			/* convert refData to 16 shade grayscale */
			hRef = (char)((rRef + gRef + bRef)/(char)3);
			hRef = (char)(hRef/(char)16);
			/* we write this to refData aswell */
			refData[rpos] = (byte)hRef;
			refData[gpos] = (byte)hRef;
			refData[bpos] = (byte)hRef;
			
			/* convert inData to 16 shade grayscale */
			hIn = (char)((rIn + gIn + bIn)/(char)3);
			hIn = (char)(hIn/(char)16);
                        inpData[rpos] =(byte)hIn;
                        inpData[gpos] = (byte)hIn;
                        inpData[bpos] = (byte)hIn;
			
			/* check difference between current and reference
			 * frame */
			hDiff = (hIn > hRef) ? (char)(hIn - hRef):
				(char)(hRef - hIn);
			
			/* color output depending of amount of movement */
			if ((char)hDiff > (char)step0 &&
					(char)hDiff < (char)step1) {
				avg_movement++;
				outData[rpos] = (byte)255;
				outData[gpos] = (byte)0;
				outData[bpos] = (byte)0;
			}
			else if ((char)hDiff > (char)step1 &&
					(char)hDiff < (char)step2) {
				avg_movement++;
				outData[rpos] = (byte)255;
				outData[gpos] = (byte)100;
				outData[bpos] = (byte)0;
			}
			else if ((char)hDiff > (char)step2 &&
					(char)hDiff < (char)step3) {
				avg_movement++;
				outData[rpos] = (byte)255;
				outData[gpos] = (byte)200;
				outData[bpos] = (byte)0;
			}
			else if ((char)hDiff > (char)step3) {
				avg_movement++;
				outData[rpos] = (byte)255;
				outData[gpos] = (byte)255;
				outData[bpos] = (byte)0;
			}
		}
                
                /* Now this is the fun and ugly part, it basically replaces all areas with
                 * "significant" movement with good looking squares in the image.
                 */
                System.arraycopy(inData,0,inpData,0,inData.length);
                for (int ii=0;ii < outData.length -
				(7 * lineStrideIn) -
				(7 * pixStrideIn) - 2;
				ii = ii + (7 * pixStrideIn)) {
                    bpos = ii;
                    gpos = ii+1;
                    rpos = ii+2;
                    
                    /* the following code draws the "boxes" in the video,
                     * I know it looks quite ugly but it's fast */
                    if (outData[rpos + 3 * pixStrideIn + 3 * lineStrideIn] ==
				    (byte)255 && outData[bpos +
				    3 * pixStrideIn + 3 * lineStrideIn] ==
				    (byte)0) {
                        if (outData[rpos + (4 * pixStrideIn) +
					3 * lineStrideIn] ==
					(byte)255 && outData[bpos +
					(4 * pixStrideIn) +
					3 * lineStrideIn] == (byte)0) {
                            if (outData[rpos + (4 * lineStrideIn) +
					    3 * pixStrideIn] ==
					    (byte)255 && outData[bpos +
					    (4 * lineStrideIn) +
					    3 * pixStrideIn] == (byte)0) {
                                if (outData[rpos + (4 * lineStrideIn) +
						(4 * pixStrideIn)] ==
						(byte)255 && outData[bpos +
						(4 * lineStrideIn) +
						(4 * pixStrideIn)] ==
						(byte)0) {
                                    inpData[rpos + 7 * pixStrideIn +
					    7 * lineStrideIn] =
					    (byte)outData[rpos +
					    7 * pixStrideIn +
					    7 * lineStrideIn];
				    inpData[gpos + 7 * pixStrideIn +
					    7 * lineStrideIn] =
					    (byte)outData[gpos +
					    7 * pixStrideIn +
					    7 * lineStrideIn];
				    inpData[bpos + 7 * pixStrideIn +
					    7 * lineStrideIn] =
					    (byte)outData[bpos +
					    7 * pixStrideIn +
					    7 * lineStrideIn];
				    
                                    for (int x = 0;x < 8;x++) {
					    inpData[rpos + 
						    (x * pixStrideIn)] =
						    (byte)outData[rpos +
						    3 * pixStrideIn +
						    3 * lineStrideIn];
					    inpData[gpos +
						    (x * pixStrideIn)] =
						    (byte)outData[gpos +
						    3 * pixStrideIn +
						    3 * lineStrideIn];
					    inpData[bpos +
						    (x * pixStrideIn)] =
						    (byte)outData[bpos +
						    3 * pixStrideIn +
						    3 * lineStrideIn];
				    }
                                    for (int x = 0;x < 8;x++) {
                                        for (int y = 0;y < 8;y++) {
                                            inpData[rpos +
						    (x * pixStrideIn) +
						    (y * lineStrideIn)] =
						    (byte)outData[rpos +
						    3 * pixStrideIn +
						    3 * lineStrideIn];
					    inpData[gpos +
						    (x * pixStrideIn) +
						    (y * lineStrideIn)] =
						    (byte)outData[gpos +
						    3 * pixStrideIn +
						    3 * lineStrideIn];
					    inpData[bpos+
						    (x * pixStrideIn) +
						    (y * lineStrideIn)] =
						    (byte)outData[bpos +
						    3 * pixStrideIn +
						    3 * lineStrideIn];
					}
				    }
                                    for (int y=0;y<8;y++) {
                                        inpData[rpos +
						(y * lineStrideIn)] =
						(byte)outData[rpos +
						3 * pixStrideIn +
						3 * lineStrideIn];
					inpData[gpos +
						(y * lineStrideIn)] =
						(byte)outData[gpos +
						3 * pixStrideIn +
						3 * lineStrideIn];
					inpData[bpos +
						(y * lineStrideIn)] =
						(byte)outData[bpos +
						3 * pixStrideIn +
						3 * lineStrideIn];
					inpData[rpos +
						(y * lineStrideIn) +
						(7 * pixStrideIn)] =
						(byte)outData[rpos +
						3 * pixStrideIn +
						3 * lineStrideIn];
					inpData[gpos +
						(y * lineStrideIn) +
						(7 * pixStrideIn)] =
						(byte)outData[gpos +
						3 * pixStrideIn +
						3 * lineStrideIn];
					inpData[bpos +
						(y * lineStrideIn) +
						(7 * pixStrideIn)] =
						(byte)outData[bpos +
						3 * pixStrideIn +
						3 * lineStrideIn];
				    }
                                    ii = ii + (7 * lineStrideIn);
                                }
                            }
                        }
                    }
                }
                System.arraycopy(inpData, 0, outData, 0, inpData.length);
		/* divide average movement by 3 (since we've got it by
		 * increasing for each color difference), this way we get
		 * the number of changed pixels in the frame */
		avg_movement = (int)(avg_movement/3);
		/* generate the percentage of change */
		movement_percent = (int)((100*avg_movement)/
				(sizeIn.width*sizeIn.height));
		/* if the average movement is less than significant, discard
		 * data and display the input directly. */
		if (movement_percent < signMov || !motdet) {
                    System.arraycopy(inData, 0, outData, 0, inData.length);
                }
		/* increase frame counter */
		frameCounter++;
		/* save a new reference frame if we've seen enough frames */
                if (frameCount != 0) {
                    if (frameCounter == frameCount) {
                        System.arraycopy(inData, 0, refData, 0, inData.length);
                        frameCounter = 0; /* reset frame counter aswell */
                    }
                }
                else if (frameCount == 0) {
                    System.arraycopy(inData, 0, refData, 0, inData.length);
                        frameCounter = 0; /* reset frame counter aswell */
                }
		/* print out the percentage using konrads Font.class */
		Font.println("Average movement: " + movement_percent + "%",
				Font.FONT_6x11, 10, 20, (byte)255,
				(byte)255,(byte)255, outBuffer);
                Font.println(inVideoMsg,
				Font.FONT_8x8, 10, 35, (byte)255,
				(byte)255,(byte)255, outBuffer);
		avg_movement = 0;
                arne = 0;
		return BUFFER_PROCESSED_OK;
	}
	
	/** 
	 * close() - not used in this implementation.
	 */
	public void close() {
	}
	
	/** 
	 * reset() - not used in this implementation.
	 */
	public void reset() {
	}
	
	/** 
	 * setInputFormat(Format format) - register which input format
	 * to accept.
	 * 
	 * @param format the format to accept
	 * @return the added format to use for input
	 */
	public javax.media.Format setInputFormat(javax.media.Format format) {
		inputFormat = format;
		return inputFormat;
	}
	
	/** 
	 * setOutPutFormat(Format output) - register which output format to
	 * accept.
	 * 
	 * @param output the format to accept
	 * @return the added format to use for output
	 */
	public Format setOutputFormat(Format output) {
		if (output == null || matches(output, outputFormats) == null)
			return null;
		RGBFormat incoming = (RGBFormat) output;
		
		Dimension size = incoming.getSize();
		int maxDataLength = incoming.getMaxDataLength();
		int lineStride = incoming.getLineStride();
		float frameRate = incoming.getFrameRate();
		int flipped = incoming.getFlipped();
		int endian = incoming.getEndian();
		
		if (size == null)
			return null;
		if (maxDataLength < size.width * size.height * 3)
			maxDataLength = size.width * size.height * 3;
		if (lineStride < size.width * 3)
			lineStride = size.width * 3;
		if (flipped != Format.FALSE)
			flipped = Format.FALSE;
		
		outputFormat = outputFormats[0].intersects(new RGBFormat(size,
					maxDataLength,
					null,
					frameRate,
					Format.NOT_SPECIFIED,
					Format.NOT_SPECIFIED,
					Format.NOT_SPECIFIED,
					Format.NOT_SPECIFIED,
					Format.NOT_SPECIFIED,
					lineStride,
					Format.NOT_SPECIFIED,
					Format.NOT_SPECIFIED));
		return outputFormat;
	}
	
	/** 
	 * matches(Format in, Format outs[]) - checks if in exists in outs[].
	 * Inported from TimeStampEffect.java from above mentioned site.
	 * 
	 * @param in format to look for
	 * @param outs where to look for it
	 * @return the matching format in outs[]
	 */
	Format matches(Format in, Format outs[]) {
		for (int i = 0;i < outs.length;i++) {
			if (in.matches(outs[i]))
				return outs[i];
		}
		return null;
	}
	
	/** 
	 * Validates the size of the Buffer buffer.
	 * Imported from TimeStampEffect.java from above mentioned site.
	 * 
	 * @param buffer Buffer to check size of.
	 * @param newSize Desired size of buffer.
	 * @return A byteArray of the correct size.
	 */
	byte[] validateByteArraySize(Buffer buffer,int newSize) {
		Object objectArray=buffer.getData();
		byte[] typedArray;
		
		if (objectArray instanceof byte[]) {
			typedArray=(byte[])objectArray;
			if (typedArray.length >= newSize ) {
				return typedArray;
			}
			
			byte[] tempArray=new byte[newSize];
			System.arraycopy(typedArray,0,tempArray,0,
					typedArray.length);
			typedArray = tempArray;
		} else {
			typedArray = new byte[newSize];
		}
		
		buffer.setData(typedArray);
		return typedArray;
	}
}
