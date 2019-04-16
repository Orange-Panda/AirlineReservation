package application;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SpecificFlightListController extends MainController
{
    @FXML
    private TextField inputFlightNumber;

    @FXML
    private TextArea specificFlightSeats;

    void displayFlightSeats() 
    {
    	
    }
    
	public void goToMainMenu()
	{
		changeScene("mainMenu");
	}
}
