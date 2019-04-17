package application;

import javafx.fxml.FXML;
import javafx.scene.control.*;

/**Responsible for controlling the FXML page for making reservations.*/
public class NewReservationController extends MainController
{
    @FXML
    public TextField inputPassengerID;
    @FXML
    public TextField inputPassengerName;
    @FXML
    public TextField inputSeatNumber;
    @FXML
    public TextField inputFlightNumber;
    @FXML
    public TextArea console;
    
    /**Uses the input fields to create a reservation and add it to the resevations.txt*/
	public void addReservation()
    {
    	try
    	{
    		if(inputPassengerName.getText().isEmpty() || inputPassengerID.getText().isEmpty())
    		{
    			console.setText("Please enter your name and ID.");
    			return;
    		}
    		
    		Reservation newReservation = new Reservation( 
    				inputPassengerID.getText(), inputPassengerName.getText(),
    				inputSeatNumber.getText(), inputFlightNumber.getText());
    		
    		boolean seatChanged = FlightSeating.reserveSeat(newReservation.getSeatNumber(), newReservation.getFlightNumber());
    		showFlightReservation();
    		console.setText(console.getText() + (seatChanged ? "\nSeat added, thanks for booking with us!" : "\nSeat is unavailable. Please check if the seat you are trying to reserve is not aready taken."));
    		if(seatChanged)
    		{
    			Reservation.addReservation(newReservation);
    	    	inputSeatNumber.clear();
    	    	inputPassengerID.clear();
    	    	inputPassengerName.clear();
    		}
    	}
    	catch(Exception e)
    	{
			console.setText("Was unable to create your reservation.");
    	}
    }
	
	/**Prints the flight seating chart to the textArea.*/
	public void showFlightReservation()
	{
		console.setText(FlightSeating.getSeatingChart(inputFlightNumber.getText()));
	}
	
    /**Returns the application to the main menu.*/
	public void goToMainMenu()
	{
		changeScene("mainMenu");
	}
}
