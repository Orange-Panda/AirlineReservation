package application;

import javafx.fxml.FXML;
import javafx.scene.control.*;

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
    			FlightSeating.addReservation(newReservation);
    		}
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
	
	public void showFlightReservation()
	{
		console.setText(FlightSeating.getSeatingChart(inputFlightNumber.getText()));
	}
	
	public void goToMainMenu()
	{
		changeScene("mainMenu");
	}
}
