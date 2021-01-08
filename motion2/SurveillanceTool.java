/**
 * SurveillanceTool.java
 *
 * This is a surveillance application, for up to three videostreams.
 * The user has the ability to add streams of his liking.
 *
 * The acctual motion detection is handled by the MotDetCodec.java.
 *
 */

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import javax.media.*;
import java.io.*;
import javax.media.control.*;
import javax.media.format.*;

/**
 * The surveillance tool application.
 * Created on den 1 october 2004, 08.12
 *
 * @author  Anna Tanskanen <carana-6@student.ltu.se>
 * @author  Jan Lindblom <linjan-1@student.luth.se>
 * @version 1.2
 * 
 */

public class SurveillanceTool {
    private static SurveillanceGUI gui;
    private static Processor[] player = new Processor[3];
    private static Object waitSync = new Object();
    private static boolean stateTransitionOK = true;
    private static int cPlayer = 0;
    private static MotDetCodec[] mdCodec = new MotDetCodec[3];
    
    /**
     * Starts up this application
     */
    public static void main( String[] args){
        gui = new SurveillanceGUI();
        gui.display();
    }
    
    private static class SurveillanceGUI extends JFrame implements javax.media.ControllerListener {
        private JPanel mainPanel;
        private TitledBorder titledBorder1, titledBorder2, titledBorder3;
        private JPanel videoPanel1, videoPanel2, videoPanel3;
        private JPanel streamPanel1, streamPanel2, streamPanel3;
        private JTabbedPane tabbedPane1, tabbedPane2, tabbedPane3; 
        private JPanel controlPanel1, controlPanel2, controlPanel3;
        private JPanel controlTopPanel1, controlTopPanel2, controlTopPanel3;
        private JPanel controlDownPanel1, controlDownPanel2, controlDownPanel3;
        private JTextField ipField1, ipField2, ipField3;
        private JTextField portField1, portField2, portField3;
        private JLabel ipLabel1, ipLabel2, ipLabel3;
        private JLabel portLabel1, portLabel2, portLabel3;
        private JButton addVideoButton1, addVideoButton2, addVideoButton3;
        private JPanel miscPanel1, miscPanel2, miscPanel3;
        private JLabel msgLabel1, msgLabel2, msgLabel3;
        private JTextField msgField1, msgField2, msgField3;
        private JButton msgSetButton1, msgSetButton2, msgSetButton3;
        private JPanel motDetPanel1, motDetPanel2, motDetPanel3; 
        private JPanel motDetButtonPanel1, motDetButtonPanel2, motDetButtonPanel3; 
        private JButton startMDButton1, startMDButton2, startMDButton3;
        private JButton stopMDButton1, stopMDButton2, stopMDButton3;
        private JPanel sensitivityPanel1, sensitivityPanel2, sensitivityPanel3; 
        private JLabel sensitivityLabel1, sensitivityLabel2, sensitivityLabel3;
        private JLabel percentageLabel1, percentageLabel2, percentageLabel3;
        private JSlider sensitivitySlider1, sensitivitySlider2, sensitivitySlider3;
        private JLabel frameLabel1, frameLabel2, frameLabel3;
        private JLabel numberLabel1, numberLabel2, numberLabel3;
        private JSlider frameSlider1, frameSlider2, frameSlider3;
        
        
        /**
         * Creates a new instance of the SrurveillanceGUI
         */
        public SurveillanceGUI(){
            
            super("SurveillanceTool");
            initComponents();
        
        } 
        
