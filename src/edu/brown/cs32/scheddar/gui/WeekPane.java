package edu.brown.cs32.scheddar.gui;

import edu.brown.cs32.scheddar.*;

/**
 * @author sdemane
 *
 * Class representing the week view of the Scheddar gui.
 *
 */
public class WeekPane extends CalendarPane {
	private static final long serialVersionUID = 1L;
	
	public WeekPane(ScheddarPane s, int day, int month, int year) {
		super(s);
		
		int weekday = UsefulMethods.dayOfTheWeek(day, month, year);
		
	}
}
