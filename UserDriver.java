import java.io.IOException;
import java.util.ArrayList;

public class UserDriver {

    public static void main(String[] args) throws IOException {
        User uno = new User("Jeff_Bridges", "ironmonger");
        User dos = new User("Cloud", "sephirothisamemory");
        Ticket one = new Ticket("AMC Bloom", "Shrek 2", "4/20", "4:20 AM", 6.90, "13", "E3");
        Ticket two = new Ticket("AMC Bloom", "Avatar", "4/29", "11:00 AM", 6.90, "10", "B4");
        Ticket three = new Ticket("GQT Bloom", "The Avengers", "6/9", "6:00 PM", 6.90, "7", "S5");
        Ticket four = new Ticket("AMC Bloom", "Sonic the Hedgehog", "4/20", "4:20 AM", 6.99, "4", "A1");
        ArrayList<Ticket> curr = new ArrayList<>();
        ArrayList<Ticket> past = new ArrayList<>();
        curr.add(one);
        curr.add(two);
        past.add(three);
        past.add(four);
        uno.setCurrentTickets(curr);
        uno.setPurchases(past);
        dos.setCurrentTickets(curr);
        dos.setPurchases(past);
        System.out.println("Test that uno has current tickets and past purchases.");
        System.out.println("Expected output: String representations of one and two.");
        System.out.println("Actual output: \n");
        for (Ticket ticket : curr) {
            System.out.print(ticket.toString() + "\n" + "\n");
        }
        System.out.println("Expected output: String representations of three and four.");
        System.out.println("Actual output: \n");
        for (Ticket ticket : past) {
            System.out.print(ticket.toString() + "\n" + "\n");
        }
        System.out.println("Next, save current and past purchases to the text file buy Username. Successful registration will be a prerequisite for this in Menu.java");
        uno.storeTickets();
        System.out.println("Next, test login() by creating a new object which simulates a User logging back in. test buy returning toString representations of the tickets. \n");
        System.out.println("Expected output: string representations of one, two, three, and four \n");
        System.out.println("Actual output: \n");
        User unoLogBackIn = User.login("Jeff_Bridges", "ironmonger");
        //IMPORTANT: the following code block from lines 41-47 MUST be commented out to test account deletion.
        assert unoLogBackIn != null;
        for (Ticket ticket : unoLogBackIn.getCurrentTickets()) {
            System.out.println(ticket.toString() + "\n" + "\n");
        }
        for (Ticket ticket : unoLogBackIn.getPurchases()) {
            System.out.println(ticket.toString() + "\n" + "\n");
        }
        dos.storeTickets();
        dos.deleteAccountData();

    }

}
