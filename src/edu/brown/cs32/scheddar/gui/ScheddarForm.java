package edu.brown.cs32.scheddar.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import edu.brown.cs32.scheddar.Group;
import edu.brown.cs32.scheddar.Scheddar;

/**
 * @author sdemane
 * 
 * This class implements a pop-up form to create a new
 * Scheddar project inside the application.
 *
 */
public class ScheddarForm extends AbstractForm {
	private static final long serialVersionUID = 1L;

	JTextField nameField;
	
	
	public ScheddarForm(ScheddarPane s) {
		super(s);
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		
		//getting name of organization
		nameField = new JTextField(20);
		nameField.add(new JLabel("Organization Name:"));
		
		
		
		
		// making "Create" button
		
		JButton create = new JButton("Create Scheddar project!");
		create.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (nameField.getText() != "") {
					_scheddarPane.renderScheddar(new Scheddar(nameField.getText()));
					dispose();
				}
			}
		});
		
		
		// adding everything
		panel.add(nameField);
		panel.add(create);
		add(panel);
		pack();
		setVisible(true);
	}
	
	
	
}
