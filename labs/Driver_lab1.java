/**
 * file: Driver_lab1.java
 * author: Anthony Connolly
 * course: CMPT 435
 * assignment: lab 1
 * due date: January 23, 2022
 * version: 1.0
 * 
 * This file contains the class Driver_lab1.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Driver_lab1
 *
 * This class converts text to corresponding numbers. 
 * It takes in a given set of lines of words and outputs corresponding
 * lines of integers. The main method reads a test file and prints the 
 * resulting numbers
 * in the console.
 */
public class Driver_lab1{
  
  public static void main(String[] args) {
    try {
          File input = new File("D:/input.10.in");
          Scanner sc = new Scanner(input);
          while (sc.hasNextLine()){
              String line_string = sc.nextLine();
              int[] num_array = str2int(line_string);
              for (int i = 0; i < num_array.length; i++){ //Print all converted characters for the line.
                  System.out.print(num_array[i]+ " "); 
              }
              System.out.println(); // Print a line break between input lines.
          } 
        } 
        catch (FileNotFoundException ex) {
          System.out.println("File not found");
      }
  }
    
/**
 * str2int
 *
 * This function converts a string to a corresponding int list where each 
 * letter is mapped to a corresponding int as an array element,
 * such that a = 0, A = 0, and z = 25, Z = 25. Blank spaces are converted as 26.
 * The function subtracts the ascii value of a from lowercase letters and 
 * the ascii value of A from uppercase letters to complete this conversion.
 * 
 * Parameters:
 *   plainText: the line of words separated by spaces that is to be converted to 
 *   numbers
 * 
 * Return number_array: plainText converted into numbers 0-26 as an int array
 */
  
  static int[] str2int(String plainText){
    int[] number_array = new int[plainText.length()];
    for(int i = 0; i < plainText.length(); i++){
      int char_value = plainText.charAt(i); // Convert letter to it's ascii value.
      if(char_value > 90) // Subtract 97 from ascii value of all lowercase characters.
        number_array[i] = (char_value - 97);
      else // Subtract 65 from ascii value of all uppercase characters.
        number_array[i] = (char_value - 65);
      if(plainText.charAt(i) == ' ') 
        number_array[i] = 26; //Override blank space to 26.
    }
    return number_array;
  }
}