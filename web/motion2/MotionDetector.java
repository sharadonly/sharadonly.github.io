/**
 * MotionDetector.java
 *
 * This is a motion detection application. It uses a GUI to view the effects 
 * and options of the motion detection. For the actual motion detection, it is 
 * using a motion detection effect codec (MotDetCodec).
 *
 * Some of the code was inspired by code from
 * http://darnok.com/programming/motion-detection/
 * by Konrad Rzeszutek.
 *
 * Created on den 13 september 2004, 08:39
 */

/**
 *
 * @author  Anna Tanskanen <carana-6@student.luth.se>
 * @author  Jan Lindblom <linjan-1@student.luth.se>
 * @author  Ng Sae Kiat <saengx-4@student.luth.se>
 * @version 1.5
 * 
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.media.*;
import javax.media.control.*;
import javax.media.format.*;
import javax.media.protocol.*;
import javax.media.Format.*;
import javax.swing.border.*;
import java.io.*;


/** asdf */
public class MotionDetector implements javax.media.ControllerListener{

    private static Processor player;
    private static MotionDetectorGUI mdGUI;
    private Object waitSync = new Object();
    private boolean stateTransitionOK = true; 
    private static MotDetCodec mdCodec;
    private static Clone clone = null;
    
    private static VideoTransmit vt;
    private static DataSource cloneableDataSource = null;
    private static DataSource clonedDataSource = null;    
    private static DataSource origDataSource = null;  
    
    /** Returnes the processor of this MotionDetector.
     * @return a processor
     */
    public Processor getProcessor(){
        return player;
    }
    
    
    /** Sets up the media file for "url" and starts the playback of that
     * media file.
     * @param url the url to a media file
     */
    public void open( String url ) {
        MediaLocator ml;
        
        // create a media locator from the url
        if ((ml = new MediaLocator(url)) == null) {
            System.out.println("Cannot build URL for " + url);
            System.exit(0);
        }
        
        try {
            origDataSource = Manager.createDataSource(ml);
        } catch (Exception e) {
            System.err.println("Cannot create DataSource from " + ml);
            System.exit(0);
        }
        cloneableDataSource = Manager.createCloneableDataSource(origDataSource);
        if (cloneableDataSource == null) {
            System.err.println("Cannot clone DataSource");
            System.exit(0);
        }
        clone = new Clone();
        
        if (!clone.open(cloneableDataSource))
            System.exit(0);
        
        try {
            player = clone.getProcessor();
	} 
        catch ( Exception e ) {
            System.out.println(e);
            System.out.println("Could not create player for " + ml);
	    System.exit(0);
	}

	player.addControllerListener( this );

	// put the processor into configured state.
	player.configure();
        
	if ( !waitForState( player.Configured ) ) {
	    System.err.println("Failed to configure the processor.ipAddress");
	    System.exit(0);
	}    
        
	// to use the processor as a player
	player.setContentDescriptor(null);
        
	// obtain the track controls.
	TrackControl tc[] = player.getTrackControls();
	if (tc == null) {
            System.err.println("Failed to obtain track controls from the processor.");
	    System.exit(0);
	}

	// search for the video track control.
	TrackControl videoTrack = null;
	for (int i = 0; i < tc.length; i++) {
	    if (tc[i].getFormat() instanceof VideoFormat) {
		videoTrack = tc[i];
		break;
	    }
	}

        // if there is no video track control we exits
	if (videoTrack == null) {
	    System.err.println("The input media does not contain a video track.");
	    System.exit(0);
	}
        
        // set up the codec chain for playback
        try{
            mdCodec = new MotDetCodec();
            Codec[] codec = {mdCodec};
            videoTrack.setCodecChain( codec );
        } catch(Exception e){
        }
        
	// realize the processor.
	player.prefetch();
        
	if (!waitForState(player.Prefetched)) {
	    System.err.println("Failed to realize the processor.");
	    System.exit(0);
	}           

        // start media file playback
        if (player != null) {
            player.start();
        }
        
        /* create second clone to transmit */
        clone = new Clone();
        if (!clone.open(((SourceCloneable)cloneableDataSource).createClone()))
            System.exit(0);
    }

    
     /**
     * Block until the processor has transitioned to the given state.
     * Return false if the transition failed.
     *
     * Copied from TestMotionDetection.java, located on 
     * http://darnok.com/programming/motion-detection/,
     * written by Konrad Rzeszutek.
     *
     * @param state the wanted state for the processor
     */
    boolean waitForState(int state) {
        // waits for a state confirmation for the player
	synchronized (waitSync) {
	    try {
		while (player.getState() != state && stateTransitionOK)
		    waitSync.wait();
	    } catch (Exception e) {}
	}
	return stateTransitionOK;
    }
    
