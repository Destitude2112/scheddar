package edu.brown.cs32.scheddar.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.List;

import javax.swing.JPanel;

import edu.brown.cs32.scheddar.Meeting;
import edu.brown.cs32.scheddar.ScheddarTime;
import edu.brown.cs32.scheddar.UsefulMethods;

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
	final Color finalMeetingColor = new Color(210,40,40);
	final Color proposedMeetingColor = new Color(120,160,210);
	final double WEEKS_TO_DISPLAY = 7.0;

	public MonthPane(ScheddarPane s, ScheddarTime st) {
		super(s);
		_scheddarPane = s;
		_time = st;
	}
	
	public void displayEvents() {
		List<Meeting> meetings = _scheddarPane._scheddar.monthMeetings(_time.getMonth(), _time.getYear());
		int[] meetingArray = new int[1+UsefulMethods.daysInMonth(_time.getMonth(), _time.getYear())];
		for(Meeting m: meetings) {
			int day = m.getFinalTime().getDay();
			int hour = m.getFinalTime().getStartHour();
			
		}
	}
	
	@Override
	public Dimension getPreferredSize() {
		Dimension d = _scheddarPane.getPreferredSize();
		return new Dimension(d.width*7/8-50, d.height);
	}

	@Override
	public void paintComponent(Graphics graphics) {
		ScheddarTime _curDay = UsefulMethods.getPrecedingSunday(UsefulMethods.getFirstOfMonth(_time));
		Graphics2D g = (Graphics2D) graphics;
		Dimension size = getPreferredSize();
		g.setBackground(Color.white);
		g.setStroke(new BasicStroke(1));
		g.setPaint(Color.DARK_GRAY);
		g.draw(new Line2D.Double(0,0,0,size.height));
		g.draw(new Line2D.Double(0,0,size.width,0));
		g.draw(new Line2D.Double(size.width,size.height,0,size.height));
		g.draw(new Line2D.Double(size.width,size.height,size.width,0));
		g.setPaint(Color.gray);
		g.setFont(new Font("SansSerif",Font.PLAIN,40));

		
		String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		String[] days =  {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
		g.drawString(months[_time.getMonth()], size.width/2, 60);
		g.setFont(new Font("SansSerif",Font.PLAIN,22));
		g.setFont(new Font("SansSerif",Font.PLAIN,15));
		
		for(double i = 1.0; i < WEEKS_TO_DISPLAY; i += 1.0) {
			for(int j = 0; j < days.length ; j++) {
				if(_curDay.getMonth()!=_time.getMonth()) {
					g.setPaint(Color.darkGray);
					g.fillRect(size.width/days.length*j, (int)(size.height/WEEKS_TO_DISPLAY*i), size.width/days.length, (int)(size.height/WEEKS_TO_DISPLAY));
					g.setPaint(Color.gray);
				}
				if(_curDay.getMonth()==_time.getMonth() && _curDay.getDay()==_time.getDay()) {
					g.setPaint(new Color(240,240,230));
					g.fillRect(size.width/days.length*j, (int)(size.height/WEEKS_TO_DISPLAY*i), size.width/days.length, (int)(size.height/WEEKS_TO_DISPLAY));
					g.setPaint(Color.gray);
				}
				g.drawString(Integer.toString(_curDay.getDay()), 5+size.width/days.length*j,(int)(20+size.height/WEEKS_TO_DISPLAY*i));
				_curDay = UsefulMethods.getNextDay(_curDay);
			}
			g.draw(new Line2D.Double(0,size.height/WEEKS_TO_DISPLAY*i,size.width,size.height/WEEKS_TO_DISPLAY*i));			
		}
		for(int i = 0 ; i < days.length ; i++) {
			drawCenteredString(g, days[i],i*size.width/7+size.width/14, (int)(size.height/WEEKS_TO_DISPLAY-10));
			g.draw(new Line2D.Double(size.width/days.length*i,size.height/WEEKS_TO_DISPLAY, size.width/days.length*i, size.height));
		}
	}
	
	/**
	 * 
	 * @param g The Graphics object used to draw the string
	 * @param s The string to be drawn
	 * @param x The x position to center the string around
	 * @param y The y position of the top of the string
	 */
	public void drawCenteredString(Graphics2D g, String s, int x, int y) {
		FontMetrics fm = getFontMetrics(g.getFont());
		Rectangle2D textsize = fm.getStringBounds(s, g);
		g.drawString(s, (int) (x-textsize.getWidth()/ 2),y);
	}
}
