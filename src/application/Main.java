package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application 
{
	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		Parent root = FXMLLoader.load(getClass().getResource("layout.fxml"));
		primaryStage.setTitle("Mirman Gragg Airline Reservations");
		primaryStage.setScene(new Scene(root,650,450));
		primaryStage.show();
		primaryStage.setMinWidth(650);
		primaryStage.setMinHeight(450);
	}
	
	public static void main(String[] args) 
	{
		launch(args);
	}
}
