import java.util.Scanner;

/**
* This class gets the name of the user from the user and then frormats
* it properly so that each name: first, middle and last takes 
* the formatting exists so that each name has 8 spaces allocated to it
* if 8 spaces are not used it will print blank spaces to fill the empty spaces 
* if the name is longer than 8 characters this program will not work
* The user has the option of entering first name, first and last name
* first name , middle initial, last name, and finally first name 
* middle name and last name. The program then will use the various
* methods to display the name with the proper formatting. 
*
*
*
* @author Drew Zabkar
* Email: zabkaran@maimioh.edu
* File: Lab9.java
* Professor: Dr. Garrett Goodman
* Assignment: Hospital Patient Insurance Name Formattter
*/

public class HospitalPatientInsuranceNameFormatter {

    private static Scanner in = new Scanner(System.in);
    
    public static void main(String [] args) {
        
        System.out.println("Welcome to the Name Formatter");
        boolean again = true;
        do {
            menu();
            int choice = in.nextInt();
            //if statement so this message will not print if the user
            //decides to end the program. 
            if (choice < 5) {
                System.out.print("Please enter the name parts separated"
                    + " by a space: ");
            }
            //switch statement that will call a method based on
            // the various parameters
            switch (choice) {
        
            case 1:
                formatName(in.next());
            break;
            case 2: 
                formatName(in.next(),in.next());
                break;
            case 3: 
                formatName(in.next(), in.next().charAt(0), in.next());
            break;
            case 4: 
                formatName(in.next(), in.next(), in.next());    
            break;
            case 5:
                System.out.println("Thank You for using the Name Formatter!");
                again = false; 
            break;
            default:
                System.out.println("Thank You for using the Name Formatter!");
                again = false;
            }
        } while (again);
    }
    
    /**
	* This method returns the users first name with the correct formatting
	* * the method will allocate 8 spaces for this name
    * if the name is over 8 spaces this will not work
    *
	* @param str a String value that conatins the users first name
	* 
	*/

    public static void formatName(String firstName) {
        
        System.out.printf("%-8s\n", firstName);
    
    }
    
    /**
	* This method returns the users first and last name with the correct
	* formatting the method will allocate 8 spaces for each of these names
    * if one of the names is over 8 spaces this will not work
    *
	* @param str a String value that conatins the users first name
	* @param str a String value that conatins the users last name
    *
	*/
    
    public static void formatName(String firstName, String lastName) {
        
        System.out.printf("%-8s %-8s\n", firstName, lastName);
    
    }
    
    /**
	* This method returns the users first name, middle initial and last name
    * the method will allocate 8 spaces for each of these names or letters
    * if one of the names is over 8 spaces this will not work
	* 
	* @param str a String value that conatins the users first name
	* @param char a character that contains the users middle initial
    * @param str a String value that conatins the users last name
    *
	*/

    
    public static void formatName(String firstName, 
        char middleInitial, String lastName) {
        
        String middle = middleInitial + "."; 
        System.out.printf("%-8s %-8s %-8s\n", firstName, middle, lastName);
    
    }
    
    /**
	* This method returns the users first name, middle name and last name
	* the method will allocate 8 spaces for each of these names
    * if one of the names is over 8 spaces this will not work
	* @param str a String value that conatins the users first name
	* @param str a String value that contains the users middle name 
    * @param str a String value that conatins the users last name
    *
	*/

    
    public static void formatName(String firstName, 
        String middleName, String lastName) {
    
        System.out.printf("%-8s %-8s %-8s"
            +"\n", firstName, middleName, lastName);
    
    }
    
    /**
	* This method prints the option menu so the user can select an option
	*/

    
    public static void menu() {
        System.out.println("Select an Option!");
        System.out.println("1. Only First Name");
        System.out.println("2. First Name, and Last Name");
        System.out.println("3. First Name, Middle Initial, and Last Name");
        System.out.println("4. First Name, Middle Name, and Last Name");
        System.out.println("5. Exit");
    
    }
    
    




}
