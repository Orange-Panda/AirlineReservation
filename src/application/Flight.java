package application;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.time.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**Data type that contains the data for a booked flight. Also contains methods for parsing files for flight data.*/
public class Flight 
{
    public static final String flightFileFormat = "%8s\t%12s\t%10s\t%8s\t%16s\t%10s\t%6s";
    public static final String[] flightPrefix = new String[] { 
    		"AC1", "NZ1", "D71", "AS1", "AA1", "CP1", "BA1", "CI1", "DL1", "EY1", "BR1", "EK1", "HA1", "JL1", "B61", "MH1", "QR1", "WN1", "UA1", "VS1", "WS1"
			};
    public static final String[] airports = new String[] {
    		"ATLANTA", "LOS ANGELES", "CHICAGO", "DALLAS", "DENVER", "NEW YORK", "SAN FRANCISCO", "LAS VEGAS", "SEATTLE", "CHARLOTTE", "NEWARK",
    		"ORLANDO", "PHOENIX", "MIAMI", "HOUSTON", "BOSTON", "MINNEAPOLIS", "DETROIT", "FORT LAUDERDALE", "PHILADELPHIA", "BALTIMORE", "SALT LAKE CITY",
    		"WASHINGTON D.C.", "SAN DIEGO", "TAMPA", "HONOLULU", "PORTLAND", "NASHVILLE", "AUSTIN", "ST. LOUIS", "SAN JOSE", "OAKLAND", "SACRAMENTO", "KANSAS CITY"
    		};
    
	//Private Flight variables
	private String flightNumber;
	private LocalDate flightDate;
	private LocalTime arrivalTime;
	private LocalTime departureTime;
	private String departureCity;
	private String destinationCity;
	private int seatsAvailable;
	
	/**List of default flights required*/
	public static final List<Flight> defaultFlights = new ArrayList<Flight>(Arrays.asList(
			new Flight("AA1150", LocalDate.of(2015, 12, 20), LocalTime.of(2, 0), LocalTime.of(23, 0), "FORT WAYNE", "ORLANDO", 70),
	        new Flight("AA1230", LocalDate.of(2015, 11, 5), LocalTime.of(14, 0), LocalTime.of(11, 30), "BLACKSBURG", "BOCA RATON", 25),
	        new Flight("AA1140", LocalDate.of(2015, 1, 4), LocalTime.of(11, 0), LocalTime.of(7, 0), "SEATTLE", "PHOENIX", 42)
		));
		
	/**Constructor for flight using native data types.*/
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
	
	/**Constructor for flight using only Strings, will parse strings for the data required. If it fails to do so will initialize with invalid data.*/
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

	/**Reads flights.txt and loads the current entries into a list then returns it. If flights.txt does not exist will initialize a list with the default entries.*/
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
	
    /**Writes to flights.txt, will return a string about the result.*/
    public static String writeFlightsText(List<Flight> flights)
    {
    	File file = new File("flights.txt");
		try
		{
			PrintWriter output = new PrintWriter(file);
			output.print("");
			output.println(String.format(flightFileFormat, "Flight #", "Flight Date", "Departure", "Arrival", "Departure City", "Destination", "Seats"));
			for(Flight flight : flights)
			{
				output.println(String.format(flightFileFormat, 
						flight.getFlightNumber(), flight.getFlightDate().toString(), flight.getDepartureTime().toString(), 
						flight.getArrivalTime().toString(), flight.getDepartureCity(), flight.getDestinationCity(), flight.getSeatsAvailable()));
			}
			output.close();
			return String.format("flights.txt located at %s has been updated!", file.getAbsolutePath());
		}
		catch(Exception fileNotFoundFlights)
		{
			return "Unable to properly save flights.txt";
		}
    }
    
    /**Parses the flights.txt and returns the flight that matches the input string. Returns null if a flight is not found.*/
    public static Flight findFlight(String flightNumber)
    {
    	List<Flight> flights = parseFlightsText();
    	for(Flight flight : flights)
    	{
    		if(flight.getFlightNumber().equalsIgnoreCase(flightNumber))
    		{
    			return flight;
    		}
    	}
    	return null;
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
	
	public static String randomFlightNumber()
	{
		return String.format("%s%03d", flightPrefix[(int)Math.floor(Math.random() * flightPrefix.length)], (int)Math.ceil(Math.random() * 999));
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
	
	public static String randomCity()
	{
		return airports[(int)Math.floor(Math.random() * airports.length)];
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
	
	public int getSeatsAvailable() 
	{
		return seatsAvailable;
	}
	
	public void setSeatsAvailable(int seatsAvailable) 
	{
		this.seatsAvailable = Math.min(Math.max(seatsAvailable, 0), 70);
	}

	public static void resetFlightsText() 
	{
    	Reservation.resetReservationsTxt();
    	Flight.writeFlightsText(Flight.defaultFlights);
		for(Flight flight : Flight.defaultFlights)
		{
			FlightSeating.generateFlightSeatingFile(FlightSeating.generateRandomSeating(flight), flight.getFlightNumber());
		}
    	List<Flight> flights = Flight.parseFlightsText();		//Adds random flights.
		for(int i = 0; i < 16; i++)
		{
			LocalTime randomTime = LocalTime.of((int)Math.floor(Math.random() * 24), (int)Math.floor(Math.random() * 60));
			LocalTime altRandomTime = LocalTime.of((int)Math.floor(Math.random() * 24), (int)Math.floor(Math.random() * 60));
			Flight randomFlight = new Flight(
					Flight.randomFlightNumber(), 
					LocalDate.now(),
					randomTime.compareTo(altRandomTime) > 0 ? randomTime : altRandomTime, 
					randomTime.compareTo(altRandomTime) > 0  ? altRandomTime : randomTime, 
					Flight.randomCity(), 
					Flight.randomCity(), 
					(int)Math.floor(Math.random() * 71));
	    	flights.add(randomFlight);
			FlightSeating.generateFlightSeatingFile(FlightSeating.generateRandomSeating(randomFlight), randomFlight.getFlightNumber());
		}
		Flight.writeFlightsText(flights);
	}
}
