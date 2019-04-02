package application;

public class Reservation 
{
	//These are not integers because they can contain characters
	private String passengerID;
	private String passengerName;
	private String seatNumber;
	private String flightNumber;
	
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
