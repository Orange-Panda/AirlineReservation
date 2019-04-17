package application;

import javafx.fxml.FXML;
import javafx.scene.control.*;

/**Responsible for controlling the page that prints out the seating chart for the input flight.*/
public class DisplayPassengersController extends MainController
{
	@FXML
	private TextField inputFlightNumber;
	@FXML
	private TextArea console;
	
	/**Prints the seating chart for the flight that was input into the textField*/
	public void showFlightReservation()
	{
		console.setText(FlightSeating.getSeatingChart(inputFlightNumber.getText()));
	}
	
	/**Returns the program to the main menu*/
	public void goToMainMenu()
	{
		changeScene("mainMenu");
	}
}
