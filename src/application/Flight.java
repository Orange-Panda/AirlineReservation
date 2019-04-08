package application;

import java.time.*;

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
	
	public Flight(String flightNumber, LocalDate flightDate, LocalTime arrivalTime, LocalTime departureTime, String departureCity, String destinationCity, int seatsAvailable)
	{
		setFlightNumber(flightNumber);
		setFlightDate(flightDate);
		setArrivalTime(arrivalTime);
		setDepartureTime(departureTime);
		setDepartureCity(departureCity);
		setDestinationCity(destinationCity);
		setSeatsAvailable(seatsAvailable);
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
