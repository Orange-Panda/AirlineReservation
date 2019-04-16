package application;

import java.util.List;
import java.time.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class NewFlightController extends MainController
{
    
	//public static Flight[] flights = new Flight[10];
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
    
	//Adds a flight to the current file by reading input and then calling updateFlightsTxt.
    public void getFlightFromButton()
    {
    	try
    	{
    		Flight newFlight = new Flight(
    				inputFlightNumber.getText(), 
    				inputFlightDate.getValue(), 
    				LocalTime.of(Integer.parseInt(inputDepartureHour.getText()), Integer.parseInt(inputDepartureMinute.getText())), 
    				LocalTime.of(Integer.parseInt(inputArrivalHour.getText()), Integer.parseInt(inputArrivalMinute.getText())), 
    				inputDepartureCity.getText(), 
    				inputArrivalCity.getText(), 
    				Integer.parseInt(inputNumOfSeats.getText())
    				);
    		addFlight(newFlight);
    	}
    	catch(Exception e)
    	{
    		console.setText("Unable to generate flight. Please confirm input is correct.");
    	}
    }
    
    //Updates the flights text by parsing the current file, if one exists, gathering the current flights then generating a new file with the argument flight added.
    public void addFlight(Flight flightToAdd)
    {
    	List<Flight> flights = Flight.parseFlightsText();
    	flights.add(flightToAdd);
		console.setText(Flight.writeFlightsText(flights));
		FlightSeating.generateFlightSeatingFile(FlightSeating.generateRandomSeating(flightToAdd), flightToAdd.getFlightNumber());
    }
    
    //Resets the current list of flights to the default flights
    public void resetFlightsTxt()
    {
    	console.setText(Flight.writeFlightsText(Flight.defaultFlights));
		for(Flight flight : Flight.defaultFlights)
		{
			FlightSeating.generateFlightSeatingFile(FlightSeating.generateRandomSeating(flight), flight.getFlightNumber());
		}
		FlightSeating.resetReservationsTxt();
    }
	
	public void goToMainMenu()
	{
		changeScene("mainMenu");
	}
}
