package application;

import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;

/**Responsible for controlling the main menu scene.*/
public class MainController 
{
	/**Exits the application*/
	public void exit()
	{
		Platform.exit();
	}
	
	/**Sends the application to the scene that matches the input string.*/
	public void changeScene(String sceneToChangeTo)
	{
	    try 
	    {   
	        Main.getScene().setRoot(FXMLLoader.load(getClass().getResource(sceneToChangeTo + ".fxml")));
	    } 
	    catch (IOException e1) 
	    {
	        e1.printStackTrace();
	    }
	}
	
	/**Sends the application to the create a flight scene.*/
	public void goToCreateFlight()
	{
	    changeScene("newFlight");
	}
	
	/**Sends the application to the create a reservation scene.*/
	public void goToCreateReservation()
	{
	    changeScene("newReservation");
	}
	
	/**Sends the application to display all flights scene.*/
	public void goToDisplayFlights()
	{
		changeScene("displayFlights");
	}
	
	/**Sends the application to the show seating chart scene.*/
	public void goToDisplayPassengers()
	{
		changeScene("displayPassengers");
	}

	/**Sends the application to the reservations for all flights scene.*/
	public void goToDisplayAllFlightsList()
	{
		changeScene("allFlightList");
	}
	
	/**Sends the application to the reservations for a specific flight scene.*/
	public void goToDisplaySpecificFlightList()
	{
		changeScene("specificFlightList");
	}
}
