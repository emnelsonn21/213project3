package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class Controller {

	public static final int MINFULLTIME = 12; 
	public static final int MIN_CREDS = 3;
	public static final int MAX_CREDS =24;
	

	Roster roster = new Roster();
	
	public void makeRoster() {
		Student[] newRoster = new Student[4];
		roster.setStudents(newRoster);
	}
	
	//makeRoster();
	
	@FXML
	private ToggleGroup status;
	
	@FXML
	private RadioButton rbResident;
	
	@FXML
	private RadioButton rbNonResident;
	
	@FXML
	private RadioButton rbTristate;
	
	@FXML
	private RadioButton rbNewYork;
	
	@FXML
	private RadioButton rbConnecticut;
	
	@FXML
	private RadioButton rbInternational;
	
	@FXML
	private RadioButton rbStudyAbroad;
	
	@FXML
	private RadioButton rbCS;
	
	@FXML
	private RadioButton rbEE;
	
	@FXML
	private RadioButton rbME;
	
	@FXML
	private RadioButton rbIT;
	
	@FXML
	private RadioButton rbBA;
	
	@FXML
	private TextField student_name;
	
	@FXML
	private TextArea textArea;
	
	@FXML
	private ToggleGroup major;
	
	@FXML
	private TextField noCredits;
	
	private boolean firstTime = true;
	
	@FXML
	void add(ActionEvent event) {
		if (firstTime) {
			makeRoster();
			firstTime = false;
		}
		boolean creditsValid = checkValidCredits();
		
		
		if (creditsValid) {
			if (rbResident.isSelected()) {
				Resident newResident = makeNewResident();
				addStudent(newResident, false, roster);
				rbResident.setSelected(false);
			} else if (rbNonResident.isSelected()) {
				Nonresident newNonresident = makeNewNonresident();
				addStudent(newNonresident, false, roster);
			} else if (rbTristate.isSelected()) {
				Tristate newTristate = makeNewTristate();
				addStudent(newTristate, false, roster);
		}
		
		} else {
			textArea.appendText("student not successfully added, please try again \n");
		}
		
		student_name.clear();
		noCredits.clear();
	}
	
	@FXML
	void remove(ActionEvent event) {
		
	}
	
	@FXML
	public void resDisableOtherButtons() {
		rbTristate.setDisable(true);
		rbNewYork.setDisable(true);
		rbConnecticut.setDisable(true);
		rbStudyAbroad.setDisable(true);
	}
	
	@FXML
	public void nonResDisableOtherButtons() {
		rbTristate.setDisable(false);
		rbNewYork.setDisable(false);
		rbConnecticut.setDisable(false);
		rbStudyAbroad.setDisable(true);
	}
	
	@FXML
	public void internationalDisableOtherButtons() {
		rbTristate.setDisable(true);
		rbNewYork.setDisable(true);
		rbConnecticut.setDisable(true);
		rbStudyAbroad.setDisable(false);
	}
	
	@FXML
	public void clearStatus() {
		rbResident.setSelected(false);
		rbNonResident.setSelected(false);
		rbTristate.setSelected(false);
		rbInternational.setSelected(false);
		rbNewYork.setSelected(false);
		rbConnecticut.setSelected(false);
		
		
		rbTristate.setDisable(false);
		rbNewYork.setDisable(false);
		rbConnecticut.setDisable(false);
		rbStudyAbroad.setDisable(false);
		
		
	}
	
	@FXML
	public boolean checkValidCredits() {
		int creds = 0;
		try {
			creds = Integer.parseInt(noCredits.getText());
		} catch(NullPointerException e) {
			
		}
		
		if (creds < 3) {
			textArea.appendText("Minimum credits is 3" + "\n");
			return false;
		}
		
		if (creds < 12 && rbInternational.isSelected()) {
			textArea.appendText("International students must be full time" + "\n");
			return false;
		}
		
		if (creds > 24) {
			textArea.appendText("Maximum credits is 24" + "\n");
			return false;
		}
		
		return true;
		
	}
	
	@FXML
	public Resident makeNewResident() {
		Profile profile = new Profile();
		
		String name= null;
		
		try {
			name = student_name.getText();
			profile.setName(name);
		} catch(NullPointerException e) {
			
		}
		
		
		Major major = null;
		try {
			if (rbCS.isSelected()) {
				major = Major.valueOf("CS");
			} else if (rbBA.isSelected()) {
				major = Major.valueOf("BA");
			} else if (rbEE.isSelected()) {
				major = Major.valueOf("EE");
			} else if (rbME.isSelected()) {
				major = Major.valueOf("ME");
			} else if (rbIT.isSelected()) {
				major = Major.valueOf("IT");
			} else {
				textArea.appendText("Must selected major");
			}
		} catch(NullPointerException e) {
			
		}
		
		profile.setMajor(major);
		
		int creds = 0;
		try {
			creds = Integer.parseInt(noCredits.getText());
		} catch(NullPointerException e) {
			
		}
		
		boolean isFullTime = (creds < MINFULLTIME) ? false : true;
		double tuitionDue = 0;
		Resident newResident = new Resident(profile, isFullTime, creds, tuitionDue);
		newResident.setTuitionDue(newResident.getTuitionDue());
		newResident.tuitionDue();
		return newResident;
		
	}
	
	@FXML
	public Nonresident makeNewNonresident() {
		Profile profile = new Profile();
		
		String name= null;
		
		try {
			name = student_name.getText();
			profile.setName(name);
		} catch(NullPointerException e) {
			
		}
		
		
		Major major = null;
		try {
			if (rbCS.isSelected()) {
				major = Major.valueOf("CS");
			} else if (rbBA.isSelected()) {
				major = Major.valueOf("BA");
			} else if (rbEE.isSelected()) {
				major = Major.valueOf("EE");
			} else if (rbME.isSelected()) {
				major = Major.valueOf("ME");
			} else if (rbIT.isSelected()) {
				major = Major.valueOf("IT");
			} else {
				textArea.appendText("Must selected major");
			}
		} catch(NullPointerException e) {
			
		}
		
		profile.setMajor(major);
		
		int creds = 0;
		try {
			creds = Integer.parseInt(noCredits.getText());
		} catch(NullPointerException e) {
			
		}
		
		boolean isFullTime = (creds < MINFULLTIME) ? false : true;
		double tuitionDue = 0;
		Nonresident newNonresident = new Nonresident(profile, isFullTime, creds, tuitionDue);
		newNonresident.setTuitionDue(newNonresident.getTuitionDue());
		newNonresident.tuitionDue();
		return newNonresident;
		
	}
	
	@FXML
	public Tristate makeNewTristate() {
		Profile profile = new Profile();
		
		String name= null;
		
		try {
			name = student_name.getText();
			profile.setName(name);
		} catch(NullPointerException e) {
			
		}
		
		
		Major major = null;
		try {
			if (rbCS.isSelected()) {
				major = Major.valueOf("CS");
			} else if (rbBA.isSelected()) {
				major = Major.valueOf("BA");
			} else if (rbEE.isSelected()) {
				major = Major.valueOf("EE");
			} else if (rbME.isSelected()) {
				major = Major.valueOf("ME");
			} else if (rbIT.isSelected()) {
				major = Major.valueOf("IT");
			} else {
				textArea.appendText("Must selected major");
			}
		} catch(NullPointerException e) {
			
		}
		
		profile.setMajor(major);
		
		int creds = 0;
		try {
			creds = Integer.parseInt(noCredits.getText());
		} catch(NullPointerException e) {
			
		}
		
		boolean isFullTime = (creds < MINFULLTIME) ? false : true;
		double tuitionDue = 0;
		String state = null;
		if (rbNewYork.isSelected()) {
			state = "NY";
		} else if (rbConnecticut.isSelected()) {
			state = "CT";
		} else {
			textArea.appendText("Tristate student must be from NY or CT");
		}
		Tristate newTristate = new Tristate(profile, isFullTime, creds, tuitionDue,state);
		newTristate.setTuitionDue(newTristate.getTuitionDue());
		newTristate.tuitionDue();
		return newTristate;
		
	}
	
	public void addStudent(Student student, boolean worked, Roster roster) {
		System.out.println(student.getProfile());
			try {
				worked = roster.add(student);
			   
				if (worked == true) {
				   textArea.appendText("Student added \n");
			   }
			   else {
				   textArea.appendText("Student already in roster \n");
			   }
				
			} catch(NullPointerException e) {
				 System.out.println("hello");
			 }
	}
	

}
