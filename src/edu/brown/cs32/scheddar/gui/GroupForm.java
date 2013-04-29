package edu.brown.cs32.scheddar.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import edu.brown.cs32.scheddar.*;

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
		
		//getting name of new group
		nameField = new JTextField(20);
		nameField.add(new JLabel("Group Name:"));
		
		//getting parent group
		String[] groupNames = getAllGroupNames();
		groupList = new JComboBox<String>(groupNames);
		groupList.setSelectedItem(_scheddarPane.getCurrentGroup());
		groupList.add(new JLabel("Parent Group:"));
		
		//getting initial members
		
		memberList = new JList<String>(_scheddarPane.getGroupMembers((String)groupList.getSelectedItem()));
		memberList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		JScrollPane memberListPane = new JScrollPane(memberList);
		memberListPane.setPreferredSize(new Dimension(75,100));
		JPanel listPanePane = new JPanel(new GridLayout(1,1));
		listPanePane.add(memberListPane);
		listPanePane.add(new JLabel("Choose members:"));
		
		
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
				Group g = new Group(nameField.getText().trim());
				g.setParentGroup(_scheddarPane._scheddar.getGroup((String)groupList.getSelectedItem()));
				
				for (String name : memberList.getSelectedValuesList())
					g.addMember(_scheddarPane._scheddar.getPerson(name));
				
				_scheddarPane._scheddar.addGroup(g);
				closeForm();
				
			}
		});
		
		
		// adding everything
		panel.add(nameField);
		panel.add(groupList);
		panel.add(memberList);
		panel.add(create);
		add(panel);
		pack();
		setVisible(true);
	}
	
	public void closeForm() {
		this.dispose();
	}
}
