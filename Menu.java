import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu 
{
	//private static String selection;
	//private static boolean acceptable;
	
	/**
	 * Checks if a given String input is an acceptable choice
	 * input must be a String equal to an integer less than or equal to numChoices
	 * @param input is the String being checked
	 * @param numChoices is the number of acceptable choices
	 * @return true if input is acceptable or false if input is not
	 */
	public static boolean acceptable(String input, int numChoices)
	{
		boolean result = false;
		for(int i = 1; i <= numChoices; i++)
		{
			String possibleChoice = "" + i;
			if(input.equals(possibleChoice))
			{result = true;}
		}
		return result;
	}

	
	public static void main(String[] args) 
	{
		Scanner in = new Scanner(System.in);
		
		MovieTheater movieTheater = new MovieTheater(); // creates an actual movie theater building
		movieTheater.populateMovies("Movies.txt"); // movies are added to that building
		movieTheater.populateTheaters(); // theaters are added to that building
		movieTheater.getTheaters().get(0).populateShowings("Theater1Showings.txt"); // showings are added to the first theater
		movieTheater.getTheaters().get(1).populateShowings("Theater2Showings.txt"); // showings are added to the second theater
		movieTheater.getTheaters().get(2).populateShowings("Theater3Showings.txt"); // showings are added to the third theater
		movieTheater.getTheaters().get(3).populateShowings("Theater4Showings.txt"); // showings are added to the fourth theater
		movieTheater.getTheaters().get(4).populateShowings("Theater5Showings.txt"); // showings are added to the fifth theater
		Admin admin = new Admin();
		
///**BOOT MENU**///
		boolean exitCompletely = false; //Exit Boolean
		while(!exitCompletely) 
		{
			System.out.print("Welcome to Movie Booking! \n\n1. Login \n2. Register New Account \n3. Exit \nYour Selection: ");
			String selection = "";
			boolean acceptable = false;
			while(!acceptable) //Check for acceptable Response
			{
				selection = in.next();
				acceptable = Menu.acceptable(selection, 3);
				if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
			}
			if(selection.equals("1")) //Login 
			{
	///**LOGIN MENU**///
				String username;
				String password;
				System.out.println("\nLogin");
				System.out.print("Username: ");
				username = in.next();
				System.out.print("Password: ");
				password = in.next();
				
				Person person = new Person(username, password);
				boolean adminLogin = false;
				boolean userLogin = false;
				
				if(person.getUsername().equals(admin.getUsername()) && person.getPassword().equals(admin.getPassword()))
				{adminLogin = true;}
				else 
				{
					boolean realUser = false;
					try {realUser = person.loginCheck(username, password);} 
					catch (FileNotFoundException e) {e.printStackTrace();}
					if(realUser)
					{userLogin = true;}
					else
					{System.out.println("\n*Login Failed: No Such User*\n");}
				}
				if(adminLogin)
				{
		///**ADMIN MENU**///
					boolean backToLoginMenu = false; //Exit boolean
					while(!backToLoginMenu)
					{
						System.out.print("\nAdmin View \n\nWhat would you like to do? \n1. Edit Movie Information \n2. Post New Movie \n3. Logout \nYour Selection: ");
						selection = ""; 
						acceptable = false;
						while(!acceptable) //Check for acceptable Response
						{
							selection = in.next();
							acceptable = Menu.acceptable(selection, 3); 
							if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
						}
						if(selection.equals("1")) //Edit Movies
						{
			///**EDIT MOVIES MENU**///
							boolean backToAdminMenu = false; //exit boolean
							int i = 0;
							while(!backToAdminMenu)
							{
				///**VIEW MOVIES MENU**///
								if(movieTheater.browseMovies().size() == 0) //No Movies in the List
								{
									System.out.println("\n*There are no movies currently posted!* \n"); //Display "Error"
									backToAdminMenu = true; //Go Back
								}
								else if(movieTheater.browseMovies().size() == 1) //Only One Movie in the List
								{
									System.out.println("Movie: (" + (i + 1) + ")"); //Display Page Number
									System.out.println(movieTheater.browseMovies().get(i)); //Display Movie
									
									System.out.print("1. Edit Movie \n2. Go Back \nYour Selection: "); //Display Options
									selection = ""; 
									acceptable = false;
									while(!acceptable) //Check for acceptable Response
									{
										selection = in.next();
										acceptable = Menu.acceptable(selection, 2);
										if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
									}
									if(selection.equals("1")) //Edit Movie
									{
					///**EDIT MENU**///
										boolean backToEditMoviesMenu = false; //exit boolean
										while(!backToEditMoviesMenu)
										{
											System.out.println("\nEdit Movie: (" + (i + 1) + ")"); //Display Page Number
											System.out.println(movieTheater.browseMovies().get(i)); //Display Movie
											
											System.out.print("1. Update Movie Details \n2. Add Showing \n3. Delete This Movie \n4. Go Back \nYour Selection: "); //Display Options
											selection = ""; 
											acceptable = false;
											while(!acceptable) //Check for acceptable Response
											{
												selection = in.next();
												acceptable = Menu.acceptable(selection, 4);
												if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
											}
											if(selection.equals("1")) //Update Details
											{
						///**UPDATE DETAILS MENU**///
												//New Information (Defaults to the old info)
												String newTitle = movieTheater.browseMovies().get(i).getTitle();
												String newDescription = movieTheater.browseMovies().get(i).getDescription();
												String newCast = movieTheater.browseMovies().get(i).getCast();
												String newGenre = movieTheater.browseMovies().get(i).getGenre();
												String newStatus = movieTheater.browseMovies().get(i).getStatus();
												
												boolean backToEditMenu = false; //Exit boolean
												while(!backToEditMenu)
												{
													System.out.println("\nWhat would you like to update?");
													System.out.print("1. Title \n2. Description \n3. Cast \n4. Genre \n5. Released Status \n6. Go Back \nYour Selection: ");
													selection = ""; 
													acceptable = false;
													while(!acceptable) //Check for acceptable Response
													{
														selection = in.next();
														acceptable = Menu.acceptable(selection, 6); 
														if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
													}
													if(selection.equals("1")) //Title
													{
														System.out.print("\nWhat would you like the new title to be? \nNew Title: ");
														in.nextLine();
														newTitle = in.nextLine();
														System.out.println("Ok, the new title will be: " + newTitle);
													}
													else if(selection.equals("2")) //Description
													{
														System.out.print("\nWhat would you like the new description to be? \nNew Description: ");
														in.nextLine();
														newDescription = in.nextLine();
														System.out.println("Ok, the new description will be: \n" + newDescription);
													}
													else if(selection.equals("3")) //Cast
													{
														System.out.print("\nWhat would you like the new cast to be? \nNew Cast: ");
														in.nextLine();
														newCast = in.nextLine();
														System.out.println("Ok, the new cast will be: \n" + newCast);
													}
													else if(selection.equals("4")) //Genre
													{
														System.out.print("What would you like the new genre to be? \nNew Genre: ");
														in.nextLine();
														newGenre = in.nextLine();
														System.out.println("Ok, the new genre will be: \n" + newGenre);
													}
													else if(selection.equals("5")) //Released Status 
													{
														System.out.print("What is the release date of this movie (yyyy-mm-dd)? \nRelease Date: ");
														String releaseDate = in.next();
														System.out.println("Ok, the release will be: \n" + releaseDate);
														String currentDate = "" + java.time.LocalDate.now();
														try {newStatus = Movie.status(currentDate, releaseDate);} 
														catch (ParseException e) {e.printStackTrace();}
													}
													else if(selection.equals("6")) //Back
													{
														movieTheater.editMovie(movieTheater.browseMovies().get(i).getTitle(), newGenre, newTitle, newStatus, 
																               newDescription, newCast, "Movies.txt"); 
														backToEditMenu = true; //Go back
														System.out.println();
													}
												}
						///**END UPDATE DETAILS MENU**///
											}
											else if(selection.equals("2")) //Add Showing
											{
						///**ADD SHOWING MENU**///
												String newDate = "";
												String newTime = "";
												int newTheater = 0;
												double newPrice = 0;
												boolean correct;
												
												System.out.println("\nAdd a new Showing:");
												correct = false;
												while(!correct) //Date
												{
													System.out.print("New showing's Date (DD/MM/YY): ");
													newDate = in.next();
													System.out.println("Ok, the new Date will be: " + newDate);
													System.out.print("Is that correct? \n1. Yes \n2. No \nYour Selection: ");
													selection = ""; 
													acceptable = false;
													while(!acceptable) //Check for acceptable Response
													{
														selection = in.next();
														acceptable = Menu.acceptable(selection, 2); 
														if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
													}
													if(selection.equals("1")) //Yes
													{
														System.out.println("\nOk\n");
														correct = true;
													}
													else if(selection.equals("2")) //No
													{
														System.out.println("\nOk\n");
													}
												}
												correct = false;
												while(!correct) //Time
												{
													System.out.print("New Showing's Time (HH:MM): ");
													newTime = in.next();
													System.out.println("Ok, the new time will be: " + newTime);
													System.out.print("Is that correct? \n1. Yes \n2. No \nYour Selection: ");
													selection = ""; 
													acceptable = false;
													while(!acceptable) //Check for acceptable Response
													{
														selection = in.next();
														acceptable = Menu.acceptable(selection, 2); 
														if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
													}
													if(selection.equals("1")) //Yes
													{
														System.out.println("\nOk\n");
														correct = true;
													}
													else if(selection.equals("2")) //No
													{
														System.out.println("\nOk\n");
													}
												}
												correct = false;
												while(!correct) //Price
												{
													System.out.print("New Showing's Price: \n1. $2 \n2. $4 \n3. $6 \n4. $8 \n5. $10 \nYour Selection: ");
													selection = "";
													acceptable = false;
													while(!acceptable) //Check for acceptable Response
													{
														selection = in.next();
														acceptable = Menu.acceptable(selection, 5); 
														if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
													}
													if(selection.equals("1")) //2
													{
														newPrice = 2;
													}
													else if(selection.equals("2")) //4
													{
														newPrice = 4;
													}
													else if(selection.equals("3")) //6
													{
														newPrice = 6;
													}
													else if(selection.equals("4")) //8
													{
														newPrice = 8;
													}
													else if(selection.equals("5")) //10
													{
														newPrice = 10;
													}
													System.out.println("Ok, the new price will be: $" + newPrice);
													System.out.print("Is that correct? \n1. Yes \n2. No \nYour Selection: ");
													selection = ""; 
													acceptable = false;
													while(!acceptable) //Check for acceptable Response
													{
														selection = in.next();
														acceptable = Menu.acceptable(selection, 2); 
														if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
													}
													if(selection.equals("1")) //Yes
													{
														System.out.println("\nOk\n");
														correct = true;
													}
													else if(selection.equals("2")) //no
													{
														System.out.println("\nOk\n");
													}
												}
												correct = false;
												while(!correct) //Theater
												{
													System.out.print("New Showing's Theater: \n1. Theater 1 \n2. Theater 2 \n3. Theater 3 \n4. Theater 4 \n5. Theater 5 \nYour Selection: ");
													selection = "";
													acceptable = false;
													while(!acceptable) //Check for acceptable Response
													{
														selection = in.next();
														acceptable = Menu.acceptable(selection, 5); 
														if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
													}
													if(selection.equals("1")) //1
													{
														newTheater = 1;
													}
													else if(selection.equals("2")) //2
													{
														newTheater = 2;
													}
													else if(selection.equals("3")) //3
													{
														newTheater = 3;
													}
													else if(selection.equals("4")) //4
													{
														newTheater = 4;
													}
													else if(selection.equals("5")) //5
													{
														newTheater = 5;
													}
													System.out.println("Ok, the new theater will be: Theater " + newTheater);
													System.out.print("Is that correct? \n1. Yes \n2. No \nYour Selection: ");
													selection = ""; 
													acceptable = false;
													while(!acceptable) //Check for acceptable Response
													{
														selection = in.next();
														acceptable = Menu.acceptable(selection, 2); 
														if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
													}
													if(selection.equals("1")) //Yes
													{
														System.out.println("\nOk\n");
														correct = true;
													}
													else if(selection.equals("2")) //no
													{
														System.out.println("\nOk\n");
													}
												}
												correct = false;
												while(!correct)
												{
													Showing newShowing = new Showing(newDate, newTime, movieTheater.getTheaters().get(newTheater - 1), 
															movieTheater.browseMovies().get(i).getTitle(), movieTheater.getTheaters().get(newTheater - 1).getSeats(), newPrice);
													System.out.println(newDate + "\n" + newTime + "\n" + newShowing.getTheater() + "\n$" + newPrice + "\nThis showing will now be posted.");
													System.out.print("Is that ok? \n1. Yes \n2. No \nYour Selection: ");
													selection = ""; 
													acceptable = false;
													while(!acceptable) //Check for acceptable Response
													{
														selection = in.next();
														acceptable = Menu.acceptable(selection, 2); 
														if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
													}
													if(selection.equals("1")) //Yes
													{
														movieTheater.getTheaters().get(newTheater - 1).addShowing(newShowing, "Theater" + newTheater + "Showings.txt");
														System.out.println("Ok, showing has been posted!\n");
														correct = true;
													}
													else if(selection.equals("2")) //No
													{
														System.out.println("Are you sure? Leaving now will discard all information for this showing.");
														System.out.print("\n1. Yes \n2. No \nYour Selection: ");
														selection = ""; 
														acceptable = false;
														while(!acceptable) //Check for acceptable Response
														{
															selection = in.next();
															acceptable = Menu.acceptable(selection, 2); 
															if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
														}
														if(selection.equals("1")) //Yes
														{
															correct = true;
															System.out.println("\nOk\n");
														}
														else if(selection.equals("2")) //No
														{
															System.out.println("\nOk\n");
														}
													}
												}
						///**END ADD SHOWING MENU**///
											}
											else if(selection.equals("3")) //Delete This Movie
											{
						///**DELETE MOVIE**///
												System.out.println("\nAre you sure you would like to Delete this movie? \n*This action will be FINAL and PERMANENT!*");
												System.out.print("1. YES \n2. NO \nYour Selection: ");
												selection = ""; 
												acceptable = false;
												while(!acceptable) //Check for acceptable Response
												{
													selection = in.next();
													acceptable = Menu.acceptable(selection, 2);
													if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
												}
												if(selection.equals("1")) //YES
												{
													System.out.println("\nMovie Deleted!\n");
													movieTheater.deleteMovie(movieTheater.browseMovies().get(i).getTitle(), "Movies.txt");
													
													backToEditMoviesMenu = true;
													backToAdminMenu = true;
													System.out.println("\n");
												}
												else if(selection.equals("2")) //NO
												{
													System.out.println("\nOk!\n");
												}
						///**END DELETE MOVIE**///
											}
											else if(selection.equals("4")) //Back
											{
												backToEditMoviesMenu = true;
												System.out.println("\n");
											}
										}
					///**END EDIT MENU**///
									}
									else if(selection.equals("2")) //Back
									{
										backToAdminMenu = true;
										System.out.println("\n");
									}
								}
								else if(i == 0) //First Movie in list (Cannot Go Backwards)
								{
									System.out.println("Movie: (" + (i + 1) + ")");
									System.out.println(movieTheater.browseMovies().get(i));
									
									System.out.print("1. Next \n2. Edit Movie \n3. Go Back \nYour Selection: ");
									selection = ""; 
									acceptable = false;
									while(!acceptable) //Check for acceptable Response
									{
										selection = in.next();
										acceptable = Menu.acceptable(selection, 3);
										if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
									}
									if(selection.equals("1")) //Next
									{
										i++;
										System.out.println("");
									}
									else if(selection.equals("2")) //Edit Movie
									{
					///**EDIT MENU**///
										boolean backToEditMoviesMenu = false; //exit boolean
										while(!backToEditMoviesMenu)
										{
											System.out.println("\nEdit Movie: (" + (i + 1) + ")"); //Display Page Number
											System.out.println(movieTheater.browseMovies().get(i)); //Display Movie
											
											System.out.print("1. Update Movie Details \n2. Add Showing \n3. Delete This Movie \n4. Go Back \nYour Selection: "); //Display Options
											selection = ""; 
											acceptable = false;
											while(!acceptable) //Check for acceptable Response
											{
												selection = in.next();
												acceptable = Menu.acceptable(selection, 4);
												if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
											}
											if(selection.equals("1")) //Update Details
											{
						///**UPDATE DETAILS MENU**///
												//New Information (Defaults to the old info)
												String newTitle = movieTheater.browseMovies().get(i).getTitle();
												String newDescription = movieTheater.browseMovies().get(i).getDescription();
												String newCast = movieTheater.browseMovies().get(i).getCast();
												String newGenre = movieTheater.browseMovies().get(i).getGenre();
												String newStatus = movieTheater.browseMovies().get(i).getStatus();
												
												boolean backToEditMenu = false; //Exit boolean
												while(!backToEditMenu)
												{
													System.out.println("\nWhat would you like to update?");
													System.out.print("1. Title \n2. Description \n3. Cast \n4. Genre \n5. Released Status \n6. Go Back \nYour Selection: ");
													selection = ""; 
													acceptable = false;
													while(!acceptable) //Check for acceptable Response
													{
														selection = in.next();
														acceptable = Menu.acceptable(selection, 6); 
														if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
													}
													if(selection.equals("1")) //Title
													{
														System.out.print("\nWhat would you like the new title to be? \nNew Title: ");
														in.nextLine();
														newTitle = in.nextLine();
														System.out.println("Ok, the new title will be: " + newTitle);
													}
													else if(selection.equals("2")) //Description
													{
														System.out.print("\nWhat would you like the new description to be? \nNew Description: ");
														in.nextLine();
														newDescription = in.nextLine();
														System.out.println("Ok, the new description will be: \n" + newDescription);
													}
													else if(selection.equals("3")) //Cast
													{
														System.out.print("\nWhat would you like the new cast to be? \nNew Cast: ");
														in.nextLine();
														newCast = in.nextLine();
														System.out.println("Ok, the new cast will be: \n" + newCast);
													}
													else if(selection.equals("4")) //Genre
													{
														System.out.print("\nWhat would you like the new genre to be? \nNew Genre: ");
														in.nextLine();
														newGenre = in.nextLine();
														System.out.println("Ok, the new genre will be: \n" + newGenre);
													}
													else if(selection.equals("5")) //Released Status 
													{
														System.out.print("\nWhat is the release date of this movie (yyyy-mm-dd)? \nRelease Date: ");
														String releaseDate = in.next();
														System.out.println("Ok, the release will be: \n" + releaseDate);
														String currentDate = "" + java.time.LocalDate.now();
														try {newStatus = Movie.status(currentDate, releaseDate);} 
														catch (ParseException e) {e.printStackTrace();}
													}
													else if(selection.equals("6")) //Back
													{
														movieTheater.editMovie(movieTheater.browseMovies().get(i).getTitle(), newGenre, newTitle, newStatus, 
																               newDescription, newCast, "Movies.txt"); 
														backToEditMenu = true; //Go back
														System.out.println();
													}
												}
						///**END UPDATE DETAILS MENU**///
											}
											else if(selection.equals("2")) //Add Showing
											{
						///**ADD SHOWING MENU**///
												String newDate = "";
												String newTime = "";
												int newTheater = 0;
												double newPrice = 0;
												boolean correct;
												
												System.out.println("Add a new Showing:");
												correct = false;
												while(!correct) //Date
												{
													System.out.print("New showing's Date (DD/MM/YY): ");
													newDate = in.next();
													System.out.println("Ok, the new Date will be: " + newDate);
													System.out.print("Is that correct? \n1. Yes \n2. No \nYour Selection: ");
													selection = ""; 
													acceptable = false;
													while(!acceptable) //Check for acceptable Response
													{
														selection = in.next();
														acceptable = Menu.acceptable(selection, 2); 
														if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
													}
													if(selection.equals("1")) //Yes
													{
														System.out.println("\nOk\n");
														correct = true;
													}
													else if(selection.equals("2")) //No
													{
														System.out.println("\nOk\n");
													}
												}
												correct = false;
												while(!correct) //Time
												{
													System.out.print("New Showing's Time (HH:MM): ");
													newTime = in.next();
													System.out.println("Ok, the new time will be: " + newTime);
													System.out.print("Is that correct? \n1. Yes \n2. No \nYour Selection: ");
													selection = ""; 
													acceptable = false;
													while(!acceptable) //Check for acceptable Response
													{
														selection = in.next();
														acceptable = Menu.acceptable(selection, 2); 
														if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
													}
													if(selection.equals("1")) //Yes
													{
														System.out.println("\nOk\n");
														correct = true;
													}
													else if(selection.equals("2")) //No
													{
														System.out.println("\nOk\n");
													}
												}
												correct = false;
												while(!correct) //Price
												{
													System.out.print("New Showing's Price: \n1. $2 \n2. $4 \n3. $6 \n4. $8 \n5. $10 \nYour Selection: ");
													selection = "";
													acceptable = false;
													while(!acceptable) //Check for acceptable Response
													{
														selection = in.next();
														acceptable = Menu.acceptable(selection, 5); 
														if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
													}
													if(selection.equals("1")) //2
													{
														newPrice = 2;
													}
													else if(selection.equals("2")) //4
													{
														newPrice = 4;
													}
													else if(selection.equals("3")) //6
													{
														newPrice = 6;
													}
													else if(selection.equals("4")) //8
													{
														newPrice = 8;
													}
													else if(selection.equals("5")) //10
													{
														newPrice = 10;
													}
													System.out.println("Ok, the new price will be: $" + newPrice);
													System.out.print("Is that correct? \n1. Yes \n2. No \nYour Selection: ");
													selection = ""; 
													acceptable = false;
													while(!acceptable) //Check for acceptable Response
													{
														selection = in.next();
														acceptable = Menu.acceptable(selection, 2); 
														if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
													}
													if(selection.equals("1")) //Yes
													{
														System.out.println("\nOk\n");
														correct = true;
													}
													else if(selection.equals("2")) //no
													{
														System.out.println("\nOk\n");
													}
												}
												correct = false;
												while(!correct) //Theater
												{
													System.out.print("New Showing's Theater: \n1. Theater 1 \n2. Theater 2 \n3. Theater 3 \n4. Theater 4 \n5. Theater 5 \nYour Selection: ");
													selection = "";
													acceptable = false;
													while(!acceptable) //Check for acceptable Response
													{
														selection = in.next();
														acceptable = Menu.acceptable(selection, 5); 
														if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
													}
													if(selection.equals("1")) //1
													{
														newTheater = 1;
													}
													else if(selection.equals("2")) //2
													{
														newTheater = 2;
													}
													else if(selection.equals("3")) //3
													{
														newTheater = 3;
													}
													else if(selection.equals("4")) //4
													{
														newTheater = 4;
													}
													else if(selection.equals("5")) //5
													{
														newTheater = 5;
													}
													System.out.println("Ok, the new theater will be: Theater " + newTheater);
													System.out.print("Is that correct? \n1. Yes \n2. No \nYour Selection: ");
													selection = ""; 
													acceptable = false;
													while(!acceptable) //Check for acceptable Response
													{
														selection = in.next();
														acceptable = Menu.acceptable(selection, 2); 
														if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
													}
													if(selection.equals("1")) //Yes
													{
														System.out.println("\nOk\n");
														correct = true;
													}
													else if(selection.equals("2")) //no
													{
														System.out.println("\nOk\n");
													}
												}
												correct = false;
												while(!correct)
												{
													Showing newShowing = new Showing(newDate, newTime, movieTheater.getTheaters().get(newTheater - 1), 
															movieTheater.browseMovies().get(i).getTitle(), movieTheater.getTheaters().get(newTheater - 1).getSeats(), newPrice);
													System.out.println(newDate + "\n" + newTime + "\n" + newShowing.getTheater() + "\n$" + newPrice + "\nThis showing will now be posted.");
													System.out.print("Is that ok? \n1. Yes \n2. No \nYour Selection: ");
													selection = ""; 
													acceptable = false;
													while(!acceptable) //Check for acceptable Response
													{
														selection = in.next();
														acceptable = Menu.acceptable(selection, 2); 
														if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
													}
													if(selection.equals("1")) //Yes
													{
														movieTheater.getTheaters().get(newTheater - 1).addShowing(newShowing, "Theater" + newTheater + "Showings.txt");
														System.out.println("Ok, showing has been posted!\n");
														correct = true;
													}
													else if(selection.equals("2")) //No
													{
														System.out.println("Are you sure? Leaving now will discard all information for this showing.");
														System.out.print("\n1. Yes \n2. No \nYour Selection: ");
														selection = ""; 
														acceptable = false;
														while(!acceptable) //Check for acceptable Response
														{
															selection = in.next();
															acceptable = Menu.acceptable(selection, 2); 
															if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
														}
														if(selection.equals("1")) //Yes
														{
															correct = true;
															System.out.println("\nOk\n");
														}
														else if(selection.equals("2")) //No
														{
															System.out.println("\nOk\n");
														}
													}
												}
						///**END ADD SHOWING MENU**///
											}
											else if(selection.equals("3")) //Delete This Movie
											{
						///**DELETE MOVIE**///
												System.out.println("\nAre you sure you would like to Delete this movie? \n*This action will be FINAL and PERMANENT!*");
												System.out.print("1. YES \n2. NO \nYour Selection: ");
												selection = ""; 
												acceptable = false;
												while(!acceptable) //Check for acceptable Response
												{
													selection = in.next();
													acceptable = Menu.acceptable(selection, 2);
													if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
												}
												if(selection.equals("1")) //YES
												{
													System.out.println("\nMovie Deleted!\n");
													movieTheater.deleteMovie(movieTheater.browseMovies().get(i).getTitle(), "Movies.txt");
													
													backToEditMoviesMenu = true;
													backToAdminMenu = true;
													System.out.println("\n");
												}
												else if(selection.equals("2")) //NO
												{
													System.out.println("\nOk!\n");
												}
						///**END DELETE MOVIE**///
											}
											else if(selection.equals("4")) //Back
											{
												backToEditMoviesMenu = true;
												System.out.println("\n");
											}
										}
					///**END EDIT MENU**///
									}
									else if(selection.equals("3")) //Back
									{
										backToAdminMenu = true; //go back
										System.out.println("\n");
									}
								}
								else if((i + 1) == movieTheater.browseMovies().size()) //Last Movie in list (Cannot Go Forwards)
								{
									System.out.println("Movie: (" + (i + 1) + ")");
									System.out.println(movieTheater.browseMovies().get(i));
									
									System.out.print("1. Previous \n2. Edit Movie \n3. Go Back \nYour Selection: ");
									selection = ""; 
									acceptable = false;
									while(!acceptable) //Check for acceptable Response
									{
										selection = in.next();
										acceptable = Menu.acceptable(selection, 3);
										if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
									}
									if(selection.equals("1")) //Previous
									{
										i--;
										System.out.println("");
									}
									else if(selection.equals("2")) //Edit Movie
									{
					///**EDIT MENU**///
										boolean backToEditMoviesMenu = false; //exit boolean
										while(!backToEditMoviesMenu)
										{
											System.out.println("\nEdit Movie: (" + (i + 1) + ")"); //Display Page Number
											System.out.println(movieTheater.browseMovies().get(i)); //Display Movie
											
											System.out.print("1. Update Movie Details \n2. Add Showing \n3. Delete This Movie \n4. Go Back \nYour Selection: "); //Display Options
											selection = ""; 
											acceptable = false;
											while(!acceptable) //Check for acceptable Response
											{
												selection = in.next();
												acceptable = Menu.acceptable(selection, 4);
												if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
											}
											if(selection.equals("1")) //Update Details
											{
						///**UPDATE DETAILS MENU**///
												//New Information (Defaults to the old info)
												String newTitle = movieTheater.browseMovies().get(i).getTitle();
												String newDescription = movieTheater.browseMovies().get(i).getDescription();
												String newCast = movieTheater.browseMovies().get(i).getCast();
												String newGenre = movieTheater.browseMovies().get(i).getGenre();
												String newStatus = movieTheater.browseMovies().get(i).getStatus();
												
												boolean backToEditMenu = false; //Exit boolean
												while(!backToEditMenu)
												{
													System.out.println("\nWhat would you like to update?");
													System.out.print("1. Title \n2. Description \n3. Cast \n4. Genre \n5. Released Status \n6. Go Back \nYour Selection: ");
													selection = ""; 
													acceptable = false;
													while(!acceptable) //Check for acceptable Response
													{
														selection = in.next();
														acceptable = Menu.acceptable(selection, 6); 
														if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
													}
													if(selection.equals("1")) //Title
													{
														System.out.print("\nWhat would you like the new title to be? \nNew Title: ");
														in.nextLine();
														newTitle = in.nextLine();
														System.out.println("Ok, the new title will be: " + newTitle);
													}
													else if(selection.equals("2")) //Description
													{
														System.out.print("\nWhat would you like the new description to be? \nNew Description: ");
														in.nextLine();
														newDescription = in.nextLine();
														System.out.println("Ok, the new description will be: \n" + newDescription);
													}
													else if(selection.equals("3")) //Cast
													{
														System.out.print("\nWhat would you like the new cast to be? \nNew Cast: ");
														in.nextLine();
														newCast = in.nextLine();
														System.out.println("Ok, the new cast will be: \n" + newCast);
													}
													else if(selection.equals("4")) //Genre
													{
														System.out.print("\nWhat would you like the new genre to be? \nNew Genre: ");
														in.nextLine();
														newGenre = in.nextLine();
														System.out.println("Ok, the new genre will be: \n" + newGenre);
													}
													else if(selection.equals("5")) //Released Status 
													{
														System.out.print("\nWhat is the release date of this movie (yyyy-mm-dd)? \nRelease Date: ");
														String releaseDate = in.next();
														System.out.println("Ok, the release will be: \n" + releaseDate);
														String currentDate = "" + java.time.LocalDate.now();
														try {newStatus = Movie.status(currentDate, releaseDate);} 
														catch (ParseException e) {e.printStackTrace();}
													}
													else if(selection.equals("6")) //Back
													{
														movieTheater.editMovie(movieTheater.browseMovies().get(i).getTitle(), newGenre, newTitle, newStatus, 
																               newDescription, newCast, "Movies.txt"); 
														backToEditMenu = true; //Go back
														System.out.println();
													}
												}
						///**END UPDATE DETAILS MENU**///
											}
											else if(selection.equals("2")) //Add Showing
											{
						///**ADD SHOWING MENU**///
												String newDate = "";
												String newTime = "";
												int newTheater = 0;
												double newPrice = 0;
												boolean correct;
												
												System.out.println("Add a new Showing:");
												correct = false;
												while(!correct) //Date
												{
													System.out.print("New showing's Date (DD/MM/YY): ");
													newDate = in.next();
													System.out.println("Ok, the new Date will be: " + newDate);
													System.out.print("Is that correct? \n1. Yes \n2. No \nYour Selection: ");
													selection = ""; 
													acceptable = false;
													while(!acceptable) //Check for acceptable Response
													{
														selection = in.next();
														acceptable = Menu.acceptable(selection, 2); 
														if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
													}
													if(selection.equals("1")) //Yes
													{
														System.out.println("\nOk\n");
														correct = true;
													}
													else if(selection.equals("2")) //No
													{
														System.out.println("\nOk\n");
													}
												}
												correct = false;
												while(!correct) //Time
												{
													System.out.print("New Showing's Time (HH:MM): ");
													newTime = in.next();
													System.out.println("Ok, the new time will be: " + newTime);
													System.out.print("Is that correct? \n1. Yes \n2. No \nYour Selection: ");
													selection = ""; 
													acceptable = false;
													while(!acceptable) //Check for acceptable Response
													{
														selection = in.next();
														acceptable = Menu.acceptable(selection, 2); 
														if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
													}
													if(selection.equals("1")) //Yes
													{
														System.out.println("\nOk\n");
														correct = true;
													}
													else if(selection.equals("2")) //No
													{
														System.out.println("\nOk\n");
													}
												}
												correct = false;
												while(!correct) //Price
												{
													System.out.print("New Showing's Price: \n1. $2 \n2. $4 \n3. $6 \n4. $8 \n5. $10 \nYour Selection: ");
													selection = "";
													acceptable = false;
													while(!acceptable) //Check for acceptable Response
													{
														selection = in.next();
														acceptable = Menu.acceptable(selection, 5); 
														if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
													}
													if(selection.equals("1")) //2
													{
														newPrice = 2;
													}
													else if(selection.equals("2")) //4
													{
														newPrice = 4;
													}
													else if(selection.equals("3")) //6
													{
														newPrice = 6;
													}
													else if(selection.equals("4")) //8
													{
														newPrice = 8;
													}
													else if(selection.equals("5")) //10
													{
														newPrice = 10;
													}
													System.out.println("Ok, the new price will be: $" + newPrice);
													System.out.print("Is that correct? \n1. Yes \n2. No \nYour Selection: ");
													selection = ""; 
													acceptable = false;
													while(!acceptable) //Check for acceptable Response
													{
														selection = in.next();
														acceptable = Menu.acceptable(selection, 2); 
														if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
													}
													if(selection.equals("1")) //Yes
													{
														System.out.println("\nOk\n");
														correct = true;
													}
													else if(selection.equals("2")) //no
													{
														System.out.println("\nOk\n");
													}
												}
												correct = false;
												while(!correct) //Theater
												{
													System.out.print("New Showing's Theater: \n1. Theater 1 \n2. Theater 2 \n3. Theater 3 \n4. Theater 4 \n5. Theater 5 \nYour Selection: ");
													selection = "";
													acceptable = false;
													while(!acceptable) //Check for acceptable Response
													{
														selection = in.next();
														acceptable = Menu.acceptable(selection, 5); 
														if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
													}
													if(selection.equals("1")) //1
													{
														newTheater = 1;
													}
													else if(selection.equals("2")) //2
													{
														newTheater = 2;
													}
													else if(selection.equals("3")) //3
													{
														newTheater = 3;
													}
													else if(selection.equals("4")) //4
													{
														newTheater = 4;
													}
													else if(selection.equals("5")) //5
													{
														newTheater = 5;
													}
													System.out.println("Ok, the new theater will be: Theater " + newTheater);
													System.out.print("Is that correct? \n1. Yes \n2. No \nYour Selection: ");
													selection = ""; 
													acceptable = false;
													while(!acceptable) //Check for acceptable Response
													{
														selection = in.next();
														acceptable = Menu.acceptable(selection, 2); 
														if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
													}
													if(selection.equals("1")) //Yes
													{
														System.out.println("\nOk\n");
														correct = true;
													}
													else if(selection.equals("2")) //no
													{
														System.out.println("\nOk\n");
													}
												}
												correct = false;
												while(!correct)
												{
													Showing newShowing = new Showing(newDate, newTime, movieTheater.getTheaters().get(newTheater - 1), 
															movieTheater.browseMovies().get(i).getTitle(), movieTheater.getTheaters().get(newTheater - 1).getSeats(), newPrice);
													System.out.println(newDate + "\n" + newTime + "\n" + newShowing.getTheater() + "\n$" + newPrice + "\nThis showing will now be posted.");
													System.out.print("Is that ok? \n1. Yes \n2. No \nYour Selection: ");
													selection = ""; 
													acceptable = false;
													while(!acceptable) //Check for acceptable Response
													{
														selection = in.next();
														acceptable = Menu.acceptable(selection, 2); 
														if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
													}
													if(selection.equals("1")) //Yes
													{
														movieTheater.getTheaters().get(newTheater - 1).addShowing(newShowing, "Theater" + newTheater + "Showings.txt");
														System.out.println("Ok, showing has been posted!\n");
														correct = true;
													}
													else if(selection.equals("2")) //No
													{
														System.out.println("Are you sure? Leaving now will discard all information for this showing.");
														System.out.print("\n1. Yes \n2. No \nYour Selection: ");
														selection = ""; 
														acceptable = false;
														while(!acceptable) //Check for acceptable Response
														{
															selection = in.next();
															acceptable = Menu.acceptable(selection, 2); 
															if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
														}
														if(selection.equals("1")) //Yes
														{
															correct = true;
															System.out.println("\nOk\n");
														}
														else if(selection.equals("2")) //No
														{
															System.out.println("\nOk\n");
														}
													}
												}
						///**END ADD SHOWING MENU**///
											}
											else if(selection.equals("3")) //Delete This Movie
											{
						///**DELETE MOVIE**///
												System.out.println("\nAre you sure you would like to Delete this movie? \n*This action will be FINAL and PERMANENT!*");
												System.out.print("1. YES \n2. NO \nYour Selection: ");
												selection = ""; 
												acceptable = false;
												while(!acceptable) //Check for acceptable Response
												{
													selection = in.next();
													acceptable = Menu.acceptable(selection, 2);
													if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
												}
												if(selection.equals("1")) //YES
												{
													System.out.println("\nMovie Deleted!\n");
													movieTheater.deleteMovie(movieTheater.browseMovies().get(i).getTitle(), "Movies.txt");
													
													backToEditMoviesMenu = true;
													backToAdminMenu = true;
													System.out.println("\n");
												}
												else if(selection.equals("2")) //NO
												{
													System.out.println("\nOk!\n");
												}
						///**END DELETE MOVIE**///
											}
											else if(selection.equals("4")) //Back
											{
												backToEditMoviesMenu = true;
												System.out.println("\n");
											}
										}
					///**END EDIT MENU**///
									}
									else if(selection.equals("3")) //Back
									{
										backToAdminMenu = true;
										System.out.println("\n");
									}
								}
								else //Movies in the middle of the list (Can Go Forwards and Backwards)
								{
									System.out.println("Movie: (" + (i + 1) + ")");
									System.out.println(movieTheater.browseMovies().get(i));
									
									System.out.print("1. Next \n2. Previous \n3. Edit Movie \n4. Go Back \nYour Selection: ");
									selection = ""; 
									acceptable = false;
									while(!acceptable) //Check for acceptable Response
									{
										selection = in.next();
										acceptable = Menu.acceptable(selection, 4);
										if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
									}
									if(selection.equals("1")) //Next
									{
										i++;
										System.out.println("");
									}
									else if(selection.equals("2")) //Previous
									{
										i--;
										System.out.println("");
									}
									else if(selection.equals("3")) //Edit Movie
									{
					///**EDIT MENU**///
										boolean backToEditMoviesMenu = false; //exit boolean
										while(!backToEditMoviesMenu)
										{
											System.out.println("\nEdit Movie: (" + (i + 1) + ")"); //Display Page Number
											System.out.println(movieTheater.browseMovies().get(i)); //Display Movie
											
											System.out.print("1. Update Movie Details \n2. Add Showing \n3. Delete This Movie \n4. Go Back \nYour Selection: "); //Display Options
											selection = ""; 
											acceptable = false;
											while(!acceptable) //Check for acceptable Response
											{
												selection = in.next();
												acceptable = Menu.acceptable(selection, 4);
												if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
											}
											if(selection.equals("1")) //Update Details
											{
						///**UPDATE DETAILS MENU**///
												//New Information (Defaults to the old info)
												String newTitle = movieTheater.browseMovies().get(i).getTitle();
												String newDescription = movieTheater.browseMovies().get(i).getDescription();
												String newCast = movieTheater.browseMovies().get(i).getCast();
												String newGenre = movieTheater.browseMovies().get(i).getGenre();
												String newStatus = movieTheater.browseMovies().get(i).getStatus();
												
												boolean backToEditMenu = false; //Exit boolean
												while(!backToEditMenu)
												{
													System.out.println("\nWhat would you like to update?");
													System.out.print("1. Title \n2. Description \n3. Cast \n4. Genre \n5. Released Status \n6. Go Back \nYour Selection: ");
													selection = ""; 
													acceptable = false;
													while(!acceptable) //Check for acceptable Response
													{
														selection = in.next();
														acceptable = Menu.acceptable(selection, 6); 
														if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
													}
													if(selection.equals("1")) //Title
													{
														System.out.print("\nWhat would you like the new title to be? \nNew Title: ");
														in.nextLine();
														newTitle = in.nextLine();
														System.out.println("Ok, the new title will be: " + newTitle);
													}
													else if(selection.equals("2")) //Description
													{
														System.out.print("\nWhat would you like the new description to be? \nNew Description: ");
														in.nextLine();
														newDescription = in.nextLine();
														System.out.println("Ok, the new description will be: \n" + newDescription);
													}
													else if(selection.equals("3")) //Cast
													{
														System.out.print("\nWhat would you like the new cast to be? \nNew Cast: ");
														in.nextLine();
														newCast = in.nextLine();
														System.out.println("Ok, the new cast will be: \n" + newCast);
													}
													else if(selection.equals("4")) //Genre
													{
														System.out.print("\nWhat would you like the new genre to be? \nNew Genre: ");
														in.nextLine();
														newGenre = in.nextLine();
														System.out.println("Ok, the new genre will be: \n" + newGenre);
													}
													else if(selection.equals("5")) //Released Status 
													{
														System.out.print("\nWhat is the release date of this movie (yyyy-mm-dd)? \nRelease Date: ");
														String releaseDate = in.next();
														System.out.println("Ok, the release will be: \n" + releaseDate);
														String currentDate = "" + java.time.LocalDate.now();
														try {newStatus = Movie.status(currentDate, releaseDate);} 
														catch (ParseException e) {e.printStackTrace();}
													}
													else if(selection.equals("6")) //Back
													{
														movieTheater.editMovie(movieTheater.browseMovies().get(i).getTitle(), newGenre, newTitle, newStatus, 
																               newDescription, newCast, "Movies.txt"); 
														backToEditMenu = true; //Go back
														System.out.println();
													}
												}
						///**END UPDATE DETAILS MENU**///
											}
											else if(selection.equals("2")) //Add Showing
											{
						///**ADD SHOWING MENU**///
												String newDate = "";
												String newTime = "";
												int newTheater = 0;
												double newPrice = 0;
												boolean correct;
												
												System.out.println("Add a new Showing:");
												correct = false;
												while(!correct) //Date
												{
													System.out.print("New showing's Date (DD/MM/YY): ");
													newDate = in.next();
													System.out.println("Ok, the new Date will be: " + newDate);
													System.out.print("Is that correct? \n1. Yes \n2. No \nYour Selection: ");
													selection = ""; 
													acceptable = false;
													while(!acceptable) //Check for acceptable Response
													{
														selection = in.next();
														acceptable = Menu.acceptable(selection, 2); 
														if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
													}
													if(selection.equals("1")) //Yes
													{
														System.out.println("\nOk\n");
														correct = true;
													}
													else if(selection.equals("2")) //No
													{
														System.out.println("\nOk\n");
													}
												}
												correct = false;
												while(!correct) //Time
												{
													System.out.print("New Showing's Time (HH:MM): ");
													newTime = in.next();
													System.out.println("Ok, the new time will be: " + newTime);
													System.out.print("Is that correct? \n1. Yes \n2. No \nYour Selection: ");
													selection = ""; 
													acceptable = false;
													while(!acceptable) //Check for acceptable Response
													{
														selection = in.next();
														acceptable = Menu.acceptable(selection, 2); 
														if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
													}
													if(selection.equals("1")) //Yes
													{
														System.out.println("\nOk\n");
														correct = true;
													}
													else if(selection.equals("2")) //No
													{
														System.out.println("\nOk\n");
													}
												}
												correct = false;
												while(!correct) //Price
												{
													System.out.print("New Showing's Price: \n1. $2 \n2. $4 \n3. $6 \n4. $8 \n5. $10 \nYour Selection: ");
													selection = "";
													acceptable = false;
													while(!acceptable) //Check for acceptable Response
													{
														selection = in.next();
														acceptable = Menu.acceptable(selection, 5); 
														if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
													}
													if(selection.equals("1")) //2
													{
														newPrice = 2;
													}
													else if(selection.equals("2")) //4
													{
														newPrice = 4;
													}
													else if(selection.equals("3")) //6
													{
														newPrice = 6;
													}
													else if(selection.equals("4")) //8
													{
														newPrice = 8;
													}
													else if(selection.equals("5")) //10
													{
														newPrice = 10;
													}
													System.out.println("Ok, the new price will be: $" + newPrice);
													System.out.print("Is that correct? \n1. Yes \n2. No \nYour Selection: ");
													selection = ""; 
													acceptable = false;
													while(!acceptable) //Check for acceptable Response
													{
														selection = in.next();
														acceptable = Menu.acceptable(selection, 2); 
														if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
													}
													if(selection.equals("1")) //Yes
													{
														System.out.println("\nOk\n");
														correct = true;
													}
													else if(selection.equals("2")) //no
													{
														System.out.println("\nOk\n");
													}
												}
												correct = false;
												while(!correct) //Theater
												{
													System.out.print("New Showing's Theater: \n1. Theater 1 \n2. Theater 2 \n3. Theater 3 \n4. Theater 4 \n5. Theater 5 \nYour Selection: ");
													selection = "";
													acceptable = false;
													while(!acceptable) //Check for acceptable Response
													{
														selection = in.next();
														acceptable = Menu.acceptable(selection, 5); 
														if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
													}
													if(selection.equals("1")) //1
													{
														newTheater = 1;
													}
													else if(selection.equals("2")) //2
													{
														newTheater = 2;
													}
													else if(selection.equals("3")) //3
													{
														newTheater = 3;
													}
													else if(selection.equals("4")) //4
													{
														newTheater = 4;
													}
													else if(selection.equals("5")) //5
													{
														newTheater = 5;
													}
													System.out.println("Ok, the new theater will be: Theater " + newTheater);
													System.out.print("Is that correct? \n1. Yes \n2. No \nYour Selection: ");
													selection = ""; 
													acceptable = false;
													while(!acceptable) //Check for acceptable Response
													{
														selection = in.next();
														acceptable = Menu.acceptable(selection, 2); 
														if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
													}
													if(selection.equals("1")) //Yes
													{
														System.out.println("\nOk\n");
														correct = true;
													}
													else if(selection.equals("2")) //no
													{
														System.out.println("\nOk\n");
													}
												}
												correct = false;
												while(!correct)
												{
													Showing newShowing = new Showing(newDate, newTime, movieTheater.getTheaters().get(newTheater - 1), 
															movieTheater.browseMovies().get(i).getTitle(), movieTheater.getTheaters().get(newTheater - 1).getSeats(), newPrice);
													System.out.println(newDate + "\n" + newTime + "\n" + newShowing.getTheater() + "\n$" + newPrice + "\nThis showing will now be posted.");
													System.out.print("Is that ok? \n1. Yes \n2. No \nYour Selection: ");
													selection = ""; 
													acceptable = false;
													while(!acceptable) //Check for acceptable Response
													{
														selection = in.next();
														acceptable = Menu.acceptable(selection, 2); 
														if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
													}
													if(selection.equals("1")) //Yes
													{
														movieTheater.getTheaters().get(newTheater - 1).addShowing(newShowing, "Theater" + newTheater + "Showings.txt");
														System.out.println("Ok, showing has been posted!\n");
														correct = true;
													}
													else if(selection.equals("2")) //No
													{
														System.out.println("Are you sure? Leaving now will discard all information for this showing.");
														System.out.print("\n1. Yes \n2. No \nYour Selection: ");
														selection = ""; 
														acceptable = false;
														while(!acceptable) //Check for acceptable Response
														{
															selection = in.next();
															acceptable = Menu.acceptable(selection, 2); 
															if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
														}
														if(selection.equals("1")) //Yes
														{
															correct = true;
															System.out.println("\nOk\n");
														}
														else if(selection.equals("2")) //No
														{
															System.out.println("\nOk\n");
														}
													}
												}
						///**END ADD SHOWING MENU**///
											}
											else if(selection.equals("3")) //Delete This Movie
											{
						///**DELETE MOVIE**///
												System.out.println("\nAre you sure you would like to Delete this movie? \n*This action will be FINAL and PERMANENT!*");
												System.out.print("1. YES \n2. NO \nYour Selection: ");
												selection = ""; 
												acceptable = false;
												while(!acceptable) //Check for acceptable Response
												{
													selection = in.next();
													acceptable = Menu.acceptable(selection, 2);
													if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
												}
												if(selection.equals("1")) //YES
												{
													System.out.println("\nMovie Deleted!\n");
													movieTheater.deleteMovie(movieTheater.browseMovies().get(i).getTitle(), "Movies.txt");
													
													backToEditMoviesMenu = true;
													backToAdminMenu = true;
													System.out.println("\n");
												}
												else if(selection.equals("2")) //NO
												{
													System.out.println("\nOk!\n");
												}
						///**END DELETE MOVIE**///
											}
											else if(selection.equals("4")) //Back
											{
												backToEditMoviesMenu = true;
												System.out.println("\n");
											}
										}
					///**END EDIT MENU**///
									}
									else if(selection.equals("4")) //Back
									{
										backToAdminMenu = true;
										System.out.println("\n");
									}
								}
				///**END MOVIE VIEWER**///
							}
			///**END EDIT MOVIES MENU**///
						}
						else if(selection.equals("2")) //Post New Movie
						{
			///**POST MENU**///
							String newTitle = "";
							String newDescription = "";
							String newCast = "";
							String newGenre = "";
							boolean correct;
							
							System.out.println("\nPost a new Movie:");
							correct = false;
							while(!correct) //Title
							{
								System.out.print("New Movie's Title: ");
								in.nextLine();
								newTitle = in.nextLine();
								System.out.println("Ok, the new title will be: \n" + newTitle);
								System.out.print("Is that correct? \n1. Yes \n2. No \nYour Selection: ");
								selection = ""; 
								acceptable = false;
								while(!acceptable) //Check for acceptable Response
								{
									selection = in.next();
									acceptable = Menu.acceptable(selection, 2); 
									if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
								}
								if(selection.equals("1")) //Yes
								{
									System.out.println("\nOk\n");
									correct = true;
								}
								else if(selection.equals("2")) //no
								{
									System.out.println("\nOk\n");
								}
							}
							correct = false;
							while(!correct) //Description
							{
								System.out.print("New Movie's Description: ");
								in.nextLine();
								newDescription = in.nextLine();
								System.out.println("Ok, the new description will be: \n" + newDescription);
								System.out.print("Is that correct? \n1. Yes \n2. No \nYour Selection: ");
								selection = ""; 
								acceptable = false;
								while(!acceptable) //Check for acceptable Response
								{
									selection = in.next();
									acceptable = Menu.acceptable(selection, 2); 
									if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
								}
								if(selection.equals("1")) //Yes
								{
									System.out.println("\nOk\n");
									correct = true;
								}
								else if(selection.equals("2")) //no
								{
									System.out.println("\nOk\n");
								}
							}
							correct = false;
							while(!correct) //Cast
							{
								System.out.print("New Movie's Cast: ");
								in.nextLine();
								newCast = in.nextLine();
								System.out.println("Ok, the new cast will be: \n" + newCast);
								System.out.print("Is that correct? \n1. Yes \n2. No \nYour Selection: ");
								selection = ""; 
								acceptable = false;
								while(!acceptable) //Check for acceptable Response
								{
									selection = in.next();
									acceptable = Menu.acceptable(selection, 2); 
									if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
								}
								if(selection.equals("1")) //Yes
								{
									System.out.println("\nOk\n");
									correct = true;
								}
								else if(selection.equals("2")) //no
								{
									System.out.println("\nOk\n");
								}
							}
							correct = false;
							while(!correct) //Genre
							{
								System.out.print("New Movie's Genre: ");
								in.nextLine();
								newGenre = in.nextLine();
								System.out.println("Ok, the new genre will be: \n" + newGenre);
								System.out.print("Is that correct? \n1. Yes \n2. No \nYour Selection: ");
								selection = ""; 
								acceptable = false;
								while(!acceptable) //Check for acceptable Response
								{
									selection = in.next();
									acceptable = Menu.acceptable(selection, 2); 
									if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
								}
								if(selection.equals("1")) //Yes
								{
									System.out.println("\nOk\n");
									correct = true;
								}
								else if(selection.equals("2")) //no
								{
									System.out.println("\nOk\n");
								}
							}
							correct = false;
							while(!correct)
							{
								System.out.println(newTitle + "\n" + newDescription + "\n" + newCast + "\n" + newGenre + "\nThis movie will now be posted.");
								System.out.print("Is that ok? \n1. Yes \n2. No \nYour Selection: ");
								selection = ""; 
								acceptable = false;
								while(!acceptable) //Check for acceptable Response
								{
									selection = in.next();
									acceptable = Menu.acceptable(selection, 2); 
									if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
								}
								if(selection.equals("1")) //Yes
								{
									movieTheater.postMovie(newGenre, newTitle, "Upcoming", newDescription, newCast, "Movies.txt"); 
									System.out.println("\nOk, movie has been posted! You can change its details in the \"Edit Movie\" Menu\n");
									correct = true;
								}
								else if(selection.equals("2")) //No
								{
									System.out.println("\nAre you sure? Leaving now will discard all information for this movie.");
									System.out.print("1. Yes \n2. No \nYour Selection: ");
									selection = ""; 
									acceptable = false;
									while(!acceptable) //Check for acceptable Response
									{
										selection = in.next();
										acceptable = Menu.acceptable(selection, 2); 
										if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
									}
									if(selection.equals("1")) //Yes
									{
										correct = true;
										System.out.println("\nOk\n");
									}
									else if(selection.equals("2")) //No
									{
										System.out.println("\nOk\n");
									}
								}
							}
			///**END POST MENU**///
						}
						else if(selection.equals("3")) //Logout
						{
							System.out.println("\nAre You sure you would like to logout?");
							System.out.print("1. Yes \n2. No \nYour Selection: ");
							selection = ""; 
							acceptable = false;
							while(!acceptable) //Check for acceptable Response
							{
								selection = in.next();
								acceptable = Menu.acceptable(selection, 2);
								if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
							}
							if(selection.equals("1")) //Yes
							{
								System.out.println("\nGoodbye!\n");
								backToLoginMenu = true;
							}
							else if(selection.equals("2")) //No
							{
								System.out.println("\nOk!\n");
							}
						}
					}
		///**END ADMIN MENU**///
				} 
				else if(userLogin)
				{
					User user = new User();
					try {user = User.login(person.getUsername(), person.getPassword());} 
					catch (FileNotFoundException e1) {e1.printStackTrace();} 
		///**USER MENU**///
					boolean backToLoginMenu = false;
					System.out.println("Hi " + user.getUsername() + ", welcome to Movie Booking! \n"); 
					while(!backToLoginMenu)
					{
						System.out.println("What would you like to do today?");//Prompt User
						System.out.print("1. Browse Movies \n2. Book a Movie \n3. View Your Current Tickets \n4. Your Account \n5. Logout \nYour Selection: ");
						selection = ""; 
						acceptable = false;
						while(!acceptable) //Check for acceptable Response
						{
							selection = in.next();
							acceptable = Menu.acceptable(selection, 5);
							if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
						}
						if(selection.equals("1")) //Browse Movies
						{
			///**BROWSE MOVIES MENU**///
							
							//Filters
							int status = 0;
							String titleIn = "";
							String genreIn = "";
							double ratingIn = 0;
							
							boolean backToUserMenu = false;
							while(!backToUserMenu)
							{
								System.out.println("\nBrowse Movies! \n");
								System.out.print("1. Browse Movies \n2. Filter Movies \n3. Go Back \nYour Selection: ");
								selection = ""; 
								acceptable = false;
								while(!acceptable) //Check for acceptable Response
								{
									selection = in.next();
									acceptable = Menu.acceptable(selection, 3);
									if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
								}
								if(selection.equals("1")) //Browse Movies With Current Filters
								{
				///**MOVIE VIEWER**///
									boolean backToBrowseMenu = false;
									int i = 0;
									
									while(!backToBrowseMenu)
									{
										if(movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).size() == 0) //No Movies in the List
										{
											System.out.println("\n*No movies match your current search parameters!* \n"); //Display "Error"
											backToBrowseMenu = true; //Go Back
										}
										else if(movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).size() == 1) //Only One Movie in the List
										{
											System.out.println("Browse Movies: (" + (i + 1) + ")"); //Display Page Number
											System.out.println(movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i) + "\n"); //Display Movie
											
											System.out.print("1. Rate Movie \n2. Go Back \nYour Selection: "); //Display Options
											selection = ""; 
											acceptable = false;
											while(!acceptable) //Check for acceptable Response
											{
												selection = in.next();
												acceptable = Menu.acceptable(selection, 2);
												if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
											}
											if(selection.equals("1")) //Rate Movie
											{
					///**RATE MOVIE MENU**///
												boolean seenThisMovie = false;
												for(int j = 0; j < user.getPurchases().size(); j++)
												{
													String oldMovieTitle = user.getPurchases().get(j).getTitle();
													String thisMovieTitle = movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).getTitle();
													if(thisMovieTitle.equals(oldMovieTitle))
													{seenThisMovie = true;}
												}
												if(!movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).isReleased()) //Check if Movie is released
												{System.out.println("\n*This movie hasn't been released yet!*");}
												else if(seenThisMovie)  //Check if User Has seen this movie before
												{
													System.out.println("\nWhat would you rate this movie?");
													System.out.println("1. 1 Star \n2. 2 Stars \n3. 3 Stars \n4. 4 Stars \n5. 5 Stars \n6. No Stars \n7. Nevermind");
													System.out.print("Your Selection: ");
													selection = ""; acceptable = false;
													while(!acceptable) //Check for acceptable Response
													{
														selection = in.next();
														acceptable = Menu.acceptable(selection, 7);
														if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
													}
													if(selection.equals("1")) 
													{movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).addRating(1);}
													else if(selection.equals("2"))
													{movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).addRating(2);}
													else if(selection.equals("3"))
													{movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).addRating(3);}
													else if(selection.equals("4"))
													{movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).addRating(4);}
													else if(selection.equals("5"))
													{movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).addRating(5);}
													else if(selection.equals("6"))
													{movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).addRating(0);}
												}
												else //User Hasn't seen this movie
												{System.out.println("\n*You haven't seen this movie!*");}
												System.out.println("\n");
					///**END RATE MOVIE MENU**///
											}
											else if(selection.equals("2")) //Back
											{
												backToBrowseMenu = true;
												System.out.println("\n");
											}
										}
										else if(i == 0) //First Movie in list (Cannot Go Backwards)
										{
											System.out.println("Browse Movies: (" + (i + 1) + ")");
											System.out.println(movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i) + "\n");
											
											System.out.print("1. Next \n2. Rate Movie \n3. Go Back \nYour Selection: ");
											selection = ""; 
											acceptable = false;
											while(!acceptable) //Check for acceptable Response
											{
												selection = in.next();
												acceptable = Menu.acceptable(selection, 3);
												if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
											}
											if(selection.equals("1")) //Next
											{
												i++;
												System.out.println("");
											}
											else if(selection.equals("2")) //Rate Movie
											{
					///**RATE MOVIE MENU**///
												boolean seenThisMovie = false;
												for(int j = 0; j < user.getPurchases().size(); j++)
												{
													String oldMovieTitle = user.getPurchases().get(j).getTitle();
													String thisMovieTitle = movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).getTitle();
													if(thisMovieTitle.equals(oldMovieTitle))
													{seenThisMovie = true;}
												}
												if(!movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).isReleased()) //Check if Movie is released
												{System.out.println("\n*This movie hasn't been released yet!*");}
												else if(seenThisMovie)  //Check if User Has seen this movie before
												{
													System.out.println("\nWhat would you rate this movie?");
													System.out.println("1. 1 Star \n2. 2 Stars \n3. 3 Stars \n4. 4 Stars \n5. 5 Stars \n6. No Stars \n7. Nevermind");
													System.out.print("Your Selection: ");
													selection = ""; acceptable = false;
													while(!acceptable) //Check for acceptable Response
													{
														selection = in.next();
														acceptable = Menu.acceptable(selection, 7);
														if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
													}
													if(selection.equals("1")) 
													{movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).addRating(1);}
													else if(selection.equals("2"))
													{movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).addRating(2);}
													else if(selection.equals("3"))
													{movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).addRating(3);}
													else if(selection.equals("4"))
													{movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).addRating(4);}
													else if(selection.equals("5"))
													{movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).addRating(5);}
													else if(selection.equals("6"))
													{movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).addRating(0);}
												}
												else //User Hasn't seen this movie
												{System.out.println("\n*You haven't seen this movie!*");}
												System.out.println("\n");
					///**END RATE MOVIE MENU**///
											}
											else if(selection.equals("3")) //Back
											{
												backToBrowseMenu = true;
												System.out.println("\n");
											}
										}
										else if((i + 1) == movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).size()) //Last Movie in list (Cannot Go Forwards)
										{
											System.out.println("Browse Movies: (" + (i + 1) + ")");
											System.out.println(movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i) + "\n");
											
											System.out.print("1. Previous \n2. Rate Movie \n3. Go Back \nYour Selection: ");
											selection = ""; 
											acceptable = false;
											while(!acceptable) //Check for acceptable Response
											{
												selection = in.next();
												acceptable = Menu.acceptable(selection, 3);
												if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
											}
											if(selection.equals("1")) //Previous
											{
												i--;
												System.out.println("");
											}
											else if(selection.equals("2")) //Rate Movie
											{
					///**RATE MOVIE MENU**///
												boolean seenThisMovie = false;
												for(int j = 0; j < user.getPurchases().size(); j++)
												{
													String oldMovieTitle = user.getPurchases().get(j).getTitle();
													String thisMovieTitle = movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).getTitle();
													if(thisMovieTitle.equals(oldMovieTitle))
													{seenThisMovie = true;}
												}
												if(!movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).isReleased()) //Check if Movie is released
												{System.out.println("\n*This movie hasn't been released yet!*");}
												else if(seenThisMovie)  //Check if User Has seen this movie before
												{
													System.out.println("\nWhat would you rate this movie?");
													System.out.println("1. 1 Star \n2. 2 Stars \n3. 3 Stars \n4. 4 Stars \n5. 5 Stars \n6. No Stars \n7. Nevermind");
													System.out.print("Your Selection: ");
													selection = ""; acceptable = false;
													while(!acceptable) //Check for acceptable Response
													{
														selection = in.next();
														acceptable = Menu.acceptable(selection, 7);
														if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
													}
													if(selection.equals("1")) 
													{movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).addRating(1);}
													else if(selection.equals("2"))
													{movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).addRating(2);}
													else if(selection.equals("3"))
													{movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).addRating(3);}
													else if(selection.equals("4"))
													{movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).addRating(4);}
													else if(selection.equals("5"))
													{movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).addRating(5);}
													else if(selection.equals("6"))
													{movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).addRating(0);}
												}
												else //User Hasn't seen this movie
												{System.out.println("\n*You haven't seen this movie!*");}
												System.out.println("\n");
					///**END RATE MOVIE MENU**///
											}
											else if(selection.equals("3")) //Back
											{
												backToBrowseMenu = true;
												System.out.println("\n");
											}
										}
										else //Movies in the middle of the list (Can Go Forwards and Backwards)
										{
											System.out.println("Browse Movies: (" + (i + 1) + ")");
											System.out.println(movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i) + "\n");
											
											System.out.print("1. Next \n2. Previous \n3. Rate Movie \n4. Go Back \nYour Selection: ");
											selection = ""; 
											acceptable = false;
											while(!acceptable) //Check for acceptable Response
											{
												selection = in.next();
												acceptable = Menu.acceptable(selection, 4);
												if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
											}
											if(selection.equals("1")) //Next
											{
												i++;
												System.out.println("");
											}
											else if(selection.equals("2")) //Previous
											{
												i--;
												System.out.println("");
											}
											else if(selection.equals("3")) //Rate Movie
											{
					///**RATE MOVIE MENU**///
												boolean seenThisMovie = false;
												for(int j = 0; j < user.getPurchases().size(); j++)
												{
													String oldMovieTitle = user.getPurchases().get(j).getTitle();
													String thisMovieTitle = movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).getTitle();
													if(thisMovieTitle.equals(oldMovieTitle))
													{seenThisMovie = true;}
												}
												if(!movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).isReleased()) //Check if Movie is released
												{System.out.println("\n*This movie hasn't been released yet!*");}
												else if(seenThisMovie)  //Check if User Has seen this movie before
												{
													System.out.println("\nWhat would you rate this movie?");
													System.out.println("1. 1 Star \n2. 2 Stars \n3. 3 Stars \n4. 4 Stars \n5. 5 Stars \n6. No Stars \n7. Nevermind");
													System.out.print("Your Selection: ");
													selection = ""; acceptable = false;
													while(!acceptable) //Check for acceptable Response
													{
														selection = in.next();
														acceptable = Menu.acceptable(selection, 7);
														if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
													}
													if(selection.equals("1")) 
													{movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).addRating(1);}
													else if(selection.equals("2"))
													{movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).addRating(2);}
													else if(selection.equals("3"))
													{movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).addRating(3);}
													else if(selection.equals("4"))
													{movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).addRating(4);}
													else if(selection.equals("5"))
													{movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).addRating(5);}
													else if(selection.equals("6"))
													{movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).addRating(0);}
												}
												else //User Hasn't seen this movie
												{System.out.println("\n*You haven't seen this movie!*");}
												System.out.println("\n");
					///**END RATE MOVIE MENU**///
											}
											else if(selection.equals("4")) //Back
											{
												backToBrowseMenu = true;
												System.out.println("\n");
											}
										}
									}
				///**END MOVIE VIEWER**///
								}
								else if(selection.equals("2")) //Filter Settings
								{
				///**FILTER MENU**///
									boolean backToBrowseMenu = false;
									System.out.println("\nFilter Movies! ");
									while(!backToBrowseMenu)
									{
										//Display Current Filters
										System.out.println("\nCurrent Filters: "); 
										//Current Title
										if(titleIn.length() == 0)
										{System.out.println(" Title: None");}
										else
										{System.out.println(" Title: Filter by Movie Titles with Keyword \"" + titleIn + "\"");}
										//Current Genre
										if(genreIn.length() == 0)
										{System.out.println(" Genre: None");}
										else
										{System.out.println(" Genre: Filter by Movie Genres with Keyword \"" + genreIn + "\"");}
										//Current Released Status
										if(status == 1)
										{System.out.println(" Released Status: Released Movies only");}
										else if(status == 2)
										{System.out.println(" Released Status: Unreleased Movies only");}
										else
										{System.out.println(" Released Status: None");}
										//Current Ratings
										System.out.println(" Ratings: Filter by Movies with Average Rating greater than " + ratingIn + " Stars");
										
										//Display Choices
										System.out.print("\n1. New Filters \n2. Reset Current Filters \n3. Back \nYour Selection: ");
										selection = ""; 
										acceptable = false;
										while(!acceptable) //Check for acceptable Response
										{
											selection = in.next();
											acceptable = Menu.acceptable(selection, 3);
											if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
										}
										if(selection.equals("1")) //Apply New Filters
										{
					///**NEW FILTERS MENU**///
											System.out.println("\nEnter your desired Filter perameters when prompted.\n");
											
											//Title
											System.out.print("Title: \nDo you wish to filter the Movie List by Title? \n\n1. Yes \n2. No \nYour Selection: ");
											selection = ""; 
											acceptable = false;
											while(!acceptable) //Check for acceptable Response
											{
												selection = in.next();
												acceptable = Menu.acceptable(selection, 2);
												if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
											}
											if(selection.equals("1")) //Yes
											{
												System.out.print("What Keyword would you like to filter by: ");
												titleIn = in.next();
												System.out.println("Ok!\n");
											}
											else if(selection.equals("2")) //No
											{
												titleIn = "";
												System.out.println("Ok!\n");
											}
											
											//Genre
											System.out.print("Genre: \nDo you wish to filter the Movie List by Genre? \n\n1. Yes \n2. No \nYour Selection: ");
											selection = ""; 
											acceptable = false;
											while(!acceptable) //Check for acceptable Response
											{
												selection = in.next();
												acceptable = Menu.acceptable(selection, 2);
												if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
											}
											if(selection.equals("1")) //Yes
											{
												System.out.print("What Keyword would you like to filter by: ");
												genreIn = in.next();
												System.out.println("Ok!\n");
											}
											else if(selection.equals("2")) //No
											{
												genreIn = "";
												System.out.println("Ok!\n");
											}
											
											//Status
											System.out.print("Released Status: \nDo you wish to view "
													+ "\n\n1. Only Released Movies \n2. Only Unreleased Movies \n3. Doesn't Matter \nYour Selection: "); //Display Choices
											selection = ""; 
											acceptable = false;
											while(!acceptable) //Check for acceptable Response
											{
												selection = in.next();
												acceptable = Menu.acceptable(selection, 3);
												if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
											}
											if(selection.equals("1")) //Only Released
											{
												status = 1;
												System.out.println("Ok!\n");
											}
											else if(selection.equals("2")) //Only Unreleased
											{
												status = 2;
												System.out.println("Ok!\n");
											}
											else if(selection.equals("3")) //Doesn't Matter
											{
												status = 3;
												System.out.println("Ok!\n");
											}
											
											//Rating
											System.out.println("Average Ratings: \nDo you wish to see movies with Average Ratings greater than"); //Display Choices
											System.out.print("\n1. 1 Stars \n2. 2 Stars \n3. 3 Stars \n4. 4 Stars \n5. Doesn't Matter \nYour Selection: ");
											selection = ""; acceptable = false;
											while(!acceptable) //Check for acceptable Response
											{
												selection = in.next();
												acceptable = Menu.acceptable(selection, 5);
												if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
											}
											if(selection.equals("1")) //1 Stars
											{
												ratingIn = 1;
												System.out.println("Ok!\n");
											}
											else if(selection.equals("2")) //2 Stars
											{
												ratingIn = 2;
												System.out.println("Ok!\n");
											}
											else if(selection.equals("3")) //3 Stars
											{
												ratingIn = 3;
												System.out.println("Ok!\n");
											}
											else if(selection.equals("4")) //4 Stars
											{
												ratingIn = 4;
												System.out.println("Ok!\n");
											}
											else if(selection.equals("5")) //Any Rating
											{
												ratingIn = 0;
												System.out.println("Ok!\n");
											}
											System.out.println("");
					///**END NEW FILTERS MENU**///
										}
										else if(selection.equals("2")) //Reset Current Filters
										{
											titleIn = "";
											genreIn = "";
											status = 0;
											ratingIn = 0;
											System.out.println("\n*Filters Reset!*");
										}
										else if(selection.equals("3")) //Go Back To Browse Menu
										{
											backToBrowseMenu = true;
											System.out.println("\n");
										}
									}
				///**END FILTER MENU**///
								}
								else if(selection.equals("3")) //Go Back to User Menu
								{
									backToUserMenu = true;
									System.out.println("\n");
								}
							}
			///**END BROWSE MOVIES MENU**///
						}
						else if(selection.equals("2")) //Book Movie
						{
			///**BOOK MOVIE MENU**///
							boolean backToBrowseMenu = false;
							int i = 0;
							
							int status = 1;
							String titleIn = "";
							String genreIn = "";
							double ratingIn = 0;
							
							while(!backToBrowseMenu)
							{
				///**MOVIE VIEWER**///
								if(movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).size() == 0) //No Movies in the List
								{
									System.out.println("\n*There are no available movies to book tickets for!* \n"); //Display "Error"
									backToBrowseMenu = true; //Go Back
								}
								else if(movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).size() == 1) //Only One Movie in the List
								{
									System.out.println("Browse Movies: (" + (i + 1) + ")"); //Display Page Number
									System.out.println(movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i) + "\n"); //Display Movie
									
									System.out.print("1. Book Movie \n2. Go Back \nYour Selection: "); //Display Options
									selection = ""; 
									acceptable = false;
									while(!acceptable) //Check for acceptable Response
									{
										selection = in.next();
										acceptable = Menu.acceptable(selection, 2);
										if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
									}
									if(selection.equals("1")) //Book Movie Menu
									{
					///**SHOWING SELECT MENU**///
										boolean backToMovieViewer = false;
										while(!backToMovieViewer)
										{
											System.out.println("Available Showings: ");
											ArrayList<Showing> movieShowings = movieTheater.getShowingsForMovie(movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).getTitle());
											for(int j = 0; j < movieShowings.size(); j++)
											{
												System.out.println((j + 1) + ". " + movieShowings.get(j));
											}
											System.out.print((movieShowings.size() + 1) + ". Go Back \nYour Selection: ");
											selection = "";
											acceptable = false;
											while(!acceptable) //Check for acceptable Response
											{
												selection = in.next();
												acceptable = Menu.acceptable(selection, (movieShowings.size() + 1)); 
												if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
											}
											if(selection.equals("(movieShowings.size() + 1)")) //go back
											{
												backToMovieViewer = true;
											}
											else
											{
												Showing thisShowing = movieShowings.get(Integer.parseInt(selection));
												boolean done = false;
												while(!done)
												{
													System.out.println("What row would you like to sit in: ");
													System.out.print("1. Row A \n2. Row B \n3. Row C \nYour Selection: ");
													selection = "";
													acceptable = false;
													while(!acceptable) //Check for acceptable Response
													{
														selection = in.next();
														acceptable = Menu.acceptable(selection, 3); 
														if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
													}
													if(selection.equals("1")) //Row A
													{
														System.out.println("What seat would you like to sit in: ");
														System.out.print("1. Seat 1 \n2. Seat 2 \n3. Seat 3 \nYour Selection: ");
														selection = "";
														acceptable = false;
														while(!acceptable) //Check for acceptable Response
														{
															selection = in.next();
															acceptable = Menu.acceptable(selection, 3); 
															if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
														}
														if(selection.equals("1")) //Seat 1
														{
															Ticket newTicket = new Ticket(movieTheater.getTheaterName(), 
																                       	  movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).getTitle(), thisShowing.getDate(), 
																                       	  thisShowing.getTime(), thisShowing.getPrice(), thisShowing.getTheater().getTheaterNumber(), "A1");
															user.addCurrentTicket(newTicket);
															System.out.println("Great! Your ticket is reserved! \nTicket Stub: \n" + newTicket);
															System.out.print("Would you like to reserve any more tickets for this showing? \n1. Yes \n2. No \nYour Selection: ");
															selection = "";
															acceptable = false;
															while(!acceptable) //Check for acceptable Response
															{
																selection = in.next();
																acceptable = Menu.acceptable(selection, 2); 
																if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
															}
															if(selection.equals("1")) //yes
															{
																System.out.println("Ok!");
															}
															else if(selection.equals("2")) //no
															{
																System.out.println("See you Soon!");
																done = true;
															}
														}
														else if(selection.equals("2")) //Seat 2
														{
															Ticket newTicket = new Ticket(movieTheater.getTheaterName(), 
																	                      movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).getTitle(), thisShowing.getDate(), 
																	                      thisShowing.getTime(), thisShowing.getPrice(), thisShowing.getTheater().getTheaterNumber(), "A2");
															user.addCurrentTicket(newTicket);
															System.out.println("Great! Your ticket is reserved! \nTicket Stub: \n" + newTicket);
															System.out.print("Would you like to reserve any more tickets for this showing? \n1. Yes \n2. No \nYour Selection: ");
															selection = "";
															acceptable = false;
															while(!acceptable) //Check for acceptable Response
															{
																selection = in.next();
																acceptable = Menu.acceptable(selection, 2); 
																if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
															}
															if(selection.equals("1")) //yes
															{
																System.out.println("Ok!");
															}
															else if(selection.equals("2")) //no
															{
																System.out.println("See you Soon!");
																done = true;
															}
														}
														else if(selection.equals("2")) //Seat 3
														{
															Ticket newTicket = new Ticket(movieTheater.getTheaterName(), 
																	                      movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).getTitle(), thisShowing.getDate(), 
																	                      thisShowing.getTime(), thisShowing.getPrice(), thisShowing.getTheater().getTheaterNumber(), "A3");
															user.addCurrentTicket(newTicket);
															System.out.println("Great! Your ticket is reserved! \nTicket Stub: \n" + newTicket);
															System.out.print("Would you like to reserve any more tickets for this showing? \n1. Yes \n2. No \nYour Selection: ");
															selection = "";
															acceptable = false;
															while(!acceptable) //Check for acceptable Response
															{
																selection = in.next();
																acceptable = Menu.acceptable(selection, 2); 
																if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
															}
															if(selection.equals("1")) //yes
															{
																System.out.println("Ok!");
															}
															else if(selection.equals("2")) //no
															{
																System.out.println("See you Soon!");
																done = true;
															}
														}
													}
													else if(selection.equals("2")) //Row B
													{
														System.out.println("What seat would you like to sit in: ");
														System.out.print("1. Seat 1 \n2. Seat 2 \n3. Seat 3 \nYour Selection: ");
														selection = "";
														acceptable = false;
														while(!acceptable) //Check for acceptable Response
														{
															selection = in.next();
															acceptable = Menu.acceptable(selection, 3); 
															if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
														}
														if(selection.equals("1")) //Seat 1
														{
															Ticket newTicket = new Ticket(movieTheater.getTheaterName(), 
																	                      movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).getTitle(), thisShowing.getDate(), 
																	                      thisShowing.getTime(), thisShowing.getPrice(), thisShowing.getTheater().getTheaterNumber(), "B1");
															user.addCurrentTicket(newTicket);
															System.out.println("Great! Your ticket is reserved! \nTicket Stub: \n" + newTicket);
															System.out.print("Would you like to reserve any more tickets for this showing? \n1. Yes \n2. No \nYour Selection: ");
															selection = "";
															acceptable = false;
															while(!acceptable) //Check for acceptable Response
															{
																selection = in.next();
																acceptable = Menu.acceptable(selection, 2); 
																if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
															}
															if(selection.equals("1")) //yes
															{
																System.out.println("Ok!");
															}
															else if(selection.equals("2")) //no
															{
																System.out.println("See you Soon!");
																done = true;
															}
														}
														else if(selection.equals("2")) //Seat 2
														{
															Ticket newTicket = new Ticket(movieTheater.getTheaterName(), 
																	                      movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).getTitle(), thisShowing.getDate(), 
																	                      thisShowing.getTime(), thisShowing.getPrice(), thisShowing.getTheater().getTheaterNumber(), "B2");
															user.addCurrentTicket(newTicket);
															System.out.println("Great! Your ticket is reserved! \nTicket Stub: \n" + newTicket);
															System.out.print("Would you like to reserve any more tickets for this showing? \n1. Yes \n2. No \nYour Selection: ");
															selection = "";
															acceptable = false;
															while(!acceptable) //Check for acceptable Response
															{
																selection = in.next();
																acceptable = Menu.acceptable(selection, 2); 
																if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
															}
															if(selection.equals("1")) //Option 1
															{
																System.out.println("Ok!");
															}
															else if(selection.equals("2")) //Option 2
															{
																System.out.println("See you Soon!");
																done = true;
															}
														}
														else if(selection.equals("2")) //Seat 3
														{
															Ticket newTicket = new Ticket(movieTheater.getTheaterName(), 
																	                      movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).getTitle(), thisShowing.getDate(), 
																	                      thisShowing.getTime(), thisShowing.getPrice(), thisShowing.getTheater().getTheaterNumber(), "B3");
															user.addCurrentTicket(newTicket);
															System.out.println("Great! Your ticket is reserved! \nTicket Stub: \n" + newTicket);
															System.out.print("Would you like to reserve any more tickets for this showing? \n1. Yes \n2. No \nYour Selection: ");
															selection = "";
															acceptable = false;
															while(!acceptable) //Check for acceptable Response
															{
																selection = in.next();
																acceptable = Menu.acceptable(selection, 2); 
																if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
															}
															if(selection.equals("1")) //Option 1
															{
																System.out.println("Ok!");
															}
															else if(selection.equals("2")) //Option 2
															{
																System.out.println("See you Soon!");
																done = true;
															}
														}
													}
													else if(selection.equals("2")) //Row C
													{
														System.out.println("What seat would you like to sit in: ");
														System.out.print("1. Seat 1 \n2. Seat 2 \n3. Seat 3 \nYour Selection: ");
														selection = "";
														acceptable = false;
														while(!acceptable) //Check for acceptable Response
														{
															selection = in.next();
															acceptable = Menu.acceptable(selection, 3); 
															if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
														}
														if(selection.equals("1")) //Seat 1
														{
															Ticket newTicket = new Ticket(movieTheater.getTheaterName(), 
																	                      movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).getTitle(), thisShowing.getDate(), 
																	                      thisShowing.getTime(), thisShowing.getPrice(), thisShowing.getTheater().getTheaterNumber(), "C1");
															user.addCurrentTicket(newTicket);
															System.out.println("Great! Your ticket is reserved! \nTicket Stub: \n" + newTicket);
															System.out.print("Would you like to reserve any more tickets for this showing? \n1. Yes \n2. No \nYour Selection: ");
															selection = "";
															acceptable = false;
															while(!acceptable) //Check for acceptable Response
															{
																selection = in.next();
																acceptable = Menu.acceptable(selection, 2); 
																if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
															}
															if(selection.equals("1")) //Option 1
															{
																System.out.println("Ok!");
															}
															else if(selection.equals("2")) //Option 2
															{
																System.out.println("See you Soon!");
																done = true;
															}
														}
														else if(selection.equals("2")) //Seat 2
														{
															Ticket newTicket = new Ticket(movieTheater.getTheaterName(), 
																	                      movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).getTitle(), thisShowing.getDate(), 
																	                      thisShowing.getTime(), thisShowing.getPrice(), thisShowing.getTheater().getTheaterNumber(), "C2");
															user.addCurrentTicket(newTicket);
															System.out.println("Great! Your ticket is reserved! \nTicket Stub: \n" + newTicket);
															System.out.print("Would you like to reserve any more tickets for this showing? \n1. Yes \n2. No \nYour Selection: ");
															selection = "";
															acceptable = false;
															while(!acceptable) //Check for acceptable Response
															{
																selection = in.next();
																acceptable = Menu.acceptable(selection, 2); 
																if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
															}
															if(selection.equals("1")) //Option 1
															{
																System.out.println("Ok!");
															}
															else if(selection.equals("2")) //Option 2
															{
																System.out.println("See you Soon!");
																done = true;
															}
														}
														else if(selection.equals("2")) //Seat 3
														{
															Ticket newTicket = new Ticket(movieTheater.getTheaterName(), 
																	                      movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).getTitle(), thisShowing.getDate(), 
																	                      thisShowing.getTime(), thisShowing.getPrice(), thisShowing.getTheater().getTheaterNumber(), "C3");
															user.addCurrentTicket(newTicket);
															System.out.println("Great! Your ticket is reserved! \nTicket Stub: \n" + newTicket);
															System.out.print("Would you like to reserve any more tickets for this showing? \n1. Yes \n2. No \nYour Selection: ");
															selection = "";
															acceptable = false;
															while(!acceptable) //Check for acceptable Response
															{
																selection = in.next();
																acceptable = Menu.acceptable(selection, 2); 
																if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
															}
															if(selection.equals("1")) //Option 1
															{
																System.out.println("Ok!");
															}
															else if(selection.equals("2")) //Option 2
															{
																System.out.println("See you Soon!");
																done = true;
															}
														}
													}
												}
											}
										}
					///**END SHOWING SELECT MENU**///
									}
									else if(selection.equals("2")) //Back
									{
										backToBrowseMenu = true;
										System.out.println("\n");
									}
								}
								else if(i == 0) //First Movie in list (Cannot Go Backwards)
								{
									System.out.println("Browse Movies: (" + (i + 1) + ")");
									System.out.println(movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i) + "\n");
									
									System.out.print("1. Next \n2. Book Movie \n3. Go Back \nYour Selection: ");
									selection = ""; 
									acceptable = false;
									while(!acceptable) //Check for acceptable Response
									{
										selection = in.next();
										acceptable = Menu.acceptable(selection, 3);
										if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
									}
									if(selection.equals("1")) //Next
									{
										i++;
										System.out.println("");
									}
									else if(selection.equals("2")) //Book Movie
									{
					///**SHOWING SELECT MENU**///
										boolean backToMovieViewer = false;
										while(!backToMovieViewer)
										{
											System.out.println("Available Showings: ");
											ArrayList<Showing> movieShowings = movieTheater.getShowingsForMovie(movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).getTitle());
											for(int j = 0; j < movieShowings.size(); j++)
											{
												System.out.println((j + 1) + ". " + movieShowings.get(j));
											}
											System.out.print((movieShowings.size() + 1) + ". Go Back \nYour Selection: ");
											selection = "";
											acceptable = false;
											while(!acceptable) //Check for acceptable Response
											{
												selection = in.next();
												acceptable = Menu.acceptable(selection, (movieShowings.size() + 1)); 
												if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
											}
											if(selection.equals("(movieShowings.size() + 1)")) //go back
											{
												backToMovieViewer = true;
											}
											else
											{
												Showing thisShowing = movieShowings.get(Integer.parseInt(selection));
												boolean done = false;
												while(!done)
												{
													System.out.println("What row would you like to sit in: ");
													System.out.print("1. Row A \n2. Row B \n3. Row C \nYour Selection: ");
													selection = "";
													acceptable = false;
													while(!acceptable) //Check for acceptable Response
													{
														selection = in.next();
														acceptable = Menu.acceptable(selection, 3); 
														if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
													}
													if(selection.equals("1")) //Row A
													{
														System.out.println("What seat would you like to sit in: ");
														System.out.print("1. Seat 1 \n2. Seat 2 \n3. Seat 3 \nYour Selection: ");
														selection = "";
														acceptable = false;
														while(!acceptable) //Check for acceptable Response
														{
															selection = in.next();
															acceptable = Menu.acceptable(selection, 3); 
															if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
														}
														if(selection.equals("1")) //Seat 1
														{
															Ticket newTicket = new Ticket(movieTheater.getTheaterName(), 
																	                      movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).getTitle(), thisShowing.getDate(), 
																	                      thisShowing.getTime(), thisShowing.getPrice(), thisShowing.getTheater().getTheaterNumber(), "A1");
															user.addCurrentTicket(newTicket);
															System.out.println("Great! Your ticket is reserved! \nTicket Stub: \n" + newTicket);
															System.out.print("Would you like to reserve any more tickets for this showing? \n1. Yes \n2. No \nYour Selection: ");
															selection = "";
															acceptable = false;
															while(!acceptable) //Check for acceptable Response
															{
																selection = in.next();
																acceptable = Menu.acceptable(selection, 2); 
																if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
															}
															if(selection.equals("1")) //yes
															{
																System.out.println("Ok!");
															}
															else if(selection.equals("2")) //no
															{
																System.out.println("See you Soon!");
																done = true;
															}
														}
														else if(selection.equals("2")) //Seat 2
														{
															Ticket newTicket = new Ticket(movieTheater.getTheaterName(), 
																	                      movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).getTitle(), thisShowing.getDate(), 
																	                      thisShowing.getTime(), thisShowing.getPrice(), thisShowing.getTheater().getTheaterNumber(), "A2");
															user.addCurrentTicket(newTicket);
															System.out.println("Great! Your ticket is reserved! \nTicket Stub: \n" + newTicket);
															System.out.print("Would you like to reserve any more tickets for this showing? \n1. Yes \n2. No \nYour Selection: ");
															selection = "";
															acceptable = false;
															while(!acceptable) //Check for acceptable Response
															{
																selection = in.next();
																acceptable = Menu.acceptable(selection, 2); 
																if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
															}
															if(selection.equals("1")) //yes
															{
																System.out.println("Ok!");
															}
															else if(selection.equals("2")) //no
															{
																System.out.println("See you Soon!");
																done = true;
															}
														}
														else if(selection.equals("2")) //Seat 3
														{
															Ticket newTicket = new Ticket(movieTheater.getTheaterName(), 
																	                      movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).getTitle(), thisShowing.getDate(), 
																	                      thisShowing.getTime(), thisShowing.getPrice(), thisShowing.getTheater().getTheaterNumber(), "A3");
															user.addCurrentTicket(newTicket);
															System.out.println("Great! Your ticket is reserved! \nTicket Stub: \n" + newTicket);
															System.out.print("Would you like to reserve any more tickets for this showing? \n1. Yes \n2. No \nYour Selection: ");
															selection = "";
															acceptable = false;
															while(!acceptable) //Check for acceptable Response
															{
																selection = in.next();
																acceptable = Menu.acceptable(selection, 2); 
																if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
															}
															if(selection.equals("1")) //yes
															{
																System.out.println("Ok!");
															}
															else if(selection.equals("2")) //no
															{
																System.out.println("See you Soon!");
																done = true;
															}
														}
													}
													else if(selection.equals("2")) //Row B
													{
														System.out.println("What seat would you like to sit in: ");
														System.out.print("1. Seat 1 \n2. Seat 2 \n3. Seat 3 \nYour Selection: ");
														selection = "";
														acceptable = false;
														while(!acceptable) //Check for acceptable Response
														{
															selection = in.next();
															acceptable = Menu.acceptable(selection, 3); 
															if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
														}
														if(selection.equals("1")) //Seat 1
														{
															Ticket newTicket = new Ticket(movieTheater.getTheaterName(), 
																	                      movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).getTitle(), thisShowing.getDate(), 
																	                      thisShowing.getTime(), thisShowing.getPrice(), thisShowing.getTheater().getTheaterNumber(), "B1");
															user.addCurrentTicket(newTicket);
															System.out.println("Great! Your ticket is reserved! \nTicket Stub: \n" + newTicket);
															System.out.print("Would you like to reserve any more tickets for this showing? \n1. Yes \n2. No \nYour Selection: ");
															selection = "";
															acceptable = false;
															while(!acceptable) //Check for acceptable Response
															{
																selection = in.next();
																acceptable = Menu.acceptable(selection, 2); 
																if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
															}
															if(selection.equals("1")) //yes
															{
																System.out.println("Ok!");
															}
															else if(selection.equals("2")) //no
															{
																System.out.println("See you Soon!");
																done = true;
															}
														}
														else if(selection.equals("2")) //Seat 2
														{
															Ticket newTicket = new Ticket(movieTheater.getTheaterName(), 
																	                      movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).getTitle(), thisShowing.getDate(), 
																	                      thisShowing.getTime(), thisShowing.getPrice(), thisShowing.getTheater().getTheaterNumber(), "B2");
															user.addCurrentTicket(newTicket);
															System.out.println("Great! Your ticket is reserved! \nTicket Stub: \n" + newTicket);
															System.out.print("Would you like to reserve any more tickets for this showing? \n1. Yes \n2. No \nYour Selection: ");
															selection = "";
															acceptable = false;
															while(!acceptable) //Check for acceptable Response
															{
																selection = in.next();
																acceptable = Menu.acceptable(selection, 2); 
																if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
															}
															if(selection.equals("1")) //Option 1
															{
																System.out.println("Ok!");
															}
															else if(selection.equals("2")) //Option 2
															{
																System.out.println("See you Soon!");
																done = true;
															}
														}
														else if(selection.equals("2")) //Seat 3
														{
															Ticket newTicket = new Ticket(movieTheater.getTheaterName(), 
																	                      movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).getTitle(), thisShowing.getDate(), 
																	                      thisShowing.getTime(), thisShowing.getPrice(), thisShowing.getTheater().getTheaterNumber(), "B3");
															user.addCurrentTicket(newTicket);
															System.out.println("Great! Your ticket is reserved! \nTicket Stub: \n" + newTicket);
															System.out.print("Would you like to reserve any more tickets for this showing? \n1. Yes \n2. No \nYour Selection: ");
															selection = "";
															acceptable = false;
															while(!acceptable) //Check for acceptable Response
															{
																selection = in.next();
																acceptable = Menu.acceptable(selection, 2); 
																if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
															}
															if(selection.equals("1")) //Option 1
															{
																System.out.println("Ok!");
															}
															else if(selection.equals("2")) //Option 2
															{
																System.out.println("See you Soon!");
																done = true;
															}
														}
													}
													else if(selection.equals("2")) //Row C
													{
														System.out.println("What seat would you like to sit in: ");
														System.out.print("1. Seat 1 \n2. Seat 2 \n3. Seat 3 \nYour Selection: ");
														selection = "";
														acceptable = false;
														while(!acceptable) //Check for acceptable Response
														{
															selection = in.next();
															acceptable = Menu.acceptable(selection, 3); 
															if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
														}
														if(selection.equals("1")) //Seat 1
														{
															Ticket newTicket = new Ticket(movieTheater.getTheaterName(), 
																	                      movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).getTitle(), thisShowing.getDate(), 
																	                      thisShowing.getTime(), thisShowing.getPrice(), thisShowing.getTheater().getTheaterNumber(), "C1");
															user.addCurrentTicket(newTicket);
															System.out.println("Great! Your ticket is reserved! \nTicket Stub: \n" + newTicket);
															System.out.print("Would you like to reserve any more tickets for this showing? \n1. Yes \n2. No \nYour Selection: ");
															selection = "";
															acceptable = false;
															while(!acceptable) //Check for acceptable Response
															{
																selection = in.next();
																acceptable = Menu.acceptable(selection, 2); 
																if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
															}
															if(selection.equals("1")) //Option 1
															{
																System.out.println("Ok!");
															}
															else if(selection.equals("2")) //Option 2
															{
																System.out.println("See you Soon!");
																done = true;
															}
														}
														else if(selection.equals("2")) //Seat 2
														{
															Ticket newTicket = new Ticket(movieTheater.getTheaterName(), 
																	                      movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).getTitle(), thisShowing.getDate(), 
																	                      thisShowing.getTime(), thisShowing.getPrice(), thisShowing.getTheater().getTheaterNumber(), "C2");
															user.addCurrentTicket(newTicket);
															System.out.println("Great! Your ticket is reserved! \nTicket Stub: \n" + newTicket);
															System.out.print("Would you like to reserve any more tickets for this showing? \n1. Yes \n2. No \nYour Selection: ");
															selection = "";
															acceptable = false;
															while(!acceptable) //Check for acceptable Response
															{
																selection = in.next();
																acceptable = Menu.acceptable(selection, 2); 
																if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
															}
															if(selection.equals("1")) //Option 1
															{
																System.out.println("Ok!");
															}
															else if(selection.equals("2")) //Option 2
															{
																System.out.println("See you Soon!");
																done = true;
															}
														}
														else if(selection.equals("2")) //Seat 3
														{
															Ticket newTicket = new Ticket(movieTheater.getTheaterName(), 
																	                      movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).getTitle(), thisShowing.getDate(), 
																	                      thisShowing.getTime(), thisShowing.getPrice(), thisShowing.getTheater().getTheaterNumber(), "C3");
															user.addCurrentTicket(newTicket);
															System.out.println("Great! Your ticket is reserved! \nTicket Stub: \n" + newTicket);
															System.out.print("Would you like to reserve any more tickets for this showing? \n1. Yes \n2. No \nYour Selection: ");
															selection = "";
															acceptable = false;
															while(!acceptable) //Check for acceptable Response
															{
																selection = in.next();
																acceptable = Menu.acceptable(selection, 2); 
																if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
															}
															if(selection.equals("1")) //Option 1
															{
																System.out.println("Ok!");
															}
															else if(selection.equals("2")) //Option 2
															{
																System.out.println("See you Soon!");
																done = true;
															}
														}
													}
												}
											}
										}
					///**END SHOWING SELECT MENU**///
									}
									else if(selection.equals("3")) //Back
									{
										backToBrowseMenu = true;
										System.out.println("\n");
									}
								}
								else if((i + 1) == movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).size()) //Last Movie in list (Cannot Go Forwards)
								{
									System.out.println("Browse Movies: (" + (i + 1) + ")");
									System.out.println(movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i) + "\n");
									
									System.out.print("1. Previous \n2. Book Movie \n3. Go Back \nYour Selection: ");
									selection = ""; 
									acceptable = false;
									while(!acceptable) //Check for acceptable Response
									{
										selection = in.next();
										acceptable = Menu.acceptable(selection, 3);
										if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
									}
									if(selection.equals("1")) //Previous
									{
										i--;
										System.out.println("");
									}
									else if(selection.equals("2")) //Book Movie
									{
					///**SHOWING SELECT MENU**///
										boolean backToMovieViewer = false;
										while(!backToMovieViewer)
										{
											System.out.println("Available Showings: ");
											ArrayList<Showing> movieShowings = movieTheater.getShowingsForMovie(movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).getTitle());
											for(int j = 0; j < movieShowings.size(); j++)
											{
												System.out.println((j + 1) + ". " + movieShowings.get(j));
											}
											System.out.print((movieShowings.size() + 1) + ". Go Back \nYour Selection: ");
											selection = "";
											acceptable = false;
											while(!acceptable) //Check for acceptable Response
											{
												selection = in.next();
												acceptable = Menu.acceptable(selection, (movieShowings.size() + 1)); 
												if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
											}
											if(selection.equals("(movieShowings.size() + 1)")) //go back
											{
												backToMovieViewer = true;
											}
											else
											{
												Showing thisShowing = movieShowings.get(Integer.parseInt(selection));
												boolean done = false;
												while(!done)
												{
													System.out.println("What row would you like to sit in: ");
													System.out.print("1. Row A \n2. Row B \n3. Row C \nYour Selection: ");
													selection = "";
													acceptable = false;
													while(!acceptable) //Check for acceptable Response
													{
														selection = in.next();
														acceptable = Menu.acceptable(selection, 3); 
														if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
													}
													if(selection.equals("1")) //Row A
													{
														System.out.println("What seat would you like to sit in: ");
														System.out.print("1. Seat 1 \n2. Seat 2 \n3. Seat 3 \nYour Selection: ");
														selection = "";
														acceptable = false;
														while(!acceptable) //Check for acceptable Response
														{
															selection = in.next();
															acceptable = Menu.acceptable(selection, 3); 
															if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
														}
														if(selection.equals("1")) //Seat 1
														{
															Ticket newTicket = new Ticket(movieTheater.getTheaterName(), 
																	                      movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).getTitle(), thisShowing.getDate(), 
																	                      thisShowing.getTime(), thisShowing.getPrice(), thisShowing.getTheater().getTheaterNumber(), "A1");
															user.addCurrentTicket(newTicket);
															System.out.println("Great! Your ticket is reserved! \nTicket Stub: \n" + newTicket);
															System.out.print("Would you like to reserve any more tickets for this showing? \n1. Yes \n2. No \nYour Selection: ");
															selection = "";
															acceptable = false;
															while(!acceptable) //Check for acceptable Response
															{
																selection = in.next();
																acceptable = Menu.acceptable(selection, 2); 
																if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
															}
															if(selection.equals("1")) //yes
															{
																System.out.println("Ok!");
															}
															else if(selection.equals("2")) //no
															{
																System.out.println("See you Soon!");
																done = true;
															}
														}
														else if(selection.equals("2")) //Seat 2
														{
															Ticket newTicket = new Ticket(movieTheater.getTheaterName(), 
																	                      movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).getTitle(), thisShowing.getDate(), 
																	                      thisShowing.getTime(), thisShowing.getPrice(), thisShowing.getTheater().getTheaterNumber(), "A2");
															user.addCurrentTicket(newTicket);
															System.out.println("Great! Your ticket is reserved! \nTicket Stub: \n" + newTicket);
															System.out.print("Would you like to reserve any more tickets for this showing? \n1. Yes \n2. No \nYour Selection: ");
															selection = "";
															acceptable = false;
															while(!acceptable) //Check for acceptable Response
															{
																selection = in.next();
																acceptable = Menu.acceptable(selection, 2); 
																if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
															}
															if(selection.equals("1")) //yes
															{
																System.out.println("Ok!");
															}
															else if(selection.equals("2")) //no
															{
																System.out.println("See you Soon!");
																done = true;
															}
														}
														else if(selection.equals("2")) //Seat 3
														{
															Ticket newTicket = new Ticket(movieTheater.getTheaterName(), 
																	                      movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).getTitle(), thisShowing.getDate(), 
																	                      thisShowing.getTime(), thisShowing.getPrice(), thisShowing.getTheater().getTheaterNumber(), "A3");
															user.addCurrentTicket(newTicket);
															System.out.println("Great! Your ticket is reserved! \nTicket Stub: \n" + newTicket);
															System.out.print("Would you like to reserve any more tickets for this showing? \n1. Yes \n2. No \nYour Selection: ");
															selection = "";
															acceptable = false;
															while(!acceptable) //Check for acceptable Response
															{
																selection = in.next();
																acceptable = Menu.acceptable(selection, 2); 
																if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
															}
															if(selection.equals("1")) //yes
															{
																System.out.println("Ok!");
															}
															else if(selection.equals("2")) //no
															{
																System.out.println("See you Soon!");
																done = true;
															}
														}
													}
													else if(selection.equals("2")) //Row B
													{
														System.out.println("What seat would you like to sit in: ");
														System.out.print("1. Seat 1 \n2. Seat 2 \n3. Seat 3 \nYour Selection: ");
														selection = "";
														acceptable = false;
														while(!acceptable) //Check for acceptable Response
														{
															selection = in.next();
															acceptable = Menu.acceptable(selection, 3); 
															if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
														}
														if(selection.equals("1")) //Seat 1
														{
															Ticket newTicket = new Ticket(movieTheater.getTheaterName(), 
																	                      movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).getTitle(), thisShowing.getDate(), 
																	                      thisShowing.getTime(), thisShowing.getPrice(), thisShowing.getTheater().getTheaterNumber(), "B1");
															user.addCurrentTicket(newTicket);
															System.out.println("Great! Your ticket is reserved! \nTicket Stub: \n" + newTicket);
															System.out.print("Would you like to reserve any more tickets for this showing? \n1. Yes \n2. No \nYour Selection: ");
															selection = "";
															acceptable = false;
															while(!acceptable) //Check for acceptable Response
															{
																selection = in.next();
																acceptable = Menu.acceptable(selection, 2); 
																if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
															}
															if(selection.equals("1")) //yes
															{
																System.out.println("Ok!");
															}
															else if(selection.equals("2")) //no
															{
																System.out.println("See you Soon!");
																done = true;
															}
														}
														else if(selection.equals("2")) //Seat 2
														{
															Ticket newTicket = new Ticket(movieTheater.getTheaterName(), 
																	                      movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).getTitle(), thisShowing.getDate(), 
																	                      thisShowing.getTime(), thisShowing.getPrice(), thisShowing.getTheater().getTheaterNumber(), "B2");
															user.addCurrentTicket(newTicket);
															System.out.println("Great! Your ticket is reserved! \nTicket Stub: \n" + newTicket);
															System.out.print("Would you like to reserve any more tickets for this showing? \n1. Yes \n2. No \nYour Selection: ");
															selection = "";
															acceptable = false;
															while(!acceptable) //Check for acceptable Response
															{
																selection = in.next();
																acceptable = Menu.acceptable(selection, 2); 
																if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
															}
															if(selection.equals("1")) //Option 1
															{
																System.out.println("Ok!");
															}
															else if(selection.equals("2")) //Option 2
															{
																System.out.println("See you Soon!");
																done = true;
															}
														}
														else if(selection.equals("2")) //Seat 3
														{
															Ticket newTicket = new Ticket(movieTheater.getTheaterName(), 
																	                      movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).getTitle(), thisShowing.getDate(), 
																	                      thisShowing.getTime(), thisShowing.getPrice(), thisShowing.getTheater().getTheaterNumber(), "B3");
															user.addCurrentTicket(newTicket);
															System.out.println("Great! Your ticket is reserved! \nTicket Stub: \n" + newTicket);
															System.out.print("Would you like to reserve any more tickets for this showing? \n1. Yes \n2. No \nYour Selection: ");
															selection = "";
															acceptable = false;
															while(!acceptable) //Check for acceptable Response
															{
																selection = in.next();
																acceptable = Menu.acceptable(selection, 2); 
																if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
															}
															if(selection.equals("1")) //Option 1
															{
																System.out.println("Ok!");
															}
															else if(selection.equals("2")) //Option 2
															{
																System.out.println("See you Soon!");
																done = true;
															}
														}
													}
													else if(selection.equals("2")) //Row C
													{
														System.out.println("What seat would you like to sit in: ");
														System.out.print("1. Seat 1 \n2. Seat 2 \n3. Seat 3 \nYour Selection: ");
														selection = "";
														acceptable = false;
														while(!acceptable) //Check for acceptable Response
														{
															selection = in.next();
															acceptable = Menu.acceptable(selection, 3); 
															if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
														}
														if(selection.equals("1")) //Seat 1
														{
															Ticket newTicket = new Ticket(movieTheater.getTheaterName(), 
																	                      movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).getTitle(), thisShowing.getDate(), 
																	                      thisShowing.getTime(), thisShowing.getPrice(), thisShowing.getTheater().getTheaterNumber(), "C1");
															user.addCurrentTicket(newTicket);
															System.out.println("Great! Your ticket is reserved! \nTicket Stub: \n" + newTicket);
															System.out.print("Would you like to reserve any more tickets for this showing? \n1. Yes \n2. No \nYour Selection: ");
															selection = "";
															acceptable = false;
															while(!acceptable) //Check for acceptable Response
															{
																selection = in.next();
																acceptable = Menu.acceptable(selection, 2); 
																if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
															}
															if(selection.equals("1")) //Option 1
															{
																System.out.println("Ok!");
															}
															else if(selection.equals("2")) //Option 2
															{
																System.out.println("See you Soon!");
																done = true;
															}
														}
														else if(selection.equals("2")) //Seat 2
														{
															Ticket newTicket = new Ticket(movieTheater.getTheaterName(), 
																	                      movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).getTitle(), thisShowing.getDate(), 
																	                      thisShowing.getTime(), thisShowing.getPrice(), thisShowing.getTheater().getTheaterNumber(), "C2");
															user.addCurrentTicket(newTicket);
															System.out.println("Great! Your ticket is reserved! \nTicket Stub: \n" + newTicket);
															System.out.print("Would you like to reserve any more tickets for this showing? \n1. Yes \n2. No \nYour Selection: ");
															selection = "";
															acceptable = false;
															while(!acceptable) //Check for acceptable Response
															{
																selection = in.next();
																acceptable = Menu.acceptable(selection, 2); 
																if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
															}
															if(selection.equals("1")) //Option 1
															{
																System.out.println("Ok!");
															}
															else if(selection.equals("2")) //Option 2
															{
																System.out.println("See you Soon!");
																done = true;
															}
														}
														else if(selection.equals("2")) //Seat 3
														{
															Ticket newTicket = new Ticket(movieTheater.getTheaterName(), 
																	                      movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).getTitle(), thisShowing.getDate(), 
																	                      thisShowing.getTime(), thisShowing.getPrice(), thisShowing.getTheater().getTheaterNumber(), "C3");
															user.addCurrentTicket(newTicket);
															System.out.println("Great! Your ticket is reserved! \nTicket Stub: \n" + newTicket);
															System.out.print("Would you like to reserve any more tickets for this showing? \n1. Yes \n2. No \nYour Selection: ");
															selection = "";
															acceptable = false;
															while(!acceptable) //Check for acceptable Response
															{
																selection = in.next();
																acceptable = Menu.acceptable(selection, 2); 
																if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
															}
															if(selection.equals("1")) //Option 1
															{
																System.out.println("Ok!");
															}
															else if(selection.equals("2")) //Option 2
															{
																System.out.println("See you Soon!");
																done = true;
															}
														}
													}
												}
											}
										}
					///**END SHOWING SELECT MENU**///
									}
									else if(selection.equals("3")) //Back
									{
										backToBrowseMenu = true;
										System.out.println("\n");
									}
								}
								else //Movies in the middle of the list (Can Go Forwards and Backwards)
								{
									System.out.println("Browse Movies: (" + (i + 1) + ")");
									System.out.println(movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i) + "\n");
									
									System.out.print("1. Next \n2. Previous \n3. Book Movie \n4. Go Back \nYour Selection: ");
									selection = ""; 
									acceptable = false;
									while(!acceptable) //Check for acceptable Response
									{
										selection = in.next();
										acceptable = Menu.acceptable(selection, 4);
										if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
									}
									if(selection.equals("1")) //Next
									{
										i++;
										System.out.println("");
									}
									else if(selection.equals("2")) //Previous
									{
										i--;
										System.out.println("");
									}
									else if(selection.equals("3")) //Book Movie
									{
					///**SHOWING SELECT MENU**///
										boolean backToMovieViewer = false;
										while(!backToMovieViewer)
										{
											System.out.println("Available Showings: ");
											ArrayList<Showing> movieShowings = movieTheater.getShowingsForMovie(movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).getTitle());
											for(int j = 0; j < movieShowings.size(); j++)
											{
												System.out.println((j + 1) + ". " + movieShowings.get(j));
											}
											System.out.print((movieShowings.size() + 1) + ". Go Back \nYour Selection: ");
											selection = "";
											acceptable = false;
											while(!acceptable) //Check for acceptable Response
											{
												selection = in.next();
												acceptable = Menu.acceptable(selection, (movieShowings.size() + 1)); 
												if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
											}
											if(selection.equals("(movieShowings.size() + 1)")) //go back
											{
												backToMovieViewer = true;
											}
											else
											{
												Showing thisShowing = movieShowings.get(Integer.parseInt(selection));
												boolean done = false;
												while(!done)
												{
													System.out.println("What row would you like to sit in: ");
													System.out.print("1. Row A \n2. Row B \n3. Row C \nYour Selection: ");
													selection = "";
													acceptable = false;
													while(!acceptable) //Check for acceptable Response
													{
														selection = in.next();
														acceptable = Menu.acceptable(selection, 3); 
														if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
													}
													if(selection.equals("1")) //Row A
													{
														System.out.println("What seat would you like to sit in: ");
														System.out.print("1. Seat 1 \n2. Seat 2 \n3. Seat 3 \nYour Selection: ");
														selection = "";
														acceptable = false;
														while(!acceptable) //Check for acceptable Response
														{
															selection = in.next();
															acceptable = Menu.acceptable(selection, 3); 
															if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
														}
														if(selection.equals("1")) //Seat 1
														{
															Ticket newTicket = new Ticket(movieTheater.getTheaterName(), 
																	                      movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).getTitle(), thisShowing.getDate(), 
																	                      thisShowing.getTime(), thisShowing.getPrice(), thisShowing.getTheater().getTheaterNumber(), "A1");
															user.addCurrentTicket(newTicket);
															System.out.println("Great! Your ticket is reserved! \nTicket Stub: \n" + newTicket);
															System.out.print("Would you like to reserve any more tickets for this showing? \n1. Yes \n2. No \nYour Selection: ");
															selection = "";
															acceptable = false;
															while(!acceptable) //Check for acceptable Response
															{
																selection = in.next();
																acceptable = Menu.acceptable(selection, 2); 
																if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
															}
															if(selection.equals("1")) //yes
															{
																System.out.println("Ok!");
															}
															else if(selection.equals("2")) //no
															{
																System.out.println("See you Soon!");
																done = true;
															}
														}
														else if(selection.equals("2")) //Seat 2
														{
															Ticket newTicket = new Ticket(movieTheater.getTheaterName(), 
																	                      movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).getTitle(), thisShowing.getDate(), 
																	                      thisShowing.getTime(), thisShowing.getPrice(), thisShowing.getTheater().getTheaterNumber(), "A2");
															user.addCurrentTicket(newTicket);
															System.out.println("Great! Your ticket is reserved! \nTicket Stub: \n" + newTicket);
															System.out.print("Would you like to reserve any more tickets for this showing? \n1. Yes \n2. No \nYour Selection: ");
															selection = "";
															acceptable = false;
															while(!acceptable) //Check for acceptable Response
															{
																selection = in.next();
																acceptable = Menu.acceptable(selection, 2); 
																if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
															}
															if(selection.equals("1")) //yes
															{
																System.out.println("Ok!");
															}
															else if(selection.equals("2")) //no
															{
																System.out.println("See you Soon!");
																done = true;
															}
														}
														else if(selection.equals("2")) //Seat 3
														{
															Ticket newTicket = new Ticket(movieTheater.getTheaterName(), 
																	                      movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).getTitle(), thisShowing.getDate(), 
																	                      thisShowing.getTime(), thisShowing.getPrice(), thisShowing.getTheater().getTheaterNumber(), "A3");
															user.addCurrentTicket(newTicket);
															System.out.println("Great! Your ticket is reserved! \nTicket Stub: \n" + newTicket);
															System.out.print("Would you like to reserve any more tickets for this showing? \n1. Yes \n2. No \nYour Selection: ");
															selection = "";
															acceptable = false;
															while(!acceptable) //Check for acceptable Response
															{
																selection = in.next();
																acceptable = Menu.acceptable(selection, 2); 
																if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
															}
															if(selection.equals("1")) //yes
															{
																System.out.println("Ok!");
															}
															else if(selection.equals("2")) //no
															{
																System.out.println("See you Soon!");
																done = true;
															}
														}
													}
													else if(selection.equals("2")) //Row B
													{
														System.out.println("What seat would you like to sit in: ");
														System.out.print("1. Seat 1 \n2. Seat 2 \n3. Seat 3 \nYour Selection: ");
														selection = "";
														acceptable = false;
														while(!acceptable) //Check for acceptable Response
														{
															selection = in.next();
															acceptable = Menu.acceptable(selection, 3); 
															if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
														}
														if(selection.equals("1")) //Seat 1
														{
															Ticket newTicket = new Ticket(movieTheater.getTheaterName(), 
																	                      movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).getTitle(), thisShowing.getDate(), 
																	                      thisShowing.getTime(), thisShowing.getPrice(), thisShowing.getTheater().getTheaterNumber(), "B1");
															user.addCurrentTicket(newTicket);
															System.out.println("Great! Your ticket is reserved! \nTicket Stub: \n" + newTicket);
															System.out.print("Would you like to reserve any more tickets for this showing? \n1. Yes \n2. No \nYour Selection: ");
															selection = "";
															acceptable = false;
															while(!acceptable) //Check for acceptable Response
															{
																selection = in.next();
																acceptable = Menu.acceptable(selection, 2); 
																if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
															}
															if(selection.equals("1")) //yes
															{
																System.out.println("Ok!");
															}
															else if(selection.equals("2")) //no
															{
																System.out.println("See you Soon!");
																done = true;
															}
														}
														else if(selection.equals("2")) //Seat 2
														{
															Ticket newTicket = new Ticket(movieTheater.getTheaterName(), 
																	                      movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).getTitle(), thisShowing.getDate(), 
																	                      thisShowing.getTime(), thisShowing.getPrice(), thisShowing.getTheater().getTheaterNumber(), "B2");
															user.addCurrentTicket(newTicket);
															System.out.println("Great! Your ticket is reserved! \nTicket Stub: \n" + newTicket);
															System.out.print("Would you like to reserve any more tickets for this showing? \n1. Yes \n2. No \nYour Selection: ");
															selection = "";
															acceptable = false;
															while(!acceptable) //Check for acceptable Response
															{
																selection = in.next();
																acceptable = Menu.acceptable(selection, 2); 
																if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
															}
															if(selection.equals("1")) //yes
															{
																System.out.println("Ok!");
															}
															else if(selection.equals("2")) //no
															{
																System.out.println("See you Soon!");
																done = true;
															}
														}
														else if(selection.equals("3")) //Seat 3
														{
															Ticket newTicket = new Ticket(movieTheater.getTheaterName(), 
																	                      movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).getTitle(), thisShowing.getDate(), 
																	                      thisShowing.getTime(), thisShowing.getPrice(), thisShowing.getTheater().getTheaterNumber(), "B3");
															user.addCurrentTicket(newTicket);
															System.out.println("Great! Your ticket is reserved! \nTicket Stub: \n" + newTicket);
															System.out.print("Would you like to reserve any more tickets for this showing? \n1. Yes \n2. No \nYour Selection: ");
															selection = "";
															acceptable = false;
															while(!acceptable) //Check for acceptable Response
															{
																selection = in.next();
																acceptable = Menu.acceptable(selection, 2); 
																if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
															}
															if(selection.equals("1")) //yes
															{
																System.out.println("Ok!");
															}
															else if(selection.equals("2")) //no
															{
																System.out.println("See you Soon!");
																done = true;
															}
														}
													}
													else if(selection.equals("3")) //Row C
													{
														System.out.println("What seat would you like to sit in: ");
														System.out.print("1. Seat 1 \n2. Seat 2 \n3. Seat 3 \nYour Selection: ");
														selection = "";
														acceptable = false;
														while(!acceptable) //Check for acceptable Response
														{
															selection = in.next();
															acceptable = Menu.acceptable(selection, 3); 
															if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
														}
														if(selection.equals("1")) //Seat 1
														{
															Ticket newTicket = new Ticket(movieTheater.getTheaterName(), 
																	                      movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).getTitle(), thisShowing.getDate(), 
																	                      thisShowing.getTime(), thisShowing.getPrice(), thisShowing.getTheater().getTheaterNumber(), "C1");
															user.addCurrentTicket(newTicket);
															System.out.println("Great! Your ticket is reserved! \nTicket Stub: \n" + newTicket);
															System.out.print("Would you like to reserve any more tickets for this showing? \n1. Yes \n2. No \nYour Selection: ");
															selection = "";
															acceptable = false;
															while(!acceptable) //Check for acceptable Response
															{
																selection = in.next();
																acceptable = Menu.acceptable(selection, 2); 
																if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
															}
															if(selection.equals("1")) //yes
															{
																System.out.println("Ok!");
															}
															else if(selection.equals("2")) //no
															{
																System.out.println("See you Soon!");
																done = true;
															}
														}
														else if(selection.equals("2")) //Seat 2
														{
															Ticket newTicket = new Ticket(movieTheater.getTheaterName(), 
																	                      movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).getTitle(), thisShowing.getDate(), 
																	                      thisShowing.getTime(), thisShowing.getPrice(), thisShowing.getTheater().getTheaterNumber(), "C2");
															user.addCurrentTicket(newTicket);
															System.out.println("Great! Your ticket is reserved! \nTicket Stub: \n" + newTicket);
															System.out.print("Would you like to reserve any more tickets for this showing? \n1. Yes \n2. No \nYour Selection: ");
															selection = "";
															acceptable = false;
															while(!acceptable) //Check for acceptable Response
															{
																selection = in.next();
																acceptable = Menu.acceptable(selection, 2); 
																if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
															}
															if(selection.equals("1")) //yes
															{
																System.out.println("Ok!");
															}
															else if(selection.equals("2")) //no
															{
																System.out.println("See you Soon!");
																done = true;
															}
														}
														else if(selection.equals("3")) //Seat 3
														{
															Ticket newTicket = new Ticket(movieTheater.getTheaterName(), 
																	                      movieTheater.browseMovies(status, titleIn, genreIn, ratingIn).get(i).getTitle(), thisShowing.getDate(), 
																	                      thisShowing.getTime(), thisShowing.getPrice(), thisShowing.getTheater().getTheaterNumber(), "C3");
															user.addCurrentTicket(newTicket);
															System.out.println("Great! Your ticket is reserved! \nTicket Stub: \n" + newTicket);
															System.out.print("Would you like to reserve any more tickets for this showing? \n1. Yes \n2. No \nYour Selection: ");
															selection = "";
															acceptable = false;
															while(!acceptable) //Check for acceptable Response
															{
																selection = in.next();
																acceptable = Menu.acceptable(selection, 2); 
																if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
															}
															if(selection.equals("1")) //yes
															{
																System.out.println("Ok!");
															}
															else if(selection.equals("2")) //no
															{
																System.out.println("See you Soon!");
																done = true;
															}
														}
													}
												}
											}
										}
					///**END SHOWING SELECT MENU**///
									}
									else if(selection.equals("4")) //Back
									{
										backToBrowseMenu = true;
										System.out.println("\n");
									}
								}
				///**END MOVIE VIEWER**///
							}
			///**END BOOK MOVIE MENU**///
						}
						else if(selection.equals("3")) //View User's Current Tickets
						{
			///**CURRENT TICKETS MENU**///
							int i = 0;
							boolean backToUserMenu = false;
							while(!backToUserMenu)
							{
				///**TICKET VIEWER**///
								if(user.getCurrentTickets().size() == 0) //Check if user has any current tickets
								{
									System.out.println("\nSorry, you have no tickets for any upcoming movies! \n");
									backToUserMenu = true;
								}
								else if(user.getCurrentTickets().size() == 1) //Check if user has one Ticket
								{
									System.out.println("\nTicket (" + (i + 1) + ")"); //Page Number
									System.out.println(user.getCurrentTickets().get(i)); //Display Ticket
									System.out.print("\n1. Go Back \nYour Selection: ");
									selection = ""; 
									acceptable = false;
									while(!acceptable) //Check for acceptable Response
									{
										selection = in.next();
										acceptable = Menu.acceptable(selection, 1); 
										if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
									}
									if(selection.equals("1")) //Go Back
									{
										backToUserMenu = true;
										System.out.println("\n");
									}
								}
								else if(i == 0) //Check if this is the first ticket in the list
								{
									System.out.println("\nTicket (" + (i + 1) + ")"); //Page Number
									System.out.println(user.getCurrentTickets().get(i)); //Display Ticket
									System.out.print("\n1. Next Ticket \n2. Go Back \nYour Selection: ");
									selection = ""; 
									acceptable = false;
									while(!acceptable) //Check for acceptable Response
									{
										selection = in.next();
										acceptable = Menu.acceptable(selection, 2); 
										if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
									}
									if(selection.equals("1")) //Next
									{
										i++;
										System.out.println("");
									}
									else if(selection.equals("2")) //Go Back
									{
										backToUserMenu = true;
										System.out.println("\n");
									}
								}
								else if((i + 1) == user.getCurrentTickets().size()) //Check if this is the last ticket in the list
								{
									System.out.println("\nTicket (" + (i + 1) + ")"); //Page Number
									System.out.println(user.getCurrentTickets().get(i)); //Display Ticket
									System.out.print("\n1. Previous Ticket \n2. Go Back \nYour Selection: ");
									selection = ""; 
									acceptable = false;
									while(!acceptable) //Check for acceptable Response
									{
										selection = in.next();
										acceptable = Menu.acceptable(selection, 2); 
										if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
									}
									if(selection.equals("1")) //Next
									{
										i--;
										System.out.println("");
									}
									else if(selection.equals("2")) //Go Back
									{
										backToUserMenu = true;
										System.out.println("\n");
									}
								}
								else //Tickets in the Middle of the List
								{
									System.out.println("\nTicket (" + (i + 1) + ")"); //Page Number
									System.out.println(user.getCurrentTickets().get(i)); //Display Ticket
									System.out.print("\n1. Next Ticket \n2. Previous Ticket \n3. Go Back \nYour Selection: ");
									selection = ""; 
									acceptable = false;
									while(!acceptable) //Check for acceptable Response
									{
										selection = in.next();
										acceptable = Menu.acceptable(selection, 3); 
										if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
									}
									if(selection.equals("1")) //Next
									{
										i++;
										System.out.println("");
									}
									else if(selection.equals("2")) //Previous
									{
										i--;
										System.out.println("");
									}
									else if(selection.equals("3")) //Go Back
									{
										backToUserMenu = true;
										System.out.println("\n");
									}
								}
				///**END TICKET VIEWER**///
							}
			///**END CURRENT TICKETS MENU**///
						}
						else if(selection.equals("4")) //User's Account
						{
			///**ACCOUNT MENU**///
							boolean backToUserMenu = false;
							while(!backToUserMenu)
							{
								System.out.println("\nYour Account: " + user.getUsername() + "\n");
								System.out.println("What would you like to do?");
								System.out.print("1. View Past Purchases \n2. Delete Account \n3. Go Back \nYour Selection: ");
								selection = ""; 
								acceptable = false;
								while(!acceptable) //Check for acceptable Response
								{
									selection = in.next();
									acceptable = Menu.acceptable(selection, 3);
									if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
								}
								if(selection.equals("1")) //View Past Purchases
								{
				///**PAST TICKETS MENU**///
									int i = 0;
									boolean backToAccountMenu = false;
									while(!backToAccountMenu)
									{
					///**TICKET VIEWER**///
										if(user.getPurchases().size() == 0) //Check if user has any current tickets
										{
											System.out.println("\nSorry, you have no past purchases to view! \n");
											backToAccountMenu = true;
										}
										else if(user.getPurchases().size() == 1) //Check if user has one Ticket
										{
											System.out.println("\nTicket (" + (i + 1) + ")"); //Page Number
											System.out.println(user.getPurchases().get(i)); //Display Ticket
											System.out.print("\n1. Go Back \nYour Selection: ");
											selection = ""; 
											acceptable = false;
											while(!acceptable) //Check for acceptable Response
											{
												selection = in.next();
												acceptable = Menu.acceptable(selection, 1); 
												if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
											}
											if(selection.equals("1")) //Go Back
											{
												backToAccountMenu = true;
												System.out.println("\n");
											}
										}
										else if(i == 0) //Check if this is the first ticket in the list
										{
											System.out.println("\nTicket (" + (i + 1) + ")"); //Page Number
											System.out.println(user.getPurchases().get(i)); //Display Ticket
											System.out.print("\n1. Next Ticket \n2. Go Back \nYour Selection: ");
											selection = ""; 
											acceptable = false;
											while(!acceptable) //Check for acceptable Response
											{
												selection = in.next();
												acceptable = Menu.acceptable(selection, 2); 
												if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
											}
											if(selection.equals("1")) //Next
											{
												i++;
												System.out.println("");
											}
											else if(selection.equals("2")) //Go Back
											{
												backToAccountMenu = true;
												System.out.println("\n");
											}
										}
										else if((i + 1) == user.getPurchases().size()) //Check if this is the last ticket in the list
										{
											System.out.println("\nTicket (" + (i + 1) + ")"); //Page Number
											System.out.println(user.getPurchases().get(i)); //Display Ticket
											System.out.print("\n1. Previous Ticket \n2. Go Back \nYour Selection: ");
											selection = ""; 
											acceptable = false;
											while(!acceptable) //Check for acceptable Response
											{
												selection = in.next();
												acceptable = Menu.acceptable(selection, 2); 
												if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
											}
											if(selection.equals("1")) //Next
											{
												i--;
												System.out.println("");
											}
											else if(selection.equals("2")) //Go Back
											{
												backToAccountMenu = true;
												System.out.println("\n");
											}
										}
										else //Tickets in the Middle of the List
										{
											System.out.println("\nTicket (" + (i + 1) + ")"); //Page Number
											System.out.println(user.getPurchases().get(i)); //Display Ticket
											System.out.print("\n1. Next Ticket \n2. Previous Ticket \n3. Go Back \nYour Selection: ");
											selection = ""; 
											acceptable = false;
											while(!acceptable) //Check for acceptable Response
											{
												selection = in.next();
												acceptable = Menu.acceptable(selection, 3); 
												if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
											}
											if(selection.equals("1")) //Next
											{
												i++;
												System.out.println("");
											}
											else if(selection.equals("2")) //Previous
											{
												i--;
												System.out.println("");
											}
											else if(selection.equals("3")) //Go Back
											{
												backToAccountMenu = true;
												System.out.println("\n");
											}
										}
					///**END TICKET VIEWER**///
									}
				///**END PAST TICKETS MENU**///
								}
								else if(selection.equals("2")) //Delete Account
								{
				///**DELETE ACCOUNT MENU**///
									System.out.println("\nAre you sure you would like to Delete your account? \n*This action will be FINAL and PERMANENT!*");
									System.out.print("1. YES \n2. NO \nYour Selection: ");
									selection = ""; 
									acceptable = false;
									while(!acceptable) //Check for acceptable Response
									{
										selection = in.next();
										acceptable = Menu.acceptable(selection, 2);
										if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
									}
									if(selection.equals("1")) //YES
									{
										System.out.println("\nGoodbye " + user.getUsername() + "!\n");
										try {user.deleteAccount();} 
										catch (IOException e) {e.printStackTrace();}
										
										backToUserMenu = true;
										backToLoginMenu = true;
									}
									else if(selection.equals("2")) //NO
									{System.out.println("\nOk!\n");}
				///**END DELETE ACCOUNT MENU**///
								}
								else if(selection.equals("3")) //Back
								{
									System.out.println();
									backToUserMenu = true;
								}
							}
			///**END ACCOUNT MENU**///
						}
						else if(selection.equals("5")) //Logout
						{
							System.out.println("\nAre You sure you would like to logout?");
							System.out.print("1. Yes \n2. No \nYour Selection: ");
							selection = ""; 
							acceptable = false;
							while(!acceptable) //Check for acceptable Response
							{
								selection = in.next();
								acceptable = Menu.acceptable(selection, 2);
								if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
							}
							if(selection.equals("1")) //Yes
							{
								System.out.println("\nGoodbye!\n");
								backToLoginMenu = true;
								try {user.deleteAccountData();
								     user.storeTickets();} 
								catch (IOException e) {e.printStackTrace();}
							}
							else if(selection.equals("2")) //No
							{System.out.println("\nOk!\n");}
						}
					}
		///**END USER MENU**///
				}
	///**END LOGIN MENU**///
			}
			else if(selection.equals("2")) //Register Account
			{
	///**REGISTER ACCOUNT MENU**///
				boolean backToBootMenu = false;
				while(!backToBootMenu)
				{
					System.out.print("\nWelcome New User! \n1. Create new Account \n2. Go Back \nYour Selection: ");
					selection = ""; 
					acceptable = false;
					while(!acceptable) //Check for acceptable Response
					{
						selection = in.next();
						acceptable = Menu.acceptable(selection, 2); 
						if(!acceptable) {System.out.print("Please Enter a Valid Response \nYour Selection: ");}
					}
					if(selection.equals("1")) //Create New Account
					{
						User newUser = new User(); //Create a new User Object
						System.out.println("\nPlease enter your desired Username.");
						System.out.println("\nThings to remeber: \n Pick a Username you like, you won't be able to change it later. "
							           	+ "\n We cannot have duplicate Usernames, so there is a chance the one you enter is already taken. "
							        	+ "\n The Username you enter MUST NOT contain any spaces.\n");
						System.out.print("Username: ");
						newUser.setUsername(in.next());
						
						System.out.println("\nPlease enter your desired Password.");
						System.out.println("\nThings to remeber: \n Pick a Password you like, you won't be able to change it later. "
								         + "\n The Password you enter MUST NOT contain any spaces. \n");
						System.out.print("Password: ");
						newUser.setPassword(in.next());
						
						System.out.println("");
						try {newUser.registerAccount();} 
						catch (IOException e) {e.printStackTrace();}
						System.out.println("");
					}
					else if(selection.equals("2")) //Back
					{
						System.out.println("\nOk\n");
						backToBootMenu = true;
					}
				}
	///**END REGISTER ACCOUNT MENU**///
			}
			else if(selection.equals("3")) //Exit Program
			{exitCompletely = true;}
		}
		in.close();
///**END BOOT MENU**///
	}
}
