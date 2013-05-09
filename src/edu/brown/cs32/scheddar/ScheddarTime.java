package edu.brown.cs32.scheddar;

import java.text.DateFormatSymbols;

public class ScheddarTime implements Comparable {
	private int startHour; // the starting hour (0 - 24)
	private int startMinutes; // the starting minutes (0 - 60 , increments of 10 or 15)
	private int duration; // in minutes
	private int dayOfWeek; // the day of the week (0 -> Sunday, 1 -> Monday, etc.)
	private int day; // the actual day number
	private int month; // the number of the month
	private int year; // the year
	private boolean isRecurring; // for meetings recurring on a particular day of the week at the given time
	
	private UsefulMethods methods = new UsefulMethods();
	private DateFormatSymbols dateFormat = new DateFormatSymbols();
	
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
	 * Default Constructor with some preset values
	 * 
	 * @param dayOfWeek
	 * @param startHour
	 * @param duration
	 */
	public ScheddarTime(int dayOfWeek, int startHour, int duration){
		this.startHour = startHour;
		this.duration = duration;
		this.dayOfWeek = dayOfWeek;
		//Default values
		this.startMinutes = 0;
		this.isRecurring = true;
		this.day = 0;
		this.month = 0;
		this.year = 0;
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
	
	/**
	 * Returns a ScheddarTime corresponding to the end of this one
	 * We assume a meeting will never be longer than an entire day here, which
	 * is a fair assumption for a business meeting
	 */
	
	public ScheddarTime getEndTime(){
		int newMinutes = this.startMinutes;
		int newHours = this.startHour;
		int newDay = this.day;
		int newDayOfWeek = this.dayOfWeek;
		int newMonth = this.month;
		int newYear = this.year;
		
		newMinutes += this.duration;
		int addedHours = newMinutes / 60;
		newHours += addedHours;
		newMinutes = newMinutes - (60 * addedHours);

		if(newHours < 24){
			return new ScheddarTime(newHours,newMinutes,0,newDayOfWeek,newDay,newMonth,newYear,false);
		}
		
		newHours = newHours - 24; // else we are in a new day, so subtract 24 hours
		newDay += 1;
		newDayOfWeek += 1;
		if(newDayOfWeek==7) newDayOfWeek = 0; // set day of week back to 0 if necessary
		int daysInThisMonth = methods.daysInMonth(newMonth, newYear);
		if(newDay<=daysInThisMonth){
			return new ScheddarTime(newHours,newMinutes,0,newDayOfWeek,newDay,newMonth,newYear,false);
		}
		// Else we are in a new month
		newDay = 1;
		newMonth += 1;
		if(newMonth<12){
			return new ScheddarTime(newHours,newMinutes,0,newDayOfWeek,newDay,newMonth,newYear,false);
		}
		else{
			return new ScheddarTime(newHours,newMinutes,0,newDayOfWeek,newDay,0,newYear+1,false);
		}
	}
	
	/**
	 * Returns the time as a nice String
	 */
	
	public String timeToString(){
		if(this.startMinutes<10){
			String minutes = ("0" + Integer.toString(this.startMinutes));
			return (Integer.toString(this.startHour) + ":" + minutes);
		} else {
			return (Integer.toString(this.startHour) + ":" + Integer.toString(this.startMinutes));
		}
	}
	
	
	/**
	 * @return date and time range in a string
	 */
	public String timeRangeToString() { 
		String startTime = timeToString();
		String endTime = getEndTime().timeToString();
		String date = month + "/" + day + "/" + year;
		return startTime + " - " + endTime + "    " + date;
	}
	
	
	/**
	 * Returns the date as a nice String, e.g.
	 * 
	 * Monday March 4, 2013
	 */
	
	public String dateToString() {
		String monthString = dateFormat.getShortMonths()[month];
		String weekdayString = dateFormat.getWeekdays()[dayOfWeek+1];
		String dateString = monthString + " " + day + ", " + year;
		return weekdayString + " " + dateString;
	}
}
