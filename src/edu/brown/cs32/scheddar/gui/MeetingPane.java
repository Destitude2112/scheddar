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
import javax.swing.JComboBox;
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
	
	JComboBox<String> nameComboBox;
	JComboBox<String> impComboBox;
	
	String[] importanceArray = {"Whatever", "Debatably Important", "Definitely Important", "Extremely Important", "Cataclysmically Important"};
	
	public MeetingPane(ScheddarPane s, Meeting m) {
		super(s);
		
		meeting = m;
		if (meeting == null)
			meeting = new Meeting();
		
		
		// setting names and description fields
		
		// TODO: Make name a separate dialog box for each new meeting
		// TODO: switch to GridBagLayout
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
		
		updateLists();
		
		
		// TODO: add labels above scrollpanes
		JScrollPane unaddedGroupsPane = new JScrollPane(unaddedGroups);
		JScrollPane addedGroupsPane = new JScrollPane(addedGroups);
		
		
		
		JPanel groupButtonsPanel = new JPanel(new GridLayout(4,1,0,3));
		
		JButton addGroup = new JButton("Add to Meeting -->");
		addGroup.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (unaddedGroups.getSelectedValue() != null) {
					meeting.addGroupInvolved(_scheddar.getGroupFromName(unaddedGroups.getSelectedValue()));
					updateLists();
				}
			}
		});
		
		JButton removeGroup = new JButton("<-- Remove from Meeting");
		removeGroup.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (addedGroups.getSelectedValue() != null) {
					meeting.removeGroupInvolved(_scheddar.getGroupFromName(addedGroups.getSelectedValue()));
					updateLists();
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
		panel.add(Box.createVerticalStrut(20));
		
		JPanel individualPanel = new JPanel(new GridLayout(1,3,8,0));
		nameComboBox = new JComboBox<String>();
		nameComboBox.setEditable(true);
		impComboBox = new JComboBox<String>(importanceArray);
		impComboBox.setSelectedIndex(2);
		JButton addPersonButton = new JButton("Add to Meeting ^^");
		addPersonButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				meeting.addExtraPerson(_scheddar.getPersonFromName((String)nameComboBox.getSelectedItem()), (impComboBox.getSelectedIndex() + 1) * 20);
				updateLists();
			}
		});
		
		individualPanel.add(nameComboBox);
		individualPanel.add(impComboBox);
		individualPanel.add(addPersonButton);
		panel.add(individualPanel);
		panel.add(Box.createVerticalStrut(20));
		
		
		
		// submitting time ranges
		JPanel timeRangePanel = new JPanel();
		timeRangePanel.setLayout(new BoxLayout(timeRangePanel,BoxLayout.Y_AXIS));
		JPanel durationPanel = new JPanel();
		String[] durations = {"0:30", "1:00", "1:30", "2:00", "2:30", "3:00", "3:30", "4:00", "4:30", "5:00", "5:30", "6:00"};
		JComboBox<String> durationBox = new JComboBox<String>(durations);
		durationBox.setSelectedIndex(1);
		durationPanel.add(new JLabel("Required meeting duration (H:MM):"));
		durationPanel.add(durationBox);
		timeRangePanel.add(durationPanel);
		
		// TODO: fill in with JFormattedTextFields (requires research)
		
		
		add(panel);
	}
	
	private void updateLists() {
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
		
		//TODO: also update person list; add individuals to groups list (attendees)
	}
	
	
	public Dimension getPreferredSize() {
		Dimension d = _scheddarPane.getPreferredSize();
		return new Dimension(d.width * 3/4 - 10, d.height);
	}
	
	
}
