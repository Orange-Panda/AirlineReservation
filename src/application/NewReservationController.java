package application;

import java.time.LocalTime;
import java.util.List;

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
    		List<Flight> flights = Flight.parseFlightsText();
    		
    		Reservation newReservation = new Reservation( 
    				inputPassengerID.getText(), inputPassengerName.getText(),
    				inputSeatNumber.getText(), inputFlightNumber.getText());
    		
    		for(Flight flight : flights)
    		{
    			if(flight.getFlightNumber() == newReservation.getFlightNumber())
    			{
    				if(FlightSeating.reserveSeat(flight, newReservation.getSeatNumber()))
    				{
    					Flight.writeFlightsText(flights);
    				}
    			}
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
