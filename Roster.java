package application;

/**
This class defines a Roster and contains all methods to edit a given roster
@author Emily Nelson, Cristofer Gomez-Martinez
*/
public class Roster {
	private Student[] roster;
	private int size;
	
	/**
	Finds the index where the student is located in the Student array
	@param student the student being looked for
	@return the index of the student if found, -1 otherwise
	@author Emily Nelson
	*/
	private int find(Student student) {
		
		for (int index = 0; index < size; index++) {
			if (student.getProfile().equals(roster[index].getProfile())) {
				return index;
			}
		}
		return -1;
	}
	
	/**
	Returns the array of students
        @return Student array
	@author Emily Nelson 
	*/
	public Student[] getStudents() {
		return roster;
	}
	
	/**
	Sets the Student array to a new Student array
	@param newStudents the new Student array to set
	@author Emily Nelson
	*/
	public void setStudents(Student[] newStudents) {
		this.roster = newStudents;
	}
	
	/**
	Increases the capacity of the Student array by 4
	Grown whenever array is full
	@author Emily Nelson
	*/
	private void grow() {
		Student[] grownRoster = new Student[roster.length + 4];
		
		for (int i = 0; i < roster.length; i++) {
			grownRoster[i] = roster[i];
		}
		
		for (int i = roster.length; i < grownRoster.length; i++) {
			grownRoster[i] = null;
		}
		
		roster = grownRoster;
	}
	
	/**
	Checks if a student can be added to the Student array
	Adds student to Student array if student is not found in array
	Does nothing if the student is alrady in the albums array
	@param student the student that is to be added
	@return true if student is not in albums array, false otherwise
	@author Emily Nelson
	*/
	public String[] printByPaymentDate() {
		String[] students = new String[size + 2];
		   if (roster[0] == null) {
			   students[0] = "Student roster is empty! \n";
			   return students;
	    	}
	    else {

	    	students[0] = "* list of students ordered by date **";
     	   
     	   //check how many students have made payments
     	   int studentPayments = 0;
     	  
     	   for(int i = 0; i < size; i++) {
     		   String date = "--/--/--";
     			if(roster[i].getDatePaid().getDay() != 0) {
     				date = roster[i].getDatePaid().printAsString(roster[i].getDatePaid());
     			}
     			
     			if((date.equals("--/--/--")) == false) {
     				studentPayments++;
     			}
     	   }
     	   
     	   System.out.println("student payments is " + studentPayments);
     	   
     	   //make new array with only students that made payments
     	   Student[] sortedRoster = new Student[studentPayments];
     	   int index = 0;
     	   
     	   for(int i = 0; i < size; i++) {
     			if(roster[i].getDatePaid().getDay() != 0) {
     				sortedRoster[index] = roster[i];
     				index++;
     			}
     			
     	   }
     	   
     	   //sort
     	   for (int i = 0; i < studentPayments; i++) {
     		   for (int j = i+1; j < studentPayments; j++) {
    				//if j is older than i, swap i and j
     			   if (sortedRoster[i].getDatePaid().compareTo(sortedRoster[j].getDatePaid()) == 1) {
     				   Student temp = sortedRoster[i];
     				   sortedRoster[i] = sortedRoster[j];
     				   sortedRoster[j] = temp;
    				}
     		   }
    	   }
     	   
     	  for (int i = 1; i < size + 1; i++) {
    		   students[i] = sortedRoster[i-1].toString();
    	   }
    	   

    	   students[size + 1] = "* end of roster **";
    	   
    	   return students;
	    }
	}
	
	/**
	Finds the first empty index/spot in the Student array
	@return the index if an empty spot is found, -1 otherwise
	@author Emily Nelson
	*/
	private int findEmptySpot() {
		for (int i = 0; i < roster.length; i++) {
			if (roster[i] == null) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	Returns the size of Roster
    @return size of Roster
	@author Emily Nelson 
	*/
	public int getSize() {
		return size;
	}
	
	
	/**
	Gets the Student that is being looked for from the Student array
	@param student the student being looked for
	@return the index of the student if found, null otherwise
	@author Emily Nelson
	*/
	public Student giveStudent(Student student) {
		for (int index = 0; index < size; index++) {
			if (student.getProfile().equals(roster[index].getProfile())) {
				return roster[index];
			}
		}
		return null;
	}
	
	/**
	Gets the index where the International student is located in the Student array
	@param student the Internation student being looked for
	@return the index of the International student if found, null otherwise
	@author Emily Nelson
	*/
	public International getInternational(Student student) {
		Student stud = new Student();
		for (int index = 0; index < size; index++) {
			if (student.getProfile().equals(roster[index].getProfile())) {
				stud = roster[index];
				if (stud instanceof International) {
					International inter = (International) stud;
					return inter;
				} else {
					return null;
				}
			}
		}
		
		return null;
	}
	
	/**
	Gets the tuition due for all students the Student array
	@author Emily Nelson
	*/
	public void getAllTuitions() {
		for (int index = 0; index < size; index++) {
			roster[index].tuitionDue();
		}
	}
	

}
