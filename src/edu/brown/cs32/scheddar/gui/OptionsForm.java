package edu.brown.cs32.scheddar.gui;

import java.awt.FlowLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
		
		adminPassword = new JPasswordField();
		adminPassword.setText(_scheddar.getPassword());
		
		adminName = new JTextField();
		adminName.setText(_scheddar.getAdminName());
		
		startFieldModel = new SpinnerDateModel();
		endFieldModel = new SpinnerDateModel();
		
		try {
			startFieldModel.setValue(timeFormat.parse(_scheddar.getStartHour()));
			endFieldModel.setValue(timeFormat.parse(_scheddar.getEndHour()));
		} catch (ParseException e) {
			System.out.println("parse error meetingpanel");
		}
		
		startDay = new JSpinner();
		endDay = new JSpinner();
		
		startFieldModel.setCalendarField(Calendar.MINUTE);
		endFieldModel.setCalendarField(Calendar.MINUTE);
		
		startDay.setModel(startFieldModel);
		endDay.setModel(endFieldModel);	
	}
}
