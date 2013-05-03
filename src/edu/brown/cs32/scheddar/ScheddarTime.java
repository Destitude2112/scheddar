package edu.brown.cs32.scheddar;

public class ScheddarTime implements Comparable {
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
	
	
	/**
	 * This compare method compares the starting times of two ScheddarTimes. The later event is > than the
	 * earlier event
	 */
	
	@Override
	public int compareTo(Object o) {
		ScheddarTime t2 = (ScheddarTime) o;
		if(this.year > t2.year) return 1;
		if(this.year < t2.year) return -1;
		if(this.month > t2.month) return 1;
		if(this.month < t2.month) return -1;
		if(this.day > t2.day) return 1;
		if(this.day < t2.day) return -1;
		if(this.startHour > t2.startHour) return 1;
		if(this.startHour < t2.startHour) return -1;
		if(this.startMinutes > t2.startMinutes) return 1;
		if(this.startMinutes < t2.startMinutes) return -1;
		return 0;
	}
}
