/**
 * Represents a Ticket purchased by a User to a particular Showing of a Movie
 * A ticket can be for more than one seat. Prices will increase accordingly.
 */
public class Ticket
{
	private String theaterName;
	private String title;
	private String date;
	private String time;
	private double price;
	private String theaterNumber;
	private String seat;

	//Constructor

	public Ticket(String theaterName, String title, String date, String time, double price, String theaterNumber,
				  String seat)
	{
		this.theaterName = theaterName;
		this.title = title;
		this.date = date;
		this.time = time;
		this.price = price;
		this.theaterNumber = theaterNumber;
		this.seat = seat;
	}

	//Getters/Setters

	public String getTheaterName()
	{
		return theaterName;
	}

	public void setTheaterName(String theaterName)
	{
		this.theaterName = theaterName;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getDate()
	{
		return date;
	}

	public void setDate(String date)
	{
		this.date = date;
	}

	public String getTime()
	{
		return time;
	}

	public void setTime(String time)
	{
		this.time = time;
	}

	public double getPrice()
	{
		return price;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}

	public String getTheaterNumber()
	{
		return theaterNumber;
	}

	public void setTheaterNumber(String theaterNumber)
	{
		this.theaterNumber = theaterNumber;
	}

	public String getSeat()
	{
		return seat;
	}

	public void setSeat(String seat)
	{
		this.seat = seat;
	}

	//Methods

	/**
	 * Returns a String representing Ticket
	 * @return Ticket String
	 */
	@Override
	public String toString()
	{
		return "Theater name: " + theaterName + "\nMovie: " + title + "\nDate: " + date + "\nTime: " + time + "\nPrice: $" +
				price + "\nTheater number: " + theaterNumber + "\nSeat: " + seat;
	}

	/**
	 * returns a String representing a ticket, in a format that will make it easier to save to and retrieve from a text file
	 * @return Ticket String
	 */
	public String toSerializableString() {
		return theaterName + "|" + title + "|" + date + "|" + time + "|" + price + "|" + theaterNumber + "|" + seat;
	}

}
