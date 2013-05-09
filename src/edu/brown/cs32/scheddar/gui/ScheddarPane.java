package edu.brown.cs32.scheddar.gui;

import java.awt.Dimension;

import javax.swing.JPanel;

import edu.brown.cs32.scheddar.*;

/**
 * @author sdemane
 * 
 * Class implementing the overall Scheddar GUI. Encompasses calendar
 * views, project/group/person/meeting creation and management, and
 * email management. Each ScheddarPanel class is created from a 
 * scheddar project file and is specific to the project, not the 
 * application itself.
 *
 */
public class ScheddarPane extends JPanel {

	private static final long serialVersionUID = 1L;
	
	ScheddarFace _scheddar;
	GUIScheddar _gui;
	GroupTreePane _groupTree;
	CalendarPane _calendar;
	
	public ScheddarPane(GUIScheddar gui, ScheddarFace s) {
		_gui = gui;
		_scheddar = s;
		_groupTree = new GroupTreePane(this);
		_calendar = new CalendarPane(this);
		
		add(_groupTree);
		add(_calendar);
	}
	
	public void setScheddar(ScheddarFace s) {
		_scheddar = s;
	}
	
	public boolean isFun() {
		return _gui.isFun();
	}
	
	public String getRootGroupName() {
		return _scheddar.getRootGroup().getName();
	}
	
	@Override
	public Dimension getPreferredSize() {
		return _gui.getScreenSize();
	}
	
	/**
	 * Brings GUI to a new (empty) meeting pane at some default time
	 */
	public void initializeMeeting() {
		_calendar.switchToMeeting(null, null);
	}
	
	public void addGroup(Group g) {
		_scheddar.addGroup(g);
		_groupTree.updateTree();
		repaint();
	}
	
	public String getCurrentGroup() {
		return _groupTree.getSelectedGroup();
	}
	
	public String[] getGroupMembers(String name) {
		return _scheddar.getGroupFromName(name).getPeopleFullNamesInGroup().toArray(new String[0]);
	}
}
