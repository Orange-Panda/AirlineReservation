package application;

import java.util.List;
import java.time.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**Responsible for controlling the FXML page for creating a new flight*/
public class NewFlightController extends MainController
{
   	@FXML
	private TextField inputFlightNumber;
	@FXML
	private DatePicker inputFlightDate;
	@FXML
	private TextField inputDepartureHour;
	@FXML
	private TextField inputDepartureMinute;
	@FXML
	private TextField inputArrivalHour;
	@FXML
	private TextField inputArrivalMinute;
	@FXML
	private TextField inputDepartureCity;
	@FXML
	private TextField inputArrivalCity;
	@FXML
	private TextField inputNumOfSeats;
	@FXML
	private TextField console;	
    
	/**Adds a flight to the current file by reading input and then calling updateFlightsTxt.*/
    public void getFlightFromButton()
    {
    	try
    	{
    		Flight newFlight = new Flight(
    				inputFlightNumber.getText().replaceAll("[^A-Za-z0-9]", ""), 
    				inputFlightDate.getValue(), 
    				LocalTime.of(Integer.parseInt(inputDepartureHour.getText()), Integer.parseInt(inputDepartureMinute.getText())), 
    				LocalTime.of(Integer.parseInt(inputArrivalHour.getText()), Integer.parseInt(inputArrivalMinute.getText())), 
    				inputDepartureCity.getText().replaceAll("[^A-Za-z0-9]", ""), 
    				inputArrivalCity.getText().replaceAll("[^A-Za-z0-9]", ""), 
    				Integer.parseInt(inputNumOfSeats.getText())
    				);
    		for(Flight flight : Flight.parseFlightsText())
    		{
        		if(newFlight.getFlightNumber().equalsIgnoreCase(flight.getFlightNumber()))
        		{
            		console.setText("Flight with that number already exists.");
            		return;
        		}
    		}
    		addFlight(newFlight);
    		inputFlightNumber.clear();
    		inputDepartureHour.clear();
    		inputDepartureMinute.clear();
    		inputArrivalHour.clear();
    		inputArrivalMinute.clear();
    		inputDepartureCity.clear();
    		inputArrivalCity.clear();
    		inputNumOfSeats.clear();
    	}
    	catch(Exception e)
    	{
    		console.setText("Unable to generate flight. Please confirm input is correct.");
    	}
    }
    
    /**Updates the flights text by parsing the current file, if one exists, gathering the current flights then generating a new file with the argument flight added.*/
    public void addFlight(Flight flightToAdd)
    {
    	List<Flight> flights = Flight.parseFlightsText();
    	flights.add(flightToAdd);
		console.setText(Flight.writeFlightsText(flights));
		FlightSeating.generateFlightSeatingFile(FlightSeating.generateRandomSeating(flightToAdd), flightToAdd.getFlightNumber());
    }
    
    /**Resets the current list of flights to the default flights*/
    public void resetFlightsTxt()
    {
    	console.setText("Generating flight data. This might take a moment please wait...");
    	Flight.resetFlightsText();
		console.setText("Reset complete.");
    }
	
    /**Returns the application to the main menu.*/
	public void goToMainMenu()
	{
		changeScene("mainMenu");
	}
}
