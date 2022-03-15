import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Represents a single Theater, or projector room, within a MovieTheater.
 * A Theater has an Identifying Number and a set number of seats. 
 * Different Theaters can have a different number of seats.
 */
public class Theater 
{
	//Variables
	/**
	 * The Identifying number of this Theater.
	 */
	private String theaterNumber;
	/**
	 * The seats within this Theater. 
	 */
	private String[] seats; 
	/**
	 * List of all Showings in this Theater
	 */
	private ArrayList<Showing> showings; 
	
	//Constructors
	/**
	 * Creates a Theater with default information.
	 */
	public Theater() 
	{
		this.theaterNumber = "0";
		this.seats = new String[10]; //**TODO**
		this.showings = new ArrayList<>();
	}
	/**
	 * Creates a Theater with given ID Number and Seats.
	 * @param theaterNumberIn is the Identifying Number for this Theater
	 * @param seatsIn is the Seats for this Theater.
	 */
	public Theater(String theaterNumberIn) 
	{
		this.theaterNumber = theaterNumberIn;
		this.seats = new String[]{"A1", "A2", "B1", "B2", "C1", "C2", "D1", "D2", "E1", "E2"};
		this.showings = new ArrayList<Showing>();
	}

	
	//Getters/Setters
	
	//Theater Number
	/**
	 * Gets the Identifying Number for this Theater.
	 * @return the theaterNumber for this Theater.
	 */
	public String getTheaterNumber() 
	{
		return this.theaterNumber;
	}
	/**
	 * Changes the Identifying Number for this Theater.
	 * @param theaterNumberIn is the new theaterNumber for this Theater.
	 */
	public void setTheaterNumber(String theaterNumberIn) 
	{
		this.theaterNumber = theaterNumberIn;
	}

	//Seats
	/**
	 * Gets the Seats for this Theater.
	 * @return the seats for this Theater.
	 */
	public String[] getSeats() 
	{
		return this.seats;
	}
	/**
	 * Changes the Seats for this Theater
	 * @param seatsIn are the new Seats for this Theater.
	 */
	public void setSeats(String[] seatsIn) 
	{
		this.seats = seatsIn;
	}
	
	//Showings
	/**
	 * Gets the list of Showings for this Theater
	 * @return the showings
	 */
	public ArrayList<Showing> getShowings() 
	{
		return this.showings;
	}
	/**
	 * Changes the Showings of this Theater
	 * @param showings the showings to set
	 */
	public void setShowings(ArrayList<Showing> showingsIn) 
	{
		this.showings = showingsIn;
	}
	/**
	 * Adds the given Showing to the list of Showings
	 * @param showingIn Showing to be added
	 */
	public void addShowing(Showing showingIn, String fileName)
	{
		this.showings.add(showingIn);
		this.storeShowings(fileName);
	}
	/**
	 * Removes the given Showing to the list of Showings
	 * @param showingIn Showing to be removed
	 */
	public void removeShowing(Showing showingOut)
	{
		this.showings.remove(showingOut);
	}
	
	
	
	//Methods
	
	public void populateShowings(String fileName) 
	{
		try 
		{
			Scanner in = new Scanner(new File(fileName));
			while (in.hasNextLine()) 
			{
				String movieTitle = in.nextLine();
				String date = in.nextLine();
				String time = in.nextLine();
				String priceString = in.nextLine();
				double price = Double.valueOf(priceString);
				String seatsLine = in.nextLine();
				Scanner seatsScanner = new Scanner(seatsLine);
				String[] seats = new String[10];
				int index = 0;
				while (seatsScanner.hasNext()) 
				{
					seats[index] = seatsScanner.next();
					index++;
				}
				showings.add(new Showing(date, time, this, movieTitle, seats, price));
			}
		}
		
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void storeShowings(String fileName) 
	{
		try 
		{
			PrintWriter out = new PrintWriter(fileName);
			for (Showing showing : showings) 
			{
				out.println(showing.getMovieTitle());
				out.println(showing.getDate());
				out.println(showing.getTime());
				out.println(showing.getPrice());
				String output = "";
				for (String seat : showing.getSeats()) 
				{
					output += seat + " ";
				}
				out.println(output);
			}
			out.close();
		}
		
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns a String representing a Theater
	 * @return Theater String
	 */
	@Override
	public String toString() {
		return "Theater " + theaterNumber;
	}
}
