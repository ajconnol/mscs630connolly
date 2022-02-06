/**
 * file: Driver_lab2.java
 * author: Anthony Connolly
 * course: CMPT 435
 * assignment: lab 2a
 * due date: January 30, 2022
 * version: 1.1
 *
 * This file contains the class Driver_lab2a.
 */

 import java.io.File;
 import java.io.FileNotFoundException;
 import java.util.Scanner;

/**
 * Driver_lab2a
 *
 * This class finds the gcd of two numbers using the euclidean algorithm.
 *The main method takes
 an input file an applies the euclidAlg method to all of the lines, producing
 the gcd for testing. The final output is printed to the terminal.
 */
public class Driver_lab2a{

  public static void main(String[] args) {
    try {
      File input = new File("D:/input.10.in");
      Scanner sc = new Scanner(input);
      while (sc.hasNextLine()){
        long a = sc.nextLong();
        long b = sc.nextLong();
        long gcd = euclidAlg(a, b);
        System.out.println(gcd);
      }
    }
    catch (FileNotFoundException ex) {
      System.out.println("File not found");
    }
  }

/**
 * euclidAlg
 *
 * This function takes two numbers and recursively calls it's self until
 * a % b = 0, signaling that the gcd a and b is the value currently held as b.
 *
 *
 * Parameters:
 *   a: an integer stored as a long > 0 and > b;
 *   b: an integer stored as a long > 0 and < b
 *
 * Return b: an integer stored as a long, gcd of inputs a and b
 */

  static long euclidAlg(long a, long b){
    long temp;
    if (a < b){ // swap values of a and b if a is not >= b
      temp = a;
      a = b;
      b = temp;
    }
    long r1 = b;
    long r2 = a % b;
    if (r2 == 0) //recursively euclidAlg until remander is 0, so r1 is gcd
      return r1;
    else
      return euclidAlg(r1, r2);
  }
}
