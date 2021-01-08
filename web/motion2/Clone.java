/*
 * Clone.java
 *
 * Own implementation of Clone, inspired by example from sun.com
 *
 * Created on den 24 september 2004, 10:27
 */

import javax.media.*;
import javax.media.control.TrackControl;
import javax.media.format.*;
import javax.media.protocol.*;
import javax.media.protocol.DataSource;

/** An implementation of a Clone. A Clone has an internal Processor which can be
 * requested out of the Clone.
 * @author Jan Lindblom <linjan-1@student.luth.se>
 * @version 1.0
 */
public class Clone implements ControllerListener {
    Processor p;
    Object waitSync = new Object();
    boolean stateTransitionOK = true;
    
    /** Tells the internal Processor of this Clone to open a given DataSource.
     * @param ds The DataSource to open
     * @return true or false, if the operation succeded or failed.
     */    
    public boolean open(DataSource ds) {
        try {
            System.out.println("Clone: Creating Processor");
            p = Manager.createProcessor(ds);
        } catch (Exception e) {
            System.err.println("Failed to create processor");
            return false;
        }
        return true;
    }
    
    /** Get the internal Processor of this Clone. The Processor is not configured and not realized.
     * @return The Processor of this Clone.
     */    
    public Processor getProcessor() {
        System.out.println("Clone: getProcessor() called");
        return p;
    }
    /** Returns the DataSource of this Clone.
     * @return the DataSource oth tis Clone.
     */    
    public DataSource getDataSource() {
        System.out.println("Clone: getDataSource() called");
        return p.getDataOutput();
    }
    
    boolean waitForState(int state) {
        synchronized (waitSync) {
            try {
                while (p.getState() < state && stateTransitionOK)
                    waitSync.wait();
            } catch (Exception e) {}
        }
        return stateTransitionOK;
    }
    
    /** Controller Listener.
     * @param evt The ControllerEvent
     */    
    public void controllerUpdate(ControllerEvent evt) {
        if (evt instanceof ConfigureCompleteEvent ||
        evt instanceof RealizeCompleteEvent ||
        evt instanceof PrefetchCompleteEvent) {
            synchronized (waitSync) {
                stateTransitionOK = true;
                waitSync.notifyAll();
            }
        }
        else if (evt instanceof ResourceUnavailableEvent) {
            synchronized (waitSync) {
                stateTransitionOK = false;
                waitSync.notifyAll();
            }
        }
        else if (evt instanceof EndOfMediaEvent) {
            p.close();
        }
        else if (evt instanceof SizeChangeEvent) {
        }
    }
    
}
