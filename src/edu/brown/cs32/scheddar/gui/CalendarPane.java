package edu.brown.cs32.scheddar.gui;

import javax.swing.JPanel;

import edu.brown.cs32.scheddar.*;

/**
 * @author sdemane
 * 
 * An abstract class encompassing the week and month views 
 * of the Scheddar gui.
 *
 */
public class CalendarPane extends ScheddarSubPane {
	private static final long serialVersionUID = 1L;
	
	public CalendarPane(ScheddarPane s) {
		super(s);
		ScheddarTime today = UsefulMethods.getCurrentTime();
		add(new WeekPane(s, today));
	}

	

}
