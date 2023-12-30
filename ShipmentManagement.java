import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



/**
 *
 * This class contains methods that will aid remote pharmacies in 
 * shipping medicines to peoples homes. It uses the shipping class and creates 
 * objects from an input file typed in by the user. 
 * The program stores all of the obects
 * created from the file into a array list of shipping objects.
 * The user will then have the
 * option to print the list out, sort by shipmentID, trackingNumber, 
 * then print the sorted list
 * enter a new file or exit
 * the program.  
 *  
 *
 * @author Drew Zabkar
 * Email: zabkaran@maimioh.edu
 * Professor: Dr. Garrett Goodman
 * Class: CSE 174 Section L
 * Assignment: Project 4 Remote Pharmacy Medicine Shipment Management
 * file Project4.java
 * 12/6/2023
 *
 * 
 */

public class ShipmentManagement {
    // user input scanner
    private static Scanner in = new Scanner(System.in); 
    // a variable that will be made true when the 
    // sorted methods are called. 
    private static boolean sorted = false;
    public static void main(String [] agrs) {
        //defines the shippingInfo and sortedShippingInfo ArrayLists
        ArrayList<Shipping> shippingInfo;
        shippingInfo = new ArrayList<Shipping>();
        ArrayList<Shipping> sortedShippingInfo = new ArrayList<Shipping>();
        int choice;
        // menu will appear as long as 6 is not chosen
        do {
            menu();
            choice = in.nextInt();
            userChoice(shippingInfo, choice, sortedShippingInfo);
        } while (choice != 6); 
    }
    
    /**
	* This method prints the menu that is needed to be displayed for 
    * so the user knows the available options and can decide what 
    * to chose. 
    *
	*/
    
    public static void menu() {
       
        System.out.println("1. Load from a file");
        System.out.println("2. Print from the loaded list");
        System.out.println("3. Sort the list based on shipment IDs");
        System.out.println("4. Sort the list based on the tracking numbers");
        System.out.println("5. Print the sorted list");
        System.out.println("6. Exit");
        System.out.println("Enter a number[1-6]: ");
    }
    
    /**
	* This method sorts through an array list using the slection sort
    * method.
    * It takes the first value and swaps it with the lowest value then 
    * repeates
    * this step excluding the fist value until there is one value left.
    * That value will be in the correct spot and be last.    
	* 
    * @param shippingInfo an array list of Shipping to be applied depending
    * on the choice the user selects
    * @param choice an integer to decide what method the program will call
    * through if statements
    * @param shippingInfo an array list of Shipping to be applied depending
    * on the choice the user selects.  
    *
	*/
    
    public static void userChoice(ArrayList<Shipping> shippingInfo, int choice, 
        ArrayList<Shipping> sortedShippingInfo) {
        // makes sure that the file is not empty
        // if file is empty it will display a message because of
        // the valid boolean variable. 
        boolean valid = shippingInfo.isEmpty();
        if (choice == 1) {
            initialize(shippingInfo, sortedShippingInfo);
            System.out.println("Loading from the file is done!");
            System.out.println("");
        } else if (choice == 2 && !valid) {
            display(shippingInfo);
        } else if (choice == 3 && !valid) {
            sort1(sortedShippingInfo);
        } else if (choice == 4 && !valid) {
            sort2(sortedShippingInfo);
        } else if (choice == 5 && !valid) {
            if (!sorted) {
                System.out.println("Nothing sorted yet!");    
            } else {
                display(sortedShippingInfo);
            }
        } else if (choice == 6) {
            System.out.println("End!");
        } else {
            System.out.println("No data has been loaded yet!");
        }
    }
    /**
	* This method initializes the array list with the values from 
    * the file using a while statement. It also uses a do while statement 
    * to make sure that the user inputs the proper file and will loop 
    * until the user enters a file that is valid.     
	* 
    * @param shippingInfo an array list of Shipping to be initialized 
    * in the method
    * @param shippingInfo an array list of Shipping to be intzialized
    * in the method  
    *
    * @return an array list filled with the values from the file that the user 
    * inputted.
    * 
	*/

