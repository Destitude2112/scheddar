package edu.brown.cs32.scheddar.gui;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import edu.brown.cs32.scheddar.*;

public abstract class AbstractForm extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	ScheddarPane _scheddarPane;
	
	public AbstractForm(ScheddarPane s) {
		super("",false,true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_scheddarPane = s;
	}
	
	/**
	 * @return A string array of the names of all the groups
	 */
	public String[] getAllGroupNames() {
		return new String[0];
	}
}
