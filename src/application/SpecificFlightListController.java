package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SpecificFlightListController extends MainController
{
	    @FXML
	    private TextField inputFlightNumber;

	    @FXML
	    private Button displayFlightSeatsButton;

	    @FXML
	    private TextArea specificFlightSeats;
	    
	    public String flightSeatFile=FlightSeating.getSeatingChart(inputFlightNumber.getText());

	    @FXML
	    void displayFlightSeats(ActionEvent event) 
	    {
	    	
	    }
	public void goToMainMenu()
	{
		changeScene("mainMenu");
	}
}
