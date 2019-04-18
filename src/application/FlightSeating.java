package application;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class FlightSeating 
{
	public static final String seatingFormat = "%2s -%1s-%1s-   %1s-%1s-%1s   -%1s-%1s-%n";
	public static final String[] firstNames = new String[] 
			{
					"James", "John", "Robert", "Michael", "William", "David", "Richard", "Joseph", "Thomas", "Charles",
					"Mary", "Patrick", "Jennifer", "Linda", "Eli", "Barbara", "Susan", "Jessica", "Sarah", "Emily",
					"Jackson", "Martin", "Gary", "Eric", "Luke", "Cobey", "Marco", "Daniel", "Mark", "Leeroy"
			};
	public static final String[] lastNames = new String[] 
			{
					"Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Moore", "White", "Harris",
					"Thompson", "Garcia", "Rodriguez", "Young", "Allen", "Lopez", "Adams", "Edwards", "Cox", "Howard",
					"Mirman", "Gragg", "Ward", "Jenkins", "Gomez", "Dixon", "Grant", "Mills", "Payne", "Ray", "Sipe"
			};
	
	
	/**Generates a seating chart file for the argument flight.*/
    public static void generateFlightSeatingFile(boolean[][] seating, String flightName)
    {
        File file = new File(flightName + ".txt");
		
        try
        {
        	PrintWriter output = new PrintWriter(file);
        	String seatingChart = "";
        	for(int i = 0; i < 10; i++)
        	{
        		seatingChart += String.format(seatingFormat, i + 1, 
        				getSeatChar(seating[i][0], 1), 
        				getSeatChar(seating[i][1], 2), 
        				getSeatChar(seating[i][2], 3), 
        				getSeatChar(seating[i][3], 4), 
        				getSeatChar(seating[i][4], 5), 
        				getSeatChar(seating[i][5], 6), 
        				getSeatChar(seating[i][6], 7)
        				);
        	}
        	output.print(seatingChart);
        	output.close();
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
    }
    
    /**Returns a 2D array of the seating for the flight. If no flight is found will return a default 2D array.*/
    public static boolean[][] parseFlightSeating(String flightName)
    {
    	boolean[][] seating = new boolean[10][7];
    	
    	try(Scanner input = new Scanner(Paths.get(flightName + ".txt"), "UTF-8"))
		{
			int rowNumber = 1;
			
			while (input.hasNextLine() && rowNumber <= 10)
			{
				try
				{
					//For every line, use the tab character to extrapolate the data of the flight
					String currentLine = input.nextLine();
					String[] currentRowData = currentLine.split("-");
		    		for(int i = 0; i < 7; i++)
		    		{
		    			seating[rowNumber - 1][i] = currentRowData[i+1].replace(" ", "").charAt(0) == 'X';
		    		}
		    		rowNumber++;
				}
				catch(Exception e)
				{
					System.out.printf("Invalid formatting in %s at row #%d - %s%n", flightName, rowNumber, e.getMessage());
					rowNumber++;
				}
			}
			input.close();
		}
		catch(Exception e)
		{
			System.out.printf("Was unable to parse %s: %s%n", flightName, e.getMessage());
		}
		
		return seating;
    }
    
    /**Creates a random seating chart based off the amount of seats open for the flight.*/
    public static boolean[][] generateRandomSeating(Flight flight) 
    {
    	boolean[][] seating = new boolean[10][7];
    	int seatsToOpen = Math.min(Math.max(flight.getSeatsAvailable(), 0), 70);
		int seatsToClose = Math.abs(70 - seatsToOpen);
		
    	//Reset array to value
		for(int i = 0; i < 10; i++)
		{
			for(int j = 0; j < 7; j++)
			{
				seating[i][j] = false;
			}
		}
				
		int seatsToModify = seatsToClose;
		int checks = 0;
		// Iterate by chance occupy/open seats.
		while(seatsToModify > 0)
		{
	    	//Reset array to value
			for(int i = 0; i < 10; i++)
			{
				for(int j = 0; j < 7; j++)
				{
					checks++;
					//Goes through each seat and if there are still seats to assign and the current seat is open has a chance to reserve a seat. 
					// Chance is proportional to the number of seats it will be assigning. The more seat to assign the more probable.
					if(seating[i][j] == false && seatsToModify > 0 && Math.random() < (Math.abs(seatsToClose / 70) * 0.5f + 0.5f)) 
					{
						//Remove seat availability
						seating[i][j] = true;
						seatsToModify--;
						//Makes a reservation for a fictional guest
						Reservation.addReservation(new Reservation(
								String.format("U%03d-%04d-%03d", (int)Math.ceil(Math.random() * 999), (int)Math.ceil(Math.random() * 9999), (int)Math.ceil(Math.random() * 999)), 
								generateRandomName(), 
								String.format("%d%c", i + 1, j + 65), 
								flight.getFlightNumber()));
					}
				}
			}
		}
		
		System.out.printf("Interated %d times.%n", checks);
		return seating;
	}
    
    public static String generateRandomName()
    {
    	try 
    	{
        	return firstNames[(int)Math.floor(Math.random() * firstNames.length)] + " " + lastNames[(int)Math.floor(Math.random() * lastNames.length)];
    	}
    	catch(Exception e)
    	{
    		return "Luke Mirman";
    	}
    }
    
    /**Returns a long String for the flight with the name input. If no flight is found will return a string stating a flight was not found.*/
    public static String getSeatingChart(String flightName)
    {
        File file = new File(flightName + ".txt");

        if(file.exists() || file.isDirectory()) 
		{ 
		    return getSeatingChart(parseFlightSeating(flightName));
		}
        else
        {
        	return String.format("Flight %s not found.", flightName);
        }
    }
    
    /**Returns a String as a seating chart for the 2D boolean array.*/
    public static String getSeatingChart(boolean[][] seating)
    {
    	String seatingChart = "";
    	for(int i = 0; i < 10; i++)
    	{
    		seatingChart += String.format(seatingFormat, i + 1, 
    				getSeatChar(seating[i][0], 1), 
    				getSeatChar(seating[i][1], 2), 
    				getSeatChar(seating[i][2], 3), 
    				getSeatChar(seating[i][3], 4), 
    				getSeatChar(seating[i][4], 5), 
    				getSeatChar(seating[i][5], 6), 
    				getSeatChar(seating[i][6], 7)
    				);
    	}
    	return seatingChart;
    }
    
    /**Returns a char for the seat index or an X char if seat is taken.*/
	public static char getSeatChar(boolean isTaken, int value)
    {
    	return isTaken ? 'X' : (char)(64 + value);
    }
	
	/**Tries to reserve a seat at the input seat number for the input flight.*/
	public static boolean reserveSeat(String seatNumber, String flightNumber) 
	{
		try
		{
    		List<Flight> flights = Flight.parseFlightsText();
			
			for(int f = 0; f < flights.size(); f++)
    		{
    			System.out.printf("Checking %s to %s%n", flights.get(f).getFlightNumber(), flightNumber);
				
				if(flights.get(f).getFlightNumber().equals(flightNumber))
    			{
	    			System.out.printf("Confirmed %s to %s%n", flights.get(f).getFlightNumber(), flightNumber);
					boolean[][] seating = parseFlightSeating(flights.get(f).getFlightNumber());
    				int i = (Integer.parseInt(seatNumber.replaceAll("\\D+","")) - 1);
    				int j = seatNumber.replaceAll("\\d+","").toUpperCase().charAt(0) - 65;;
    				
    				System.out.printf("Input [%d][%d]%n", i, j);
    				
    				if(seating[i][j] == false)
    				{
    					System.out.println("Set true");
    					seating[i][j] = true;
    					FlightSeating.generateFlightSeatingFile(seating, flights.get(f).getFlightNumber());;
    					flights.get(f).setSeatsAvailable(flights.get(f).getSeatsAvailable() - 1);
    					Flight.writeFlightsText(flights);
    					return true;
    				}
    				else
    				{
    					System.out.println("Set false");
    					return false;
    				}
    			}
    		}
			
			return false;
		}
		catch(Exception e)
		{
			System.out.println("Bad input");
			return false;
		}
	}
}