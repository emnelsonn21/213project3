package application;

import java.util.Calendar;
import java.util.StringTokenizer;


/**
This class defines a Date object, assigning it a month/day/year value
It contains the methods that check if a user-inputted date is valid and that compares two Dates
@author Emily Nelson, Cristofer Gomez-Martinez
*/public class Date implements Comparable<Date> {
	private int year;
	private int month;
	private int day;
	
	/**
	Takes "mm/dd/yyyy" and creates a Date object out of it
	Does nothing if the date is invalid
	@param date the date that is being made a Date object
	@author Emily Nelson
	*/
	public Date(String date) {
		StringTokenizer tokenizer = new StringTokenizer(date, "/");
		if (tokenizer.hasMoreElements()) {
			try {
				month = Integer.valueOf(tokenizer.nextToken());
				day = Integer.valueOf(tokenizer.nextToken());
				year = Integer.valueOf(tokenizer.nextToken().trim());
			}
			catch (NumberFormatException nfe) {
				
		    }
			
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, year);
			calendar.set(Calendar.MONTH, month);
			calendar.set(Calendar.DATE, day);
	    }
	}
	
	/** 
	Creates a Date object with today's date
	@author Emily Nelson
	*/
	public Date() {
		Calendar today = Calendar.getInstance();
		year = today.get(Calendar.YEAR);
		month = today.get(Calendar.MONTH) + 1;
		day = today.get(Calendar.DATE);
	}
	
	/** 
	returns a Date as a String
	@author Emily Nelson
	*/
	public String printAsString(Date date) {
		return month + "/" + day + "/" + year;
		
	}
	
	
	public static final int QUADRENNIAL = 4;
	public static final int CENTENNIAL = 100;
	public static final int QUARTERCENTENNIAL = 400;
	public static final int THIS_YEAR = 2021;
	public static final int JANUARY = 1;
	public static final int DECEMBER = 12;
	public static final int FIRST_DAY = 1;
	public static final int LAST_DAY = 31;
	public static final int THIRTIETH_DAY = 30;
	public static final int FEB_LAST_DAY = 28;
	public static final int FEB_LEAP_LAST_DAY = 29;
	public static final int FEB = 2;
	public static final int APR = 4;
	public static final int JUNE = 6;
	public static final int SEPT = 9;
	public static final int NOV = 11;
	
	/**
	Checks if the date is valid 
	Does nothing if date is not valid
	@return true if date is valid, false otherwise
	@author Emily Nelson
	*/
	public boolean isValid() {
		
		if (day > LAST_DAY) {
			return false;
		}
		
		if (month < JANUARY || month > DECEMBER) {
			return false;
		}
		
		if (year == THIS_YEAR) {
			Date todaysDate = new Date();
			if (month == todaysDate.month) {
				if (day > todaysDate.day) {
					return false;
				}
			}
			if (month > todaysDate.month) {
				return false;
			}
			
		}
		
		if(year < THIS_YEAR || year > THIS_YEAR) {
			return false;
		}
		
		if (month == APR || month == JUNE || month == SEPT || month == NOV) {
			if (day > 30) {
				return false;
			}
		}
		
		
		if (month == FEB) {
			if (day > FEB_LEAP_LAST_DAY) {
				return false;
			}
			
			
			
			if (day == FEB_LEAP_LAST_DAY && year % QUADRENNIAL == 0) { 
				if (year % CENTENNIAL == 0) { 
					if (year % QUARTERCENTENNIAL == 0) { 
						return true;
					}
					return false;
				}
				return false;
			} 
			
			if (day == FEB_LEAP_LAST_DAY && year % QUADRENNIAL != 0) {
				return false;
			}

			
			return true;
			
		}
		
		return true;
	}
	
	@Override
	public int compareTo(Date date) {
		int yeardiff = this.year - date.year;
		int monthdiff = this.month - date.month;
		int daydiff = this.day - date.day;
		
		//return positive if "date" is older
		//return negative if "this" is older
		//example:    9/22/2021 compareTo ( 1/1/2021)
		//"date" is older,  return 1 
		
		if (yeardiff > 0) {
			return 1;
		}
		
		if (yeardiff < 0) {
			return -1;
		}
		
		if (monthdiff > 0) {
			return 1;
		}
		
		if (monthdiff < 0) {
			return -1;
		}
		
		if (daydiff > 0) {
			return 1;
		}
		
		if (daydiff < 0) {
			return -1;
		}
		
		return 0;
	}
		
	
	public int getDay() {
		return this.day;
	}
	
}
