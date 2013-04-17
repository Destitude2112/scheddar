package edu.brown.cs32.scheddar;
import java.sql.Time;
import java.sql.Date;

public class ScheddarTime {
	private Time startTime; // the starting time of this time block
	private int duration; // the duration of this time block in minutes
	private Date date; // the date in which this time block begins
	
	/**
	 * Getter functions
	 */
	
	public Time getStartTime(){
		return this.startTime;
	}
	
	public int getDuration(){
		return this.duration;
	}
	
	public Date getDate(){
		return this.date;
	}
	
	/**
	 * Setter functions
	 */
	
	public void setStartTime(Time newTime){
		this.startTime = newTime;
	}
	
	public void setDuration(int newDuration){
		this.duration = newDuration;
	}
	
	public void setDate(Date newDate){
		this.date = newDate;
	}
	
}
