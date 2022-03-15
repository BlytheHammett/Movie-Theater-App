import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * Represents a Movie to be played at a MovieTheater. 
 * A Movie can have multiple showings.
 *
 */
public class Movie 
{
	//Variables
	/**
	 * The Title of this Movie.
	 */
	private String title;
	/**
	 * The Description of this Movie.
	 */
	private String description;
	/**
	 * The Cast of this Movie.
	 */
	private String cast;
	/**
	 * The Genre of this Movie.
	 */
	private String genre;
	/**
	 * The Price of this Movie.
	 */
	private double price;
	/**
	 * The Released Status of this Movie.
	 */
	private boolean released;
	/**
	 * The List of Showings for this Movie.
	 */
	private ArrayList<Showing> showings;
	/**
	 * The List of Ratings for this Movie.
	 */
	private ArrayList<Double> ratings;
	/**
	 * The List of users who bought a ticket to this Movie.
	 */
	private ArrayList<User> users; //TODO: Perhaps there is a better place for this?

	private String status;

	
	//Constructors
	/**
	   * Creates a new Movie with the given title, description, cast, genre, price, and released status.
	   * @param titleIn is the Title of the Movie.
	   * @param descriptionIn is the description of the Movie.
	   * @param castIn is the cast of the Movie.
	   * @param genreIn is the genre of the Movie.
	   * @param priceIn is the price of the Movie.
	   * @param releasedIn is the released status of the Movie.
	   */
	public Movie(String genre, String title, String status, String description, String cast, ArrayList<Double> ratings)
	{
		this.genre = genre;
		this.title = title;
		this.status = status;
		if (status.equals("Currently running")) 
		{this.released = true;}
		else 
		{this.released = false;}
		this.description = description;
		this.cast = cast;
		this.ratings = ratings;
		this.users = new ArrayList<User>();
	}
	
	public static String status(String currentDate, String releaseDate) throws ParseException // string format "yyyy-MM-dd"
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date current = sdf.parse(currentDate);
		Date release = sdf.parse(releaseDate);
		String status = "";
		if (release.after(current)) 
		{status = "Upcoming";}
		else if (release.before(current)) 
		{status = "Currently running";}
		
