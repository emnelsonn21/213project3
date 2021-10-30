package application;

import java.text.DecimalFormat;

/**
This class defines the type Student, with all of its attributes and methods
@author Emily Nelson, Cristofer Gomez-Martinez
*/
public class Student {
	/**
	Does nothing
	@author Emily Nelson
	*/
	public void tuitionDue() {
	}
	
	
	
	private Profile profile;
	private boolean isFullTime;
	private int creditHours;
	private double tuitionDue = 0;
	private Date datePaid = new Date("00/00/0000");
	double originalTuition = 0;
	private double didFinancialAid = 0;
	
	/**
	Sets the profile of student
	@param profile the profile to set 
	@author Emily Nelson
	*/
	public Student(Profile profile) {
		this.profile = profile;
	}
		
	/**
	Constructor for a Student
	@param profile the profile to set 
	@param isFullTime sets the student to being full time or not 
	@param creditHours the credit hours to set
	@param tuitionDue the tuition due to set
	@author Emily Nelson
	*/
	
	public Student(Profile profile, boolean isFullTime, int creditHours, double tuitionDue) {
		this.profile = profile;
		this.isFullTime = isFullTime;
		this.creditHours = creditHours;
		this.tuitionDue = tuitionDue;
	}
	
	public Student() {
		
	}
	
	/**
	Returns the student in string form
	@return textual representation of student 
	@author Cristofer Gomez-Martinez
	*/
	@Override
	public String toString() {
		String date = "--/--/--";
		if(this.datePaid.getDay() != 0) {
			date = datePaid.printAsString(datePaid);
		}
		
		DecimalFormat df = new DecimalFormat("0.00");
		
		double totalPayment = 0;
		if (this.getDidFinancialAid() != 0) {
			totalPayment = originalTuition - tuitionDue - didFinancialAid;
		} else {
			totalPayment = originalTuition - tuitionDue;
		}
		
		return profile.toString() + ":" + String.valueOf(creditHours) + " credit hours" + ":tuition due:" 
		+ df.format(tuitionDue) + ":total payment:" + df.format(totalPayment) + ":last payment date: " + date + ":";
	}
	
	/**
	Returns the profile of the student
	@return profile of student
	@author Emily Nelson
	*/  
	public Profile getProfile() {
		return profile;
	}
	
	/**
	Checks if the student is full-time
	@return true if student is full-time, false otherwise
	@author Emily Nelson
	*/
	public boolean getIsFullTime() {
		return isFullTime;
	}
	
	
	/**
	Sets the student to either full-time or not
	@param isFullTime the new boolean value to set
	@author Emily Nelson
	*/
	public void setIsFullTime(boolean isFullTime) {
		this.isFullTime = isFullTime;
	}
	
	
	
	/**
	Returns the credit hours of student
	@return credit hours of student
	@author Emily Nelson
	*/
	public int getCreditHours() {
		return creditHours;
	}
	
	
	/**
	Returns the tuition due from student
	@return tuition due from student
	@author Emily Nelson
	*/
	public double getTuitionDue() {
		return tuitionDue;
	}
	
	/**
	Sets the tuition due from student to new tuition due
	@param tuitionDue the new tuition due to set 
	@author Emily Nelson
	*/
	public void setTuitionDue(double tuitionDue) {
		this.tuitionDue = tuitionDue;
	}
	
	public double getDidFinancialAid() {
		return this.didFinancialAid;
	}
	
	
	public void setDidFinancialAid(double fa) {
		this.didFinancialAid = fa;
	}
	
	
	public Date getDatePaid() {
		return datePaid;
	}
	
	
	public void setDatePaid(Date date) {
		this.datePaid = date;
	}
	
}
