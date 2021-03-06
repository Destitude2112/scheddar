package edu.brown.cs32.scheddar.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import edu.brown.cs32.scheddar.Scheddar;
import edu.brown.cs32.scheddar.ScheddarFace;

/**
 * @author sdemane
 * 
 * This class implements a pop-up form to create a new
 * Scheddar project inside the application.
 *
 */
public class ScheddarForm extends AbstractForm {
	private static final long serialVersionUID = 1L;

	JTextField nameField,adminField,emailField,passwordField;
	
	GUIScheddar _gui;
	
	public ScheddarForm(ScheddarPane s, GUIScheddar gui) {
		super(s);
		this.setTitle("New Scheddar");
		_gui = gui;
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		
		//getting name of organization
		nameField = new JTextField(20);	
		adminField = new JTextField(20);
		emailField = new JTextField(20);
		passwordField = new JPasswordField(20);
		
		// making "Create" button
		
		JButton create = new JButton("Create");
		create.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (nameField.getText() != "") {
					ScheddarFace s = new Scheddar(nameField.getText(), adminField.getText(), emailField.getText(), passwordField.getText());
					_scheddarPane = new ScheddarPane(_gui, s);
					setVisible(false);
				}
			}
		});
		
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		
		// adding intro
		panel.add(Box.createVerticalStrut(20));
		panel.add(new JLabel("Create a new Scheddar project:"));
		panel.add(Box.createVerticalStrut(15));
		
		
		// adding fields
		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(new GridLayout(4, 2, 5, 10));
		
		fieldPanel.add(new JLabel("Organization Name:",SwingConstants.RIGHT));
		fieldPanel.add(nameField);
		
		fieldPanel.add(new JLabel("Administrator Name:",SwingConstants.RIGHT));
		fieldPanel.add(adminField);
		
		fieldPanel.add(new JLabel("Email:",SwingConstants.RIGHT));
		fieldPanel.add(emailField);
		
		fieldPanel.add(new JLabel("Email Password:",SwingConstants.RIGHT));
		fieldPanel.add(passwordField);
		
		panel.add(fieldPanel);
		panel.add(Box.createVerticalStrut(15));
		
		
		// different panel for buttons.
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.X_AXIS));
		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.add(create);
		buttonPanel.add(Box.createHorizontalStrut(5));
		buttonPanel.add(cancel);
		
		panel.add(buttonPanel);
		panel.add(Box.createVerticalStrut(20));
		
		
		// adding horizontal border space
		JPanel otherPanel = new JPanel();
		otherPanel.setLayout(new BoxLayout(otherPanel,BoxLayout.X_AXIS));
		//otherPanel.add(Box.createHorizontalStrut(30));
		otherPanel.add(panel);
		otherPanel.add(Box.createHorizontalStrut(30));
		
		add(otherPanel);
		pack();
		setVisible(true);
	}
	
}
