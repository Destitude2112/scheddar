package edu.brown.cs32.scheddar.gui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

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
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		if (isFun()) {
			setOpaque(false);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_IN, 0.0f));
		}
	}
}
