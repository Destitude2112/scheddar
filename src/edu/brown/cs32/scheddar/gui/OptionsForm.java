package edu.brown.cs32.scheddar.gui;

import java.awt.FlowLayout;
import java.text.SimpleDateFormat;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

public class OptionsForm extends AbstractForm {
	private static final long serialVersionUID = 1L;
	
	JTextField adminEmail;
	JPasswordField adminPassword;
	JTextField adminName;
	JSpinner startDay;
	JSpinner endDay;
	
	SpinnerDateModel startFieldModel;
	SpinnerDateModel endFieldModel;
	
	SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
	
	public OptionsForm(ScheddarPane s) {
		super(s);
		
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		
		adminEmail = new JTextField();
		adminEmail.setText(_scheddar.getUsername());
		
		
		
		
	}

}
