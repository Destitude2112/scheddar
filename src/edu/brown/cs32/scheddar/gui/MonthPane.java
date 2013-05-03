package edu.brown.cs32.scheddar.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JPanel;

/**
 * @author sdemane
 *
 * Class representing the month view of the Scheddar gui.
 *
 */
public class MonthPane extends CalendarPane {
	private static final long serialVersionUID = 1L;
	
	ScheddarPane _scheddarPane;
	public MonthPane(ScheddarPane s) {
		super(s);
		_scheddarPane = s;
		this.setLayout(new GridLayout(2,1));
		JPanel labels = new JPanel() {
			
			@Override
			public void paintComponent(Graphics g) {
				String[] days =  {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
				for(int i = 0 ; i < days.length ; i++) {
					g.drawString(days[i], 5, i* _scheddarPane.getWidth()/7);
				}
			}
		};
		labels.setSize(s.getWidth(), 50);
	}
	
	public void displayEvents() {
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
	}
}
