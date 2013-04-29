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
	
	Scheddar _scheddar;
	Dimension _size;
	GroupTreePane _groupTree;
	
	public ScheddarPane(GUIScheddar gui, Scheddar s) {
		_scheddar = s;
		_size = gui._screenSize;
	}
	
	@Override
	public Dimension getPreferredSize() {
		return _size;
	}
	
	/**
	 * Brings GUI to a new (empty) meeting pane at some default time
	 */
	public void initializeMeeting() {
		
	}
	
	public String getCurrentGroup() {
		return _groupTree.getSelectedGroup();
	}
	
	public String[] getGroupMembers(String name) {
		return _scheddar.getGroup(name).getPeopleFullNamesInGroup().toArray(new String[0]);
	}
	
}
