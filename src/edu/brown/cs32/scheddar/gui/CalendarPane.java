package edu.brown.cs32.scheddar.gui;



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
	
	public void switchToMonth(ScheddarTime time) {
		revalidate();
	}
	
	public void switchToWeek(ScheddarTime time) {
		removeAll();
		add(new WeekPane(_scheddarPane,time));
		revalidate();
	}
	
	public void switchToMeeting(ScheddarTime time, Meeting m) {
		
		removeAll();
		
		if (time == null)
			time = UsefulMethods.getCurrentTime();
		
		add(new DayPane(_scheddarPane, time));
		add(new MeetingPane(_scheddarPane, m));
		revalidate();
	}

	

}
