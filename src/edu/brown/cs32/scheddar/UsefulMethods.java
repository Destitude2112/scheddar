package edu.brown.cs32.scheddar;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * This class is a collection of methods that are useful in several other classes
 * @author atutino
 */

public class UsefulMethods {
	
	/**
	 * Returns true if a given year number is a leap year, and
	 * false if it is not
	 * 
	 * @param year the year number
	 * @return true if it is a leap year, false if not
	 */
	
	public static boolean isLeapYear(int year){
		if(year%400==0) return true;
		else if (year%100==0) return false;
		else if (year%4==0) return true;
		else return false;
	}
	
	
	/**
	 * Returns the ScheddarTime referring to 00:00 on sunday before this
	 * time, to find a reference to the start of the week.
	 * 
	 * @param time
	 * @return
	 */
	public static ScheddarTime getPrecedingSunday(ScheddarTime time) {
		int day = time.getDay() - time.getDayOfWeek();
		int month = time.getMonth();
		int year = time.getYear();
		if (day < 1) {
			month--;
			if (month < 1) {
				year--;
				month = 12;
			}
			day = day + daysInMonth(month, year);
		}
		
		return new ScheddarTime(0, 0, 0, 0, day, month, year, false);
	}

	public static ScheddarTime getFirstOfMonth(ScheddarTime time) {
		return new ScheddarTime(0, 0, 0, (time.getDayOfWeek()-time.getDay()+36)%7, 1, time.getMonth(), time.getYear(), false);
	}
	
	
	/**
	 * Returns the day after time.
	 * 
	 * @param time
	 * @return
	 */
	public static ScheddarTime getNextDay(ScheddarTime time) {
		int weekday = (time.getDayOfWeek() + 1) % 7;		
		int day = time.getDay() + 1;
		int month  = time.getMonth();
		int year = time.getYear();
		
		if (day > daysInMonth(month, year)) {
			month++;
			day = 1;
		}
		
		if (month > 12) {
			year++;
			month = 0;
		}
		
		return new ScheddarTime(0, 0, 0, weekday, day, month, year, false);		
	}
	
	
	/**
	 * Create and return a ScheddarTime of the real life current time with a
	 * duration of 0
	 */
	
	public static ScheddarTime getCurrentTime() {
		Date date = new Date();
		String dateString = date.toString();
		String[] splitDate = dateString.split(" ");
		String dayName = splitDate[0];
		DateFormatSymbols dateFormat = new DateFormatSymbols();
		// Convert the day of the week to the correct number
		
		String[] dayNames = dateFormat.getShortWeekdays();
		int dayOfWeek = 0;
		for (int i = 0; i < 7; i++) {
			if (dayName.equals(dayNames[i])) {
				dayOfWeek = i;
				break;
			}
		}
		
		// Convert the month of the year to the correct number
		
		String monthName = splitDate[1];
		String[] monthNames = dateFormat.getShortMonths();
		int month = 1;
		for (int i = 0; i < 12; i++) {
			if (monthName.equals(monthNames[i])) {
				month = i+1;
				break;
			}
		}
		
		int day = Integer.parseInt(splitDate[2]);
		String[] timeSpl = splitDate[3].split(":");
		int hour = Integer.parseInt(timeSpl[0]);
		int minute = Integer.parseInt(timeSpl[1]);
		int year = Integer.parseInt(splitDate[5]);
		
		return new ScheddarTime(hour,minute,0,dayOfWeek,day,month,year,false);
	}
	
	/**
	 * Return the number of days in a given month (0 -> Jan, 1-> Feb, etc.)
	 *
	 *@param month the number of the month
	 *@param year the year (used for deciding if leap year)
	 */
	
	public static int daysInMonth(int month, int year){
		if(month==8 || month==3 || month==5 || month==10){
			return 30;
		}
		else if(month==1){
			if(isLeapYear(year)){
				return 29;
			}
			else{
				return 28;
			}
		}
		else{
			return 31;
		}
	}
	
	
	/**
	 * To be filled in: return a number (0->6) indicating the day of the week
	 * 
	 * @param day the day number
	 * @param month the month number
	 * @param year the year number
	 * @return
	 */
	
	public static int dayOfTheWeek(int day, int month, int year) {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date(year-1900,month,day));
		return c.get(Calendar.DAY_OF_WEEK)-1;
	}
	
	
	//TODO : Since this our application is meant to be used mainly by businesses, I assume
	// here that there are no meetings that run from one day into the next (businesses
	// generally do not hold meetings that go through midnight into the next day)
	// We may want to change the behavior of ScheddarTimes to allow for that at some point,
	// but I feel like it's unnecessary   - atutino
	
	/**
	 * Return true if the two given ScheddarTimes overlap, and false if they do not
	 * @param t1 the first ScheddarTime
	 * @param t2 the second ScheddarTime
	 * @return true if the times overlap, false if they do not
	 */
	
	public static boolean doTimesConflict(ScheddarTime t1, ScheddarTime t2){
		if (t1.getYear() != t2.getYear()) return false;
		if (t1.getMonth() != t2.getMonth()) return false;
		if (t1.getDay() != t2.getDay()) return false;

		int t1StartMinutes = t1.getStartHour() * 60 + t1.getStartMinutes();
		int t1EndMinutes = t1.getStartHour() * 60 + t1.getStartMinutes() + t1.getDuration();
		int t2StartMinutes = t2.getStartHour() * 60 + t2.getStartMinutes();
		int t2EndMinutes = t2.getStartHour() * 60 + t2.getStartMinutes() + t2.getDuration();
		
		if(t1StartMinutes >= t2StartMinutes && t1StartMinutes < t2EndMinutes){
			return true;
		}
		if(t2StartMinutes >= t1StartMinutes && t2StartMinutes < t1EndMinutes){
			return true;
		}
		
		return false;
	}
	
	public String getMonthName(int month) {
		return null;
	}
	
	public String getWeekdayName(int weekday) {
		return null;
	}
	
}
