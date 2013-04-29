package edu.brown.cs32.scheddar.gui;

import javax.swing.JInternalFrame;

import edu.brown.cs32.scheddar.*;

public abstract class AbstractForm extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	ScheddarPane _scheddar;
	
	public AbstractForm(ScheddarPane s) {
		_scheddar = s;
	}
	
}
