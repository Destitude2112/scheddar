package edu.brown.cs32.scheddar;

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
	 * Create and return a ScheddarTime of the real life current time with a
	 * duration of 0
	 */
	
	public static ScheddarTime getCurrentTime(){
		Date date = new Date();
		String dateString = date.toString();
		System.out.println(dateString);
		String[] splitDate = dateString.split(" ");
		String dayName = splitDate[0];
		
		// Convert the day of the week to the correct number
		
		int dayOfWeek = -1;
		if(dayName.equals("Sun")) dayOfWeek = 0;
		else if(dayName.equals("Mon")) dayOfWeek = 1;
		else if(dayName.equals("Tue")) dayOfWeek = 2;
		else if(dayName.equals("Wed")) dayOfWeek = 3;
		else if(dayName.equals("Thu")) dayOfWeek = 4;
		else if(dayName.equals("Fri")) dayOfWeek = 5;
		else if(dayName.equals("Sat")) dayOfWeek = 6;
		
		// Convert the month of the year to the correct number
		
		String monthName = splitDate[1];
		int month = -1;
		if(monthName.equals("Jan")) month = 0;
		else if(monthName.equals("Feb")) month = 1;
		else if(monthName.equals("Mar")) month = 2;
		else if(monthName.equals("Apr")) month = 3;
		else if(monthName.equals("May")) month = 4;
		else if(monthName.equals("Jun")) month = 5;
		else if(monthName.equals("Jul")) month = 6;
		else if(monthName.equals("Aug")) month = 7;
		else if(monthName.equals("Sep")) month = 8;
		else if(monthName.equals("Oct")) month = 9;
		else if(monthName.equals("Nov")) month = 10;
		else month = 11;
		
		int day = Integer.parseInt(splitDate[2]);
		String[] timeSpl = splitDate[3].split(":");
		int hour = Integer.parseInt(timeSpl[0]);
		int minute = Integer.parseInt(timeSpl[1]);
		int year = Integer.parseInt(splitDate[5]);
		
		return new ScheddarTime(hour,minute,0,dayOfWeek,day,month,year,false);
	}
	
	/**
	 * Return the number of days in a given month (1 -> Jan, 2-> Feb, etc.)
	 *
	 *@param month the number of the month
	 *@param year the year (used for deciding if leap year)
	 */
	
	public static int daysInMonth(int month,int year){
		if(month==9 || month==4 || month==6 || month==11){
			return 30;
		}
		else if(month==2){
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
	 * To be filled in: return a number (1-7) indicating the day of the week
	 * 
	 * @param day
	 * @param month
	 * @param year
	 * @return
	 */
	public static int dayOfTheWeek(int day, int month, int year) {
		return -1;
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
	
}
