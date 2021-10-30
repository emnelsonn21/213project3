package application;

/**
This class defines the atrribute Profile of Student, with all of its attributes and methods
@author Emily Nelson, Cristofer Gomez-Martinez
*/
public class Profile {
	private String name;
	private Major major;
	
	/**
	Returns the profile of student in string form
	@return textual represential of profile
	@author Emily Nelson
	*/ 
	@Override
	public String toString() {
		return name + ":" + major;
	}
	
	/**
	Checks if the names and majors are the same for two profiles
	Does nothing if the object is not a profile
	@param obj the object that is compared to the profile
	@return true if the names and majors are the same, false otherwise.
	@author Emily Nelson
	*/  
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Profile) {
			Profile profile = (Profile) obj;
			return profile.name.equals(this.name) && profile.major.equals(this.major); 
		}
		
		return false;
	}
	
	/**
	Returns the name of the student in profile
	@return name of student
	@author Emily Nelson
	*/
	public String getName() {
		return name;
	}
	
	/**
	Sets the name of the student in profile to a new name
	@param newName the new title to set 
	@author Emily Nelson
	*/
	public void setName(String newName) {
		this.name = newName;
	}
	
	/**
	Returns the major of the student in profile
	@return major of student
	@author Emily Nelson
	*/
	public Major getMajor() {
		return major;
	}
	
	/**
	Sets the major of the student in profile to a new major
	@param newMajor the new major to set 
	@author Emily Nelson
	*/
	public void setMajor(Major newMajor) {
		this.major = newMajor;
	}
}
