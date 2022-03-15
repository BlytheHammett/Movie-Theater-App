import java.io.*;
import java.util.*;

/**
 * Represents a Person who can access the MovieTheater System.
 * A person has login credentials. (Username and Password)
 */
public class Person 
{
	//Variables
	/**
	 * The login username of this Person. Usernames should be unique.
	 */
	private String username;
	/**
	 * The login password of this Person. Password should not have spaces.
	 */
	private String password;

	/**
	 * A Map, with usernmaes as a key, of login credentials. This ensures that usernames are unique, and is used in verifying login info.
	 */
	private static Map<String, String> loginMap;
	//Constructors
	/**
	 * Creates a Person with a random login username and a default login password.
	 */
	public Person()
	{
		this.username = "GuestUser" + (int)(Math.random() * (101));
		this.password = "guest";
		loginMap = new HashMap<>();
	}
	/**
	 * Creates a Person with a given login username and password.
	 * @param usernameIn is the login username for this Person.
	 * @param passwordIn is the login password for this Person.
	 */
	public Person(String usernameIn, String passwordIn)
	{
		this.username = usernameIn;
		this.password = passwordIn;
		loginMap = new HashMap<>();
	}
	
	
	// Getters/Setters
	
	//Username
	/**
	 * Gets the login username for this Person.
	 * @return the username for this Person.
	 */
	public String getUsername()
	{
		return this.username;
	}
	/**
	 * Changes the login username for this Person.
	 * @param usernameIn is the new username for this Person.
	 */
	public void setUsername(String usernameIn)
	{
		this.username = usernameIn;
	}
		
	//Password
	/**
	 * Gets the login password for this Person.
	 * @return the password for this Person.
	 */
	public String getPassword()
	{
		return this.password;
	}
	/**
	 * Changes the login password for this Person.
	 * @param passwordIn is the new password for this Person.
	 */
	public void setPassword(String passwordIn)
	{
		this.password = passwordIn;
	}
	/**
	sets the loginMap, a static variable, for this running of the Person CLass.
	 Used in registerAccount() to prevent duplicate usernames and in login() to check login information.
	 */
	public static void setLoginMap(String inputFile) throws FileNotFoundException {
		loginMap = new HashMap<>();
		File input = new File(inputFile);
		Scanner in = new Scanner(input);
		String[] tempLogin;
		//populate the map with credentials saved in the text file
		while (in.hasNextLine()) {
			tempLogin = in.nextLine().split(" ");
			loginMap.put(tempLogin[0], tempLogin[1]);
		}
		in.close();
	}
	

	
	//Methods
	
	/**
	 * Registers a Default Guest account as a full Account.
	 * Changes Username and Password to something provided by the person.
	 * Duplicate Usernames are not allowed.
	 * Stores this Information.
	 */
	public void registerAccount() throws IOException
	{
		setLoginMap("src//loginInfo.txt"); //call to setter for loginMap
		File loginInfo = new File("src//loginInfo.txt"); //file to which info is saved
		Writer save = new FileWriter(loginInfo, true); //a FileWriter is used here because this method will be called multiple times separately. A PrintWriter would hard cap saved info at just one username/password.
		if (!(loginMap.containsKey(this.getUsername()))) { //check to see if the given account details are NOT already in loginMap (which means they would not be registered yet). if true, save their details
			save.write(this.getUsername() + " " + this.getPassword() + "\n");
			save.close();
			System.out.println("Registration Successful");
		}
		else { //otherwise, do nothing and inform the user that registration has failed due to duplicate username.
			System.out.println("Registration Failed: that username is already taken.");
		}


	}
	
	/**
	 * Checks if given login credentials are stored or not.
	 * Returns true or false if they match any saved credentials.
	 * Boolean used to break out of a loop.
	 * @param username the username
	 * @param password the password
	 * @return Whether or not the login credentials match any stored credentials.
	 */
	public boolean loginCheck(String username, String password) throws FileNotFoundException
	{
		//TODO
		setLoginMap("src//loginInfo.txt"); //call to setter for login map
		//checks the arguments, first to see if the username is in the map, then checks the password to see if it matches.
		//if the username and password BOTH match, return true. otherwise, return false.
		if (loginMap.containsKey(username)) {
			return loginMap.get(username).equals(password);
		}
		else {
			return false;
		}

	}
	
	/**
	 * Signs a person out of the MovieTheater System.
	 * @return boolean of false to break a menu loop.
	 */
	public boolean signOut()
	{
		//TODO
		return true;
	}
	
	/**
	 * Removes this Person from all stored lists.
	 * person is prompted to confirm that they want to delete their account.
	 * person object is removed from system.
	 * person's username and password are removed from the text file.
	 * @return boolean of false to break a menu loop.
	 */
	public boolean deleteAccount() throws IOException
	{
		//TODO: clean this up, test it
		File inputFile = new File("src//loginInfo.txt"); //input file containing the saved usernames and passwords
		ArrayList<String> linesToKeep = new ArrayList<>(); //ArrayList of lines to keep.
		Scanner in = new Scanner(inputFile);
		while (in.hasNextLine()) { //first, parse through the text file and add every line to the list.
			linesToKeep.add(in.nextLine());
		}
		String[] target = {this.getUsername(), this.getPassword()}; //target account to be deleted from the system.
		ListIterator<String> it = linesToKeep.listIterator(); //iterator to modify the contents of linesToKeep to avoid a ConcurrentModificationException

		while(it.hasNext()) { //remove target credentials from linesToKeep
			String temp = it.next();
			if (Arrays.equals(temp.split(" "), target)) {
				it.remove();
			}
		}
		File outputFile = new File("src//loginInfo.txt");
		PrintWriter out = new PrintWriter(outputFile);
		for (String line : linesToKeep) {
			out.println(line);
		}
		out.close();

		return true;
	}


}
