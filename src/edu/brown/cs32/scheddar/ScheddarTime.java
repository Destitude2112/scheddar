package edu.brown.cs32.scheddar;
import java.sql.Time;
import java.sql.Date;

public class ScheddarTime {
	private int startHour; // the starting hour (0 - 24)
	private int startMinutes; // the starting minutes (0 - 60 , increments of 10 or 15)
	private int duration; // in minutes
	private int dayOfWeek; // the day of the week (0 -> Sunday, 1 -> Monday, etc.)
	private int day; // the actual day number
	private int month; // the number of the month
	private int year; // the year
	private boolean isRecurring; // for meetings recurring on a particular day of the week at the given time
	
	/**
	 * Full constructor
	 */
	
	public ScheddarTime(int startHour,int startMinutes, int duration, int dayOfWeek, int day,
			int month, int year, boolean isRecurring){
		this.startHour = startHour;
		this.startMinutes = startMinutes;
		this.duration = duration;
		this.dayOfWeek = dayOfWeek;
		this.day = day;
		this.month = month;
		this.year = year;
		this.isRecurring = isRecurring;
	}
	
	/**
	 * Getter functions
	 */
	
	public int getStartHour(){
		return this.startHour;
	}
	
	public int getStartMinutes(){
		return this.startMinutes;
	}
	
	public int getDuration(){
		return this.duration;
	}
	
	public int getDayOfWeek(){
		return this.dayOfWeek;
	}
	
	public int getDay(){
		return this.day;
	}
	
	public int getMonth(){
		return this.month;
	}
	
	public int getYear(){
		return this.year;
	}
	
	public boolean isRecurring(){
		return this.isRecurring;
	}
	
	/**
	 * Setter Functions
	 */
	
	public void setStartHour(int start){
		this.startHour = start;
	}
	
	public void setStartMinutes(int startMinutes){
		this.startMinutes = startMinutes;
	}
	
	public void setDuration(int minutes){
		this.duration = minutes;
	}
	
	public void setDayOfWeek(int dayNum){
		this.dayOfWeek = dayNum;
	}
	
	public void setDay(int day){
		this.day = day;
	}
	
	public void setMonth(int month){
		this.month = month;
	}
	
	public void setYear(int year){
		this.year = year;
	}
	
	public void setIsRecurring(boolean isRecurring){
		this.isRecurring = isRecurring;
	}
	
	
//	private Time startTime; // the starting time of this time block
//	private int duration; // the duration of this time block in minutes
//	private Date date; // the date in which this time block begins
//	
//	/**
//	 * Getter functions
//	 */
//	
//	public Time getStartTime(){
//		return this.startTime;
//	}
//	
//	public int getDuration(){
//		return this.duration;
//	}
//	
//	public Date getDate(){
//		return this.date;
//	}
//	
//	/**
//	 * Setter functions
//	 */
//	
//	public void setStartTime(Time newTime){
//		this.startTime = newTime;
//	}
//	
//	public void setDuration(int newDuration){
//		this.duration = newDuration;
//	}
//	
//	public void setDate(Date newDate){
//		this.date = newDate;
//	}
//	
}