     /** The update method for this ControllerListener, sets he proper actions
     * when updated.
     *
     * @param event the occured event
     */
    public void controllerUpdate(ControllerEvent event) {
   
        // if the playeris dead - we leave 
	if (player == null)
	    return;
  
        if ( event instanceof ConfigureCompleteEvent ||
	     event instanceof RealizeCompleteEvent   ||
	     event instanceof PrefetchCompleteEvent ) {
	    synchronized ( waitSync ) {
		stateTransitionOK = true;
		waitSync.notifyAll();
	    }
	} else if ( event instanceof ResourceUnavailableEvent ) {
	    synchronized ( waitSync ) {
		stateTransitionOK = false;
		waitSync.notifyAll();
	    }
        // close the application
	} else if ( event instanceof EndOfMediaEvent       ||
                    event instanceof ControllerClosedEvent ||
                    event instanceof ControllerErrorEvent ) {
	    player.close();
	    System.exit(0);  
	}
    }
    
    /**
     * Starts the motion detection.
     */
    public void start() {
        mdCodec.setDetection( true );
    }
      
      
    /**
     * Stops the motion detection.
     */
    public void stop() {
        mdCodec.setDetection( false );
    }
    
    
    /** Sets the minimum percentage of average movement sensitivity for 
     * motion detection.
     *
     * @param value minimum sensitivity
     */
    public void setSignificantMovement( int value ) {
      mdCodec.setSignMovement( value );
    }
    /** sets the number of frames between input and reference in the codec
     * @param frames the number of frames between input and reference in the codec
     */    
    public void setFrameSpace(int frames) {
        mdCodec.setFrameSpace(frames);
    }
    /**
     * Sets the text to display in the video.
     *
     * @param string text to display in video.
     */    
    public void setInVideoText(String string) {
        mdCodec.setVideoMsg(string);
    }
    /** Returns the movement percentage in the output media file 
     *
     * @return movement percentage
     */    
    public static int getMovementPercentage() {
        return mdCodec.getMovementPercentage();
    }
      
