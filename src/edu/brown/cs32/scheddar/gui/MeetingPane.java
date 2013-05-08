package edu.brown.cs32.scheddar.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

import edu.brown.cs32.scheddar.Group;
import edu.brown.cs32.scheddar.Meeting;
import edu.brown.cs32.scheddar.Person;
import edu.brown.cs32.scheddar.ScheddarTime;


/**
 * @author sdemane
 * 
 * In-frame panel for meeting creation and editing. Should
 * be paired with a DayPanel object.
 *
 */
public class MeetingPane extends ScheddarSubPane {
	private static final long serialVersionUID = 1L;
	
	Meeting meeting;
	
	JTextField name;
	Document nameDoc;
	JTextField description;
	Document descripDoc;
	
	JList<String> unaddedGroups;
	JList<String> addedGroups;
	
	public MeetingPane(ScheddarPane s, Meeting m) {
		super(s);
		
		meeting = m;
		if (meeting == null)
			meeting = new Meeting();
		
		
		// setting names and description fields
		
		name = new JTextField(meeting.getName(),20);
		nameDoc = name.getDocument();
		nameDoc.addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				meeting.setName(name.getText());
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				meeting.setName(name.getText());
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				meeting.setName(name.getText());
			}
			
		});
		
		description = new JTextField(meeting.getDescription(),40);
		descripDoc = description.getDocument();
		descripDoc.addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				meeting.setDescription(description.getText());
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				meeting.setDescription(description.getText());
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				meeting.setDescription(description.getText());
			}
			
		});
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(Box.createVerticalStrut(20));
		
		JPanel namePanel = new JPanel();
		namePanel.add(new JLabel("Meeting Title:"));
		namePanel.add(name);
		panel.add(namePanel);
		panel.add(Box.createVerticalStrut(10));
		
		JPanel descriPanel = new JPanel();
		descriPanel.add(new JLabel("Description:"));
		descriPanel.add(description);
		panel.add(descriPanel);
		panel.add(Box.createVerticalStrut(20));
		
		
		
		
		
		JPanel groupPanel = new JPanel(new GridLayout(1,3,10,0));
		
		addedGroups = new JList<String>();
		addedGroups.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		unaddedGroups = new JList<String>();
		unaddedGroups.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		updateGroupLists();
		
		JScrollPane unaddedGroupsPane = new JScrollPane(unaddedGroups);
		JScrollPane addedGroupsPane = new JScrollPane(addedGroups);
		
		
		
		JPanel groupButtonsPanel = new JPanel(new GridLayout(4,1,0,3));
		
		JButton addGroup = new JButton("Add Group to Meeting -->");
		addGroup.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (unaddedGroups.getSelectedValue() != null) {
					meeting.addGroupInvolved(_scheddar.getGroupFromName(unaddedGroups.getSelectedValue()));
					updateGroupLists();
				}
			}
		});
		
		JButton removeGroup = new JButton("<-- Remove Group from Meeting");
		removeGroup.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (addedGroups.getSelectedValue() != null) {
					meeting.removeGroupInvolved(_scheddar.getGroupFromName(addedGroups.getSelectedValue()));
					updateGroupLists();
				}
			}
		});
		
		groupButtonsPanel.add(new JPanel());
		groupButtonsPanel.add(addGroup);
		groupButtonsPanel.add(removeGroup);
		groupButtonsPanel.add(new JPanel());
		
		groupPanel.add(unaddedGroupsPane);
		groupPanel.add(groupButtonsPanel);
		groupPanel.add(addedGroupsPane);
		
		
		panel.add(groupPanel);
		
		add(panel);
	}
	
	private void updateGroupLists() {
		DefaultListModel<String> addedList = new DefaultListModel<String>();
		DefaultListModel<String> unaddedList = new DefaultListModel<String>();
		
		for (Group g : meeting.getGroupsInvolved()) {
			addedList.addElement(g.getName());
		}
		
		for (String n : _scheddar.getGroups().keySet()) {
			if (!addedList.contains(n)) {
				unaddedList.addElement(n);
			}
		}
		
		addedGroups.setModel(addedList);
		unaddedGroups.setModel(unaddedList);
	}
	
	
	public Dimension getPreferredSize() {
		Dimension d = _scheddarPane.getPreferredSize();
		return new Dimension(d.width*3/4 - 25, d.height);
	}
	
	
}
