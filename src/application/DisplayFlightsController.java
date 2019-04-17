package application;

import java.io.File;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

/**Responsible for controlling the page that will display all the current flights.*/
public class DisplayFlightsController extends MainController
{
	@FXML
	private TextArea flightInfo;
		
	/**Reads flight.txt and prints the output to the textArea.*/
	public void readFlightData()
	{
		try
		{
			File file=new File("flights.txt");
			String flightData = "";
			Scanner input= new Scanner(file);
			while (input.hasNextLine())
			{
				flightData += input.nextLine() + "\n";
			}
			flightInfo.setText(flightData);
			input.close();
		}
		catch(Exception ex)
		{
			flightInfo.setText("Was unable to read flights.txt. Please ensure that flights have been created.");
		}
 	}
	
	/**Returns the application to the main menu.*/
	public void goToMainMenu()
	{
		changeScene("mainMenu");
	}
}
