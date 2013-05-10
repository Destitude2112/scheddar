package edu.brown.cs32.scheddar.gui;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
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
 * Class implementing the group creation/editor form.
 *
 */
public class EditGroupForm extends AbstractForm {
	private static final long serialVersionUID = 1L;
	
	JTextField nameField;
	JComboBox<String> groupList;
	JList<String> memberList;
	Group g;
	
	public EditGroupForm(final ScheddarPane s, Group group) {
		super(s);
		this.setTitle("Edit Group");
		g = group;
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		
		//getting name of new group
		nameField = new JTextField(20);
		nameField.setMaximumSize(new Dimension(250,25));
		nameField.setText(group.getName());
		panel.add(new JLabel("Group Name:"));
		panel.add(nameField);
		if(g.getParentGroup()==null) {
			nameField.setEditable(false);
		}
		
		//getting parent group
		if(_scheddar.getRootGroup()!=g) {
			String[] groupNames = getAllGroupNames();
			groupList = new JComboBox<String>(groupNames);
			groupList.setSelectedItem(g.getParentGroup().getName());
			groupList.setPreferredSize(new Dimension(260,25));
			panel.add(new JLabel("Parent Group:"));
			panel.add(groupList);
		} else {
			panel.setPreferredSize(new Dimension(370,110));
		}
		memberList = new JList<String>(_scheddarPane.getGroupMembers(g.getName()));
		memberList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		for(int i=0;i<memberList.getModel().getSize();i++) {
			if(g.hasMember(memberList.getModel().getElementAt(i))) {
				memberList.addSelectionInterval(i, i);
			}
		}
		JScrollPane memberListPane = new JScrollPane(memberList);
		memberListPane.setPreferredSize(new Dimension(75,100));
		JPanel listPanePane = new JPanel(new GridLayout(1,1));
		listPanePane.add(memberListPane);
		panel.setPreferredSize(new Dimension(370,Math.max(110,80+28*memberList.getModel().getSize())));
		
		// making "Create Group" button
		
		JButton create = new JButton("Save");
		create.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				String proposedName = nameField.getText();
				if(proposedName.trim().length()>0) {
					Collection<Group> existingGroups = _scheddarPane._scheddar.getAllGroups();
					if(!proposedName.equals(g.getName())) {
						for(Group eg : existingGroups){
							if(eg.getName().equals(proposedName)){
								PopUps.popUpGroupAlreadyExists();
								return;
							}
						}
					}
					
					if(groupList!=null) {
						String proposedParent = (String)groupList.getSelectedItem();
						if(!proposedParent.equals(g.getName())) {
							g.getParentGroup().removeSubgroup(g);
							g.setParentGroup(_scheddarPane._scheddar.getGroupFromName(proposedParent));
						}
					}
					List<Person> members = new ArrayList<Person>();
					for (String name : memberList.getSelectedValuesList()) {
						members.add(_scheddar.getPersonFromName(name));
					}
					g.setMembers(members);
					_scheddar.getGroups().remove(g.getName());
					g.setName(proposedName);
					_scheddar.getGroups().put(g.getName(),g);
					_scheddarPane._groupTree.updateTree();
					dispose();
				}
			}
		});
		
		// adding everything
		if(memberList.getModel().getSize()>0) {
			panel.add(new JLabel("Choose members:"));
			panel.add(memberList);
		}
		JButton importanceButton = new JButton("Edit Importance Values");
		importanceButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new EditPane(s, g);
			}
			
		});
		panel.add(importanceButton);
		panel.add(create);
		add(panel);
		pack();
		setVisible(true);
	}	
}
