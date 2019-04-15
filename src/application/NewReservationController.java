package application;

import java.time.LocalTime;
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
    
	public void addReservation()
    {
    	try
    	{
    		Reservation newReservation = new Reservation(
    				inputPassengerID.getText(),
    				inputPassengerName.getText(),
    				inputSeatNumber.getText(),
    				inputFlightNumber.getText()
    				);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
	
	public void goToMainMenu()
	{
		changeScene("mainMenu");
	}
}
