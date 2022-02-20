/**
 * file: Driver_lab4.java
 * author: Anthony Connolly
 * course: CMPT 435
 * assignment: lab 4
 * due date: February 20, 2022
 * version: 1.0
 *
 * This file contains the class Driver_lab4.
 */

import java.util.Scanner;
/**
 * Driver_lab4
 *
 * This class contains a main method takes an input string as a key, and uses
 * the AESCipher class function aesRoundKeys to generated 11 round keys, and
 * prints them
 */
public class Driver_lab4{

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String KeyHex = sc.nextLine();
    String[] roundKeysHex = AESCipher.aesRoundKeys(KeyHex);
    for (int i = 0; i < roundKeysHex.length; i++){
        String key = roundKeysHex[i];
        System.out.println(key);
    }
  }
}
