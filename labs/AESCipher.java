/**
 * file: AESCipher.java
 * author: Anthony Connolly
 * assignment: lab 5
 * due date: February 20, 2022
 * version: 2.0
 *
 * This file contains the class AESCipher.
 */

/**
 * AESCipher
 * This class contains the constants and functions to create a list of 10 other
 * keys generated from a given key using the AES Cipher.
 *
 * This class also contains the functions nescessary to complete the AES cypher.
 */

public class AESCipher {

  //Table declarations exceed char limit for visual clarity.
  // 0     1     2     3     4     5     6     7     8     9     A     B     C     D     E     F
  static int[][] sbox = {
    {0x63, 0x7c, 0x77, 0x7b, 0xf2, 0x6b, 0x6f, 0xc5, 0x30, 0x01, 0x67, 0x2b, 0xfe, 0xd7, 0xab, 0x76},//0
    {0xca, 0x82, 0xc9, 0x7d, 0xfa, 0x59, 0x47, 0xf0, 0xad, 0xd4, 0xa2, 0xaf, 0x9c, 0xa4, 0x72, 0xc0},//1
    {0xb7, 0xfd, 0x93, 0x26, 0x36, 0x3f, 0xf7, 0xcc, 0x34, 0xa5, 0xe5, 0xf1, 0x71, 0xd8, 0x31, 0x15},//2
    {0x04, 0xc7, 0x23, 0xc3, 0x18, 0x96, 0x05, 0x9a, 0x07, 0x12, 0x80, 0xe2, 0xeb, 0x27, 0xb2, 0x75},//3
    {0x09, 0x83, 0x2c, 0x1a, 0x1b, 0x6e, 0x5a, 0xa0, 0x52, 0x3b, 0xd6, 0xb3, 0x29, 0xe3, 0x2f, 0x84},//4
    {0x53, 0xd1, 0x00, 0xed, 0x20, 0xfc, 0xb1, 0x5b, 0x6a, 0xcb, 0xbe, 0x39, 0x4a, 0x4c, 0x58, 0xcf},//5
    {0xd0, 0xef, 0xaa, 0xfb, 0x43, 0x4d, 0x33, 0x85, 0x45, 0xf9, 0x02, 0x7f, 0x50, 0x3c, 0x9f, 0xa8},//6
    {0x51, 0xa3, 0x40, 0x8f, 0x92, 0x9d, 0x38, 0xf5, 0xbc, 0xb6, 0xda, 0x21, 0x10, 0xff, 0xf3, 0xd2},//7
    {0xcd, 0x0c, 0x13, 0xec, 0x5f, 0x97, 0x44, 0x17, 0xc4, 0xa7, 0x7e, 0x3d, 0x64, 0x5d, 0x19, 0x73},//8
    {0x60, 0x81, 0x4f, 0xdc, 0x22, 0x2a, 0x90, 0x88, 0x46, 0xee, 0xb8, 0x14, 0xde, 0x5e, 0x0b, 0xdb},//9
    {0xe0, 0x32, 0x3a, 0x0a, 0x49, 0x06, 0x24, 0x5c, 0xc2, 0xd3, 0xac, 0x62, 0x91, 0x95, 0xe4, 0x79},//A
    {0xe7, 0xc8, 0x37, 0x6d, 0x8d, 0xd5, 0x4e, 0xa9, 0x6c, 0x56, 0xf4, 0xea, 0x65, 0x7a, 0xae, 0x08},//B
    {0xba, 0x78, 0x25, 0x2e, 0x1c, 0xa6, 0xb4, 0xc6, 0xe8, 0xdd, 0x74, 0x1f, 0x4b, 0xbd, 0x8b, 0x8a},//C
    {0x70, 0x3e, 0xb5, 0x66, 0x48, 0x03, 0xf6, 0x0e, 0x61, 0x35, 0x57, 0xb9, 0x86, 0xc1, 0x1d, 0x9e},//D
    {0xe1, 0xf8, 0x98, 0x11, 0x69, 0xd9, 0x8e, 0x94, 0x9b, 0x1e, 0x87, 0xe9, 0xce, 0x55, 0x28, 0xdf},//E
    {0x8c, 0xa1, 0x89, 0x0d, 0xbf, 0xe6, 0x42, 0x68, 0x41, 0x99, 0x2d, 0x0f, 0xb0, 0x54, 0xbb, 0x16}//F
  };

