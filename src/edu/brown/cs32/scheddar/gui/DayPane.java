package edu.brown.cs32.scheddar.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.util.Date;
import java.util.List;

import javax.swing.JPanel;

import edu.brown.cs32.scheddar.*;


/**
 * @author sdemane
 *
 * Class representing the day view of the scheddar GUI. Should
 * be paired with MeetingPanel in the panel opposite GroupTree.
 * Looks similar to MonthPanel and DayPanel, but different size
 * and function.
 *
 */
public class DayPane extends ScheddarSubPane {
	private static final long serialVersionUID = 1L;
	
	int day,month,year;
	
	public DayPane(ScheddarPane s, ScheddarTime st) {
		super(s);
		this.day = st.getDay();
		this.month = st.getMonth();
		this.year = st.getYear();
	}
	
	@Override
	public Dimension getPreferredSize() {
		Dimension d = _scheddarPane.getPreferredSize();
		System.out.println("DayPane: "+_scheddarPane.getPreferredSize());
		d.width = d.width / 8;
		return d;
	}
	
	private Rectangle getTimeBlock(ScheddarTime st) {
		Dimension d = getPreferredSize();
		return null;
	}
	
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		// drawing border
		
		Dimension size = getPreferredSize();
		g2.setStroke(new BasicStroke(5));
		g2.setPaint(Color.darkGray);
		g2.draw(new Line2D.Double(0,0,0,size.height));
		g2.draw(new Line2D.Double(0,0,size.width,0));
		g2.draw(new Line2D.Double(size.width,size.height,0,size.height));
		g2.draw(new Line2D.Double(size.width,size.height,size.width,0));
		
		g2.setPaint(Color.gray);
		g2.setFont(new Font("SansSerif",Font.PLAIN,6));
		
		for (double i = 1.0; i < 24; i += 1.0) {
			
			g2.draw(new Line2D.Double(0,size.height/24.0*i,size.width,size.height/24.0*i));
			
			g2.drawString((int)i+":00",3,(int)(size.height/24.0*i-3));
			
		}
		
		g2.drawString(new Date().toString(), 10, 3);
		
		List<Meeting> meetings = _scheddar.dayMeetings(day, month, year);
		
		for (Meeting m : meetings) {
			if (m.isDecided()) {
				Rectangle block = getTimeBlock(m.getFinalTime());
			}
		}
		
		
		
	}
}