    /** Sets up and starts the motion detectior application
     * @param args arguments for the application
     */
    public static void main( String args[] ) {
        MotionDetector md = new MotionDetector();
        md.open("vfw://0");
     
        mdGUI = new MotionDetectorGUI( md );
        mdGUI.display();    
        
        // checks every 50 ms, if we let the alarm go off 
        int delay = 50; //milliseconds
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if(mdGUI.alarmValue <= getMovementPercentage()){
                    mdGUI.setAlarm(true);
                }else{
                    mdGUI.setAlarm(false);
                }
                mdGUI.display();
            }
        };
        
        new Timer(delay, taskPerformer).start();
        
    }
    
    
    /**
     * MotionDetectorGUI.java
     *
     * This is a the GUI for the motion detection application. Is views the 
     * output from the motion detection and it has special components for 
     * setting options for the motion detection.
     *
     */
    private static class MotionDetectorGUI extends JFrame{
    
        int width=400, height = 540;
        private JPanel  mainPanel;// set up orientation of labels and slider relative to each other
        private JPanel  videoPanel;
        private JPanel  buttonPanel;
        private JPanel  advancedPanel;
        private JPanel  sensitivityPanel;
        private JPanel  alarmPanel;
        private JPanel  transmitPanel;
        private JPanel  miscPanel;
        private JButton startButton;
        private JButton stopButton;

        private JSlider sensitivitySlider;
        private JLabel  sensitivityLabel1;
        private JLabel  sensitivityLabel2;
        private JRadioButton alarm;
        private JSlider alarmSlider;
        private JLabel  alarmLabel1;
        private JLabel  alarmLabel2;
        private JTabbedPane tabbedPane;
        private JSlider frameSlider;
        private JLabel  frameLabel1;
        private JLabel  frameLabel2;
        private JTextField msgField;
        private JLabel  msgLabel;
        private JButton msgSetButton;
        
        private JTextField transmitPort;
        private JTextField transmitIP;
        private JLabel  transmitPortLabel;
        private JLabel  transmitIPLabel;
        private JLabel  transmitQualityLabel;        
        private JButton transmitStartButton;
        private JButton transmitStopButton;
        private JSlider qualitySlider;
        
        private Processor processor;
        private MotionDetector MD;
        public int alarmValue = 0;
        private boolean alarmFlag = false;
    
        /**
         * Creates a new instance of MotionDetecttransmitStopButtonionGUI
         *
         * @param md the motion detector application
         */
        public MotionDetectorGUI( MotionDetector md ) {
        
            super( "MotionDetector" );
            MD = md;
            // retrieve the processor
            this.processor = md.getProcessor();
    
            // add closing functionality
            addWindowListener(new WindowAdapter() {
                public void windowClosing( WindowEvent event ) {
                    processor.close();
                    System.exit(0);
                }
            });
        
            // set up the view of the output from the motion detection
            videoPanel = new JPanel();
            videoPanel.setBackground( Color.BLACK );
            LineBorder lb = new LineBorder( Color.BLACK, 20, false );
            LineBorder lb2 = new LineBorder( Color.GREEN, 2, false );
            videoPanel.setBorder(BorderFactory.createCompoundBorder( lb, lb2 ) );
            videoPanel.setLayout( new BorderLayout() );
            Component cc, vc;
            if ((vc = processor.getVisualComponent()) != null) {
                videoPanel.add("Center", vc);
            }
        
            if ((cc = processor.getControlPanelComponent()) != null) {
                videoPanel.add("South", cc);
            }
        
            // set up a button for starting the motion detection, 
            // adding a handler
            startButton = new JButton( "Start Detection" );
            startButton.setSize( new Dimension( 35, 30 ) );
            startButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    MD.start();
                }
            });

            // set up a button for stopping the motion detection, 
            // adding a handler
            stopButton = new JButton( "Stop Detection" );
            stopButton.setSize( new Dimension( 35, 30 ) );
            stopButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                     MD.stop();
                }
            });

            // set up a button for Starting Transmit 
            // adding a handler
            transmitStartButton = new JButton( "Start Transmit" );
            transmitStartButton.setSize( new Dimension( 35, 30 ) );
            transmitStartButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    //VideoTransmit 
                    String ipAddress = transmitIP.getText();
                    String port = transmitPort.getText();
                    float quality = ((float)(qualitySlider.getValue())) ;
                    vt = new VideoTransmit(clone.getProcessor(), ipAddress, port, quality);
                    vt.start();
                }
            });       
            
            // set up a button for Stopping Transmit 
            // adding a handler
            transmitStopButton = new JButton( "Stop Transmit" );
            transmitStopButton.setSize( new Dimension( 35, 30 ) );
            transmitStopButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    vt.stop();
                }
            });       
                        
            // set up the orientation of the buttons
            buttonPanel = new JPanel();
            buttonPanel.setBackground( Color.BLACK );
            buttonPanel.setLayout(new BoxLayout( buttonPanel, BoxLayout.X_AXIS ));
            buttonPanel.add(startButton);
            buttonPanel.add( Box.createRigidArea( new Dimension( 40, 0 ) ) );
            buttonPanel.add(stopButton);
            
            
            // set op a slider (with a belonging labels) for user interaction
            // on "Average Movement Sensitivity", and adding a handler.
            sensitivityLabel1 = new JLabel( "Average Movement Sensitivity :" );
            sensitivityLabel1.setAlignmentX(Component.CENTER_ALIGNMENT );
            sensitivityLabel1.setForeground(Color.GREEN);
            sensitivityLabel2 = new JLabel( " 0 %" );
            sensitivityLabel2.setAlignmentX(Component.CENTER_ALIGNMENT );
            sensitivityLabel2.setForeground(Color.GREEN);
            
            sensitivitySlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
            sensitivitySlider.setBackground(Color.BLACK);
            sensitivitySlider.setAlignmentX(Component.CENTER_ALIGNMENT );
            sensitivitySlider.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent event) {
                    int value = sensitivitySlider.getValue();
                    sensitivityLabel2.setText( value + "%" );
                    MD.setSignificantMovement(value);
                }
            });
            
            frameLabel1 = new JLabel("Number of frames between comparisons:");
            frameLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);
            frameLabel1.setForeground(Color.GREEN);
            frameLabel2 = new JLabel("5");
            frameLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);
            frameLabel2.setForeground(Color.GREEN);
            
            frameSlider = new JSlider(JSlider.HORIZONTAL, 0, 20, 5);
            frameSlider.setBackground(Color.BLACK);
            frameSlider.setAlignmentX(Component.CENTER_ALIGNMENT);
            frameSlider.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent event) {
                    int value = frameSlider.getValue();
                    frameLabel2.setText(value + "");
                    if (value == 0)
                        frameLabel2.setText(value + " (save each frame)");
                    MD.setFrameSpace(value);
                }
            });
           
            // set up orientation of labels and slider relative to each other
            sensitivityPanel = new JPanel();
            sensitivityPanel.setBackground( Color.BLACK );
            sensitivityPanel.setLayout(new BoxLayout( sensitivityPanel, BoxLayout.Y_AXIS ));
            LineBorder lb1 = new LineBorder(Color.BLACK, 10, false);
            sensitivityPanel.setBorder( lb1 );
            sensitivityPanel.add( Box.createRigidArea( new Dimension( 0, 44 ) ) );
            sensitivityPanel.add( sensitivityLabel1 );
            sensitivityPanel.add( sensitivitySlider );
            sensitivityPanel.add( sensitivityLabel2 );
            sensitivityPanel.add(frameLabel1);
            sensitivityPanel.add(frameSlider);
            sensitivityPanel.add(frameLabel2);
            
            transmitPanel = new JPanel();
            transmitPanel.setBackground(Color.BLACK);
            transmitPanel.setLayout(new BoxLayout(transmitPanel, BoxLayout.Y_AXIS));
            LineBorder lb0 = new LineBorder(Color.BLACK, 10, false);
            transmitPanel.setBorder(lb0);
            transmitIPLabel = new JLabel("IP Address");
            transmitIPLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            transmitIPLabel.setForeground(Color.GREEN);
            transmitPortLabel = new JLabel("Port");
            transmitPortLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            transmitPortLabel.setForeground(Color.GREEN);
            transmitQualityLabel = new JLabel("Quality of Jpeg");
            transmitQualityLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            transmitQualityLabel.setForeground(Color.GREEN);
            transmitIP = new JTextField();
            //TransmitIP.setSize(35,30);
            transmitPort = new JTextField();
            transmitIP.setText("Enter IP Number of receiver");
            transmitPort.setText("Enter Port Number of receiver");
            
            qualitySlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
            qualitySlider.setBackground(Color.BLACK);
            qualitySlider.setAlignmentX(Component.CENTER_ALIGNMENT );
                    
       
            transmitPanel.add(transmitIPLabel);
            transmitPanel.add(transmitIP);
            transmitPanel.add(transmitPortLabel);
            transmitPanel.add(transmitPort);
            transmitPanel.add(transmitQualityLabel);
            transmitPanel.add(qualitySlider);
            //transmitPanel.add(transmitQuality);
            transmitPanel.add(transmitStartButton);
            transmitPanel.add(transmitStopButton);
            
            miscPanel = new JPanel();
            miscPanel.setBackground(Color.BLACK);
            miscPanel.setLayout(new BoxLayout(miscPanel, BoxLayout.Y_AXIS));
            LineBorder lbl = new LineBorder(Color.BLACK, 10, false);
            miscPanel.setBorder(lbl);
            msgLabel = new JLabel("Custom message in video:");
            msgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            msgLabel.setForeground(Color.GREEN);
            msgField = new JTextField();
            msgField.setText("");
            msgSetButton = new JButton();
            msgSetButton.setText("Set text");
            msgSetButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    MD.setInVideoText(msgField.getText());
                }
            });
            miscPanel.add(msgLabel);
            miscPanel.add(msgField);
            miscPanel.add(msgSetButton);
            
            // set up an alarm radiobutton with belonging label for user 
            // interaction, and adding a handler.
            alarm = new JRadioButton( "Alarm", false );
            alarm.setAlignmentX(Component.RIGHT_ALIGNMENT);
            alarm.setForeground(Color.GREEN);
            alarm.setBackground(Color.BLACK);
            
            alarm.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent event) {
                    if (event.getStateChange() == ItemEvent.SELECTED){
                        alarmFlag = true;
                    } else if (event.getStateChange()== ItemEvent.DESELECTED){               
                        alarmFlag = false;
                    }
                }
            });
            
            // set op a slider (with a belonging labels) for user interaction
            // on "Average Alarm Movement Sensitivity", and adding a handler.
            alarmLabel1 = new JLabel( "Average Alarm Movement Sensitivity :" );
            alarmLabel1.setAlignmentX(Component.CENTER_ALIGNMENT );
            alarmLabel1.setForeground(Color.GREEN);
            alarmLabel2 = new JLabel( " 0 %" );
            alarmLabel2.setAlignmentX(Component.CENTER_ALIGNMENT );
            alarmLabel2.setForeground(Color.GREEN);
            
            alarmSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
            alarmSlider.setBackground(Color.BLACK);
            alarmSlider.setAlignmentX(Component.CENTER_ALIGNMENT );
            alarmSlider.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent event) {
                    alarmValue = alarmSlider.getValue();
                    alarmLabel2.setText( alarmValue + "%" );
                }
            });
             
            // set up orientation of labels and slider relative to each other
            alarmPanel = new JPanel();
            alarmPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
            alarmPanel.setBackground( Color.BLACK );
            alarmPanel.setLayout(new BoxLayout( alarmPanel, BoxLayout.Y_AXIS ));
            alarmPanel.setBorder( lb1 );
            alarmPanel.add( alarm );
            alarmPanel.add( Box.createRigidArea( new Dimension( 0, 20 ) ) );
            alarmPanel.add( alarmLabel1 );
            alarmPanel.add( alarmSlider );
            alarmPanel.add( alarmLabel2 );
             
            // set up a tabbedpane for all the user interactive components
            tabbedPane = new JTabbedPane();
            tabbedPane.setBorder( new LineBorder( Color.BLACK, 20, false ) );
            tabbedPane.addTab( "Sensitivity", sensitivityPanel );
            tabbedPane.addTab( "Alarm", alarmPanel );
            tabbedPane.addTab( "Transmit", transmitPanel );
            tabbedPane.addTab( "Misc", miscPanel );
           
            // set up the main container for the GUI
            Container c = getContentPane();
            c.setLayout( new BoxLayout( c, BoxLayout.Y_AXIS) );
            c.setBackground( Color.BLACK);
            c.add(videoPanel);
            c.add(buttonPanel);
            c.add(tabbedPane);
        }

        /**
         * Changes the GUI to view alarm or not.
         *
         * @param alarming alarm on/off -> true/false
         */
        public void setAlarm(boolean alarming){
            if (alarmFlag && alarming){
                LineBorder lb = new LineBorder( Color.BLACK, 15, false );
                LineBorder lb2 = new LineBorder( Color.RED, 7, false );
                videoPanel.setBorder(BorderFactory.createCompoundBorder( lb, lb2 ) );
            } else{
                LineBorder lb3 = new LineBorder( Color.BLACK, 20, false );
                LineBorder lb4 = new LineBorder( Color.GREEN, 2, false );
                videoPanel.setBorder(BorderFactory.createCompoundBorder( lb3, lb4 ) );
            }
        }   
        
        
        /**
         * Packs and displays the GUI.
         */
        public void display() {
            pack();
            show();
        }
}
}
