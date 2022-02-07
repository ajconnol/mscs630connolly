/**
 * file: Driver_lab3b.java
 * author: Anthony Connolly
 * course: CMPT 435
 * assignment: lab 3a
 * due date: February 6, 2022
 * version: 1.0
 *
 * This file contains the class Driver_lab3b.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Driver_lab3b
 *
 * This class finds the converts a phrase or string into an matrix where the
 * input chars are represented as hex ascii values. The array has 4 rows and
 * coloumns are multiples of 4. If the input doesn't fill the matrix,
 * a designatated substitution character fills the matrix to the requirement.
 * The main method represents this array by creating as many 4x4 matricies
 * as are needed for the input, and filling them with corresponding substrings
 * of the given input. These substrings are converted to ascii using the
 * getHexMatP function. The main method prints these matricies in hex.  
 */
public class Driver_lab3b{

  public static void main(String[] args) {
    File input1 = new File("D:/input.10.in");
    try {
      Scanner sc = new Scanner(input1);
      Scanner inputReader1 = new Scanner(input1);
      char s = inputReader1.next().charAt(0);
      inputReader1.nextLine();
      String p = inputReader1.nextLine();
      double num = (double)p.length() / (double)16;
      num = (int) Math.ceil(num);
      for (int i = 0; i < num; i++){
        String subS = p.substring(i * 16, p.length());
        subS = String.format("%-16s", subS);
        System.out.println(subS);
        int[][] res = getHexMatP(s, subS);
        for (int j = 0; j < 4; j++){
          for (int k = 0; k <4; k++){
            System.out.print(Integer.toHexString(res[k][j]).toUpperCase() + " ");
          }
          System.out.println();
        }
        System.out.println();
      }
    }catch(FileNotFoundException ex) {
    Logger.getLogger(Driver_lab3a.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
/**
 * getHexMatP
 *
 * This function converts a substring portion of a string into a 4x4 matrix.
 * Chars are converted into to ascii values, the matrix is filled by the sub
 * character.
 * Parameters:
 *   s: char, substitution character
 *   p: matrix to be converted into hex matrix
 *
 * Return res: 4x4 int matrix
 */

  static int[][] getHexMatP(char s, String p){
    int[][] res = new int[4][4];
    int ascii = 0;
      for (int i = 0; i < 4; i++){
        for (int j = 0; j < 4; j++){
          int index = (i * 4) + j;
          if (p.charAt(index) == ' '){
              if(index == 15)
                ascii = (int) s;
              else if(p.charAt(index + 1) == ' ')
                ascii = (int) s;
              else
                ascii = (int)p.charAt((i * 4) + j) ;
          }
          else
            ascii = (int)p.charAt((i * 4) + j) ;
          res[i][j] = ascii;
        }
    }
      return res;
  }
}
