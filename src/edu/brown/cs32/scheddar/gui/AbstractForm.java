package edu.brown.cs32.scheddar.gui;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import edu.brown.cs32.scheddar.*;

public abstract class AbstractForm extends JFrame {

	private static final long serialVersionUID = 1L;
	ScheddarPane _scheddarPane;
	
	public AbstractForm(ScheddarPane s) {
		super();
		_scheddarPane = s;
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
//		Dimension d = _scheddarPane._size;
//		setBounds(d.width / 3, d.height / 3, 1, 1);
		setLocationRelativeTo(null);
		
	}
	
	/**
	 * @return A string array of the names of all the groups
	 */
	public String[] getAllGroupNames() {
		return new String[0];
	}
}
