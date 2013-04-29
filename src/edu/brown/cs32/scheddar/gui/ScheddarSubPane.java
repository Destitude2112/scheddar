package edu.brown.cs32.scheddar.gui;

import javax.swing.JPanel;

import edu.brown.cs32.scheddar.*;


/**
 * @author sdemane
 * 
 * Abstract class which should be the parent class of all
 * subpanels of the ScheddarPane. Meant to generalize methods
 * for accessing data global to the ScheddarPane.
 *
 */
public abstract class ScheddarSubPane extends JPanel {
	
	private static final long serialVersionUID = 1L;
	ScheddarPane _scheddarPane;
	Scheddar _scheddar;
	
	public ScheddarSubPane(ScheddarPane s) {
		_scheddarPane = s;
		_scheddar = s._scheddar;
	}
	
}