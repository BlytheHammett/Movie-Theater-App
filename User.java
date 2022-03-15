import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListIterator;
import java.util.Scanner;

/**
 * Represents a User of the MovieTheater System (Customer)
 */
public class User extends Person
{
	//Variables
	/**
	 * List of all, if any, current Tickets for upcoming showings.
	 */
	private ArrayList<Ticket> currentTickets;
	/**
	 * List of all Past Purchases
	 */
	private ArrayList<Ticket> purchases;

	// Constructors
	/**
	 * Creates a User with a random login username and a default login password.
	 */
	public User()
	{
		super();

		this.currentTickets = new ArrayList<>();
		this.purchases = new ArrayList<>();
	}
	/**
	 * Creates a Person with a given login username and password.
	 * @param usernameIn is the login username for this User.
	 * @param passwordIn is the login password for this User.
	 */
	public User(String usernameIn, String passwordIn)
	{
		super(usernameIn, passwordIn);

		this.currentTickets = new ArrayList<>();
		this.purchases = new ArrayList<>();
	}

	/**
	 * Creates a User with given username, password, list of current tickets, and list of past purchses, to use in Login().
	 * @param usernameIn username
	 * @param passwordIn password
	 * @param theseCurrTickets the list of current tickets, obtained from the save file
	 * @param thesePastTickets the list of past tickets, obtained from the save file
	 */

	public User(String usernameIn, String passwordIn, ArrayList<Ticket> theseCurrTickets, ArrayList<Ticket> thesePastTickets) {
		super(usernameIn, passwordIn);
		this.currentTickets = theseCurrTickets;
		this.purchases = thesePastTickets;
	}

	// Getters/Setters

	//Current Tickets
	/**
	 * Gets List of Current Tickets for this User.
	 * @return currentTickets
	 */
	public ArrayList<Ticket> getCurrentTickets()
	{
		return this.currentTickets;
	}
	/**
	 * Changes the currentTickets for this User.
	 * @param currentTicketsIn is the new currentTickets.
	 */
	public void setCurrentTickets(ArrayList<Ticket> currentTicketsIn)
	{
		this.currentTickets = currentTicketsIn;
	}
	/**
	 * Adds given ticketIn to the list of Current Tickets.
	 * @param ticketIn Ticket to be added.
	 */
	public void addCurrentTicket(Ticket ticketIn)
	{
		this.currentTickets.add(ticketIn);
	}
	/**
	 * Removes given ticketOut from the list of Current Tickets.
	 * @param ticketOut Ticket to be removed.
	 */
	public void removeCurrentTicket(Ticket ticketOut)
	{
		this.currentTickets.remove(ticketOut);
	}
	/**
	 * Removes all Tickets from the list of Current Tickets.
	 */
	public void clearAllCurrentTickets()
	{
		this.currentTickets = new ArrayList<>();
	}

	//Purchases
	/**
	 * Gets the List of Past Purchases made by this User
	 * @return purchases
	 */
	public ArrayList<Ticket> getPurchases()
	{
		return this.purchases;
	}
	/**
	 * Changes the List of Past Purchases made by the User.
	 * @param purchasesIn is the new purchases
	 */
	public void setPurchases(ArrayList<Ticket> purchasesIn)
	{
		this.purchases = purchasesIn;
	}
	/**
	 * Adds the given purchaseIn to the list of Purchases.
	 * @param purchaseIn Ticket to be added.
	 */
	public void addPurchase(Ticket purchaseIn)
	{
		this.purchases.add(purchaseIn);
	}



	//Methods

	/**
	 * Takes the given ticket list and converts it to a serialized format via toSerializableString() and a StringBuilder.
	 * @param ticketList an ArrayList of Tickets
	 * @return a serialized string of the ticket list.
	 */
	public String serializeTicketList(ArrayList<Ticket> ticketList) {
		ArrayList<String> ticketStrings = new ArrayList<>(); //List of tickets in a string format that is easier to work with in a text file
		for (Ticket ticket : ticketList) {
			ticketStrings.add(ticket.toSerializableString());
		}
		StringBuilder out = new StringBuilder();
		for (int i = 0; i < ticketStrings.size(); i++) {
			if (i == ticketStrings.size() - 1) {
				out.append(ticketStrings.get(i));
			}
			else {
				out.append(ticketStrings.get(i)).append("_");
			}
		}
		return out.toString();
	}

