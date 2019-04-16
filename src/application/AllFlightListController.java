package application;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class AllFlightListController extends MainController
{
	@FXML
	private TextArea console;
	
	public void printReservations()
	{
		String textOutput = "";
		textOutput += String.format(FlightSeating.reservationFormat, "Flight #", "Seat", "Legal Name", "ID");
		for(Reservation passenger : FlightSeating.parseReservationsTxt())
		{
			textOutput += String.format(FlightSeating.reservationFormat, passenger.getFlightNumber(), passenger.getSeatNumber(), passenger.getPassengerName(), passenger.getPassengerID());
		}
		
		console.setText(textOutput);
	}
	
	public void goToMainMenu()
	{
		changeScene("mainMenu");
	}
}
