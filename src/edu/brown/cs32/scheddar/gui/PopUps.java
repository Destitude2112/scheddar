package edu.brown.cs32.scheddar.gui;

import javax.swing.JOptionPane;

public class PopUps {
	public static void popUpPersonAlreadyExists(){
		JOptionPane.showMessageDialog(null, "That person already exists.");
	}
	public static void popUpGroupAlreadyExists(){
		JOptionPane.showMessageDialog(null, "That group already exists.");
	}
	public static void popUpMeetingAlreadyExists(){
		JOptionPane.showMessageDialog(null,"That meeting name already exists.");
	}
	
	
	/**
	 * Makes a confimation box appear that returns true if they
	 * press the yes button
	 * @return true if yes is pressed, else false
	 */
	
	public static boolean popUpConfirmationBox(){
		return(JOptionPane.showConfirmDialog(null, "Are you sure?","Scheddar",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)
				== JOptionPane.YES_OPTION);
	}
	
} 	
