package edu.brown.cs32.scheddar.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import javax.swing.JPanel;


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
	
	public DayPane(ScheddarPane s, int day, int month, int year) {
		super(s);
		this.day = day;
		this.month = month;
		this.year = year;
	}
	
	
	public Dimension getPreferredSize() {
		Dimension d = _scheddarPane.getPreferredSize();
		d.width = d.width / 8;
		return d;
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
		if (i < 12) {
			g2.drawString((int)i + " am",3,(int)size.height/24.0*i-3);
		} else {
			g2.drawString((int)(i + " am",3,(int)size.height/24.0*i-3);
		}
		g2.draw(new Line2D.Double(size.width,size.height,size.width,0));
		
		g2.setPaint(Color.gray);
		g2.setFont(new Font("SansSerif",Font.PLAIN,6));
		
		for (double i = 1.0; i < 24; i += 1.0) {
			
			g2.draw(new Line2D.Double(0,size.height/24.0*i,size.width,size.height/24.0*i));
			
			g2.drawString((int)i+":00",3,(int)size.height/24.0*i-3);
			
		}
		
		List<Meeting> 
		
		
		
		
	}
}
