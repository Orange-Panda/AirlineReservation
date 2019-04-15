package application;

import java.nio.file.Paths;
import java.time.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Flight 
{
	//Private Flight variables
	private String flightNumber;
	private LocalDate flightDate;
	private LocalTime arrivalTime;
	private LocalTime departureTime;
	private String departureCity;
	private String destinationCity;
	private int seatsAvailable;
	
	public static final List<Flight> defaultFlights = new ArrayList<Flight>(Arrays.asList(
			new Flight("AA1150", LocalDate.of(2015, 12, 20), LocalTime.of(23, 0), LocalTime.of(2, 0), "FORT WAYNE", "ORLANDO", 70),
	        new Flight("AA1230", LocalDate.of(2015, 11, 5), LocalTime.of(11, 30), LocalTime.of(14, 0), "BLACKSBURG", "BOCA RATON", 25),
	        new Flight("AA1140", LocalDate.of(2015, 1, 4), LocalTime.of(7, 0), LocalTime.of(11, 0), "SEATTLE", "PHOENIX", 42)
		));
		
	
	public Flight(String flightNumber, LocalDate flightDate, LocalTime arrivalTime, LocalTime departureTime, String departureCity, String destinationCity, int seatsAvailable)
	{
		flightNumber = flightNumber.replaceAll(" ", "").toUpperCase();
		departureCity = departureCity.replaceAll(" ", "").toUpperCase();
		destinationCity = destinationCity.replaceAll(" ", "").toUpperCase();
		setFlightNumber(flightNumber);
		setFlightDate(flightDate);
		setArrivalTime(arrivalTime);
		setDepartureTime(departureTime);
		setDepartureCity(departureCity);
		setDestinationCity(destinationCity);
		setSeatsAvailable(seatsAvailable);
	}
	
	public Flight(String flightNumber, String flightDate, String departureTime, String arrivalTime, String departureCity, String destinationCity, String seatsAvailable) 
	{
		seatsAvailable = seatsAvailable.replaceAll(" ", "");
		arrivalTime = arrivalTime.replaceAll(" ", "");
		departureTime = departureTime.replaceAll(" ", "");
		flightDate = flightDate.replaceAll(" ", "");
		flightNumber = flightNumber.replaceAll(" ", "").toUpperCase();
		departureCity = departureCity.replaceAll(" ", "").toUpperCase();
		destinationCity = destinationCity.replaceAll(" ", "").toUpperCase();
		
		//Non parse
		setFlightNumber(flightNumber);
		setDepartureCity(departureCity);
		setDestinationCity(destinationCity);
		
		try
		{
			setSeatsAvailable(Integer.parseInt(seatsAvailable));
			setArrivalTime(LocalTime.parse(arrivalTime));
			setDepartureTime(LocalTime.parse(departureTime));
			setFlightDate(LocalDate.parse(flightDate));
		}
		catch(Exception e)
		{
			System.out.printf("Was unable to parse strings when creating flight%n");
			setArrivalTime(LocalTime.of(0, 0));
			setDepartureTime(LocalTime.of(0,0));
			setFlightDate(LocalDate.of(0,0,0));
			setSeatsAvailable(0);
		}
	}

	//Reads flights.txt and loads the current entries into a list then returns it. If flights.txt does not exist will initialize a list with the default entries.
    public static List<Flight> parseFlightsText()
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
	
	// Get and Set Methods
	public String getFlightNumber() 
	{
		return flightNumber;
	}
	
	public void setFlightNumber(String flightNumber) 
	{
		this.flightNumber = flightNumber;
	}
	
	public LocalDate getFlightDate() 
	{
		return flightDate;
	}
	
	public void setFlightDate(LocalDate flightDate) 
	{
		this.flightDate = flightDate;
	}
	
	public LocalTime getArrivalTime() 
	{
		return arrivalTime;
	}
	
	public void setArrivalTime(LocalTime arrivalTime) 
	{
		this.arrivalTime = arrivalTime;
	}
	
	public LocalTime getDepartureTime() 
	{
		return departureTime;
	}
	
	public void setDepartureTime(LocalTime departureTime) 
	{
		this.departureTime = departureTime;
	}
	
	public String getDepartureCity() 
	{
		return departureCity;
	}
	
	public void setDepartureCity(String departureCity) 
	{
		this.departureCity = departureCity;
	}
	
	public String getDestinationCity() 
	{
		return destinationCity;
	}
	
	public void setDestinationCity(String destinationCity) 
	{
		this.destinationCity = destinationCity;
	}
	
	public int getSeatsAvailable() {
		return seatsAvailable;
	}
	
	public void setSeatsAvailable(int seatsAvailable) 
	{
		this.seatsAvailable = seatsAvailable;
	}
}
