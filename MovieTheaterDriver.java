import java.util.ArrayList;

public class MovieTheaterDriver 
{

	public static void main(String[] args) 
	{
		MovieTheater movieTheater = new MovieTheater();
		movieTheater.populateMovies("TestMovies.txt");
		movieTheater.populateTheaters();
		movieTheater.getTheaters().get(0).populateShowings("Theater1Showings.txt");
		movieTheater.getTheaters().get(1).populateShowings("Theater2Showings.txt");
		movieTheater.getTheaters().get(2).populateShowings("Theater3Showings.txt");

		System.out.println("PRINTING ALL MOVIES\n");
		System.out.println(movieTheater.printMovies());
		
		System.out.println("POSTING A NEW MOVIE\n");
		movieTheater.postMovie("Test Genre", "Test Title", "Currently running", "Test Description", "Test Cast", "TestMovies.txt");
		System.out.println(movieTheater.printMovies());
		
		System.out.println("EDITING THE LAST MOVIE\n");
		movieTheater.editMovie("Test Title", "New genre", "New Title", "0", "0", "0", "0");
		System.out.println(movieTheater.printMovies());
		
		System.out.println("DELETING THE LAST MOVIE\n");
		movieTheater.deleteMovie("New Title", "TestMovies.txt");
		System.out.println(movieTheater.printMovies());
		
		System.out.println("BROWSING MOVIES WITH NO FILTERS\n");
		System.out.println(movieTheater.printFilteredMovies(movieTheater.browseMovies()));
		
		System.out.println("BROWSING MOVIES BY TITLE\n");
		System.out.println(movieTheater.printFilteredMovies(movieTheater.browseMovies(0, "Shrek", "", 0)));
		
		System.out.println("BROWSING MOVIES BY RELEASED\n");
		System.out.println(movieTheater.printFilteredMovies(movieTheater.browseMovies(1, "", "", 0)));
		
		System.out.println("BOOKING ONE TICKET TO THE FIRST MOVIE\n");
		User user = new User("testUsername", "testPassword");
		ArrayList<String> seats = new ArrayList<>();
		seats.add("A1");
		movieTheater.bookMovie(user, movieTheater.getMovies().get(0), movieTheater.getTheaters().get(0).getShowings().get(0), movieTheater.getTheaters().get(0), seats, "TestTheater1Showings.txt");
		
		System.out.println("SHOWING NAMES OF USERS WHO BOUGHT TICKETS TO THE FIRST MOVIE\n");
		System.out.println(movieTheater.getUsers(movieTheater.getMovies().get(0)));
	}

}