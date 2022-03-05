/**
 * file: Driver_lab5.java
 * author: Anthony Connolly
 * assignment: lab 5
 * due date: March 6, 2022
 * version: 1.0
 *
 * This file contains the class Driver_lab5.
 */

import java.util.Scanner;
/**
 * Driver_lab4
 *
 * This class contains a main method takes input strings a key and
 * a plaintext string , uses AESCipher class function AES to create cypherText.
 */
public class Driver_lab5{

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String keyHex = sc.nextLine();
    String plaintext = sc.nextLine();
    System.out.print(AESCipher.AES(keyHex, plaintext));
  }
}
