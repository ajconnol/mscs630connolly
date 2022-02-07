/**
 * file: Driver_lab3a.java
 * author: Anthony Connolly
 * course: CMPT 435
 * assignment: lab 3a
 * due date: February 6, 2022
 * version: 1.0
 *
 * This file contains the class Driver_lab3a.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Driver_lab3a
 *
 * This class finds the smallest integer corresponding to det(A) in modulo m.
 * an input file an applies the coefModDet method to the matrix from File
 * and print the solution.
 */
public class Driver_lab3a{

  public static void main(String[] args) {
    File input1 = new File("D:/input.10.in");
    try {
      Scanner sc = new Scanner(input1);
      Scanner inputReader1 = new Scanner(input1);
      String strIn = inputReader1.nextLine();
      int[] input = Arrays.stream(strIn.split(" ")).mapToInt(Integer::parseInt).toArray();
      int matrixSize = input[1];
      int[][] inputMatrix = new int[matrixSize][matrixSize];
      while (inputReader1.hasNextLine()) {
        int[] currentInputs;
          for (int row = 0; row < matrixSize; row++){
            String nextLine = inputReader1.nextLine();
            currentInputs = Arrays.stream(nextLine.split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int col = 0; col < matrixSize; col++) {
              inputMatrix[row][col] = currentInputs[col];
            }
          }
      System.out.println(coefModDet(input[0],inputMatrix));
    }
  }catch (FileNotFoundException ex) {
    Logger.getLogger(Driver_lab3a.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

/**
 * coefModDet
 *
 * This function finds the smallest integer corresponding to det(A) in modulo m.
 * The function computes modulo at all of the integer operations and to the
 * final result
 *
 * Parameters:
 *   m: integer, modulo to perform calculations using ;
 *   A: integer array storing the matrix
 *
 * Return determinant: smallest det(A) mod m
 */

  static int coefModDet(int m, int[][] A){
    if (A.length == 1){
      return mod(A[0][0], m);
    }
    else if (A.length == 2){
      int temp = mod(mod(A[0][0],m) * mod(A[1][1],m), m) - mod(mod(A[0][1],m) * mod(A[1][0],m),m);
      return mod(temp, m);
    }
    else{
      int determinant = 0;
      //
      int[][] temp = new int[A.length - 1][A.length - 1];
      for (int i = 0; i < A.length; i++){
        for (int j = 0; j < A.length; j++){
          for (int k = 1; k < A.length; k++){
            if (j > i){
              temp[k - 1][j - 1] = A[k][j];
            }
            else if (j < i){
              temp[k - 1][j] = A[k][j];
            }
          }
        }
        System.out.println(Arrays.deepToString(temp));
        int factor = mod(A[0][i] * coefModDet(m, temp),m);
        if (i%2 == 1)
          factor = factor * -1;
        determinant = determinant += factor;
      }
      return mod(determinant,m);
    }
  }

  /**
   * mod
   *
   * This function finds a mod b, but adjusts for negative solutions, unlike
   * the java operation.

   * Parameters:
   *   a: integer, dividend
   *   b: integer, divisor
   *
   * Return c: a mod b
   */
  static int mod(int a, int b){
    int c = a % b;
    if (c < 0){
      c += b;
    }
    return c;
  }
}
