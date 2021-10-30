package application;

/**
This class defines the type Tristate, which is an extenstion of Nonresident, with all of its attributes and methods
@author Emily Nelson, Cristofer Gomez-Martinez
*/
public class Tristate extends Nonresident {

	private String state;
	
	/**
	Constructor for a Tristate student
	Creates a type Nonresident for this student
	@param profile the profile to set 
	@param isFullTime sets the student to being full time or not 
	@param creditHours the credit hours to set
	@param tuitionDue the tuition due to set
	@param state sets the state student is from, NY or CT
	@author Emily Nelson
	*/  
	public Tristate(Profile profile, boolean isFullTime,  int creditHours, double tuitionDue, String state) {
		super(profile, isFullTime, creditHours, tuitionDue);
		this.state = state;
	}
	
	public static final int FULL_TIME_TUITION = 29737;
	public static final int FULL_TIME_FEE = 3268;
	public static final double PART_TIME_FEE = 3268 * 0.8;
	public static final int PRICE_PER_CREDIT_HOUR = 966;
	public static final int MAX_CREDIT_NO_FEE = 16;
	public static final int NY_DISCOUNT = 4000;
	public static final int CT_DISCOUNT = 5000;
	
	/**
	Sets the amount of tuituion due from student
	@author Emily Nelson
	*/
	@Override
	public void tuitionDue() { //why would this return void? shouldn't it return the value of tuition?
		//also how are we supposed to access the information of the student if there's no input??
		
		double tuition;
		
		if(!this.getIsFullTime()) {
			tuition = (PRICE_PER_CREDIT_HOUR * this.getCreditHours()) + PART_TIME_FEE;
		}
		
		else {
			tuition = FULL_TIME_TUITION + FULL_TIME_FEE;
		
		//create instance of student??? how to access credit hours???
		//when calling : exampleResident.tuitionDue();
		
			if (this.getCreditHours() > MAX_CREDIT_NO_FEE) {
				tuition += (PRICE_PER_CREDIT_HOUR * (this.getCreditHours() - MAX_CREDIT_NO_FEE));
			}
		}
		
		if (this.getState().equals("NY")) {
			tuition -= NY_DISCOUNT;
		} 
		else if (this.getState().contentEquals("CT")) {
			tuition -= CT_DISCOUNT;
		}
		
		if (this.getTuitionDue() == 0) {
			this.setTuitionDue(tuition);
			this.originalTuition = tuition;
		}
		
		if (this.getDidFinancialAid() != 0) {
			this.setTuitionDue(this.getTuitionDue());
		}
		
	}
	
	/**
	Returns the student in string form
	@return textual represential of student 
	@author Emily Nelson
	*/ 
	@Override
	public String toString() {
		String str = super.toString();
		return str + " (Tristate): " + state;
	}
	
	/**
	Returns the state the student is from, NY or CT
	@return state abbreviation
	@author Emily Nelson
	*/ 
	public String getState() {
		return state;
	}
	
	/**
	Sets the state the student is from to a new state
	Only states should be NY or CT
	@param newState the new state to set 
	@author Emily Nelson
	*/  
	public void setState(String newState) {
		this.state = newState;
	}


}