  static int[] rcon = {
    0x8d, 0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, 0x80, 0x1b, 0x36, 0x6c, 0xd8, 0xab, 0x4d, 0x9a,
    0x2f, 0x5e, 0xbc, 0x63, 0xc6, 0x97, 0x35, 0x6a, 0xd4, 0xb3, 0x7d, 0xfa, 0xef, 0xc5, 0x91, 0x39,
    0x72, 0xe4, 0xd3, 0xbd, 0x61, 0xc2, 0x9f, 0x25, 0x4a, 0x94, 0x33, 0x66, 0xcc, 0x83, 0x1d, 0x3a,
    0x74, 0xe8, 0xcb, 0x8d, 0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, 0x80, 0x1b, 0x36, 0x6c, 0xd8,
    0xab, 0x4d, 0x9a, 0x2f, 0x5e, 0xbc, 0x63, 0xc6, 0x97, 0x35, 0x6a, 0xd4, 0xb3, 0x7d, 0xfa, 0xef,
    0xc5, 0x91, 0x39, 0x72, 0xe4, 0xd3, 0xbd, 0x61, 0xc2, 0x9f, 0x25, 0x4a, 0x94, 0x33, 0x66, 0xcc,
    0x83, 0x1d, 0x3a, 0x74, 0xe8, 0xcb, 0x8d, 0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, 0x80, 0x1b,
    0x36, 0x6c, 0xd8, 0xab, 0x4d, 0x9a, 0x2f, 0x5e, 0xbc, 0x63, 0xc6, 0x97, 0x35, 0x6a, 0xd4, 0xb3,
    0x7d, 0xfa, 0xef, 0xc5, 0x91, 0x39, 0x72, 0xe4, 0xd3, 0xbd, 0x61, 0xc2, 0x9f, 0x25, 0x4a, 0x94,
    0x33, 0x66, 0xcc, 0x83, 0x1d, 0x3a, 0x74, 0xe8, 0xcb, 0x8d, 0x01, 0x02, 0x04, 0x08, 0x10, 0x20,
    0x40, 0x80, 0x1b, 0x36, 0x6c, 0xd8, 0xab, 0x4d, 0x9a, 0x2f, 0x5e, 0xbc, 0x63, 0xc6, 0x97, 0x35,
    0x6a, 0xd4, 0xb3, 0x7d, 0xfa, 0xef, 0xc5, 0x91, 0x39, 0x72, 0xe4, 0xd3, 0xbd, 0x61, 0xc2, 0x9f,
    0x25, 0x4a, 0x94, 0x33, 0x66, 0xcc, 0x83, 0x1d, 0x3a, 0x74, 0xe8, 0xcb, 0x8d, 0x01, 0x02, 0x04,
    0x08, 0x10, 0x20, 0x40, 0x80, 0x1b, 0x36, 0x6c, 0xd8, 0xab, 0x4d, 0x9a, 0x2f, 0x5e, 0xbc, 0x63,
    0xc6, 0x97, 0x35, 0x6a, 0xd4, 0xb3, 0x7d, 0xfa, 0xef, 0xc5, 0x91, 0x39, 0x72, 0xe4, 0xd3, 0xbd,
    0x61, 0xc2, 0x9f, 0x25, 0x4a, 0x94, 0x33, 0x66, 0xcc, 0x83, 0x1d, 0x3a, 0x74, 0xe8, 0xcb, 0x8d
  };

  static int[][] multMat = {
    {2, 3, 1, 1},
    {1, 2, 3, 1},
    {1, 1, 2, 3},
    {3, 1, 1, 2}
  };



/**
 * aesRoundKeys
 *
 * This function takes a given key and generates 10 keys using the AES key
 * schedule.
 * This function leverages helper functions to make Sbox substitutions and pull
 * round constants.
 *
 * Parameters:
 *   KeyHex: a string for the 32 bit starting key
 *
 * Return keys: a list of 11 32 bit round keys
 */


