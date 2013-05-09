package edu.brown.cs32.scheddar.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

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
import javax.swing.SwingConstants;

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
		panel.setPreferredSize(new Dimension(360,165+31*groupMemberships.getModel().getSize()));
		
		// making "Create Person" button
		
		JButton create = new JButton("Create person");
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
					_scheddarPane._scheddar.addPerson(p);
					_scheddarPane._groupTree.updateTree();
					_scheddarPane._gui._mb.getMenu(2).getMenuComponent(0).setEnabled(true);
					_scheddarPane._gui._mb.getMenu(2).getMenuComponent(2).setEnabled(true);
					dispose();				
				}
			}
		});
		
		
		// adding everything
		JPanel fieldsPanel = new JPanel(new GridLayout(5,2,5,5));
		fieldsPanel.add(new JLabel("First name:",SwingConstants.RIGHT));
		fieldsPanel.add(firstName);
		fieldsPanel.add(new JLabel("Last name:",SwingConstants.RIGHT));
		fieldsPanel.add(lastName);
		fieldsPanel.add(new JLabel("Email:",SwingConstants.RIGHT));
		fieldsPanel.add(email);
		fieldsPanel.add(new JLabel("Phone:",SwingConstants.RIGHT));
		fieldsPanel.add(phone);
		fieldsPanel.add(new JLabel("Description:",SwingConstants.RIGHT));
		fieldsPanel.add(description);
		
		panel.add(fieldsPanel);
		panel.add(new JScrollPane(groupMemberships));
		panel.add(create);
		add(panel);
		pack();
		setVisible(true);
	}
}
