package application;

import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class Controller 
{
	public void exit()
	{
		Platform.exit();
	}
	
	public void changeScene()
	{
	    try 
	    {
	    	Parent newRoot = FXMLLoader.load(getClass().getResource("newFlight.fxml"));
	        
	        Main.getScene().setRoot(newRoot);
	    } 
	    catch (IOException e1) 
	    {
	        e1.printStackTrace();
	    }
	}
}
