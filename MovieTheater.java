
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Represents a Movie Theater System booking Movie Tickets.
 * A MovieTheater has multiple Theaters (Projector Rooms)
 */
public class MovieTheater 
{
	//Variables
	/**
	 * List of Movies currently Playing at this MovieTheater
	 */
	private ArrayList<Movie> movies;
	/**
	 * List of Theaters within this MovieTheater
	 */
	private ArrayList<Theater> theaters;
	
	private String theaterName;
	
	private String address;
	
	//Constructors
	/**
	 * Creates a MovieTheater with no Theaters and no Movies.
	 */
	public MovieTheater() 
	{
		this.movies = new ArrayList<>();
		this.theaters = new ArrayList<>();
	}
	/**
	 * Creates a MovieTheater with given Movies and Theaters.
	 * @param moviesIn List of Movies to be set as movies.
	 * @param theatersIn List of Theaters to be set as theaters
	 */
	public MovieTheater(ArrayList<Movie> moviesIn, ArrayList<Theater> theatersIn) 
	{
		this.movies = moviesIn;
		this.theaters = theatersIn;
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
	public String getAddress() 
	{
		return address;
	}
	public void setAddress(String address) 
	{
		this.address = address;
	}
	
	//Movies
	/**
	 * Gets the List of Movies currently Playing at this MovieTheater.
	 * @return movies.
	 */
	public ArrayList<Movie> getMovies() 
	{
		return this.movies;
	}
	/**
	 * Changes the List of Movies currently Playing at this MovieTheater
	 * @param moviesIn is the new movies.
	 */
	public void setMovies(ArrayList<Movie> moviesIn) 
	{
		this.movies = moviesIn;
	}
	/**
	 * Adds the given movieIn to the list of Movies.
	 * @param movieIn Movie to be added to the List of Movies.
	 */
	public void addMovie(Movie movieIn)
	{
		this.movies.add(movieIn);
	}
	/**
	 * Removes the given movieOut from the List of Movies.
	 * @param movieOut Movie to be removed.
	 */
	public void removeMovie(Movie movieOut)
	{
		this.movies.remove(movieOut);
	}

	//Theaters
	/**
	 * Gets the List of Theaters within this MovieTheater.
	 * @return theaters
	 */
	public ArrayList<Theater> getTheaters() 
	{
		return this.theaters;
	}
	/**
	 * Changes the List of Theaters within this MovieTheater.
	 * @param theatersIn is the new theaters
	 */
	public void setTheaters(ArrayList<Theater> theatersIn) 
	{
		this.theaters = theatersIn;
	}
	/**
	 * Adds the given theaterIn to the List of Theaters.
	 * @param theaterIn Theater to be added.
	 */
	public void addTheater(Theater theaterIn)
	{
		this.theaters.add(theaterIn);
	}
	/**
	 * Removes the given theaterOut from the List of Theaters
	 * @param theaterOut Theater to be removed.
	 */
	public void removeTheater(Theater theaterOut)
	{
		this.theaters.remove(theaterOut);
	}
	

	//Methods
	
	/**
	 * Creates a new Movie Object with the provided details.
	 * Adds that Movie to movies.
	 * @param genre New Movie's Genre
	 * @param title New Movie's Title
	 * @param status New Movie's Status
	 */
	public void postMovie(String genre, String title, String status, String description, String cast, String fileName)
	{
		ArrayList<Double> ratings = new ArrayList<>();
		ratings.add(0.0);
		Movie newMovie = new Movie(genre, title, status, description, cast, ratings);
		movies.add(newMovie);
		this.storeMovies(fileName);
	}
	
	/**
	 * Allows Admin to Edit Movies.
	 * presents the admin with a list of all available movies.
	 * admin provides the title of the movie that they want to edit.
	 * that particular Movie object is retrieved.
	 * admin is able to edit the details of that movie or delete it altogether.
	 * admin can also access the array list of user objects that contains the list of names of people who
	 * purchased tickets to a particular movie.
	 */
	public void editMovie(String movieTitle, String genre, String title, String status, String description, String cast, String fileName)
	{
		for (int i = 0; i < movies.size(); i++) 
		{
			if (movies.get(i).getTitle().equals(movieTitle)) 
			{
				if (!(genre.equals("0"))) // if the admin does not want to edit a particular detail, that detail is set to 0 in the menu and supplied to the method
				{
					movies.get(i).setGenre(genre);
				}
				
				if (!(title.equals("0"))) 
				{
					movies.get(i).setTitle(title);
				}
				
				if (!(status.equals("0"))) 
				{
					movies.get(i).setStatus(status);
				}
				
				if (!(description.equals("0"))) 
				{
					movies.get(i).setDescription(description);
				}
				
				if (!(cast.equals("0"))) 
				{
					movies.get(i).setCast(cast);
				}
			}
			
			this.storeMovies(fileName);
		}
	}
	
	public void deleteMovie(String movieTitle, String fileName) 
	{
		for (int i = 0; i < movies.size(); i++) 
		{
			if (movies.get(i).getTitle().equals(movieTitle)) 
			{
				movies.remove(i);
				this.storeMovies(fileName);
			}
		}
	}
	
	public ArrayList<String> getUsers(Movie movie) 
	{
		ArrayList<User> customers = movie.getUsers();
		ArrayList<String> usernames = new ArrayList();
		for (User user : customers) 
		{
			usernames.add(user.getUsername());
		}
		return usernames;
	}
	
	//Browse Movies
		/**
		 * User is shown the list of movies.
		 * No filters are applied.
		 * @return The List of All Movies
		 */
		public ArrayList<Movie> browseMovies()
		{
			return this.filter(0, "", "", 0);
		}
		/**
		 * User is shown a list of movies.
		 * All given filters are applied.
		 * @param status Filter based on the released status of a Movie (1: Released 2: Unreleased Else: Doesn't Matter)
		 * @param titleIn Filter based on Title ("" will filter out nothing, returning the list as is)
		 * @param genreIn Filter based on Genre ("" will filter out nothing, returning the list as is)
		 * @param minRating Filter based on Average Rating (0 will filter out nothing, returning the list as is)
		 * @return The Filtered List
		 */
		public ArrayList<Movie> browseMovies(int status, String title, String genre, double rating)
		{
			return this.filter(status, title, genre, rating);
		}
		/**
		 * Filters Movie List based on Parameters.
		 * @param status Filter based on the released status of a Movie (1: Released 2: Unreleased Else: Doesn't Matter)
		 * @param titleIn Filter based on Title ("" will filter out nothing, returning the list as is)
		 * @param genreIn Filter based on Genre ("" will filter out nothing, returning the list as is)
		 * @param minRating Filter based on Average Rating (0 will filter out nothing, returning the list as is)
		 * @return The Filtered List
		 */
		private ArrayList<Movie> filter(int status, String titleIn, String genreIn, double minRating)
		{
			ArrayList<Movie> filteredMovies = new ArrayList<>();
			
			for(int i = 0; i < this.movies.size(); i++)
			{
				filteredMovies.add(this.movies.get(i));
			}
			
			//Released Status
			if(status == 1) //Released Movies
			{
				for(int i = 0; i < filteredMovies.size(); i++)
				{
					if(!filteredMovies.get(i).isReleased())
					{
						filteredMovies.remove(i);
						i--;
					}
				}
			}
			else if(status == 2) //Unreleased Movies
			{
				for(int i = 0; i < filteredMovies.size(); i++)
				{
					if(filteredMovies.get(i).isReleased())
					{
						filteredMovies.remove(i);
						i--;
					}
				}
				
				//Else: Returns All Movies
			} 
			
			//Filter by Title
			if(titleIn.length() > 0)
			{
				for(int i = 0; i < filteredMovies.size(); i++)
				{
					if(!filteredMovies.get(i).getTitle().contains(titleIn))
					{
						filteredMovies.remove(i);
						i--;
					}
				}
			}
			
			//Filter by Genre
			if(genreIn.length() > 0)
			{
				for(int i = 0; i < filteredMovies.size(); i++)
				{
					if(!filteredMovies.get(i).getGenre().contains(genreIn))
					{
						filteredMovies.remove(i);
						i--;
					}
				}
			}
			
			//Filter by Average Rating
			if(minRating > 0)
			{
				for(int i = 0; i < filteredMovies.size(); i++)
				{
					if(filteredMovies.get(i).getAverageRating() < minRating)
					{
						filteredMovies.remove(i);
						i--;
					}
				}
			}
			
			//Returns list that fits all given filters
			return filteredMovies;
		}
	
	/**
	 * Allows User to Purchase a Ticket to a particular Showing of a Movie.
	 * User chooses the theater location that they want to see the movie at.
	 * The availableSeats method is called and the user is shown the number of available seats for that
	 * movie at different showings.
	 * User provides the number of tickets, particular showing, and seat numbers.
	 * If the transaction is successful, the user’s purchases and current tickets are updated.
	 * That particular User object is added to the Movie’s array list of users.
	 * @param obj
	 */
	public void bookMovie(User user, Movie movie, Showing showing, Theater theater, ArrayList<String> seats, String showingFileName)
	{
		for (int i = 0; i < seats.size(); i++) 
		{
			user.addCurrentTicket(new Ticket(this.getTheaterName(), movie.getTitle(), showing.getDate(), showing.getTime(),
											 showing.getPrice(), theater.getTheaterNumber(), seats.get(i)));
			movie.addUser(user);
			showing.removeSeat(seats.get(i), showingFileName);
		}
	}

	public void populateMovies(String fileName) 
	{
		try 
		{
			Scanner in = new Scanner(new File(fileName));
			while (in.hasNextLine()) 
			{
				String genre = in.nextLine();
				String title = in.nextLine();
				String status = in.nextLine();
				String description = in.nextLine();
				String cast = in.nextLine();
				String ratingsLine = in.nextLine();
				Scanner ratingsScanner = new Scanner(ratingsLine);
				ArrayList<Double> ratings = new ArrayList<>();
				while (ratingsScanner.hasNextDouble()) 
				{
					ratings.add(ratingsScanner.nextDouble());
				}
				movies.add(new Movie(genre, title, status, description, cast, ratings));
			}
		}
		
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void storeMovies(String fileName) 
	{
		try 
		{
			PrintWriter out = new PrintWriter(fileName);
			for (Movie movie : movies) 
			{
				out.println(movie.getGenre());
				out.println(movie.getTitle());
				out.println(movie.getStatus());
				out.println(movie.getDescription());
				out.println(movie.getCast());
				String output = "";
				for (Double rating : movie.getRatings()) 
				{
					output += rating + " ";
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
	
	public String printMovies() 
	{
		String output = "";
		for (Movie movie : movies) 
		{
			output += movie.toString() + "\n";
		}
		return output;
	}
	
	public String printFilteredMovies(ArrayList<Movie> movies) 
	{
		String output = "";
		for (Movie movie : movies) 
		{
			output += movie.toString() + "\n";
		}
		return output;
	}
	
	public void populateTheaters() 
	{
		try 
		{
			Scanner in = new Scanner(new File("Theaters.txt"));
			while (in.hasNextLine()) 
			{
				String theaterNumber = in.nextLine();
				this.addTheater(new Theater(theaterNumber));
			}
		}
		
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void storeTheaters() 
	{
		try 
		{
			PrintWriter out = new PrintWriter("Theaters.txt");
			for (Theater theater : theaters) 
			{
				out.println(theater.getTheaterNumber());
			}
			out.close();
		}
		
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	
	public ArrayList<Showing> getShowingsForMovie(String movieTitleIn)
	{
		ArrayList<Showing> showingsForMovie = new ArrayList<>();
		
		for(Theater thtr : this.getTheaters())
		{
			for(Showing showing : thtr.getShowings())
			{
				if(showing.getMovieTitle().equals(movieTitleIn))
				{
					showingsForMovie.add(showing);
				}
			}
		}
		return showingsForMovie;
	}
	
}
