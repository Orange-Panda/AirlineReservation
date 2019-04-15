package application;

import java.io.File;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class DisplayFlightsController extends MainController
{
	@FXML
	private TextArea flightInfo;
	
	public String flightData;
	
	public void readFlightData()
	{
		File file=new File("flights.txt");
		
		try
		{
			Scanner input= new Scanner(file);
			flightData = "";
			while (input.hasNextLine())
			{
				flightData += input.nextLine() + "\n";
			}
			flightInfo.setText(flightData);
			input.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
 	}
	
	public void goToMainMenu()
	{
		changeScene("mainMenu");
	}
}
