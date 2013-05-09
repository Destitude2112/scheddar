package edu.brown.cs32.scheddar.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
 * Class implementing the group creation/editor form.
 *
 */
public class GroupForm extends AbstractForm {
	private static final long serialVersionUID = 1L;
	
	JTextField nameField;
	JComboBox<String> groupList;
	JList<String> memberList;
	
	public GroupForm(ScheddarPane s) {
		super(s);
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(2,2,5,5));
		
		//getting name of new group
		nameField = new JTextField(20);
		nameField.setMaximumSize(new Dimension(250,25));
		topPanel.add(new JLabel("Group Name:"));
		topPanel.add(nameField);
		
		//getting parent group
		String[] groupNames = getAllGroupNames();
		groupList = new JComboBox<String>(groupNames);
		groupList.setSelectedItem(_scheddarPane.getCurrentGroup());
		groupList.setPreferredSize(new Dimension(260,25));
		topPanel.add(new JLabel("Parent Group:"));
		topPanel.add(groupList);
		
		//getting initial members
		memberList = new JList<String>(_scheddarPane.getGroupMembers((String)groupList.getSelectedItem()));
		memberList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		JScrollPane memberListPane = new JScrollPane(memberList);
		memberListPane.setPreferredSize(new Dimension(75,100));
		JPanel listPanePane = new JPanel(new GridLayout(1,1));
		listPanePane.add(memberListPane);
		panel.setPreferredSize(new Dimension(370,Math.max(100,80+28*memberList.getModel().getSize())));
		
		//adding listener so member list is updated if parent group changes
		groupList.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				memberList.setListData(_scheddarPane.getGroupMembers((String)groupList.getSelectedItem()));
			}
		});
		
		
		// making "Create Group" button
		
		JButton create = new JButton("Create group");
		create.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(nameField.getText().trim().length()>0) {
					Group g = new Group(nameField.getText().trim());
					Collection<Group> existingGroups = _scheddarPane._scheddar.getAllGroups();
					for(Group eg : existingGroups){
						if(eg.getName().equals(g.getName())){
							PopUps.popUpGroupAlreadyExists();
							return;
						}
					}
					
					g.setParentGroup(_scheddarPane._scheddar.getGroupFromName((String)groupList.getSelectedItem()));
					for (String name : memberList.getSelectedValuesList()) {
						g.addMember(_scheddarPane._scheddar.getPersonFromName(name));
						g.addMemberRanking(name, Scheddar.importanceArrayValues[1]);
					}
					
					_scheddarPane.addGroup(g);
					dispose();
				}
			}
		});
		
		
		// adding everything
		if(memberList.getModel().getSize()>0) {
			panel.add(new JLabel("Choose members:"));
			panel.add(memberList);
		}
		panel.add(topPanel);
		panel.add(create);
		add(panel);
		pack();
		setVisible(true);
	}
	
}
