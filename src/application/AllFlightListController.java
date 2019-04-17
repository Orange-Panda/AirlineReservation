package application;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

/**Responsible for controlling the page that prints the reservations for all flights.*/
public class AllFlightListController extends MainController
{
	@FXML
	private TextArea console;
	
	/**Prints the reservations for all flights to the textArea.*/
	public void printReservations()
	{
		String textOutput = "";
		textOutput += String.format(Reservation.reservationFormat, "Flight #", "Seat", "Legal Name", "ID");
		for(Reservation passenger : Reservation.parseReservationsTxt())
		{
			textOutput += String.format(Reservation.reservationFormat, 
					passenger.getFlightNumber(), 
					passenger.getSeatNumber(), 
					passenger.getPassengerName(),
					passenger.getPassengerID());
		}
		
		console.setText(textOutput);
	}
	
	/**Returns the program to the main menu.*/
	public void goToMainMenu()
	{
		changeScene("mainMenu");
	}
}
