package application;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FlightSeating 
{
	public static final String seatingFormat = "%2s -%1s-%1s-   %1s-%1s-%1s   -%1s-%1s-%n";
	
	//Generates a seating chart file for the argument flight.
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
    
    public static boolean[][] generateRandomSeating(Flight flight) 
    {
    	boolean[][] seating = new boolean[10][7];
    	int seatsToOpen = Math.min(Math.max(flight.getSeatsAvailable(), 0), 70);
		int seatsToClose = Math.abs(70 - seatsToOpen);
    	boolean openingClosedSeats = seatsToOpen <= 35;
		
    	//Reset array to value
		for(int i = 0; i < 10; i++)
		{
			for(int j = 0; j < 7; j++)
			{
				seating[i][j] = openingClosedSeats;
			}
		}
				
		int seatsToModify = openingClosedSeats ? seatsToOpen : seatsToClose;
		
		// Iterate by chance occupy/open seats.
		while(seatsToModify > 0)
		{
	    	//Reset array to value
			for(int i = 0; i < 10; i++)
			{
				for(int j = 0; j < 7; j++)
				{
					if(seating[i][j] == openingClosedSeats && seatsToModify > 0 && Math.random() < 0.4f)
					{
						seating[i][j] = !openingClosedSeats;
						seatsToModify--;
					}
				}
			}
		}
		return seating;
	}
    
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
    
	public static char getSeatChar(boolean isTaken, int value)
    {
    	return isTaken ? 'X' : (char)(64 + value);
    }
	
	
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
