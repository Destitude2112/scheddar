package edu.brown.cs32.scheddar.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import edu.brown.cs32.scheddar.Group;
import edu.brown.cs32.scheddar.Person;

/**
 * @author sdemane
 * 
 * Class implementing the person creation/editor form.
 *
 */
public class EditPersonForm extends AbstractForm {
	private static final long serialVersionUID = 1L;
	
	JTextField firstName;
	JTextField lastName;
	JTextField email;
	JTextField phone;
	JTextField description;
	JList<String> groupMemberships;
	
	public EditPersonForm(ScheddarPane s, Person p) {
		super(s);
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		//getting name of new group
		firstName = new JTextField(20);
		lastName = new JTextField(20);
		email = new JTextField(20);
		phone = new JTextField(20);
		description = new JTextField(20);
		
		//getting groups
		String[] groupNames = getAllGroupNames();
		groupMemberships = new JList<String>(groupNames);
		groupMemberships.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		groupMemberships.setSelectedValue(s._scheddar.getRootGroup().getName(), true);
		JScrollPane memberListPane = new JScrollPane(groupMemberships);
		memberListPane.setPreferredSize(new Dimension(75,100));
		JPanel listPanePane = new JPanel(new GridLayout(1,1));
		listPanePane.add(memberListPane);
		listPanePane.add(new JLabel("Choose members:"));		
		panel.setPreferredSize(new Dimension(360,165+31*groupMemberships.getModel().getSize()));
		
		// making "Create Person" button
		
		JButton create = new JButton("Save");
		create.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(firstName.getText().trim().length()>0 && lastName.getText().trim().length()>0) {
					
					Person p = new Person(firstName.getText().trim(),lastName.getText().trim(),email.getText().trim(),phone.getText().trim(),description.getText().trim());
					Collection <Person> existingPeople = _scheddarPane._scheddar.getAllPeople();
					for(Person ep : existingPeople){
						if(ep.getFullName().equals(p.getFullName())){
							PopUps.popUpPersonAlreadyExists();
							return;
						}
					}
					List<String> groups = groupMemberships.getSelectedValuesList(); 
					for (String name : groups) {
						Group g = _scheddarPane._scheddar.getGroupFromName(name);
						p.addGroup(g);
						g.addMember(p);
					}				
					_scheddarPane._groupTree.updateTree();
					dispose();				
				}
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