		return status;
	}

	//Getters/Setters
	
	public String getStatus() 
	{
		return status;
	}
	
	public void setStatus(String status) 
	{
		this.status = status;
	}

	//Getters/Setters
	

	//Title
	/**
	   * Gets the title of the Movie.
	   * @return this Movie's title.
	   */
	public String getTitle() 
	{
		return this.title;
	}
	/**
	   * Changes the title of the Movie.
	   * @param titleIn is the new title of the Movie.
	   */
	public void setTitle(String titleIn) 
	{
		this.title = titleIn;
	}
	

	// Description
	/**
	   * Gets the description of the Movie.
	   * @return this Movie's description.
	   */
	public String getDescription() 
	{
		return this.description;
	}
	/**
	   * Changes the description of the Movie.
	   * @param descriptionIn is the new description of the Movie.
	   */
	public void setDescription(String descriptionIn) 
	{
		this.description = descriptionIn;
	}
	

	// Cast
	/**
	   * Gets the cast of the Movie.
	   * @return this Movie's description.
	   */
	public String getCast() 
	{
		return this.cast;
	}
	/**
	   * Changes the cast of the Movie.
	   * @param castIn is the new cast of the Movie.
	   */
	public void setCast(String castIn) 
	{
		this.cast = castIn;
	}
	

	// Genre
	/**
	   * Gets the genre of the Movie.
	   * @return this Movie's genre.
	   */
	public String getGenre() 
	{
		return this.genre;
	}
	/**
	   * Changes the genre of the Movie.
	   * @param genreIn is the new genre of the Movie.
	   */
	public void setGenre(String genreIn) 
	{
		this.genre = genreIn;
	}
	
	
	// Price
	/**
	   * Gets the price of the Movie.
	   * @return this Movie's price.
	   */
	public double getPrice() 
	{
		return this.price;
	}
	/**
	   * Changes the price of the Movie.
	   * @param priceIn is the new price of the Movie.
	   */
	public void setPrice(double priceIn) 
	{
		this.price = priceIn;
	}
	

	// Released
	/**
	   * Gets the released status of the Movie.
	   * @return this Movie's status.
	   */
	public boolean isReleased() 
	{
		return this.released;
	}
	/**
	   * Changes the released status of the Movie.
	   * @param releasedIn is the new status of the Movie.
	   */
	public void setReleased(boolean releasedIn) 
	{
		this.released = releasedIn;
	}
	

	// Showings
	/**
	   * Gets the ArrayList of showings of the Movie.
	   * @return this Movie's showings.
	   */
	public ArrayList<Showing> getShowings() 
	{
		return this.showings;
	}
	/**
	   * Changes the showings of the Movie.
	   * @param showingsIn are the new showings of the Movie.
	   */
	public void setShowings(ArrayList<Showing> showingsIn) 
	{
		this.showings = showingsIn;
	}
	/**
	   * Adds a showing to the ArrayList of showings for the Movie.
	   * @param showingIn is the new showing for the Movie.
	   */
	public void addShowing(Showing showingIn)
	{
		this.showings.add(showingIn);
	}
	/**
	   * Removes a showing from the ArrayList of showings for the Movie.
	   * @param showingOut is the removed showing for the Movie.
	   */
	public void removeOldShowing(Showing showingOut)
	{
		this.showings.remove(showingOut);
	}
	

	// Ratings
	/**
	   * Gets the ArrayList of ratings of the Movie.
	   * @return this Movie's ratings.
	   */
	public ArrayList<Double> getRatings() 
	{
		return this.ratings;
	}
	/**
	   * Gets the Average Rating of the Movie.
	   * @return this Movie's average rating.
	   */
	public double getAverageRating()
	{
		int i = 0;
		double average = 0;
		for(i = 0; i < this.ratings.size(); i++)
		{
			average = average + this.ratings.get(i);
		}
		
		if(i > 0)
		{
			average = average / i;
		}
		average = Math.round(average * 10.0) / 10.0;
		return average;
	}
	/**
	   * Changes the ratings of the Movie.
	   * @param ratingsIn are the new ratings of the Movie.
	   */
	public void setRatings(ArrayList<Double> ratingsIn) 
	{
		this.ratings = ratingsIn;
	}
	/**
	   * Adds a rating to the Movie.
	   * @param ratingIn is the new rating for the Movie.
	   */
	public void addRating(double ratingIn)
	{
		this.ratings.add(ratingIn);
	}
	
	
	//Users TODO: Maybe find a better home for this??
	/**
	   * Gets the users who have bought tickets to the Movie.
	   * @return this Movie's users.
	   */
	public ArrayList<User> getUsers() 
	{
		return this.users;
	}
	/**
	   * Changes the users that have bought tickets to the Movie.
	   * @param usersIn are the new users that have bought tickets to the Movie.
	   */
	public void setUsers(ArrayList<User> usersIn) 
	{
		this.users = usersIn;
	}
	/**
	   * Adds a user who has bought a ticket to the Movie.
	   * @param userIn is the new user for the Movie.
	   */
	public void addUser(User userIn)
	{
		this.users.add(userIn);
	}
	/**
	   * Removes a user who has bought a ticket to the Movie.
	   * @param userOut is the removed user for the Movie.
	   */
	public void removeUser(User userOut)
	{
		this.users.remove(userOut);
	}
	
	/**
	 * Returns a String that represents Movie
	 * @return Movie String
	 */
	@Override
	public String toString() 
	{
		String formattedDescription = "";
		String newDescription = this.description;
		
		if(newDescription.length() > 50)
		{
			boolean done = false;
			while(!done)
			{
				int j = 50;
				boolean foundSpace = false;
				while(!foundSpace)
				{
					if(newDescription.charAt(j) == ' ')
					{foundSpace = true;}
					else
					{j--;}
				}
				formattedDescription = formattedDescription + newDescription.substring(0, j) + "\n";
				newDescription = newDescription.substring(j);
				if(newDescription.length() <= 50)
				{
					formattedDescription = formattedDescription + newDescription;
					done = true;
				}
			}
		}
		else
		{formattedDescription = newDescription;}
		
		if(released)
		{return this.title + " - " + this.getAverageRating() + " Stars \n" + formattedDescription + "\n" + this.genre + "\n" + this.cast + "\nOut Now!\n";}
		else
		{return this.title + "\n" + formattedDescription + "\n" + this.genre + "\n" + this.cast + "\nComing Soon!\n";}
	}
	

}
