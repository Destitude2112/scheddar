package edu.brown.cs32.scheddar.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import edu.brown.cs32.scheddar.Group;
import edu.brown.cs32.scheddar.Person;
import edu.brown.cs32.scheddar.Scheddar;

/**
 * @author sdemane
 * 
 * Class implementing the person creation/editor form.
 *
 */
public class PersonForm extends AbstractForm {
	private static final long serialVersionUID = 1L;
	
	JTextField firstName;
	JTextField lastName;
	JTextField email;
	JTextField phone;
	JTextField description;
	JList<String> groupMemberships;
	
	public PersonForm(ScheddarPane s) {
		super(s);
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		
		//getting name of new group
		firstName = new JTextField(20);
		lastName = new JTextField(20);
		email = new JTextField(30);
		phone = new JTextField(20);
		description = new JTextField(30);
		
		//getting groups
		String[] groupNames = getAllGroupNames();
		groupMemberships = new JList<String>(groupNames);
		groupMemberships.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		JScrollPane memberListPane = new JScrollPane(groupMemberships);
		memberListPane.setPreferredSize(new Dimension(75,100));
		JPanel listPanePane = new JPanel(new GridLayout(1,1));
		listPanePane.add(memberListPane);
		listPanePane.add(new JLabel("Choose members:"));		
		
		// making "Create Group" button
		
		JButton create = new JButton("Create group");
		create.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				Person p = new Person(firstName.getText().trim(),lastName.getText().trim(),email.getText().trim(),phone.getText().trim(),description.getText().trim());								
				for (String name : groupMemberships.getSelectedValuesList()) {
					Group g = _scheddarPane._scheddar.getGroup(name);
					while(g!=null) {
						p.addGroup(g);
						g = g.getParentGroup();
					}
				}				
				_scheddarPane._scheddar.addPerson(p);
				dispose();				
			}
		});
		
		
		// adding everything
		panel.add(new JLabel("First name:"));
		panel.add(firstName);
		panel.add(new JLabel("Last name:"));
		panel.add(lastName);
		panel.add(new JLabel("Email:"));
		panel.add(email);
		panel.add(new JLabel("Phone:"));
		panel.add(phone);
		panel.add(new JLabel("Description:"));
		panel.add(description);
		panel.add(groupMemberships);
		panel.add(create);
		add(panel);
		pack();
		setVisible(true);
	}
}
