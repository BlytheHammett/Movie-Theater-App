import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class PersonDriver {
    public static void main(String[] args) throws IOException { //test method, will delete before merging
//        Scanner sc = new Scanner(System.in);
        System.out.println("Create three Person objects and register their accounts, then tests registration.");
        File target = new File("src//loginInfo.txt");
        PrintWriter wipe = new PrintWriter(target);
        wipe.print("");
        Person one = new Person();
        one.setUsername("Lysanderoth");
        one.setPassword("bababooey420");
        System.out.println("Expected result: Registration Successful.");
        System.out.print("Actual result: ");
        one.registerAccount();
        Person two = new Person("Spongebob2000", "IMREADY!");
        System.out.println("Expected result: Registration Successful.");
        System.out.print("Actual result: ");
        two.registerAccount();
        Person three = new Person("Lysanderoth", "nowingsangel");
        System.out.println("Expected result: Registration Failed.");
        System.out.print("Actual result: ");
        three.registerAccount();
        System.out.println();
        System.out.println("Next, test login(username, password). a login should fail if either a password does not match a correct username or if a username is not found.");
        String thisUsername = "Lysanderoth";
        String thisPassword = "bababooey420";
        System.out.println("Expected result: Login success");
        System.out.print("Actual result: ");
        if (one.loginCheck(thisUsername, thisPassword)) {
            System.out.println("Login success.");
        }
        else {
            System.out.println("Login failure.");
        }
        //this next test uses a correct username but an incorrect password, and should therefore fail.
        thisUsername = "Spongebob2000";
        thisPassword = "imready!";
        System.out.println("Expected result: Login failure.");
        System.out.print("Actual result: ");
        if (two.loginCheck(thisUsername, thisPassword)) {
            System.out.println("Login Success.");
        }
        else {
            System.out.println("Login failure.");
        }
        //finally, this test uses completely inaccurate credentials and should fail.
        System.out.println("Expected Result: Login failure.");
        System.out.print("Actual result: ");
        if (two.loginCheck("bruh", "moment")) {
            System.out.println("Login success.");
        }
        else {
            System.out.println("Login failure.");
        }
        System.out.println();
        System.out.println("Next, test account deletion. This will be accomplished by calling deleteAccount() for an object, and then attempting to login.");
        two.deleteAccount();
        System.out.println("Expected Result: Login failure.");
        System.out.print("Actual result: ");
        if (two.loginCheck(two.getUsername(), two.getPassword())) {
            System.out.println("Login success.");
        }
        else {
            System.out.println("Login failure.");
        }
        System.out.println("Expected result: Login Success");
        System.out.print("Actual result: ");
        if (one.loginCheck(one.getUsername(), one.getPassword())) {
            System.out.println("Login success.");
        }
        else {
            System.out.println("Login failure.");
        }
        wipe.close();
    }
}
