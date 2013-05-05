package edu.brown.cs32.scheddar.gui;

import edu.brown.cs32.scheddar.*;

/**
 * @author sdemane
 *
 * Class representing the week view of the Scheddar gui.
 *
 */
public class WeekPane extends ScheddarSubPane {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructs the week view containing the given time
	 * 
	 * @param s
	 * @param time Any time
	 */
	public WeekPane(ScheddarPane s, ScheddarTime time) {
		super(s);
		
		ScheddarTime curDay = UsefulMethods.getPrecedingSunday(time);
		
		for (int i = 0; i < 7; i++) {
			add(new DayPane(_scheddarPane, curDay));
			curDay = UsefulMethods.getNextDay(curDay);
		}
		
	}
}
