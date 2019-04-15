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
    private final String flightFormat = "%8s\t%12s\t%10s\t%8s\t%16s\t%10s\t%6s";
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
	
	private final List<Flight> defaultFlights = new ArrayList<Flight>(Arrays.asList(
		new Flight("AA1150", LocalDate.of(2015, 12, 20), LocalTime.of(23, 0), LocalTime.of(2, 0), "FORT WAYNE", "ORLANDO", 70),
        new Flight("AA1230", LocalDate.of(2015, 11, 5), LocalTime.of(11, 30), LocalTime.of(14, 0), "BLACKSBURG", "BOCA RATON", 25),
        new Flight("AA1140", LocalDate.of(2015, 1, 4), LocalTime.of(7, 0), LocalTime.of(11, 0), "SEATTLE", "PHOENIX", 42)
	));
	
	//Reads flights.txt and loads the current entries into a list then returns it. If flights.txt does not exist will initialize a list with the default entries.
    public List<Flight> parseFlightsText()
    {
        List<Flight> flights = new ArrayList<Flight>();
        for(Flight flight : defaultFlights)
        {
        	flights.add(flight);
        }
        
		try(Scanner input = new Scanner(Paths.get("flights.txt"), "UTF-8"))
		{
			try
			{
				//Skips the first few lines because their result is already accounted for.
				for(int i = 0; i < 4; i++)
				{
					input.nextLine();
				}
			}
			catch(Exception e)
			{
				System.out.println("Insufficient lines found in flights.txt, returning default flights.");
				return flights;
			}
			
			//For debug purposes
			int flightIndexNumber = 4;
			
			while (input.hasNextLine())
			{
				try
				{
					//For every line, use the tab character to extrapolate the data of the flight
					String currentLine = input.nextLine();
					String[] currentFlightData = currentLine.split("\t");
		    		flights.add(new Flight(
		    				currentFlightData[0], currentFlightData[1], currentFlightData[2], 
		    				currentFlightData[3], currentFlightData[4], currentFlightData[5], 
		    				currentFlightData[6]));
					flightIndexNumber++;
				}
				catch(Exception e)
				{
					System.out.printf("Invalid formatting in flights.txt at index #%d - %s%n", flightIndexNumber, e.getMessage());
					flightIndexNumber++;
				}
			}
			input.close();
		}
		catch(Exception e)
		{
			System.out.printf("Was unable to parse flights.txt: %s%n", e.getMessage());
		}
		
		return flights;
    }
    
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
    		newFlightFileCreation();
    	}
    	catch(Exception e)
    	{
    		console.setText(String.format("Unable to create flight: ", e.getMessage()));
    	}
    }
    
    public void updateFlightsTxt(Flight flightToAdd)
    {
    	List<Flight> flights = parseFlightsText();
    	flights.add(flightToAdd);
		
    	File file = new File("flights.txt");
		try
		{
			PrintWriter output = new PrintWriter(file);
			output.print("");
			output.println(String.format(flightFormat, "Flight #", "Flight Date", "Departure", "Arrival", "Departure City", "Destination", "Seats"));
			for(Flight flight : flights)
			{
				output.println(String.format(flightFormat, 
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
    
    public void resetFlightsTxt()
    {
    	File file = new File("flights.txt");
		try
		{
			PrintWriter output = new PrintWriter(file);
			output.print("");
			output.println(String.format(flightFormat, "Flight #", "Flight Date", "Departure", "Arrival", "Departure City", "Destination", "Seats"));
			for(Flight flight : defaultFlights)
			{
				output.println(String.format(flightFormat, 
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
    
    public void newFlightFileCreation()
    {
        File file = new File("Your Flight:"+inputFlightNumber.getText());
        try
        {
        	PrintWriter output = new PrintWriter(file);
        	output.print("1     A B   C D E   F G\n" +
                      	"2     A B   C D E   F G\n" +
                      	"3     A B   C D E   F G\n" +
                      	"4     A B   C D E   F G\n" +
                      	"5     A B   C D E   F G\n" +
                      	"6     A B   C D E   F G\n" +
                      	"7     A B   C D E   F G\n" +
                      	"8     A B   C D E   F G\n" +
                      	"9     A B   C D E   F G\n" +
                      	"10    A B   C D E   F G");
        	output.close();
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
    }
	
	public void goToMainMenu()
	{
		changeScene("mainMenu");
	}
}
