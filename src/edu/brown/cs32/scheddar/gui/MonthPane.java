package edu.brown.cs32.scheddar.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JPanel;

import edu.brown.cs32.scheddar.Meeting;
import edu.brown.cs32.scheddar.ScheddarTime;

/**
 * @author sdemane
 *
 * Class representing the month view of the Scheddar gui.
 *
 */
public class MonthPane extends ScheddarSubPane {
	private static final long serialVersionUID = 1L;
	
	ScheddarPane _scheddarPane;
	ScheddarTime _time;
	public MonthPane(ScheddarPane s, ScheddarTime st) {
		super(s);
		_scheddarPane = s;
		_time = st;
		this.setLayout(new GridLayout(2,1));
		JPanel labels = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			public void paintComponent(Graphics g) {
				String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
				String[] days =  {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
				g.drawString(months[_time.getMonth()], _scheddarPane.getWidth()/2, 5);
				for(int i = 0 ; i < days.length ; i++) {
					g.drawString(days[i], 5, i* _scheddarPane.getWidth()/7);
					g.drawLine(i*_scheddarPane.getWidth()/7, 20, i*_scheddarPane.getWidth()/7, _scheddarPane.getHeight());
				}
			}
		};
		labels.setSize(s.getWidth(), 50);
	}
	
	public void displayEvents() {
		List<Meeting> meetings = _scheddarPane._scheddar.monthMeetings(_time.getMonth(), _time.getYear());
		for(Meeting meeting: meetings) {
			int day = meeting.getFinalTime().getDay();
			int hour = meeting.getFinalTime().getStartHour();
			
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
	}
}
