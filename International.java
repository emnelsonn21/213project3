package application;
/**
This class defines the type International, which is an extenstion of Nonresident, with all of its attributes and methods
@author Emily Nelson, Cristofer Gomez-Martinez
*/
public class International extends Nonresident {

	private boolean isStudyAbroad; 
	
	/**
	Constructor for an International student
	Creates a type Nonresident for this student
	@param profile the profile to set 
	@param isFullTime sets the student to being full time or not 
	@param creditHours the credit hours to set
	@param tuitionDue the tuition due to set
	@param isStudyAbroad sets the student to studying abroad or not
	@author Emily Nelson
	*/  
	public International(Profile profile, boolean isFullTime, int creditHours, double tuitionDue, boolean isStudyAbroad) {
		super(profile, isFullTime, creditHours, tuitionDue);
		this.isStudyAbroad = isStudyAbroad;
	}
	
	public static final int FULL_TIME_TUITION = 29737;
	public static final int FULL_TIME_FEE = 3268;
	public static final int ADDITIONAL_FEE = 2650;
	public static final double PART_TIME_FEE = 3268 * 0.8;
	public static final int PRICE_PER_CREDIT_HOUR = 966;
	public static final int MAX_CREDIT_NO_FEE = 16;
	
	/**
	Sets the amount of tuituion due from student
	@author Emily Nelson
	*/
	@Override
	public void tuitionDue() { //why would this return void? shouldn't it return the value of tuition?
		
		double tuition;
		
		if(this.getIsStudyAbroad()) {
			tuition = FULL_TIME_FEE + ADDITIONAL_FEE;
		}
		
		else {
			tuition = FULL_TIME_TUITION + FULL_TIME_FEE + ADDITIONAL_FEE;
			
			if (this.getCreditHours() > MAX_CREDIT_NO_FEE) {
				tuition += (PRICE_PER_CREDIT_HOUR * (this.getCreditHours() - MAX_CREDIT_NO_FEE));
			}
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
		return str + "International";
	}
	
	/**
	Checks if the student is studying abroad
	@return true if student is studying abroad, false otherwise
	@author Emily Nelson
	*/
	public boolean getIsStudyAbroad() {
		return isStudyAbroad;
	}
	
	/**
	Sets the student to either studying abroad or not
	@param isStudyAbroad the new boolean value to set
	@author Emily Nelson
	*/
	public void setIsStudyAbroad(boolean isStudyAbroad) {
		this.isStudyAbroad = isStudyAbroad;
	}


}
