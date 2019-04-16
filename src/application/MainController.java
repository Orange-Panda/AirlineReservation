package application;

import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;

public class MainController 
{
	public void exit()
	{
		Platform.exit();
	}
	
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
	
	public void goToCreateFlight()
	{
	    changeScene("newFlight");
	}
	
	public void goToCreateReservation()
	{
	    changeScene("newReservation");
	}
	
	public void goToDisplayFlights()
	{
		changeScene("displayFlights");
	}
	
	public void goToDisplayPassengers()
	{
		changeScene("displayPassengers");
	}
	
	public void goToDisplayAllFlightsList()
	{
		changeScene("allFlightList");
	}
	
	public void goToDisplaySpecificFlightList()
	{
		changeScene("specificFlightList");
	}
}