	/**
	 * Saves user data including a username, current tickets, and past tickets in groups of three lines in a text file
	 * void return type.
	 */
	public void storeTickets() throws IOException {
		String outStringCurr = serializeTicketList(this.getCurrentTickets());
		String outStringPast = serializeTicketList(this.getPurchases());
		File outputFile = new File("src//ticketInfo.txt"); //file to store current tickets
		Writer out = new FileWriter(outputFile, true); //FileWriter which appends a new user's information when called.
		out.write(this.getUsername() + "~" + outStringCurr + "~" + outStringPast + "\n");
		out.close();
	}


	/**
	 * A second login function, used internally after a SUCCESSFUL loginCheck() that returns a User object with given username, password, and the stored current and past ticket details.
	 * @param username the logged in user's username
	 * @param password the logged in user's password
	 * @return a User object with the given username, password, and ticket lists if the login is successful
	 */
	public static User login(String username, String password) throws FileNotFoundException {
		File inputFile = new File("src//ticketInfo.txt");
		Scanner in = new Scanner(inputFile);
		String[] currTemp; //temp array of the serialized currTicket strings
		String[] currTempElements; //temp array of the elements of each string in currTemp
		String[] pastTemp; //temp array of the serialized pastTicket strings
		String[] pastTempElements; //temp array of the elements of each string in pastTemp
		ArrayList<Ticket> theseCurrTickets = new ArrayList<>();
		ArrayList<Ticket> thesePastTickets = new ArrayList<>();
		while (in.hasNextLine()) {
			String temp = in.nextLine();
			if (temp.split("~")[0].equals(username)) 
			{
				if(temp.split("~").length == 3)
				{
					currTemp = temp.split("~")[1].split("_");
					pastTemp = temp.split("~")[2].split("_");
					for (String s : currTemp) 
					{
						currTempElements = s.split("\\|");
						Ticket t = new Ticket(currTempElements[0], currTempElements[1], currTempElements[2], currTempElements[3], Double.parseDouble(currTempElements[4]), currTempElements[5], currTempElements[6]);
						theseCurrTickets.add(t);
					}
					for (String s : pastTemp) 
					{
						pastTempElements = s.split("\\|");
						Ticket t = new Ticket(pastTempElements[0], pastTempElements[1], pastTempElements[2], pastTempElements[3], Double.parseDouble(pastTempElements[4]), pastTempElements[5], pastTempElements[6]);
						thesePastTickets.add(t);
					}
				}
				else
				{
					return new User(username, password);
				}
			}
			else {
				return new User(username, password);
			}
		}
		in.close();
		return new User(username, password, theseCurrTickets, thesePastTickets);
	}

	/**
	 * Deletes User data. Called right before deleteAccount() in Menu.java
	 */

	public void deleteAccountData() throws IOException {
		File inputFile = new File("src//ticketInfo.txt"); //file to parse through
		ArrayList<String> linesToKeep = new ArrayList<>(); //list of lines to keep
		Scanner in = new Scanner(inputFile); //scanner to parse through input file
		while (in.hasNextLine()) {
			linesToKeep.add(in.nextLine());
		}
		String[] target = {this.getUsername(), serializeTicketList(this.getCurrentTickets()), serializeTicketList(this.getPurchases())}; //target info to delete
		ListIterator<String> it = linesToKeep.listIterator(); //iterator to modify the contents of linesToKeep to avoid a ConcurrentModificationException
		while(it.hasNext()) { //remove target credentials from linesToKeep
			String temp = it.next();
			if (Arrays.equals(temp.split("~"), target)) {
				it.remove();
			}
		}
		File outputFile = new File("src//ticketInfo.txt");
		PrintWriter out = new PrintWriter(outputFile);
		for (String line : linesToKeep) {
			out.println(line);
		}
		out.close();

	}


}