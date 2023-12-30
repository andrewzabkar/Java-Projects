/**
* This class takes a file containing 3000 lines each containing 
* a nickname and a id number. The goal of this class is to format all of
* the customers into a customer object array. Using the Customer class
* There are methods that will sort through
* the array either linear or binary. Then the search methods are used to
* find various customers in the array. 
* 
*   
*
* @author Drew Zabkar
* Email: zabkaran@maimioh.edu
* File: Lab12.java
* Professor: Dr. Garrett Goodman
* Assignment: Online Business Customer Management
* 11/16/2023
*
*/

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class IDOrganizer {

    public static void main(String[] args) {
    
        //Customer cos1 = new Customer("blue_jet", 11135422);
        //Customer cos2 = new Customer("blue_jet", 222356478);
    
        //System.out.println(cos1.equals(cos2));
        //System.out.println(cos1.getName());
        //System.out.println(cos2.getId());
        //System.out.println(cos2.toString());
        
		lineCount();
        //defines the customer array
        Customer[] customers = new Customer[3000]; 
        initialize(customers);
        System.out.println("[0]: " + customers[0]);
        System.out.println("[10]: " + customers[10]);
        System.out.println("[1500]: " + customers[1500]);
        System.out.println("[last index]: " + customers[customers.length - 1]);
        System.out.println();
        test(customers);
        test2(customers);
	}
    
    /**
	* This method counts the number of lines in the given file
    * using a while loop 
	* 
    * 
    * @return an integer value of the number of lines in the file
    *
	*/

    public static void lineCount() {
        boolean success = false;
		String fileContents = "";
		Scanner file = null;
        int lines = 0;
	    while (!success) {
			try {
				file = new Scanner(new 
                    File("Customer_list.txt"));
                //adds a tally each time the file has a line
				while (file.hasNextLine()) {
					fileContents += file.nextLine();
                    lines++;
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
        System.out.println("There are " + lines + " lines in the file");
    }
    
    /**
	* This method takes an empty array with 3000 indeces. Then the
    * method gets creates new objects for each of of the lines 
    * in the file.   
	* 
    * @param customers a Customer object array to be filled with new 
    * customer objets with data from the file.
    * 
    * @return customers as an object array filled with Customer objects
    *
	*/

    public static Customer[] initialize(Customer[] customers) {
        boolean success = false;
		Scanner file = null;
        int lines = 0;
	    while (!success) {
			try {
				file = new Scanner(new 
                    File("Customer_list.txt"));
				while (file.hasNextLine()) {
                    //loops through the file and creates a new 
                    //customer object for each line in the file
					customers[lines] = new 
                        Customer(file.next(), file.nextLong());
                    lines++;
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
        return customers;
    }
    
    /**
	* This method searches through the array index by index to find the
    * matching object defined by the key variable   
	* 
    * @param customers a Customer object array to be compared to a 
    * given key.
    * @param key a Customer object to be compared to the object ids in the 
    * customers array.
    * 
    * @return an int of the index value that corresponds to index 
    * of the array customers that the
    * key was found at.
    *
	*/

    public static int linearSearch(Customer[] customers, Customer key) {
        
        int counter = 0;
        for (int i = 0; i < customers.length; i++) {
            counter++;
            if (customers[i].getId() == key.getId() 
                && customers[i].getName().equals(key.getName())) {
                System.out.println("Linear Search: the key is found after " 
                    + counter + " comparison");
                return i;
            }
        }
        System.out.println("Linear Search: the key is found after " 
            + counter + " comparison");
        return -1;
    }
    
    /**
	* This method searches through the array using a 
    * binary method to find the
    * matching object defined by the key variable   
	* 
    * @param customers a Customer object array to be compared to a 
    * given key.
    * @param key a Customer object to be compared to the object ids in the 
    * customers array.
    * 
    * @return an int of the index value that corresponds to index 
    * of the array customers that the
    * key was found at.
    *
	*/
    
    public static int binarySearch(Customer[] customers, Customer key) {
        
        int counter = 0;
        int low = 0;
		int high = customers.length - 1;
		
		while (low <= high) {
            counter++;
			int mid = (low + high) / 2;
			
			if (customers[mid].getId() == key.getId()) {
                System.out.println("Binary Search: the key is found after "
                     + counter + " comparison");
				return mid;
			}	
			if (customers[mid].getId() > key.getId()) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		System.out.println("Binary Search: the key is found after " 
            + counter + " comparison");
        return -1;
	}
    
    /**
	* This method prints where the given key was 
    * found in the customer array   
	*/
    
    public static void test(Customer[] customers) {
    
        Customer key1 = new Customer("Zola", 2074980639L);
        Customer key = new Customer("Delilah", 4350600189L);
        
        System.out.printf("The object " + key.toString() 
            + " located at an index of: " + linearSearch(customers, key));
        System.out.println();
        System.out.printf("The object " + key.toString() 
            + " located at an index of: \n" + binarySearch(customers, key));
        System.out.println();
        System.out.printf("The object " + key1.toString() 
            + " located at an index of: " + linearSearch(customers, key1));
        System.out.println();
        System.out.printf("The object " + key1.toString() 
            + " located at an index of: " + binarySearch(customers, key1));
        System.out.println();
        System.out.println();
    }
    
     /**
	* This method prints where the given key was 
    * found in the customer array   
	*/

    public static void test2(Customer[] customers) {
        
        Customer key2 = new Customer("Reinaldo", 9988586038L);
        Customer key3 = new Customer("CSE174", 1111111111L);

        
        System.out.printf("The object " + key2.toString() 
            + " located at an index of: " + linearSearch(customers, key2));
        System.out.println();
        System.out.printf("The object " + key2.toString() 
            + " located at an index of: " + binarySearch(customers, key2));
        System.out.println();
        System.out.println();
        System.out.printf("The object " + key3.toString() 
            + " located at an index of: " + linearSearch(customers, key3));
        System.out.println();
        System.out.println();
        System.out.printf("The object " + key3.toString()
             + " located at an index of: " + binarySearch(customers, key3));

    
    }
    
    
}
