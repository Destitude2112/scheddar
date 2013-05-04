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
import javax.swing.JMenuBar;
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
	GUIScheddar frame;
	
	public ScheddarForm(ScheddarPane s) {
		super(s);
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		frame = null;
		
		//getting name of organization
		nameField = new JTextField(20);	
		// TODO: add fields for admin, email, password
		
		// making "Create" button
		
		JButton create = new JButton("Create");
		create.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (nameField.getText() != "") {
					String t = nameField.getText();
					_scheddarPane.renderScheddar(new Scheddar());
					frame.setGroup(t);
					JMenuBar mb = frame.getMenu();
					mb.getMenu(1).setEnabled(true);
					dispose();
				}
			}
		});
		
		
		// adding everything
		panel.add(new JLabel("Organization Name:"));
		panel.add(nameField);
		panel.add(create);
		add(panel);
		pack();
		setVisible(true);
	}
	
	public void setFrame(GUIScheddar f) {
		frame = f;
	}
	
}
