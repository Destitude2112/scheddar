package edu.brown.cs32.scheddar.gui;

import java.awt.CardLayout;
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
		
		//getting parent group
		String[] groupNames = getAllGroupNames();
		groupList = new JComboBox<String>(groupNames);
		groupList.setSelectedItem(_scheddarPane.getCurrentGroup());
		
		//getting initial members
		memberList = new JList<String>(_scheddarPane.getGroupMembers((String)groupList.getSelectedItem()));
		memberList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		JScrollPane memberListPane = new JScrollPane(memberList);
		memberListPane.setPreferredSize(new Dimension(75,100));
		JPanel listPanePane = new JPanel(new GridLayout(1,1));
		listPanePane.add(memberListPane);
		
		
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
				g.setParentGroup(_scheddarPane._scheddar.getGroupFromName((String)groupList.getSelectedItem()));
				
				for (String name : memberList.getSelectedValuesList())
					g.addMember(_scheddarPane._scheddar.getPersonFromName(name));
				
				_scheddarPane.addGroup(g);
				dispose();
				
			}
		});
		
		
		// adding everything
		panel.add(new JLabel("Group Name:"));
		panel.add(nameField);
		panel.add(new JLabel("Parent Group:"));
		panel.add(groupList);
		System.out.println(memberList.getModel().getSize());
		if(memberList.getModel().getSize()>1) {
			panel.add(new JLabel("Choose members:"));
			panel.add(memberList);
		}
		panel.add(create);
		JPanel cards;
		final String BUTTONPANEL = "Card with JButtons";
		final String TEXTPANEL = "Card with JTextField";
		cards = new JPanel(new CardLayout());
		JPanel card1 = new JPanel();
		JPanel card2 = new JPanel();
		cards.add(card1, BUTTONPANEL);
		cards.add(card2, TEXTPANEL);
		String comboBoxItems[] = { BUTTONPANEL, TEXTPANEL };
		JComboBox cb = new JComboBox(comboBoxItems);
		cb.setEditable(false);
		card1.add(cb);
		panel.add(cards);
		add(panel);
		pack();
		setVisible(true);
	}
	
}
