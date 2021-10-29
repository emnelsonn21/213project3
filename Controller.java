package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
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
	
	
	//FIRST PAGE
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
	private TextField student_name;
	
	@FXML
	private RadioButton rbBA;
	
	@FXML
	private RadioButton rbCS;
	
	@FXML
	private RadioButton rbEE;
	
	@FXML
	private RadioButton rbME;
	
	@FXML
	private RadioButton rbIT;
	
	@FXML
	private TextField noCredits;
	
    @FXML
    private Button btnAdd;
	
	@FXML
	private Button btnRemove;
	    
	@FXML
	private Button btnEdit;
	
	@FXML
	private TextArea textArea;
	
	//SECOND PAGE
	
	@FXML
	private TextField payment_student_name;
	
	@FXML
	private RadioButton rbBA2;
	
	@FXML
	private RadioButton rbCS2;
	
	@FXML
	private RadioButton rbEE2;
	
	@FXML
	private RadioButton rbME2;
	
	@FXML
	private RadioButton rbIT2;
	
	@FXML
	private TextField amountPaid;
	
	@FXML
	private DatePicker datePicker;
	
    @FXML
    private TextField financialAidAmt;
    
    @FXML
    private Button btnPay;
    
    @FXML
    private Button btnSetAmt;

    @FXML
    private TextArea textArea2;
    
    //THIRD PAGE
    
    @FXML
    private MenuItem btnPrint;

    @FXML
    private MenuItem btnPrintDate;

    @FXML
    private MenuItem btnPrintName; 
    
    @FXML
    private Button btnCalculate;
    
    @FXML
    private TextArea textArea3;

 
	
	
	private boolean firstTime = true;
	
	@FXML
	void add(ActionEvent event) {
		if (firstTime) {
			makeRoster();
			firstTime = false;
		}
		boolean didWork = false;
		
		
			if (rbResident.isSelected()) {
				Resident newResident = makeNewResident();
				if (newResident != null) {
					addStudent(newResident, false, roster);
					didWork = true;
				}
			} else if (rbTristate.isSelected()) {
				Tristate newTristate = makeNewTristate();
				if (newTristate != null) {
					addStudent(newTristate, false, roster);
					didWork = true;
				}
			} 
			else if (rbNonResident.isSelected()) {
				if (rbTristate.isSelected()) {
					Tristate newTristate = makeNewTristate();
					if (newTristate != null) {
						addStudent(newTristate, false, roster);
						didWork = true;
					}
				} else {
					Nonresident newNonresident = makeNewNonresident();
					if (newNonresident != null) {
						addStudent(newNonresident, false, roster);
						didWork = true;
					}
				}
			} else if (rbInternational.isSelected()) {
				International newInternational = makeNewInternational();
				if (newInternational != null) {
					addStudent(newInternational,false,roster);
					didWork = true;
				}
			}
		
		if (didWork == true) {
			student_name.clear();
			noCredits.clear();
			clearStatus();
			clearMajor();
		}
	}
	
	@FXML
	void remove(ActionEvent event) {
		Profile profile = makeNewProfileTab1();
		Student student= new Student(profile);
		boolean didWork = roster.remove(student);
		if (didWork) {
			textArea.appendText("Student removed \n");
		} else {
			textArea.appendText("Student not found \n");
		}
		
		if (didWork) {
			student_name.clear();
			noCredits.clear();
			clearStatus();
			clearMajor();
		}
	}
	
	@FXML
	void updateInternational(ActionEvent event) {
		Profile profile = makeNewProfileTab1();
		Student student = new Student(profile);
		International foundInternational = roster.getInternational(student);
		if (foundInternational != null) {
			foundInternational.setIsStudyAbroad(true);
			textArea.appendText("International student updated. \n");
			student_name.clear();
			noCredits.clear();
			clearStatus();
			clearMajor();
			return;
		} else {
			textArea.appendText("International Student not found. \n");
			return;
		}
	}
	
	@FXML
	void pay(ActionEvent event) {
		
		Profile profile = makeNewProfileTab2();
		Double amtPaid = Double.parseDouble(amountPaid.getText());
		if (amtPaid < 0) {
			textArea2.appendText("Payment cannot be negative.");
			return;
		}
		
		String strDate = String.valueOf(datePicker.getValue());
		String date = makeDateFormat(strDate);
		Date newDate = new Date(date);
				
		Student newStudent = new Student(profile);
		Student foundStudent = roster.giveStudent(newStudent);
		if (foundStudent.getTuitionDue() >= amtPaid) {
			foundStudent.setTuitionDue(foundStudent.getTuitionDue() - amtPaid);
		} else {
			textArea2.appendText("Payment exceeds tuition due. \n");
			return;
		}
		
		if (newDate.isValid()) {
			foundStudent.setDatePaid(newDate);
			textArea2.appendText("Tuition updated. \n");
			clearMajor();
			payment_student_name.clear();
			amountPaid.clear();
			return;
		} else {
			textArea2.appendText("invalid date. \n");
			return;
		}

	}
	
	@FXML
	void updateFinancialAid(ActionEvent event) {
		Profile profile = makeNewProfileTab2();
		Student newStudent = new Student(profile);
		Student foundStudent = roster.giveStudent(newStudent);
		if (!(foundStudent instanceof Resident)) {
			textArea2.appendText("Non-resident student does not qualify for financial aid.");
			return;
		}
		if (!foundStudent.getIsFullTime()) {
			textArea2.appendText("Parttime student doesn't quality for financial aid");
			return;
		}
		if (foundStudent.getDidFinancialAid() != 0) {
			textArea2.appendText("Awarded once already.");
			return;
		}
		double finAid = Integer.parseInt(financialAidAmt.getText());
		if (finAid < 0 || finAid > 10000) {
			textArea2.appendText("Financial aid value invalid.");
			return;
		}
		foundStudent.setTuitionDue(foundStudent.getTuitionDue() - finAid);
		foundStudent.setDidFinancialAid(finAid);;
		textArea2.appendText("Financial aid applied.");
		return;
	}
	
	@FXML
	void calculateAllTuitions(ActionEvent e) {
		roster.getAllTuitions();
		textArea3.appendText("Calculation Completed");
	}
	
	@FXML
	void print(ActionEvent e) {
		textArea3.clear();
		String[] students = roster.print();
		
		for (int i = 0; i < roster.getSize() + 2; i++) {
			
			if (students[i] != null) {
				textArea3.appendText(students[i] + "\n");
			}
			
		}
	}
	
	@FXML
	void printByName(ActionEvent e) {
		textArea3.clear();
		String[] students = roster.printByName();
		
		for (int i = 0; i < roster.getSize() + 2; i++) {
			
			if (students[i] != null) {
				textArea3.appendText(students[i] + "\n");
			}
			
		}
	}
	
	@FXML
	void printByDate(ActionEvent e) {
		textArea3.clear();
		String[] students = roster.printByPaymentDate();
		
		for (int i = 0; i < students.length; i++) {
			
			if (students[i] != null) {
				textArea3.appendText(students[i] + "\n");
			}
			
		}
	}
	

	@FXML
	public Resident makeNewResident() {
		Profile profile = new Profile();
		
		String name= null;
		
		try {
			name = student_name.getText();
			profile.setName(name);
		} catch(NullPointerException e) {
			textArea.appendText("please input student name");
			return null;
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
				textArea.appendText("Must select major \n");
				return null;
			}
		} catch(NullPointerException e) {
			
		}
		
		profile.setMajor(major);
		
		int creds = 0;
		try {
			creds = Integer.parseInt(noCredits.getText());
			if (!checkValidCredits(creds)) {
				return null;
			} 
		} catch(NumberFormatException nef) {
			textArea.appendText("please input an integer as the number of credits \n");
			return null;
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
			textArea.appendText("please enter name");
			return null;
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
				return null;
			}
		} catch(NullPointerException e) {
			
		}
		
		profile.setMajor(major);
		
		int creds = 0;
		try {
			creds = Integer.parseInt(noCredits.getText());
			if (!checkValidCredits(creds)) {
				return null;
			} 
		} catch(NumberFormatException nef) {
			textArea.appendText("please input an integer as the number of credits \n");
			return null;
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
			textArea.appendText("please enter name");
			return null;
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
				return null;
			}
		} catch(NullPointerException e) {
			return null;
		}
		
		profile.setMajor(major);
		
		int creds = 0;
		try {
			creds = Integer.parseInt(noCredits.getText());
			if (!checkValidCredits(creds)) {
				return null;
			} 
		} catch(NumberFormatException nef) {
			textArea.appendText("please input an integer as the number of credits \n");
			return null;
		}
		
		boolean isFullTime = (creds < MINFULLTIME) ? false : true;
		double tuitionDue = 0;
		String state = null;
		System.out.println("hello");
		if (rbNewYork.isSelected()) {
			state = "NY";
		} else if (rbConnecticut.isSelected()) {
			state = "CT";
		} else {
			textArea.appendText("Tristate student must be from NY or CT \n");
			return null;
		}
		Tristate newTristate = new Tristate(profile, isFullTime, creds, tuitionDue, state);
		newTristate.setTuitionDue(newTristate.getTuitionDue());
		newTristate.tuitionDue();
		return newTristate;
		
	}
	
	@FXML 
	public International makeNewInternational() {
		Profile profile = new Profile();
		
		String name= null;
		
		try {
			name = student_name.getText();
			profile.setName(name);
		} catch(NullPointerException e) {
			textArea.appendText("please enter name");
			return null;
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
				return null;
			}
		} catch(NullPointerException e) {
			return null;
		}
		
		profile.setMajor(major);
		
		int creds = 0;
		try {
			creds = Integer.parseInt(noCredits.getText());
			if (!checkValidCredits(creds)) {
				return null;
			} 
		} catch(NumberFormatException nef) {
			textArea.appendText("please input an integer as the number of credits \n");
			return null;
		}
		
		boolean isFullTime = (creds < MINFULLTIME) ? false : true;
		double tuitionDue = 0;
		boolean isStudyAbroad = false;
		if (rbStudyAbroad.isSelected()) {
			isStudyAbroad = true;
			
			if (creds > 12) {
				textArea.appendText("International students studying abroad may not enroll in more than 12 credits.");
				return null;
			}
		}
		International newInternational= new International(profile, isFullTime, creds, tuitionDue, isStudyAbroad);
		newInternational.setTuitionDue(newInternational.getTuitionDue());
		newInternational.tuitionDue();
		return newInternational;
	}
	
	public void addStudent(Student student, boolean worked, Roster roster) {
			try {
				worked = roster.add(student);
			   
				if (worked == true) {
				   textArea.appendText("Student added \n");
			   }
			   else {
				   textArea.appendText("Student already in roster \n");
			   }
				
			} catch(NullPointerException e) {
			 }
	}
	
	public Profile makeNewProfileTab1() {
		Profile profile = new Profile();
		String name= null;
		try {
			name = student_name.getText();
			profile.setName(name);
		} catch(NullPointerException e) {
			textArea2.appendText("please input student name");
			return null;
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
				textArea2.appendText("Must select major");
				return null;
			}
		} catch(NullPointerException e) {
			
		}
		
		profile.setMajor(major);
		
		return profile;
	}
	public Profile makeNewProfileTab2() {
		Profile profile = new Profile();
		String name= null;
		try {
			name = payment_student_name.getText();
			profile.setName(name);
		} catch(NullPointerException e) {
			textArea2.appendText("please input student name");
			return null;
		}
		
		Major major = null;
		try {
			if (rbCS2.isSelected()) {
				major = Major.valueOf("CS");
			} else if (rbBA2.isSelected()) {
				major = Major.valueOf("BA");
			} else if (rbEE2.isSelected()) {
				major = Major.valueOf("EE");
			} else if (rbME2.isSelected()) {
				major = Major.valueOf("ME");
			} else if (rbIT2.isSelected()) {
				major = Major.valueOf("IT");
			} else {
				textArea2.appendText("Must select major");
				return null;
			}
		} catch(NullPointerException e) {
			
		}
		
		profile.setMajor(major);
		
		return profile;
	}
	
	public String makeDateFormat(String strDate) {
		String year = null;
		String month = null;
		String day = null;
		
		char charYear[] = new char[4];
		for (int i = 0; i < 4; i++) {
			charYear[i] = strDate.charAt(i);
			year = String.valueOf(charYear);
		}
		char charMonth[] = new char[2];
		for (int i = 5; i < 7; i++) {
			charMonth[i-5] = strDate.charAt(i);
			month = String.valueOf(charMonth);
		}
		char charDay[] = new char[2];
		for (int i = 8; i < 10; i++) {
			charDay[i-8] = strDate.charAt(i);
			day = String.valueOf(charDay);
		}
		
		
		return month + "/" + day + "/" + year;
	}
	
	public boolean checkValidCredits(int credits) {
		
		if (credits < 3) {
			textArea.appendText("Minimum credits is 3" + "\n");
			return false;
		}
		
		if (credits < 12 && rbInternational.isSelected()) {
			textArea.appendText("International students must be full time" + "\n");
			return false;
		}
		
		if (credits > 24) {
			textArea.appendText("Maximum credits is 24" + "\n");
			return false;
		}
		
		return true;
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
	
	public void clearMajor() {
		rbCS.setSelected(false);
		rbIT.setSelected(false);
		rbBA.setSelected(false);
		rbME.setSelected(false);
		rbEE.setSelected(false);
		
		rbCS2.setSelected(false);
		rbIT2.setSelected(false);
		rbBA2.setSelected(false);
		rbME2.setSelected(false);
		rbEE2.setSelected(false);
		
	}
	
}
