package edu.brown.cs32.scheddar.gui;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
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
	ScheddarTime _startDay;
	final Color finalMeetingColor = new Color(210,40,40);
	final Color proposedMeetingColor = new Color(120,160,210);
	final double WEEKS_TO_DISPLAY = 7.0;

	public MonthPane(ScheddarPane s, ScheddarTime st) {
		super(s);
		_scheddarPane = s;
		_time = st;
		_startDay = UsefulMethods.getPrecedingSunday(UsefulMethods.getFirstOfMonth(st));
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Dimension size = getPreferredSize();
				int x = (e.getPoint().x)/(size.width/7);
				int y = (e.getPoint().y)/(int)(size.height/WEEKS_TO_DISPLAY)-1;
				if(y<0) return; // Clicked outside calendar
				ScheddarTime curDay = _startDay;
				for(int i = 0 ; i < 7*y+x ; i++) {
					curDay = UsefulMethods.getNextDay(curDay);
				}
				_scheddarPane._calendar.switchToWeek(curDay);
			}
		});
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		JButton prevButton = new JButton("Previous Month");
		JButton weekButton = new JButton("Week View");
		JButton nextButton = new JButton("Next Month");
		prevButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadPrevMonth();
			}
		});
		weekButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_scheddarPane._calendar.switchToWeek(_time);
			}
		});
		nextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadNextMonth();
			}
		});
		this.add(prevButton);
		this.add(weekButton);
		this.add(nextButton);
}	
	
	@Override
	public Dimension getPreferredSize() {
		Dimension d = _scheddarPane.getPreferredSize();
		return new Dimension(d.width*7/8-20, d.height);
	}

	@Override
	public void paintComponent(Graphics graphics) {
		ScheddarTime _curDay = _startDay;
		Graphics2D g = (Graphics2D) graphics;
		Dimension size = getPreferredSize();
		Composite originalComposite = g.getComposite();
		Composite transparentComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
		g.setBackground(Color.white);
		
		if (isFun()) {
			BufferedImage img = null;
			try {
			    img = ImageIO.read(new File("data/cheese.jpg"));
			    g.drawImage(img,0,0,getPreferredSize().width,getPreferredSize().height,null);
			} catch (IOException e) {
			}
		}
		
		
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
		drawCenteredString(g, months[_time.getMonth()]+" "+_time.getYear(), size.width/2, 60);
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
					if (isFun())
					g.setComposite(transparentComposite);
					g.fillRect(size.width/days.length*j, (int)(size.height/WEEKS_TO_DISPLAY*i), size.width/days.length, (int)(size.height/WEEKS_TO_DISPLAY));
					g.setPaint(Color.gray);
					g.setComposite(originalComposite);
				}
				g.drawString(Integer.toString(_curDay.getDay()), 5+size.width/days.length*j,(int)(20+size.height/WEEKS_TO_DISPLAY*i));
				_curDay = UsefulMethods.getNextDay(_curDay);
			}
			g.draw(new Line2D.Double(0,size.height/WEEKS_TO_DISPLAY*i,size.width,size.height/WEEKS_TO_DISPLAY*i));			
		}
		g.setFont(new Font("SansSerif",Font.PLAIN,20));

		for(int i = 0 ; i < days.length ; i++) {
			drawCenteredString(g, days[i],i*size.width/7+size.width/14, (int)(size.height/WEEKS_TO_DISPLAY-10));
			g.draw(new Line2D.Double(size.width/days.length*i,size.height/WEEKS_TO_DISPLAY, size.width/days.length*i, size.height));
		}
	}

	public void displayEvents(Graphics2D g) {
		List<Meeting> meetings = _scheddarPane._scheddar.monthMeetings(_time.getMonth(), _time.getYear());
		int[] meetingArray = new int[1+UsefulMethods.daysInMonth(_time.getMonth(), _time.getYear())];
		for(Meeting m: meetings) {
			int day = m.getFinalTime().getDay();
			if(meetingArray[day]<5) { 	// Only displays first 5 meetings on a given day
				Point p = getBlock(m.getFinalTime());
				g.drawString(m.getName(), p.x+5, p.y+5+15*meetingArray[day]);
				meetingArray[day] = meetingArray[day] + 1;
			}
		}
	}
	
	public Point getBlock(ScheddarTime t) {
		Dimension size = getPreferredSize();
		int x = t.getDayOfWeek();
		int startDay = _startDay.getMonth()==t.getMonth() ? t.getDay() : t.getDay()-UsefulMethods.daysInMonth(_startDay.getMonth(), _startDay.getYear());
		int y = (t.getDay()-startDay)/7;
		return new Point(size.width/7*x, (int)(size.height/WEEKS_TO_DISPLAY*y));
	}

	public void loadPrevMonth() {
		int month = _time.getMonth()-1;
		int year = _time.getYear();
		if(month<0) {
			year--;
			month = 11;
		}
		int day = Math.min(_time.getDay(), UsefulMethods.daysInMonth(month, year));
		_scheddarPane._calendar.switchToMonth(new ScheddarTime(0, 0, 0, UsefulMethods.dayOfTheWeek(day, month, year), day, month, year, false));
	}
	
	public void loadNextMonth() {
		int month = _time.getMonth()+1;
		int year = _time.getYear();
		if(month>11) {
			year++;
			month = 0;
		}
		int day = Math.min(_time.getDay(), UsefulMethods.daysInMonth(month, year));
		_scheddarPane._calendar.switchToMonth(new ScheddarTime(0, 0, 0, UsefulMethods.dayOfTheWeek(day, month, year), day, month, year, false));
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
