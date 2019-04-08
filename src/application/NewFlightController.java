package application;

import java.io.File;
import java.io.PrintWriter;
import java.time.*;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class NewFlightController 
{
    //initializes Arrays for Data
	public static Flight[] flights = new Flight[10];

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
	
    public static void initialize()
    {
        //I am initializing the flight.txt items so that they take up the same place they were in when the flights.txt gets overwritten.
        flights[0] = new Flight("AA1150", LocalDate.of(2015, 12, 20), LocalTime.of(23, 0), LocalTime.of(2, 0), "FORT WAYNE", "ORLANDO", 70);
        flights[1] = new Flight("AA1230", LocalDate.of(2015, 11, 5), LocalTime.of(11, 30), LocalTime.of(14, 0), "BLACKSBURG", "BOCA RATON", 25);
        flights[2] = new Flight("AA1140", LocalDate.of(2015, 1, 4), LocalTime.of(7, 0), LocalTime.of(11, 0), "SEATTLE", "PHOENIX", 42);
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
    		System.out.println("All guud");
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
    
    /*
    public static void updateFlightsTxt()
    {
        File file = new File("flights.txt");
        try
        {
         PrintWriter output = new PrintWriter(file);
         output.print("Flight#   FDate   DTime   ATime  DepartCity  DestCity  AvailableSeats \n" +
                      "AA1150    20Dec15 23:00   02:00  FORT WAYNE  ORLANDO        70    \n" +
                      "AA1230    5Nov15  11:30   14:00  BLACKSBURG  BOCA RATON     25    \n" +
                      "AA1140    4Jan15  07:00   11:00  SEATTEL	    PHONEIX        42    \n" +
                      ""+flightNum[q1]+"    "+""+flightDate[q2]+"    "+""+departureTime[q3]+"    "+""+arrivalTime[q4]+"    "+""+departCity[q5]+"    "+""+destCity[q6]+"    "+""+availableSeats[q7]);
         output.close();
        }
        catch(Exception ex)
        {
         System.err.println(ex.getMessage());
        }
    }
    
    public static void newFlightFileCreation()
    {
        File file = new File("Your Flight:"+k);
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
        catch(Exception ex)
        {
         System.err.println(ex.getMessage());
        }
    }	
    */
}
