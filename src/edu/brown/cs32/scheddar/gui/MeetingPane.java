package edu.brown.cs32.scheddar.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
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
	
	JTextField name;
	JList<String> groupsInvolved;
	JTextField newGroup;
	JTextField description;
	JList<String> peopleAttending;
	JTextField newAttendee;
	
	public MeetingPane(ScheddarPane s, Meeting m) {
		super(s);
		
		name = new JTextField(20);
		description = new JTextField(40);
		
		
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
		
	}
	
	public Dimension getPreferredSize() {
		Dimension d = _scheddarPane.getPreferredSize();
		d.width = (int)(d.width * .75);
		return d;
	}
	
	
}
