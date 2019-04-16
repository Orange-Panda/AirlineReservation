package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.time.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;

public class NewFlightController extends MainController
{
    public static final String flightFileFormat = "%8s\t%12s\t%10s\t%8s\t%16s\t%10s\t%6s";
    
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
    public void addFlight()
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
    		updateFlightsTxt(newFlight);
    	}
    	catch(Exception e)
    	{
    		console.setText(String.format("Unable to create flight: ", e.getMessage()));
    	}
    }
    
    //Updates the flights text by parsing the current file, if one exists, gathering the current flights then generating a new file with the argument flight added.
    public void updateFlightsTxt(Flight flightToAdd)
    {
    	List<Flight> flights = Flight.parseFlightsText();
    	flights.add(flightToAdd);
		
    	File file = new File("flights.txt");
		try
		{
			PrintWriter output = new PrintWriter(file);
			output.print("");
			output.println(String.format(flightFileFormat, "Flight #", "Flight Date", "Departure", "Arrival", "Departure City", "Destination", "Seats"));
			for(Flight flight : flights)
			{
				FlightSeating.generateFlightSeatingFile(flight);
				output.println(String.format(flightFileFormat, 
						flight.getFlightNumber(), flight.getFlightDate().toString(), flight.getDepartureTime().toString(), 
						flight.getArrivalTime().toString(), flight.getDepartureCity(), flight.getDestinationCity(), flight.getSeatsAvailable()));
			}
			output.close();
			System.out.printf("Flight %s has been added to the flights.txt located at %s%n", inputFlightNumber.getText(), file.getAbsolutePath());
			console.setText(String.format("Flight %s has been added to the flights.txt located at %s", inputFlightNumber.getText(), file.getAbsolutePath()));
		}
		catch(Exception fileNotFoundFlights)
		{
			fileNotFoundFlights.printStackTrace();
		}
    }
    
    //Resets the current list of flights to the default flights
    public void resetFlightsTxt()
    {
    	File file = new File("flights.txt");
		try
		{
			PrintWriter output = new PrintWriter(file);
			output.print("");
			output.println(String.format(flightFileFormat, "Flight #", "Flight Date", "Departure", "Arrival", "Departure City", "Destination", "Seats"));
			for(Flight flight : Flight.defaultFlights)
			{
				FlightSeating.generateFlightSeatingFile(flight);
				output.println(String.format(flightFileFormat, 
						flight.getFlightNumber(), flight.getFlightDate().toString(), flight.getDepartureTime().toString(), 
						flight.getArrivalTime().toString(), flight.getDepartureCity(), flight.getDestinationCity(), flight.getSeatsAvailable()));
			}
			output.close();
			System.out.printf("Reset flights.txt located at %s%n", file.getAbsolutePath());
			console.setText("Reset flights.txt located at " + file.getAbsolutePath());
		}
		catch(Exception fileNotFoundFlights)
		{
			fileNotFoundFlights.printStackTrace();
		}
    }
	
	public void goToMainMenu()
	{
		changeScene("mainMenu");
	}
}