        /**
         * Sets up the properties, funtionality and appearance of the gui.
         */
        private void initComponents(){
         
         // add closing functionalities to this gui
            addWindowListener(new WindowAdapter() {
                public void windowClosing( WindowEvent event ) {
                    System.exit(0);
                }
            });
         
         // set up the properties of the buttons of the motion detection panels
            startMDButton1 = new JButton("Start Detection");
            startMDButton2 = new JButton("Start Detection");
            startMDButton3 = new JButton("Start Detection");
            //adds start motiondetection funtionality
            startMDButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mdCodec[0].setDetection(true);
            }
            });
            startMDButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mdCodec[1].setDetection(true);
            }
            });
            startMDButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mdCodec[2].setDetection(true);
            }
            });
            stopMDButton1 = new JButton("Stop Detection");
            stopMDButton2 = new JButton("Stop Detection");
            stopMDButton3 = new JButton("Stop Detection");
            //adds stop motiondetection funtionality
            stopMDButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mdCodec[0].setDetection(false);
            }
            });
            stopMDButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mdCodec[1].setDetection(false);
            }
            });
            stopMDButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mdCodec[2].setDetection(false);
            }
            });
            
         // set up the properties of the button panels of the motion detection panels
            motDetButtonPanel1 = new JPanel();
            motDetButtonPanel2 = new JPanel();
            motDetButtonPanel3 = new JPanel();
            motDetButtonPanel1.setBackground(Color.BLACK);
            motDetButtonPanel2.setBackground(Color.BLACK);
            motDetButtonPanel3.setBackground(Color.BLACK);
            motDetButtonPanel1.setLayout(new BoxLayout(motDetButtonPanel1, BoxLayout.X_AXIS));
            motDetButtonPanel2.setLayout(new BoxLayout(motDetButtonPanel2, BoxLayout.X_AXIS));
            motDetButtonPanel3.setLayout(new BoxLayout(motDetButtonPanel3, BoxLayout.X_AXIS));
            motDetButtonPanel1.add(startMDButton1);
            motDetButtonPanel2.add(startMDButton2);
            motDetButtonPanel3.add(startMDButton3);
            motDetButtonPanel1.add( Box.createRigidArea(new Dimension(40, 0)));
            motDetButtonPanel2.add( Box.createRigidArea(new Dimension(40, 0)));
            motDetButtonPanel3.add( Box.createRigidArea(new Dimension(40, 0)));
            motDetButtonPanel1.add(stopMDButton1);
            motDetButtonPanel2.add(stopMDButton2);
            motDetButtonPanel3.add(stopMDButton3);
            
         // set up the properties of the labels for the sensitivity panels
            sensitivityLabel1 = new JLabel("Average Movement Sensitivity :");
            sensitivityLabel2 = new JLabel("Average Movement Sensitivity :");
            sensitivityLabel3 = new JLabel("Average Movement Sensitivity :");
            sensitivityLabel1.setAlignmentX(Component.CENTER_ALIGNMENT );
            sensitivityLabel2.setAlignmentX(Component.CENTER_ALIGNMENT );
            sensitivityLabel3.setAlignmentX(Component.CENTER_ALIGNMENT );
            sensitivityLabel1.setForeground(Color.GREEN);
            sensitivityLabel2.setForeground(Color.GREEN);
            sensitivityLabel3.setForeground(Color.GREEN);
            percentageLabel1 = new JLabel(" 0 % ");
            percentageLabel2 = new JLabel(" 0 % ");
            percentageLabel3 = new JLabel("  0 % ");
            percentageLabel1.setAlignmentX(Component.CENTER_ALIGNMENT );
            percentageLabel2.setAlignmentX(Component.CENTER_ALIGNMENT );
            percentageLabel3.setAlignmentX(Component.CENTER_ALIGNMENT );
            percentageLabel1.setForeground(Color.GREEN);
            percentageLabel2.setForeground(Color.GREEN);
            percentageLabel3.setForeground(Color.GREEN);
            frameLabel1 = new JLabel("Number of Frames Between Comparisons :");
            frameLabel2 = new JLabel("Number of Frames Between Comparisons :");
            frameLabel3 = new JLabel("Number of Frames Between Comparisons :");
            frameLabel1.setAlignmentX(Component.CENTER_ALIGNMENT );
            frameLabel2.setAlignmentX(Component.CENTER_ALIGNMENT );
            frameLabel3.setAlignmentX(Component.CENTER_ALIGNMENT );
            frameLabel1.setForeground(Color.GREEN);
            frameLabel2.setForeground(Color.GREEN);
            frameLabel3.setForeground(Color.GREEN);
            numberLabel1 = new JLabel("5");
            numberLabel2 = new JLabel("5");
            numberLabel3 = new JLabel("5");
            numberLabel1.setAlignmentX(Component.CENTER_ALIGNMENT );
            numberLabel2.setAlignmentX(Component.CENTER_ALIGNMENT );
            numberLabel3.setAlignmentX(Component.CENTER_ALIGNMENT );
            numberLabel1.setForeground(Color.GREEN);
            numberLabel2.setForeground(Color.GREEN);
            numberLabel3.setForeground(Color.GREEN);
            
         // set up the properties of the sliders of the sensistvity panels
            sensitivitySlider1 = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
            sensitivitySlider2 = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
            sensitivitySlider3 = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
            sensitivitySlider1.setBackground(Color.BLACK);
            sensitivitySlider2.setBackground(Color.BLACK);
            sensitivitySlider3.setBackground(Color.BLACK);
            sensitivitySlider1.setAlignmentX(Component.CENTER_ALIGNMENT );
            sensitivitySlider2.setAlignmentX(Component.CENTER_ALIGNMENT );
            sensitivitySlider3.setAlignmentX(Component.CENTER_ALIGNMENT );
            //adds change of sensitivity functionality
            sensitivitySlider1.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent event) {
                    int value = sensitivitySlider1.getValue();
                    percentageLabel1.setText( value + "%" );
                    mdCodec[0].setSignMovement(value);
                }
            });
            sensitivitySlider2.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent event) {
                    int value = sensitivitySlider2.getValue();
                    percentageLabel2.setText( value + "%" );
                    mdCodec[1].setSignMovement(value);
                }
            });
            sensitivitySlider3.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent event) {
                    int value = sensitivitySlider3.getValue();
                    percentageLabel3.setText( value + "%" );
                    mdCodec[2].setSignMovement(value);
                }
            });
            frameSlider1 = new JSlider(JSlider.HORIZONTAL, 0, 20, 5);
            frameSlider2 = new JSlider(JSlider.HORIZONTAL, 0, 20, 5);
            frameSlider3 = new JSlider(JSlider.HORIZONTAL, 0, 20, 5);
            frameSlider1.setBackground(Color.BLACK);
            frameSlider2.setBackground(Color.BLACK);
            frameSlider3.setBackground(Color.BLACK);
            frameSlider1.setAlignmentX(Component.CENTER_ALIGNMENT);
            frameSlider2.setAlignmentX(Component.CENTER_ALIGNMENT);
            frameSlider3.setAlignmentX(Component.CENTER_ALIGNMENT);
            // add change of frame space functionality
            frameSlider1.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent event) {
                    int value = frameSlider1.getValue();
                    numberLabel1.setText(value + "");
                    if (value == 0)
                        numberLabel1.setText(value + " (save each frame)");
                    mdCodec[0].setFrameSpace(value);
                }
            });
            frameSlider2.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent event) {
                    int value = frameSlider2.getValue();
                    numberLabel2.setText(value + "");
                    if (value == 0)
                        numberLabel2.setText(value + " (save each frame)");
                    mdCodec[1].setFrameSpace(value); 
                }
            });
            frameSlider3.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent event) {
                    int value = frameSlider3.getValue();
                    numberLabel3.setText(value + "");
                    if (value == 0)
                        numberLabel3.setText(value + " (save each frame)");
                    mdCodec[2].setFrameSpace(value);
                }
            });
            
         // set up the properties of the sensitivity panels of the motion detection panels
            sensitivityPanel1 = new JPanel();
            sensitivityPanel2 = new JPanel();
            sensitivityPanel3 = new JPanel();
            sensitivityPanel1.setBackground(Color.BLACK);
            sensitivityPanel2.setBackground(Color.BLACK);
            sensitivityPanel3.setBackground(Color.BLACK);
            sensitivityPanel1.setLayout(new BoxLayout(sensitivityPanel1, BoxLayout.Y_AXIS));
            sensitivityPanel2.setLayout(new BoxLayout(sensitivityPanel2, BoxLayout.Y_AXIS));
            sensitivityPanel3.setLayout(new BoxLayout(sensitivityPanel3, BoxLayout.Y_AXIS));
            sensitivityPanel1.add(Box.createRigidArea(new Dimension(0, 20)));
            sensitivityPanel2.add(Box.createRigidArea(new Dimension(0, 20)));
            sensitivityPanel3.add(Box.createRigidArea(new Dimension(0, 20)));
            sensitivityPanel1.add(sensitivityLabel1);
            sensitivityPanel2.add(sensitivityLabel2);
            sensitivityPanel3.add(sensitivityLabel3);
            sensitivityPanel1.add(sensitivitySlider1);
            sensitivityPanel2.add(sensitivitySlider2);
            sensitivityPanel3.add(sensitivitySlider3);
            sensitivityPanel1.add(percentageLabel1);
            sensitivityPanel2.add(percentageLabel2);
            sensitivityPanel3.add(percentageLabel3);
            sensitivityPanel1.add(Box.createRigidArea(new Dimension(0, 20)));
            sensitivityPanel2.add(Box.createRigidArea(new Dimension(0, 20)));
            sensitivityPanel3.add(Box.createRigidArea(new Dimension(0, 20)));
            sensitivityPanel1.add(frameLabel1);
            sensitivityPanel2.add(frameLabel2);
            sensitivityPanel3.add(frameLabel3);
            sensitivityPanel1.add(frameSlider1);
            sensitivityPanel2.add(frameSlider2);
            sensitivityPanel3.add(frameSlider3);
            sensitivityPanel1.add(numberLabel1);
            sensitivityPanel2.add(numberLabel2);
            sensitivityPanel3.add(numberLabel3);
           
            
         // set up the properties of the motion detection panels of the video panels
            motDetPanel1 = new JPanel();
            motDetPanel2 = new JPanel();
            motDetPanel3 = new JPanel();
            motDetPanel1.setBackground(Color.BLACK);
            motDetPanel2.setBackground(Color.BLACK);
            motDetPanel3.setBackground(Color.BLACK);
            motDetPanel1.setLayout(new BoxLayout(motDetPanel1, BoxLayout.Y_AXIS));
            motDetPanel2.setLayout(new BoxLayout(motDetPanel2, BoxLayout.Y_AXIS));
            motDetPanel3.setLayout(new BoxLayout(motDetPanel3, BoxLayout.Y_AXIS));
            motDetPanel1.setBorder(new LineBorder(Color.BLACK, 5));
            motDetPanel2.setBorder(new LineBorder(Color.BLACK, 5));
            motDetPanel3.setBorder(new LineBorder(Color.BLACK, 5));
            motDetPanel1.add(Box.createRigidArea(new Dimension(0, 10)));
            motDetPanel2.add(Box.createRigidArea(new Dimension(0, 10)));
            motDetPanel3.add(Box.createRigidArea(new Dimension(0, 10)));
            motDetPanel1.add(motDetButtonPanel1);
            motDetPanel2.add(motDetButtonPanel2);
            motDetPanel3.add(motDetButtonPanel3);
            motDetPanel1.add(sensitivityPanel1);
            motDetPanel2.add(sensitivityPanel2);
            motDetPanel3.add(sensitivityPanel3);
              
         // set up the properties of the labels of the control panels
            ipLabel1 = new JLabel("IP Address");
            ipLabel2 = new JLabel("IP Address");
            ipLabel3 = new JLabel("IP Address");
            portLabel1 = new JLabel("Port");
            portLabel2 = new JLabel("Port");
            portLabel3 = new JLabel("Port");
            ipLabel1.setForeground(Color.GREEN);
            ipLabel2.setForeground(Color.GREEN);
            ipLabel3.setForeground(Color.GREEN);
            portLabel1.setForeground(Color.GREEN);
            portLabel2.setForeground(Color.GREEN);
            portLabel3.setForeground(Color.GREEN);
            
         // set up the properties for the testfields of the control panels
            ipField1 = new JTextField("aaa.bbb.ccc.ddd");
            ipField2 = new JTextField("aaa.bbb.ccc.ddd");
            ipField3 = new JTextField("aaa.bbb.ccc.ddd");
            ipField1.setMaximumSize( new Dimension( 32767, 25 ) );
            ipField2.setMaximumSize( new Dimension( 32767, 25 ) );
            ipField3.setMaximumSize( new Dimension( 32767, 25 ) );
            ipField1.setToolTipText("Enter IP Address of Streaming Device");
            ipField2.setToolTipText("Enter IP Address of Streaming Device");
            ipField3.setToolTipText("Enter IP Address of Streaming Devicer");
            portField1 = new JTextField("2400");
            portField2 = new JTextField("2400");
            portField3 = new JTextField("2400");
            portField1.setMaximumSize( new Dimension( 32767, 25 ) );
            portField2.setMaximumSize( new Dimension( 32767, 25 ) );
            portField3.setMaximumSize( new Dimension( 32767, 25 ) );
            portField1.setToolTipText("Enter Port Number of Streaming Device");
            portField2.setToolTipText("Enter Port Number of Streaming Device");
            portField3.setToolTipText("Enter Port Number of Streaming Device");
            
         // set up properties for the buttons of the control panels
            addVideoButton1 = new JButton("Add Stream");
            addVideoButton2 = new JButton("Add Stream");
            addVideoButton3 = new JButton("Add Stream");
            addVideoButton1.setToolTipText("Add Stream Session to Main Window");
            addVideoButton2.setToolTipText("Add Stream Session to Main Window");
            addVideoButton3.setToolTipText("Add Stream Session to Main Window");
            // adds add videostream functionality
            addVideoButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(1, ipField1.getText(), portField1.getText());
            }
            });
            addVideoButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(2, ipField2.getText(), portField2.getText());
            }
            });
            addVideoButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(3, ipField3.getText(), portField3.getText());
            }
            });
            
         // set up the properties of the subpanels of the control panels
            controlTopPanel1= new JPanel();
            controlTopPanel2= new JPanel();
            controlTopPanel3= new JPanel();
            controlTopPanel1.setBackground(Color.BLACK);
            controlTopPanel2.setBackground(Color.BLACK);
            controlTopPanel3.setBackground(Color.BLACK);
            controlTopPanel1.setLayout(new BoxLayout(controlTopPanel1, BoxLayout.X_AXIS));
            controlTopPanel2.setLayout(new BoxLayout(controlTopPanel2, BoxLayout.X_AXIS));
            controlTopPanel3.setLayout(new BoxLayout(controlTopPanel3, BoxLayout.X_AXIS));
            controlTopPanel1.add(ipLabel1);
            controlTopPanel2.add(ipLabel2);
            controlTopPanel3.add(ipLabel3);
            controlTopPanel1.add(Box.createRigidArea(new Dimension(70, 0)));
            controlTopPanel2.add(Box.createRigidArea(new Dimension(70, 0)));
            controlTopPanel3.add(Box.createRigidArea(new Dimension(70, 0)));
            controlTopPanel1.add(portLabel1);
            controlTopPanel2.add(portLabel2);
            controlTopPanel3.add(portLabel3);
            controlTopPanel1.add(Box.createRigidArea(new Dimension(140, 0)));
            controlTopPanel2.add(Box.createRigidArea(new Dimension(140, 0)));
            controlTopPanel3.add(Box.createRigidArea(new Dimension(140, 0)));
            controlDownPanel1= new JPanel();
            controlDownPanel2= new JPanel();
            controlDownPanel3= new JPanel();
            controlDownPanel1.setBackground(Color.BLACK);
            controlDownPanel2.setBackground(Color.BLACK);
            controlDownPanel3.setBackground(Color.BLACK);
            controlDownPanel1.setLayout(new BoxLayout(controlDownPanel1, BoxLayout.X_AXIS));
            controlDownPanel2.setLayout(new BoxLayout(controlDownPanel2, BoxLayout.X_AXIS));
            controlDownPanel3.setLayout(new BoxLayout(controlDownPanel3, BoxLayout.X_AXIS));
            controlDownPanel1.add(ipField1);
            controlDownPanel2.add(ipField2);
            controlDownPanel3.add(ipField3);
            controlDownPanel1.add(Box.createRigidArea(new Dimension(5, 0)));
            controlDownPanel2.add(Box.createRigidArea(new Dimension(5, 0)));
            controlDownPanel3.add(Box.createRigidArea(new Dimension(5, 0)));
            controlDownPanel1.add(portField1);
            controlDownPanel2.add(portField2);
            controlDownPanel3.add(portField3);
            controlDownPanel1.add(Box.createRigidArea(new Dimension(5, 0)));
            controlDownPanel2.add(Box.createRigidArea(new Dimension(5, 0)));
            controlDownPanel3.add(Box.createRigidArea(new Dimension(5, 0)));
            controlDownPanel1.add(addVideoButton1);
            controlDownPanel2.add(addVideoButton2);
            controlDownPanel3.add(addVideoButton3);
            
         // set up the properties of the control panels for the video streams
            controlPanel1 = new JPanel();
            controlPanel2 = new JPanel();
            controlPanel3 = new JPanel();
            controlPanel1.setBackground(Color.BLACK);
            controlPanel2.setBackground(Color.BLACK);
            controlPanel3.setBackground(Color.BLACK);
            controlPanel1.setLayout(new BoxLayout(controlPanel1, BoxLayout.Y_AXIS));
            controlPanel2.setLayout(new BoxLayout(controlPanel2, BoxLayout.Y_AXIS));
            controlPanel3.setLayout(new BoxLayout(controlPanel3, BoxLayout.Y_AXIS));
            controlPanel1.setBorder(new LineBorder(Color.BLACK, 5));
            controlPanel2.setBorder(new LineBorder(Color.BLACK, 5));
            controlPanel3.setBorder(new LineBorder(Color.BLACK, 5));
            controlPanel1.add(Box.createRigidArea(new Dimension(0, 50)));
            controlPanel2.add(Box.createRigidArea(new Dimension(0, 50)));
            controlPanel3.add(Box.createRigidArea(new Dimension(0, 50)));
            controlPanel1.add(controlTopPanel1);
            controlPanel2.add(controlTopPanel2);
            controlPanel3.add(controlTopPanel3);
            controlPanel1.add(controlDownPanel1);
            controlPanel2.add(controlDownPanel2);
            controlPanel3.add(controlDownPanel3);
            
         // set up the properties of the labels of the misc panels
            msgLabel1 = new JLabel("Custom Message in Video:");
            msgLabel2 = new JLabel("Custom Message in Video:");
            msgLabel3 = new JLabel("Custom Message in Video:");
            msgLabel1.setForeground(Color.GREEN);
            msgLabel2.setForeground(Color.GREEN);
            msgLabel3.setForeground(Color.GREEN);
            
         // set up the properties of the test fields of the misc panels
            msgField1 = new JTextField();
            msgField2 = new JTextField();
            msgField3 = new JTextField();
            msgField1.setMaximumSize(new Dimension(32767, 25));
            msgField2.setMaximumSize(new Dimension(32767, 25));
            msgField3.setMaximumSize(new Dimension(32767, 25));
            
         // set up the properties of the buttons of the misc panels
            msgSetButton1 = new JButton();
            msgSetButton2 = new JButton();
            msgSetButton3 = new JButton();
            msgSetButton1.setText("Set Text");
            msgSetButton2.setText("Set Text");
            msgSetButton3.setText("Set Text");
            // adds textsetting in video functionality
            msgSetButton1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    msgSetButtonActionPerformed(1, msgField1.getText());
                }
            });
            msgSetButton2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    msgSetButtonActionPerformed(2, msgField2.getText());
                }
            });
            msgSetButton3.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    msgSetButtonActionPerformed(3, msgField1.getText());
                }
            });
          
         // set up the properties of the misc panels of the video panels
            miscPanel1 = new JPanel();
            miscPanel2 = new JPanel();
            miscPanel3 = new JPanel();
            miscPanel1.setBackground(Color.BLACK);
            miscPanel2.setBackground(Color.BLACK);
            miscPanel3.setBackground(Color.BLACK);
            miscPanel1.setLayout(new BoxLayout(miscPanel1, BoxLayout.Y_AXIS));
            miscPanel2.setLayout(new BoxLayout(miscPanel2, BoxLayout.Y_AXIS));
            miscPanel3.setLayout(new BoxLayout(miscPanel3, BoxLayout.Y_AXIS));
            miscPanel1.setBorder(new LineBorder(Color.BLACK, 5));
            miscPanel2.setBorder(new LineBorder(Color.BLACK, 5));
            miscPanel3.setBorder(new LineBorder(Color.BLACK, 5));
            miscPanel1.add(Box.createRigidArea(new Dimension(0, 25)));
            miscPanel2.add(Box.createRigidArea(new Dimension(0, 25)));
            miscPanel3.add(Box.createRigidArea(new Dimension(0, 25)));
            miscPanel1.add(msgLabel1);
            miscPanel2.add(msgLabel2);
            miscPanel3.add(msgLabel3);
            miscPanel1.add(Box.createRigidArea(new Dimension(0, 5)));
            miscPanel2.add(Box.createRigidArea(new Dimension(0, 5)));
            miscPanel3.add(Box.createRigidArea(new Dimension(0, 5)));
            miscPanel1.add(msgField1);
            miscPanel2.add(msgField2);
            miscPanel3.add(msgField3);
            miscPanel1.add(Box.createRigidArea(new Dimension(0, 5)));
            miscPanel2.add(Box.createRigidArea(new Dimension(0, 5)));
            miscPanel3.add(Box.createRigidArea(new Dimension(0, 5)));
            miscPanel1.add(msgSetButton1);
            miscPanel2.add(msgSetButton2);
            miscPanel3.add(msgSetButton3);
            
         // set up the properties for the stream panels of the videopanels
            streamPanel1 = new JPanel();
            streamPanel2 = new JPanel();
            streamPanel3 = new JPanel();
            streamPanel1.setBackground(Color.BLACK);
            streamPanel2.setBackground(Color.BLACK);
            streamPanel3.setBackground(Color.BLACK);
            streamPanel1.setBorder(new LineBorder(Color.BLACK, 5));
            streamPanel2.setBorder(new LineBorder(Color.BLACK, 5));
            streamPanel3.setBorder(new LineBorder(Color.BLACK, 5));
            streamPanel1.setPreferredSize(new Dimension(320,260));
            streamPanel2.setPreferredSize(new Dimension(320,260));
            streamPanel3.setPreferredSize(new Dimension(320,260));
            
         // set up the properties of the tabbed panes of the video panels
            tabbedPane1 = new JTabbedPane();
            tabbedPane2 = new JTabbedPane();
            tabbedPane3 = new JTabbedPane();
            tabbedPane1.setBorder(new LineBorder(Color.BLACK, 5));
            tabbedPane2.setBorder(new LineBorder(Color.BLACK, 5));
            tabbedPane3.setBorder(new LineBorder(Color.BLACK, 5));
            tabbedPane1.add("Motion Detection", motDetPanel1);
            tabbedPane2.add("Motion Detection", motDetPanel2);
            tabbedPane3.add("Motion Detection", motDetPanel3);
            tabbedPane1.add("Add Stream", controlPanel1);
            tabbedPane2.add("Add Stream", controlPanel2);
            tabbedPane3.add("Add Stream", controlPanel3);
            tabbedPane1.add("Misc", miscPanel1);
            tabbedPane2.add("Misc", miscPanel2);
            tabbedPane3.add("Misc", miscPanel3);
            
         // set up the properties of the titled borders
            titledBorder1 = new TitledBorder("Video Stream 1");
            titledBorder2 = new TitledBorder("Video Stream 2");
            titledBorder3 = new TitledBorder("Video Stream 3");
            titledBorder1.setTitleColor(Color.GREEN);
            titledBorder2.setTitleColor(Color.GREEN);
            titledBorder3.setTitleColor(Color.GREEN);
            
         // set up the properties of the video panels of this gui
            videoPanel1 = new JPanel();
            videoPanel2 = new JPanel();
            videoPanel3 = new JPanel();
            videoPanel1.setBackground(Color.BLACK);
            videoPanel2.setBackground(Color.BLACK);
            videoPanel3.setBackground(Color.BLACK);
            videoPanel1.setLayout(new BoxLayout(videoPanel1, BoxLayout.Y_AXIS));
            videoPanel2.setLayout(new BoxLayout(videoPanel2, BoxLayout.Y_AXIS));
            videoPanel3.setLayout(new BoxLayout(videoPanel3, BoxLayout.Y_AXIS));
            videoPanel1.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK, 5), titledBorder1));
            videoPanel2.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK, 5), titledBorder2));
            videoPanel3.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK, 5), titledBorder3));
            videoPanel1.add(streamPanel1);
            videoPanel2.add(streamPanel2);
            videoPanel3.add(streamPanel3);
            videoPanel1.add(tabbedPane1);
            videoPanel2.add(tabbedPane2);
            videoPanel3.add(tabbedPane3);
            
        // set up the properties for the main panel of this gui
            mainPanel = new JPanel();
            mainPanel.setBackground(Color.BLACK);
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
            mainPanel.add(videoPanel1);
            mainPanel.add(videoPanel2);
            mainPanel.add(videoPanel3);
            
         // set up the container of this gui
            Container c = getContentPane();
            c.setBackground( Color.BLACK);
            c.setLayout(new BorderLayout()); 
            c.add(mainPanel, BorderLayout.CENTER); 
        
        }
        
        /**
         * Adds the stream found at the given ipaddress and port to the panel 
         * with index n.
         *
         * @param n index of panel
         * @param ipAddress the ip addess of the source of the videostream
         * @param portNr the port number for the videostream on the source
         */
         private void addButtonActionPerformed(int n, String ipAddress, String portNr) {
             switch (n) {
                 case 1:
                     //video for stream 1 added
                     cPlayer = 0;
                     break;
                 case 2:
                     //video for stream 2 added
                     cPlayer = 1;
                     break;
                 case 3:
                     //video for stream 3 added
                     cPlayer = 2;
                     break;
             }
             String url= "rtp://" + ipAddress + ":" + portNr + "/video";
             System.out.println(url);
             MediaLocator mrl= new MediaLocator(url);
             
             // create a media locator from the url
             if (mrl == null) {
                 System.err.println("Can't build MRL for RTP");
                 System.exit(0);
             }
             try {
                 System.out.println("trying to create processor...");
                 player[cPlayer] = Manager.createProcessor(mrl);
                 
                 player[cPlayer].addControllerListener( this );
                 
                 System.out.println("configure()...");
                 player[cPlayer].configure();
                 
                 if ( !waitForState(player[cPlayer].Configured, cPlayer) ) {
                     System.err.println("Failed to configure the processor.");
                     System.exit(0);
                 }
                 
                 System.out.println("setContentDescriptor(null)...");
                 player[cPlayer].setContentDescriptor(null);
                 
                 // obtain the track controls.
                 System.out.println("getting track controls...");
                 TrackControl tc[] = player[cPlayer].getTrackControls();
                 if (tc == null) {
                     System.err.println("Failed to obtain track controls from the processor.");
                     System.exit(0);
                 }
                 
                 // search for the video track control.
                 TrackControl videoTrack = null;
                 for (int i = 0; i < tc.length; i++) {
                     if (tc[i].getFormat() instanceof VideoFormat) {
                         videoTrack = tc[i];
                         System.out.println("found video track controls");
                         break;
                     }
                 }
                 // if there is no video track control we exits
                 if (videoTrack == null) {
                     System.err.println("The input media does not contain a video track.");
                     System.exit(0);
                 }
                 
                 // set up the codec chain for playback
                 try {
                     System.out.println("trying to set up codec");
                     mdCodec[cPlayer] = new MotDetCodec();
                     Codec[] codec = { mdCodec[cPlayer] };
                     videoTrack.setCodecChain( codec );
                 } catch(Exception e) {
                     System.out.println("failed to set up codec");
                 }
                 // realizes the players
                 System.out.println("prefetch()...");
                 player[cPlayer].prefetch();
                 if ( !waitForState( player[cPlayer].Prefetched, cPlayer) ) {
                     System.err.println("Failed to realize the processor.");
                     System.exit(0);
                 }    
                 
                 if (player[cPlayer] != null) {
                     System.out.println("starting playback");
                     player[cPlayer].start();
                 }
                 
                 // adds the found video component to the gui
                 Component vc;
                 if ((vc = player[cPlayer].getVisualComponent()) != null) {
                     System.out.println("found visual component");
                     switch (n) {
                         case 1:
                             streamPanel1.add("Center", vc);
                             break;
                         case 2:
                             streamPanel2.add("Center", vc);
                             break;
                         case 3:
                             streamPanel3.add("Center", vc);
                             break;
                     }
                 }
                
             } catch (NoPlayerException e) {
                 System.err.println("Error:" + e);
                 System.exit(0);
             } catch (IOException e) {
                 System.err.println("Error:" + e);
                 System.exit(0);
             }
             display();
         }
        
         /** 
          * Sets a text message in the video frame
          *
          * @param n index of the frame
          * @param msg message
          */
         private void msgSetButtonActionPerformed(int n, String msg) {
             mdCodec[n-1].setVideoMsg(msg);
         }
         
         
        /**
         * Packs and displays this gui.
         */
        public void display() {
            pack();
            show();
        }
        
        
        /** 
         * The update method for this ControllerListener, sets he proper actions
         * when updated.
         * 
         * @param event the occured event
         */
        public void controllerUpdate(javax.media.ControllerEvent event) {
            // if the playeris dead - we leave 
            if (player[cPlayer] == null)
                return;
            
            if (event instanceof ConfigureCompleteEvent ||
                event instanceof RealizeCompleteEvent ||
                event instanceof PrefetchCompleteEvent) {
                    synchronized ( waitSync ) {
                        stateTransitionOK = true;
                        waitSync.notifyAll();
                    }
            } else if ( event instanceof ResourceUnavailableEvent ) {
                System.out.println("Resource Unavailable");
                synchronized ( waitSync ) {
                    stateTransitionOK = false;
                    waitSync.notifyAll();
                }
                // close the application
            } else if ( event instanceof EndOfMediaEvent ||
            event instanceof ControllerClosedEvent ||
            event instanceof ControllerErrorEvent ) {
                player[cPlayer].close();
                System.exit(0);  
            }
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
        boolean waitForState(int state, int p) {
            // waits for a state confirmation for the player
            synchronized (waitSync) {
                try {
                    while (player[p].getState() != state && stateTransitionOK)
                        waitSync.wait();
                } catch (Exception e) {
                    System.err.println("Exception in waitForState: " + e);
                }
            }
            return stateTransitionOK;
        }
        
    }
    
}