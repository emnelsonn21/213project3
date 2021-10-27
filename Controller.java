package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class Controller {

	@FXML
	private ToggleGroup action;
	
	@FXML
	private ToggleGroup status;
	
	@FXML
	private TextField name;
	
	@FXML
	private ToggleGroup major;
	
	@FXML
	private TextField noCredits;
	
	@FXML
	void add(ActionEvent event) {
		
	}
}
