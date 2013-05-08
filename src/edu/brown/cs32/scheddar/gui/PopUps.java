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
}