  static String[] aesRoundKeys(String KeyHex){
    //Declare w.
    int[][] w = new int[4][44];
    //Fill first 4 columns of w with keyhex.
    int keyhexIndex = 0;
    for(int j = 0; j < 4; j++){
      for(int i = 0; i < 4; i++){
        String hexTemp = KeyHex.substring(keyhexIndex, keyhexIndex + 2);
        w[i][j] = Integer.parseInt(hexTemp, 16);
        keyhexIndex+=2;
      }
    }

    //Create result list and key variable.
    String key = KeyHex;
    String[] keys = new String[11];
    String segment = "";
    // Handle other 40 columns.
    for (int j = 4; j < 44; j++){
      //Handle if column number is not a multiple of 4.
      if(j % 4 != 0){
        for(int i = 0; i < 4; i++){
          w[i][j] = w[i][j - 4] ^ w[i][j-1];
          segment = Integer.toHexString(w[i][j]);
          if (segment.length() < 2)
            segment = "0" + segment;
          key = key + segment;
          segment = "";
          if (j == 43)
            keys[10] = key.toUpperCase();
        }
      }
      //Handle if column number starts new round.
      else {
        int round = j/4;
        key = key.toUpperCase();
        keys[round - 1] = key;
        key = "";
        int[] tempAr = {w[1][j-1], w[2][j-1], w[3][j-1], w[0][j-1]};
        for(int i = 0; i < tempAr.length; i++){
          tempAr[i] = aesSBOX(tempAr[i]);
          if(i == 0){
            tempAr[i] = tempAr[i] ^ aesRcon(round);
          }
          w[i][j] = tempAr[i] ^ w[i][j - 4];
          segment = Integer.toHexString(w[i][j]);
          if (segment.length() < 2)
            segment = "0" + segment;
          key = key + segment;
        }
       }
    }
    return keys;
  }
  /**
   * aesSBOX
   *
   * This function substitues a hex value for another corresponding hex value
   * in the sBox.
   *
   * Parameters:
   *   InHex: integer hexvalue to be transformed using sbox
   *
   * Return outHex: transformed hexvalue
   */

  static int aesSBOX(int inHex){
    String keys = Integer.toHexString(inHex);
    if (keys.length() < 2)
      keys = "0" + keys;
    String x = Character.toString(keys.charAt(0));
    String y = Character.toString(keys.charAt(1));
    int xIndex = Integer.parseInt(x, 16);
    int yIndex = Integer.parseInt(y, 16);

    int outHex = sbox[xIndex][yIndex];

    return outHex;
  }
  /**
   * aesRcon
   *
   * This function takes a round number and returns the corresponding round
   * constant.
   *
   * Parameters:
   *   round: integer round number
   *
   * Return rcon[round]: round constant
   */

  static int aesRcon(int round){

    return rcon[round];

  }

  /**
   * AESStateXOR
   *
   * This function takes two matricies and adds them by xoring them at each
   * index.
   *
   * Parameters:
   *   sHex: matrix in hex
   *   keyHex: key matrix in hex
   * Return result: resultant matrix of the xors
   */

  static int[][] AESStateXOR(int[][] sHex, int[][] keyHex){
    int[][] result = new int[4][4];
    for(int i = 0; i < 4; i++){
      for(int j = 0; j < 4; j++){
        result[i][j] = sHex[i][j] ^ keyHex[i][j];
      }
    }
    return result;
  }

  /**
   * AESNibbleSub
   *
   * This function takes a matrix and uses the SBOX to perform Nibble
   * substitions.
   *
   * Parameters:
   *   inStateHex: cypherText matrix
   *
   * Return result: converted matrix.
   */

  static int[][] AESNibbleSub(int[][] inStateHex){
    int[][] result = new int[4][4];
    for(int i = 0; i < 4; i++){
      for(int j = 0; j < 4; j++){
        result[j][i] = aesSBOX(inStateHex[j][i]);
      }
    }
    return result;
  }

  /**
   * AESShiftRow
   *
   * This function takes a matrix and shifts each row according to the AES
   * algorithm.
   *
   * Parameters:
   *   inStateHex: cypherText matrix
   *
   * Return result: shifted converted matrix.
   */

  static int[][] AESShiftRow(int[][] inStateHex){
    // Shift row 1.
    int[][] result = new int[4][4];
    result[0] = inStateHex[0];
    // Shift row 2.
    for (int i = 0; i < 3; i++)
      result[1][i] = inStateHex[1][i+1];
    result[1][3] = inStateHex[1][0];
    // Shift row 3.
    for (int i = 0; i < 2; i++)
      result[2][i] = inStateHex[2][i + 2];
    for (int i = 2; i < 4; i++)
      result[2][i] = inStateHex[2][i - 2];
    //Shift row 4.
    result[3][0] = inStateHex[3][3];
    for(int i = 1; i < 4; i++)
      result[3][i] = inStateHex[3][i - 1];

    return result;
  }

