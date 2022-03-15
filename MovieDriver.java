import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MovieDriver 
{

	public static void main(String[] args) throws ParseException 
	{
		MovieTheater movieTheater = new MovieTheater();
		movieTheater.populateMovies("TestMovies.txt");
		movieTheater.populateTheaters();
		movieTheater.getTheaters().get(0).populateShowings("Theater1Showings.txt");
		movieTheater.getTheaters().get(1).populateShowings("Theater2Showings.txt");
		movieTheater.getTheaters().get(2).populateShowings("Theater3Showings.txt");

		System.out.println("GETTING STATUS OF A MOVIE BEING RELEASED ON 5/5/2021\n");
		System.out.println(Movie.status("2021-04-30", "2021-05-05"));
		
		System.out.println("GETTING STATUS OF A MOVIE RELEASED ON 4/25/2021\n");
		System.out.println(Movie.status("2021-04-30", "2021-04-25"));
	}

}
