/*
AUTHOR: Elizabeth Purdum
INSTRUCTOR: Dr. Sharad Sharma
COURSE: CTEC396 - 101: Java Programming
SEMESTER: Fall 2007
ASSIGNMENT NO: Final Project
PROJECT: Exercise P2.7 - PayrollApp
DUE DATE: December 10, 2007
SUBMISSION DATE: December 10, 2007
SUMMARY
	The following code below will create an applet with a button.  The button 
	will then start the PayRoll Application which is done in a series of dialog 
	boxes.
INPUT: 
	Keyboard
OUTPUT:
	The output will display a an applet that loads an image as a button.  The 
	button will then start a series of dialog boxes that will ask the user to input 
	info needed to calculate the weekly pay. Then the final result will be the weekly 
	pay for the employee.
CLASS HIERARCHY
	Object - JApplet - Payroll
ASSUMPTIONS:
	I expect the user to be able to input the correct employee info when prompted.
*/

/* Libraries */

import java.awt.*;
import javax.swing.*;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;

/* The following class below, PayrollApp. will extend a JApplet and implement 
 * ActionListener for the button created.  The class will generate an displaying 
 * a message at the top and a button in the middle.  The button will then start 
 * a series of dialog input boxes prompting the user to enter in Employee Name, 
 * Employee Type, Weekly Hours Worked, and either Hourly, Rate Pay, Salary, or 
 * Percent of Commission depending on the type of employee.  Once that is completed 
 * it will go back to the applet. 
*/

public class PayrollApp extends JApplet implements ActionListener
{
	JLabel message;  //Message at the top of the applet.
	
	public void init()  //Initiates and configures JButton
	{
		JButton b = new JButton()					;   //Creates a button
		ImageIcon i = new ImageIcon("bulbs.jpg")	;
		
		message = new JLabel("Click start to begin Payroll application", 
		    JLabel.CENTER)									;
	    message.setForeground(Color.white)					;
	    message.setBackground(Color.black)					;
	    message.setOpaque(true)								;
	    
		setBackground(Color.white)							;
		setSize(600, 400)									;
		
		b.addActionListener(this)							;
		b.setIcon(i)										;
		
		getContentPane().add(message)						;
		getContentPane().setBackground(Color.black)			;
	    getContentPane().setLayout( new GridLayout(3,1) )	;
		getContentPane().add(b)								;
	}//end of init()

	public void actionPerformed(ActionEvent evt) 
	{
	          /* Respond to a button click by showing a dialog
	             and setting the message label to describe the
	             user's response.
	          */

	    String command = evt.getActionCommand();

	    if (command.equals(""))
	    {
	    	message.setText("Currently Displaying Payroll Application");
	         
	        String name			;
	 		String hours		;
	 		String rate			;
	 		String salary		;
	 		String option		;
	 		double hrswk		;
	 		double payRt		;
	 		double totPay = 0	;
	 		String sales		;
	 		String result = ""	;
	 		double sal			;
	 		double salesAmt		;
	 		int optionChoice	;
	 		
	 		// First input box that prompts the user for the Employee name.
	 		
	 		name = JOptionPane.showInputDialog("Enter Employee Name: ")		;
	 		
	 		/* Second prompt box requesting the user to choose which type of 
	 			pay the employee is - Hourly, Salary, or Commission.
	 		*/
	 		
	 		option = JOptionPane.showInputDialog("Enter Employee Type: 1 - " +
	 				"Hourly 2 - Salary 3 - Commission")						;
	 		
	 		optionChoice = Integer.parseInt(option);  //Parses the option from above
	 				
	 		result = result + name + "\n"									;
	 		
	 		/* The following switch will perform the correct corresponding 
	 		    calculations and resulting weekly pay depending upon the option 
	 		    selected.
	 		*/
	 		
	 		switch(optionChoice)
	 		{
	 			 case 1:
	 				hours = JOptionPane.showInputDialog("Enter Hours Worked")	;
	 				rate = JOptionPane.showInputDialog("Enter Pay Rate")		;
	 				hrswk = Double.parseDouble(hours)							;
	 			    payRt = Double.parseDouble(rate)							;
	 				totPay = hrswk * payRt										;
	 				result = result + "Hourly Employee"					    	;
	 				break														;
	 				
	 			case 2:
	 				salary = JOptionPane.showInputDialog("Enter Monthly Salary") ;
	 				sal = Double.parseDouble(salary)							 ;
	 				totPay = sal / 4.0											 ;
	 				result = result + "Salary Employee "						 ;
	 				break														 ;
	 				
	 			case 3:
	 				sales = JOptionPane.showInputDialog("Enter Weekly Sales Amount");
	 				salesAmt = Double.parseDouble(sales) 					        ;
	 				totPay = salesAmt * 0.2										    ;	
	 				result = result + "Commission Employee "					    ;
	 				break														    ;
	 		}//end of switch
	 		
	 		result = result + "\n Total Weekly Payment is $" + totPay;
	 		
	 		JOptionPane.showMessageDialog(null, result, "Weekly Payment ", 
	 			JOptionPane.INFORMATION_MESSAGE)									;
	 		
	        JOptionPane.showMessageDialog(null,
	            "Thank you for using the Payroll Application!")				        ;
	        message.setText("The Payroll Application Ended")					    ;
	        
	        //Beginning of Confirm Dialog Box setup
	        
	        int response = JOptionPane.showConfirmDialog(null,
	            "Would you like to run the Payroll Application again?")			    ;
	         switch(response) 
	         {
	         	 case JOptionPane.YES_OPTION: 
	         		 message.setText("You want to run the Payroll" + 
	         		     "Application again.")									    ;
	         		 break														    ;
	         	 
	             case JOptionPane.NO_OPTION: 
	            	 message.setText("You don't want to run the Payroll" +
	            		 "Application again.")   								    ;
	            	 break														    ;
	             
	             case JOptionPane.CANCEL_OPTION: 
	            	 message.setText("You canceled")							    ;
	            	 break														    ;
	            	 
	             case JOptionPane.CLOSED_OPTION: 
	            	 message.setText("You closed without making a selection.")	    ;
	         }//end of switch
	    }//end of if statement
    }//end of action Performed    
}//end of class





