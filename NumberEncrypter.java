/**
 * Drew Zabkar
 * Section: L
 * Fall 2023
 * This program encrypts the digits of a phone number.
 */
import java.util.Scanner;  // importing Scanner class

public class NumberEncrypter {
    public static void main(String[] args) {
        // Defining a Scanner object
        Scanner in = new Scanner(System.in);
        
        // Prompting the user with a message
        System.out.print("Enter a 10 digit phone number (e.g. 5131234567): ");
        
        // Saving the given number inside a constant variable
        final long PHONE_NUM = in.nextLong();
        
        // Variable that will obtain and store the Area Code
        long first3Digits = PHONE_NUM / 10000000;
        int areaCode = (int) first3Digits;
        
        //Variable that will obtain and store the Central Office Code 
        long second3Digits = (PHONE_NUM / 10000) % 1000;
        int centralOfficeCode = (int) second3Digits; 
        
        //Variable that will obtain and store the Station Number 
        long last4Digits = PHONE_NUM % 10000;
        int stationNumber = (int) last4Digits; 
        
        //Code that will print the phone number in proper format
        System.out.printf("(%d) %d - %d", areaCode, centralOfficeCode, 
            stationNumber);
        
        //Code that will encrypt the Station Number
        int encryptedStationNumber1 = stationNumber / 100;
        encryptedStationNumber1 = encryptedStationNumber1 + 33;
        char stationCharacter1 = (char) encryptedStationNumber1;
        int encryptedStationNumber2 = stationNumber % 100;
        encryptedStationNumber2 = encryptedStationNumber2 + 33;
        char stationCharacter2 = (char) encryptedStationNumber2;
        
        System.out.printf("\n(%d) %d - " + stationCharacter2 
            + stationCharacter1, areaCode, centralOfficeCode);
             
        //Code that will encrypt the rest of the phone number
        int areaAndOfficeEncryption = (areaCode * 1000) + centralOfficeCode; 
        areaAndOfficeEncryption = Integer.MAX_VALUE - areaAndOfficeEncryption;
        System.out.println("\n" + areaAndOfficeEncryption);  
        
        //Code to arrange the results
        String encryptionNumber = "" + areaAndOfficeEncryption;
        System.out.println(stationCharacter2 
            + encryptionNumber + stationCharacter1);
      
         
    }
}
