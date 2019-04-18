package application;
	
import java.io.File;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**Entry point for the application.*/
public class Main extends Application 
{
	private static Scene scene;

	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		Parent root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
		primaryStage.setTitle("Mirman Gragg Airline Reservations");
		setScene(new Scene(root,650,450));
		primaryStage.setScene(getScene());
		primaryStage.show();
		primaryStage.setMinWidth(650);
		primaryStage.setMinHeight(450);
		File file = new File("flights.txt");
        
        if(!file.exists())
        {
        	Flight.resetFlightsText();
        }
	}
	
	public static void main(String[] args) 
	{
		launch(args);
	}
	
	public static Scene getScene() 
	{
		return scene;
	}

	public static void setScene(Scene scene) 
	{
		Main.scene = scene;
	}
}
