
/**
 * Represents a showing of a Movie at a MovieTheater
 */
public class Showing 
{
	//Variables
	/**
	 * The Date of this Showing.
	 * Format: DD/MM/YY
	 */
	private String date; 
	/**
	 * The time of this Showing.
	 * Format: HH:MM
	 */
	private String time; 
	/**
	 * The Theater where this Showing is taking place.
	 */
	private Theater theater;
	
	//Perhaps here is where we can put the list of users who have bought tickets
	
	private double price;
	
	private String movieTitle;
	
	private String[] seats;
	
	//Constructor
	/**
	 * Creates a new Showing with default information.
	 */
	public Showing() 
	{
		this.date = "00/00/00";
		this.time = "00:00";
		this.theater = new Theater();
	}
	
	/**
	 * Creates a new Showing with given date, time, and theater.
	 * @param dateIn is the date of this showing.
	 * @param timeIn is the time of this showing.
	 * @param theaterIn is the Theater where this Showing is taking place.
	 */
	public Showing(String dateIn, String timeIn, Theater theaterIn, String movieTitle, String[] seats, double price) 
	{
		this.date = dateIn;
		this.time = timeIn;
		this.theater = theaterIn;
		this.movieTitle = movieTitle;
		this.seats = seats;
		this.price = price;
	}

	//Getters/Setters
	
	public String[] getSeats() 
	{
		return seats;
	}
	
	public void setSeats(String[] seats) 
	{
		this.seats = seats;
	}
	
	public String getMovieTitle() 
	{
		return movieTitle;
	}
	
	public void setMovieTitle(String movieTitle) 
	{
		this.movieTitle = movieTitle;
	}
	
	public double getPrice() 
	{
		return price;
	}
	public void setPrice(double price) 
	{
		this.price = price;
	}
	
	//Date
	/**
	 * Gets the Date of this Showing.
	 * @return The Date of this Showing.
	 */
	public String getDate() 
	{
		return this.date;
	}
	/**
	 * Changes the Date of this Showing.
	 * @param dateIn is the new Date of this Showing. Format: DD/MM/YY
	 */
	public void setDate(String dateIn) 
	{
		this.date = dateIn;
	}

	//Time
	/**
	 * Gets the Time of this Showing.
	 * @return The Time of this Showing.
	 */
	public String getTime() 
	{
		return this.time;
	}
	/**
	 * Changes the Time of this Showing.
	 * @param timeIn is the new Time of this Showing. Format: HH:MM
	 */
	public void setTime(String timeIn) 
	{
		this.time = timeIn;
	}

	//Theater
	/**
	 * Gets the Theater where this Showing is taking place.
	 * @return The Theater of this Showing.
	 */
	public Theater getTheater() 
	{
		return this.theater;
	}
	/**
	 * Changes the Theater where this Showing is taking place.
	 * @param theaterIn is the new Theater of this Showing.
	 */
	public void setTheater(Theater theaterIn) 
	{
		this.theater = theaterIn;
	}
	
	//Methods 
	
	/**
	 * Checks the array of Seats to see how many are still available
	 * Also sets filled to true if all seats are filled
	 * @return The number of available seats.
	 */
	public int availableSeats() 
	{
		if(theater.getSeats().length == 0)
		{
			theater.setFilled(true);
		}
		else
		{
			theater.setFilled(false);
		}
		
		return theater.getSeats().length;
		
	}
	
	public String printAvailableSeats()
	{
		String output = "";
		for (String seat : seats) 
		{
			if (!(seat.equals("0"))) 
			{
				output += seat + " ";
			}
		}
		return output;
	}
	
	public void removeSeat(String seat, String fileName) 
	{
		for (int i = 0; i < seats.length; i++) 
		{
			if (seat.equals(seats[i])) 
			{
				seats[i] = "0";
			}
			theater.storeShowings(fileName);
		}
	}
	
	/**
	 * Returns a String representing a Showing
	 * @return Showing String
	 */
	@Override
	public String toString() 
	{
		return date + ", at " + time + ", in " + theater;
	}
	
}
