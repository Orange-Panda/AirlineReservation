package application;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SpecificFlightListController extends MainController
{
    @FXML
    private TextField inputFlightNumber;

    @FXML
    private TextArea textArea;

    public void displayFlightSeats() 
    {
    	List<Reservation> listedReservations=FlightSeating.parseReservationsTxt();
    	List<Reservation> matchingReservations= new ArrayList<Reservation>();
    	boolean foundFlight=false;
    	for(int i=0; i<listedReservations.size(); i++) 
    	{
    		System.out.printf("Comparing %s to %s.%n", listedReservations.get(i).getFlightNumber(), inputFlightNumber.getText());
    		if(listedReservations.get(i).getFlightNumber().contains(inputFlightNumber.getText())) 
    		{
    			System.out.println("match found");
    			foundFlight=true;
    			matchingReservations.add(listedReservations.get(i));
    		}
    	}
    	String textOutput = "";
		textOutput += foundFlight ? String.format(FlightSeating.reservationFormat, "Flight #", "Seat", "Legal Name", "ID") : "No reservations found for that flight.";
		for(Reservation passenger : matchingReservations)
		{
			textOutput += String.format(FlightSeating.reservationFormat, passenger.getFlightNumber(), passenger.getSeatNumber(), passenger.getPassengerName(), passenger.getPassengerID());
		}
		
		textArea.setText(textOutput);
    }
    
	public void goToMainMenu()
	{
		changeScene("mainMenu");
	}
}
