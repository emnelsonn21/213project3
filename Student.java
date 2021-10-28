import java.text.DecimalFormat;

public class Student {
	public void tuitionDue() {
	}
	
	
	
	private Profile profile;
	private boolean isFullTime;
	private int creditHours;
	private double tuitionDue = 0;
	private Date datePaid = new Date();
	double originalTuition = 0;
	private double didFinancialAid = 0;
	
	public Student(Profile profile) {
		this.profile = profile;
	}

	public Student(Profile profile, boolean isFullTime, int creditHours) {
		this.profile = profile;
		this.isFullTime = isFullTime;
		this.creditHours = creditHours;
	}
	
	public Student(Profile profile, boolean isFullTime, int creditHours, double tuitionDue) {
		this.profile = profile;
		this.isFullTime = isFullTime;
		this.creditHours = creditHours;
		this.tuitionDue = tuitionDue;
	}
	
	public Student() {
		
	}
	
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
	
	
	public Profile getProfile() {
		return profile;
	}
	
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	
	public boolean getIsFullTime() {
		return isFullTime;
	}
	
	public void setIsFullTime(boolean isFullTime) {
		this.isFullTime = isFullTime;
	}
	
	public int getCreditHours() {
		return creditHours;
	}
	
	public void setCreditHours(int creditHours) {
		this.creditHours = creditHours;
	}
	
	public double getTuitionDue() {
		return tuitionDue;
	}
	
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
