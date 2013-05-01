package edu.brown.cs32.scheddar.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
	
	ScheddarPane pane;
	JTextField name;
	JTextField time;
	JList<String> groupsInvolved;
	JTextField newGroup;
	JTextField description;
	JList<String> peopleAttending;
	JTextField newAttendee;
	JTextField importanceThreshold;
	
	public MeetingPane(ScheddarPane s, Meeting m) {
		super(s);
		pane = s;
		JPanel panel = new JPanel();
		name = new JTextField(20);
		time = new JTextField(20);
		groupsInvolved = new JList<String>();
		newGroup = new JTextField(20);
		description = new JTextField(20);
		peopleAttending = new JList<String>();
		newAttendee = new JTextField(20); 
		importanceThreshold = new JTextField(20);
		
		if(m!=null) {
			name.setText(m.getName());
			description.setText(m.getDescription());
			importanceThreshold.setText(Double.toHexString(m.getImportanceThreshold()));
		}
		JButton save = new JButton("Save");
		
		JButton addGroup = new JButton("Add group");
		addGroup.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});

		
		panel.add(new JLabel("Name:"));
		panel.add(name);
		panel.add(new JLabel("Time:"));
		panel.add(time);
		panel.add(new JLabel("Groups:"));
		panel.add(groupsInvolved);
		panel.add(new JLabel("Description:"));
		panel.add(description);
		panel.add(new JLabel("Attendees:"));
		panel.add(peopleAttending);
		panel.add(new JLabel("Importance:"));
		panel.add(importanceThreshold);
		panel.add(save);
		add(panel);		
	}
	
	
}
