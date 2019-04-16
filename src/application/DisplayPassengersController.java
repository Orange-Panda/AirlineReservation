package application;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class DisplayPassengersController extends MainController
{
	@FXML
	private TextField inputFlightNumber;
	@FXML
	private TextArea console;
	
	public void showFlightReservation()
	{
		console.setText(FlightSeating.getSeatingChart(inputFlightNumber.getText()));
	}
	
	public void goToMainMenu()
	{
		changeScene("mainMenu");
	}
}