    public static ArrayList<Shipping> initialize(ArrayList<Shipping> 
        shippingInfo, ArrayList<Shipping> sortedShippingInfo) {
        boolean success = false;
		Scanner file = null;
        int lines = 0;
        String input = "";
	    while (!success) {
            System.out.print("Enter the name of the file: ");
            System.out.println("");
            input = in.next();
            try {
				file = new Scanner(new File(input));
				while (file.hasNext()) {
                    //adds the new shipping object to the end 
                    //of the list using .add()
                    Shipping shipment = new Shipping(file.nextInt(), 
                        file.nextInt(), file.next(), file.next());
                    shippingInfo.add(shipment);
                    sortedShippingInfo.add(shipment);
                }
				success = true;
			} catch (FileNotFoundException e) {
				System.out.println(e.getMessage());
			} finally {
				try {
					file.close();
				} catch (Exception f) {
					f.printStackTrace();
				}
			}
		}
        return shippingInfo;
    }
    
    /**
	* This method displays the Shipping values of the array line by line, 
    * 10 values at a time. After the 10th display the method will ask the user 
    * to enter an something other than 's' to continue. As long as 's' is not 
    * entered the method will continue to print 10 Shipping values at a time
    * until all values have been displayed. After all values have been 
    * displayed, the user can enter any value to be returned back to the 
    * menu. If 's' is pressed at any time the method will end.    
	* 
    * @param arr an array list of Shipping to be printed in the below method
    *  
	*/
    
    public static void display(ArrayList<Shipping> arr) {
        System.out.println("**** Printing the list ****");
        int i = 0;
        // boolean to determine when how long the do loop
        // will run for. 
        boolean run = true;
        do {
            System.out.println(arr.get(i) + " ");
            i++;
            if (i % 10 == 0) {
                System.out.println("Enter something to continue"
                    + "/enter s to stop");
                String stop = in.next();
                if (stop.equals("s")) {
                    //will cause the do loop to end. 
                    run = false;
                } else if (i == arr.size()) {
                    run = false;
                }
                     
            }
        } while (run && i < arr.size());
        System.out.println("");
    }
    
    /**
	* This method sorts the array of shipping class by the shipment ID
    * The method does so by using the bubble sort method and comparing 
    * adjacent elements to each other. The program will run until
    * the sort is completed.     
	* 
    * @param arr an array list of Shipping to be sorted by the below method
    *  
	*/

    public static void sort1(ArrayList<Shipping> arr) {
        sorted = true;
        boolean madeSwap;
        do {
            madeSwap = false;
            for (int i = 0; i < arr.size() - 1; i++) {
                // compare side by side elements
                if (arr.get(i).getShipmentID() 
                    > arr.get(i + 1).getShipmentID()) {
                    Shipping temp = arr.get(i);
                    arr.set(i, arr.get(i + 1));
                    arr.set(i + 1, temp);
                    madeSwap = true;
                } 
            }
        } while (madeSwap); 
        System.out.println("Sorting is done!");
        System.out.println("");
    }
    
    /**
	* This method sorts the array of shipping class by the Tracking Number. 
    * The method does so by using the bubble sort method and comparing 
    * adjacent elements to each other. The program will run until
    * the sort is completed.     
	* 
    * @param arr an array list of Shipping to be sorted by the below method
    *  
	*/

    public static void sort2(ArrayList<Shipping> arr) {
        sorted = true;
        boolean madeSwap;
        do {
            madeSwap = false;
            for (int i = 0; i < arr.size() - 1; i++) {
                // compare side by side elements
                if (arr.get(i).getTrackingNumber().compareTo(
                    arr.get(i + 1).getTrackingNumber()) > 0) {
                    Shipping temp = arr.get(i);
                    arr.set(i, arr.get(i + 1));
                    arr.set(i + 1, temp);
                    madeSwap = true;
                } 
            }
        } while (madeSwap);
        System.out.println("Sorting is done!");
        System.out.println("");
 
    } 

    

}
