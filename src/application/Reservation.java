package application;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**Reservation data type is responsible for containing the data for flight reservations.*/
public class Reservation 
{
	public static final String reservationFormat = "%8s\t%4s\t%16s\t%16s%n";
	
	//These are not integers because they can contain characters
	private String passengerID;
	private String passengerName;
	private String seatNumber;
	private String flightNumber;
	
	public Reservation(String passengerID, String passengerName, String seatNumber, String flightNumber)
	{
		setPassengerID(passengerID);
		setPassengerName(passengerName);
		setSeatNumber(seatNumber);
		setFlightNumber(flightNumber);
	}
	
	/**Adds a reservation to the reservations.txt*/
	public static void addReservation(Reservation reservation)
	{
        File file = new File("reservations.txt");
        
        if(!file.exists())
        {
        	resetReservationsTxt();
        }
        
        try
        {
        	PrintWriter output = new PrintWriter(new FileWriter(file, true));
        	output.print(String.format(reservationFormat, reservation.getFlightNumber(), reservation.getSeatNumber(), reservation.getPassengerName(), reservation.getPassengerID()));
            output.close();
        }
        catch(Exception e)
        {
        	return;
        }
	}
	
	/**Reverts the reservations text to the initial state.*/
	public static void resetReservationsTxt() 
	{
        File file = new File("reservations.txt");
        try
        {
        	PrintWriter output = new PrintWriter(file);
        	output.print(String.format(reservationFormat, "Flight #", "Seat", "Legal Name", "ID"));
            output.close();
        }
        catch(Exception e)
        {
        	return;
        }
	}
	
	/**Parses the passengers reservations text and returns a list of all the reservations.*/
	public static List<Reservation> parseReservationsTxt()
	{
        List<Reservation> reservations = new ArrayList<Reservation>();
        
		try(Scanner input = new Scanner(Paths.get("reservations.txt"), "UTF-8"))
		{
			try
			{
				//Skips the first few lines because their result is already accounted for.
				input.nextLine();
			}
			catch(Exception e)
			{
				System.out.println("Insufficient lines found in flights.txt.");
				return reservations;
			}
			
			//For debug purposes
			int lineNumber = 2;
			
			while (input.hasNextLine())
			{
				try
				{
					//For every line, use the tab character to extrapolate the data of the flight
					String currentLine = input.nextLine();
					String[] currentReserve = currentLine.split("\t");
					for(int i = 0; i < currentReserve.length; i++)
					{
						currentReserve[i] = currentReserve[i].replaceAll(" ", "");
					}
					
					reservations.add(new Reservation(
		    				currentReserve[3], currentReserve[2], 
		    				currentReserve[1], currentReserve[0]));
					lineNumber++;
				}
				catch(Exception e)
				{
					System.out.printf("Invalid formatting in reservations.txt at index #%d - %s%n", lineNumber, e.getMessage());
					lineNumber++;
				}
			}
			input.close();
		}
		catch(Exception e)
		{
			System.out.printf("Was unable to parse reservations.txt: %s%n", e.getMessage());
		}
		
		return reservations;
	}
	
	//Get and Set Methods
	public String getPassengerID() 
	{
		return passengerID;
	}
	
	public void setPassengerID(String passengerID) 
	{
		this.passengerID = passengerID;
	}
	
	public String getPassengerName() 
	{
		return passengerName;
	}
	
	public void setPassengerName(String passengerName) 
	{
		this.passengerName = passengerName;
	}
	
	public String getSeatNumber() 
	{
		return seatNumber;
	}
	
	public void setSeatNumber(String seatNumber) 
	{
		this.seatNumber = seatNumber;
	}
	
	public String getFlightNumber() 
	{
		return flightNumber;
	}
	
	public void setFlightNumber(String flightNumber) 
	{
		this.flightNumber = flightNumber;
	}
}
