package application;

import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

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
	    	Parent newRoot = FXMLLoader.load(getClass().getResource(sceneToChangeTo + ".fxml"));
	        
	        Main.getScene().setRoot(newRoot);
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
}
