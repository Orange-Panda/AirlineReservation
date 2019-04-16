package application;

import java.io.File;
import java.io.PrintWriter;

public class FlightSeating 
{
	//Generates a seating chart file for the argument string.
    public static void generateFlightSeatingFile(Flight flight)
    {
        File file = new File(flight.getFlightNumber() + ".txt");
        
		/*
        if(file.exists() || file.isDirectory()) 
		{ 
		    return;
		}
        */
		boolean[][] seating = new boolean[10][7];
		
		seating = generateRandomSeating(seating, flight);
		
        try
        {
        	PrintWriter output = new PrintWriter(file);
        	output.print("");
        	for(int i = 0; i < 10; i++)
        	{
        		output.println(String.format("%2s |%1s|%1s|  |%1s|%1s|%1s|  |%1s|%1s|", i + 1, 
        				getSeatChar(seating[i][0], 1), 
        				getSeatChar(seating[i][1], 2), 
        				getSeatChar(seating[i][2], 3), 
        				getSeatChar(seating[i][3], 4), 
        				getSeatChar(seating[i][4], 5), 
        				getSeatChar(seating[i][5], 6), 
        				getSeatChar(seating[i][6], 7)
        				));
        	}
        	output.close();
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
    }
    
    private static boolean[][] generateRandomSeating(boolean[][] seating, Flight flight) 
    {
		System.out.println("Starting for flight " + flight.getFlightNumber());
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
		
		System.out.printf("%s%n", "Reset complete");
		
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
						System.out.printf("Value modified, %d remaining%n", seatsToModify);
					}
				}
			}
		}
		return seating;
	}

	public static char getSeatChar(boolean isTaken, int value)
    {
    	return isTaken ? 'X' : (char)(64 + value);
    }
    
    
}
