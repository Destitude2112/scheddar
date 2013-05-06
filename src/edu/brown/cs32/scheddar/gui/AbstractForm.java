package edu.brown.cs32.scheddar.gui;

import java.util.Collection;

import javax.swing.JFrame;

import edu.brown.cs32.scheddar.*;

public abstract class AbstractForm extends JFrame {

	private static final long serialVersionUID = 1L;
	
	
	ScheddarPane _scheddarPane;
	ScheddarFace _scheddar;
	
	public AbstractForm(ScheddarPane s) {
		super();
		_scheddar = null;
		_scheddarPane = s;
		if (_scheddarPane != null)
			_scheddar = _scheddarPane._scheddar;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		setLocationRelativeTo(null);
		
	}
	
	/**
	 * @return A string array of the names of all the groups
	 */
	public String[] getAllGroupNames() {
		Collection<Group> groups = _scheddar.getAllGroups();
		String[] names = new String[groups.size()];
		int i = 0;
		for (Group g : groups) {
			names[i] = g.getName();
			i++;
		}
		return names;
	}
}
