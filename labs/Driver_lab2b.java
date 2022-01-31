/**
 * file: Driver_lab2.java
 * author: Anthony Connolly
 * course: CMPT 435
 * assignment: lab 2b
 * due date: January 30, 2022
 * version: 1.0
 *
 * This file contains the class Driver_lab2a.
 */


/**
 * Driver_lab2b
 *
 * This class finds the gcd of two numbers using the euclidian algorithm
 * extension, and also finds x and y such that d = ax + by is true where d is
 * the gcd. The main class uses the euclidAlgExt method to find d, x, and y.
 * These are stored in an array where the values are printed the terminal for
 * testing.
 */
public class Driver_lab2b{

  public static void main(String[] args) {
    long[] num_array = euclidAlgExt (240, 46);
    System.out.println(num_array[0] + " " + num_array[1] + " " + num_array[2]);
  }

/**
 * euclidAlgExt
 *
 * This function finds the gcd of two numbers using the euclidian algorithm
 * extension, and also finds x and y such that d = ax + by is true where d is
 * the gcd. The function ensures that a >= b before returning the result
 * from the overriden function.
 *
 * Parameters:
 *   a: an integer stored as a long > 0 and > b;
 *   b: an integer stored as a long > 0 and < b;

 * Return res: long array where res[0] is d, res[1] is x, res[2] = y
 */

 static long[] euclidAlgExt(long a, long b){
    long temp;
    if (a < b){ // swap values of a and b if a is not >= b
      temp = a;
      a = b;
      b = temp;
    }
    return euclidAlgExt(a, b, 1, 0, 0, 1);
  }
  /**
   * euclidAlgExt
   *
   * This function is an overriden function for euclidAlgExt which recursively
   * calls itself until b = 0, signaling the gcd of a and b is the value
   * currently held as a. X1, Y1, x, and y values are updated at each iteration
   * such that x and y when b = 0 satisfy, d = ax + by.
   *
   * Parameters:
   *   a: an integer stored as a long > 0 and > b;
   *   b: an integer stored as a long > 0 and < b;
   *   x: an integer stored as a long
   *   y: an integer stored as a long
   *   x1: an integer stored as a long
   *   y1: an integer stored as a long

   * Return res: long array where res[0] is d, res[1] is x, res[2] = y
   */

  static long[] euclidAlgExt(long a, long b, long x, long y, long x1, long y1){
    if (b == 0){
      long res[] = {a, x, y};
      return res;
    }
    long q = a / b; //Find floor quotient of a/b;
    long r1 = b;
    long r2 = a % b;

    //Declare temporary variables so future computations are accurate / atomic.
    long xtemp = x;
    long ytemp = y;
    long x1Temp = x1;
    //System.out.println(x1);
    long y1Temp = y1;
    //System.out.println(x2);


    x1 = xtemp - (q * x1Temp);
    y1 = ytemp - (q * y1Temp);

    x = x1Temp;
    y = y1Temp;

    return euclidAlgExt(r1, r2, x, y, x1, y1);
  }
}
