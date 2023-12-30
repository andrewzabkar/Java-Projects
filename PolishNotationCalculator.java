//Drew Zabkar
//10/9/23
//CSE 174 Section L

//Defines Imports 
import java.io.File; 
import java.util.Scanner;

//the goal of this program is to read from a given file
//chosen by the user. The program will then read the file 
//line by line and do the math on the program indicated by 
//the operators in the given line file
//the program will also take any error 
//into account
//then the program will print the result line by line

public class PolishNotationCalculator { 
    
    public static void main(String [] args) 
        throws java.io.FileNotFoundException {
        //Creates a scanner for user input as well as a scanner
        //That will read the given file that the 
        //user inputs
        Scanner in = new Scanner(System.in);
        System.out.print("Enter input file name: ");
        final String TEXT_FILE = in.next();
        Scanner read = new Scanner(new File(TEXT_FILE));
        //Defines empty variables that will be used during the program
        //that need to be used inside and outside of the following loops
        //Alert will be changed to have a different value when
        //there is a divide by zero, no operator or a non int value
        String operator = "";
        int lineNumber = 0;
        int previousLine = 0;
        String line = "";
        int alert = 0; 
        //while loop that runs while the file that the user inputted
        //has lines
        while (read.hasNextLine()) {
            //Initialized the variabe lineTotal which will increase by one 
            //each time the loop runs
            //Assigns the string "line" to a line then the string is attatched 
            //to a scanner so the line can be read token by token
            int lineTotal = 0;
            lineNumber++;
            line = read.nextLine();
            Scanner lineRead = new Scanner(line);
            operator = lineRead.next();
            boolean isFirstValue = true;
            //The following code is a while loop that will read while the 
            //scannerhas a next value. Inside this while loop is if statements 
            //the if statements check the operator and then
            //execute the appropriate operation.
            //After the while statement the if statement 
            //will run if the next value is a number if the
            //next value is not a number than it will cause the program to 
            //inside some of the if statementsa are more conditions that 
            //will check the validity of the next inputs 
            while (lineRead.hasNext()) {
                if (lineRead.hasNext()) {
                    if (operator.equals("+")) {
                        if (lineRead.hasNextInt()) {
                            lineTotal += lineRead.nextInt();
                        } else {
                            alert = 1;
                            lineRead.nextLine();
                        }   
                    } else if (operator.equals("-")) {
                        //this if statement uses a boolean value to
                        //get the next integer from the line then changes
                        //the value to false to avoid any subtraction errors 
                        if (lineRead.hasNextInt()) {
                            if (isFirstValue) {
                                lineTotal = lineRead.nextInt();
                                isFirstValue = false;
                            } else {
                                lineTotal -= lineRead.nextInt();
                            }
                        } else {
                            alert = 1;
                            lineRead.nextLine();
                        }
                    } else if (operator.equals("*")) {
                        if (lineRead.hasNextInt()) {
                            lineTotal = lineRead.nextInt(); 
                            lineTotal *= lineRead.nextInt();
                        } else {
                            alert = 1;
                            lineRead.nextLine();    
                        }
                    } else if (operator.equals("/")) {
                        if (lineRead.hasNextInt()) {
                            lineTotal = lineRead.nextInt();
                        } else {
                            alert = 1;
                            lineRead.nextLine();    
                        }
                        //the first if statement checks if the scanner
                        //and int if not it ill change the value of 
                        //alert this will cause the error message 
                        //to be printed on the given output line
                        //the next input will check to make sure
                        //there is no divide by zero because divide
                        //by zero would break the program. 
                        //if there is a divide by zero an error
                        //divide by zero message would be shown
                        //if there is no divide by zero the program 
                        //will continue as normal
                        if (lineRead.hasNextInt()) {
                            int nextInt = lineRead.nextInt();
                            if (nextInt == 0) {
                                alert = 2;
                            } else {
                                lineTotal /= nextInt;
                            }  
                        } else {
                            alert = 1;
                            lineRead.nextLine();
                        }
                    } else if (operator.equals("<+")) {
                        if (lineRead.hasNextInt()) {
                            previousLine += lineRead.nextInt();
                            lineTotal = previousLine;
                        } else {
                            alert = 1;
                            lineRead.nextLine();
                        }
                    } else if (operator.equals("<-")) {
                        if (lineRead.hasNextInt()) {
                            previousLine -= lineRead.nextInt();
                            lineTotal = previousLine;
                        } else {
                            alert = 1;
                            lineRead.nextLine();
                        }
                    } else if (operator.equals("<*")) {
                        if (lineRead.hasNextInt()) {
                            previousLine *= lineRead.nextInt();
                            lineTotal = previousLine;
                        } else {alert = 1;
                            lineRead.nextLine();
                        }
                    } else if (operator.equals("</")) {
                        //the same thing will be done for this 
                        //operator as the previous division
                        //operator. It will also check for
                        //int next. 
                        if (lineRead.hasNextInt()) {
                            int nextInt2 = lineRead.nextInt();
                            if (nextInt2 == 0) {
                                alert = 2;
                            } else {
                                previousLine /= nextInt2;
                                lineTotal = previousLine;    
                            }
                        }
                    } else {
                        alert = 3;
                        lineRead.nextLine();
                    }

                } else {
                    String character = lineRead.next();
                    alert = 1;
                }
            }
            //the follow if statements chose what message
            //should be displayed in the console
            //alert will be changed based on if 
            //the lines in the files contain errors    
            if (alert == 0) {
                System.out.printf("Result of Line %d:"
                    + " %d\n", lineNumber, lineTotal);
            } else if (alert == 1) {
                System.out.printf("Result of Line %d:"
                    + " Non-integer input on this Line\n", lineNumber);
            } else if (alert == 2) {
                System.out.printf("Result of Line %d:"
                    + " Error: / by zero\n", lineNumber);   
            } else if (alert == 3) {
                System.out.printf("Result of Line %d:"
                    + " No operator %s\n", lineNumber, line);        
            }
            //the following code deals with the fact that
            //previous line related operators could be 
            //effected if the previous line has an error
            //this will take care of this issue by checking
            //if the loop noticed an error
            //if it did the previousLine will be changed
            //to zero 
            if (alert != 0) {
                previousLine = 0;
            } else {
                previousLine = lineTotal;
            }
            lineTotal = 0;
            alert = 0;
            lineRead.close();
        }
        //always close scanner
        read.close();
    }
   
}
     