  /**
   * AESMixColumn
   *
   * This function takes a matrix and completes the AES Mix columns step using
   * multiplaction over Gallois fields.
   *
   * Parameters:
   *   inStateHex: cypherText matrix
   *
   * Return result: mixed matrix.
   */

  static int[][] AESMixColumn(int[][] inStateHex){
    int[][] result = new int[4][4];
    int temp = 0;
    for(int col = 0; col < 4; col++){
      for(int row = 0; row < 4; row++){
        result[row][col] = 0;
        for(int it = 0; it < 4; it++){
          temp = 0;
          //2
          if(multMat[row][it] == 2){
            temp = inStateHex[it][col] << 1;
            if(temp >= 256)
              temp -= 256;
            if ((inStateHex[it][col] & 0x80) != 0)
              temp ^= 0x1b;
          }
          //3
          else if(multMat[row][it] == 3){
            temp = inStateHex[it][col] << 1;
            if(temp >= 256)
              temp -= 256;
            if ((inStateHex[it][col] & 0x80) != 0)
              temp ^= 0x1b;
            temp ^= inStateHex[it][col];
          }
          //1
          else
            temp = inStateHex[it][col];
          result[row][col] ^= temp;
        }
      }
    }
    return result;
  }

  /**
   * AES
   *
   * This function uses helper functions to convert a plaintext string into
   * cyphertext using the AES algorithm.
   *
   * Parameters:
   *   pTextHex: plaintext String
   *   keyHex: round 0 key
   *
   * Return cypherText: AES encoded string
   */

  static String AES(String pTextHex, String keyHex){

    //Do Key Expansion
    String [] roundKeys = aesRoundKeys(keyHex);

    //Load base key into matrix
    int[][] keyHexMat = new int[4][4];
    int keyhexIndex = 0;
    for(int j = 0; j < 4; j++){
      for(int i = 0; i < 4; i++){
        String hexTemp = keyHex.substring(keyhexIndex, keyhexIndex + 2);
        keyHexMat[i][j] = Integer.parseInt(hexTemp, 16);
        keyhexIndex += 2;
      }
    }

    //Load plain text into matrix.
    int [][] pTextMat = new int[4][4];
    int index = 0;
    for(int j = 0; j < 4; j++){
      for(int i = 0; i < 4; i++){
        String pTextTemp = pTextHex.substring(index, index + 2);
        pTextMat[i][j] = Integer.parseInt(pTextTemp, 16);
        index +=2;
      }
    }

    //Add round 0 key.
    int[][] res = AESStateXOR(pTextMat, keyHexMat);

    //Perform rounds 1 - 9.
    for (int round = 1; round < 10; round++){
      //Load round key.
      keyhexIndex = 0;
      for(int j = 0; j < 4; j++){
        for(int i = 0; i < 4; i++){
          String hexTemp = roundKeys[round].substring(keyhexIndex, keyhexIndex + 2);
          keyHexMat[i][j] = Integer.parseInt(hexTemp, 16);
          keyhexIndex += 2;
        }
      }

      //Do nibble sub.
      res = AESNibbleSub(res);
      //Do shift rows.
      res = AESShiftRow(res);
      //Do mix columns.
      res = AESMixColumn(res);
      //Add key.
      res = AESStateXOR(res, keyHexMat);
    }
    //Do round 10.

    //Do nibble sub.
    res = AESNibbleSub(res);

    //Do shift rows.
    res = AESShiftRow(res);

    //Add key.
    keyhexIndex = 0;
    for(int j = 0; j < 4; j++){
      for(int i = 0; i < 4; i++){
        String hexTemp = roundKeys[10].substring(keyhexIndex, keyhexIndex + 2);
        keyHexMat[i][j] = Integer.parseInt(hexTemp, 16);
        keyhexIndex += 2;
      }
    }
    res = AESStateXOR(res, keyHexMat);

    //Convert matrix to String.
    String cypherText = "";
    String segment = "";
    for(int col = 0; col < 4; col++){
      for(int row = 0; row < 4; row++){
        segment = Integer.toHexString(res[row][col]);
        if (segment.length() < 2)
          segment = "0" + segment;
        cypherText = cypherText + segment;
      }
    }

    return cypherText.toUpperCase();
  }
}
