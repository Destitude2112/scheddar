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
import javax.swing.SwingConstants;

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
	Person p;
	
	public EditPersonForm(ScheddarPane s, Person person) {
		super(s);
		p = person;
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		//getting name of new group
		firstName = new JTextField(20);
		firstName.setText(p.getFirstName());
		lastName = new JTextField(20);
		lastName.setText(p.getLastName());
		email = new JTextField(20);
		email.setText(p.getEmail());
		phone = new JTextField(20);
		phone.setText(p.getPhoneNum());
		description = new JTextField(20);
		description.setText(p.getDescription());
		panel.setPreferredSize(new Dimension(360,200));
		
		// making "Create Person" button
		
		JButton create = new JButton("Save");
		create.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(firstName.getText().trim().length()>0 && lastName.getText().trim().length()>0) {
					Collection <Person> existingPeople = _scheddarPane._scheddar.getAllPeople();
					if(!p.getFirstName().equals(firstName.getText().trim()) || !p.getLastName().equals(lastName.getText().trim())) {
						for(Person ep : existingPeople){
							if(ep.getFirstName().equals(firstName.getText().trim()) && ep.getLastName().equals(lastName.getText().trim())){
								PopUps.popUpPersonAlreadyExists();
								return;
							}
						}
					}
					p.setFirstName(firstName.getText().trim());
					p.setLastName(lastName.getText().trim());
					p.setEmail(email.getText().trim());
					p.setPhoneNum(phone.getText().trim());
					p.setDescription(description.getText().trim());
					_scheddarPane._groupTree.updateTree();
					dispose();				
				}
			}
		});

		JPanel fieldsPanel = new JPanel(new GridLayout(5,2,5,5));

		fieldsPanel.add(new JLabel("First name:",SwingConstants.LEFT));
		fieldsPanel.add(firstName);
		fieldsPanel.add(new JLabel("Last name:",SwingConstants.LEFT));
		fieldsPanel.add(lastName);
		fieldsPanel.add(new JLabel("Email:",SwingConstants.LEFT));
		fieldsPanel.add(email);
		fieldsPanel.add(new JLabel("Phone:",SwingConstants.LEFT));
		fieldsPanel.add(phone);
		fieldsPanel.add(new JLabel("Description:",SwingConstants.LEFT));
		fieldsPanel.add(description);
		fieldsPanel.setPreferredSize(new Dimension(350,140));

		panel.add(fieldsPanel);
		panel.add(create);
		add(panel);
		pack();
		setVisible(true);
	}
}
