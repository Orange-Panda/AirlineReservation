package application;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**Responsible for controlling the FXML page that will print the reservations for a specific flight.*/
public class SpecificFlightListController extends MainController
{
    @FXML
    private TextField inputFlightNumber;
    @FXML
    private TextArea textArea;
    
    /**Gathers the reservations from reservations.txt and prints the reservations that matches the input.*/
    public void displayFlightSeats() 
    {
    	List<Reservation> listedReservations = Reservation.parseReservationsTxt();
    	List<Reservation> matchingReservations = new ArrayList<Reservation>();
    	boolean foundFlight = false;
    	
    	//Filters the reservations to only the ones that match the input.
    	for(int i = 0; i < listedReservations.size(); i++) 
    	{
    		System.out.printf("Comparing %s to %s.%n", listedReservations.get(i).getFlightNumber(), inputFlightNumber.getText());
    		if(listedReservations.get(i).getFlightNumber().contains(inputFlightNumber.getText())) 
    		{
    			System.out.println("match found");
    			foundFlight=true;
    			matchingReservations.add(listedReservations.get(i));
    		}
    	}
    	
    	//Prints the found reservations if they are found.
    	String textOutput = "";
		textOutput += foundFlight ? String.format(Reservation.reservationFormat, "Flight #", "Seat", "Legal Name", "ID") : "No reservations found for that flight.";
		for(Reservation passenger : matchingReservations)
		{
			textOutput += String.format(Reservation.reservationFormat, passenger.getFlightNumber(), passenger.getSeatNumber(), passenger.getPassengerName(), passenger.getPassengerID());
		}
		
		textArea.setText(textOutput);
    }
    
    /**Returns the application to the main menu.*/
	public void goToMainMenu()
	{
		changeScene("mainMenu");
	}
}
