import * as React from 'react';
import { Button, View, Text, TextInput, StyleSheet  } from 'react-native';
import AsyncStorage from '@react-native-async-storage/async-storage';
function HomeScreen({ navigation }) {
    const[amt, onChangeBet] = React.useState();
  
    
    const key = [0x4B,0x62,0x50,0x65,0x53,0x68,0x56,0x6D,0x59,0x71,0x33,0x74,0x36,0x77,0x39,0x7A]
  
    const sbox = [0x63, 0x7c, 0x77, 0x7b, 0xf2, 0x6b, 0x6f, 0xc5, 0x30, 0x01, 0x67, 0x2b, 0xfe, 0xd7, 0xab, 0x76, 0xca, 0x82, 0xc9, 0x7d, 0xfa, 0x59, 0x47, 0xf0, 0xad, 0xd4, 0xa2, 0xaf, 0x9c, 0xa4, 0x72, 0xc0, 0xb7, 0xfd, 0x93, 0x26, 0x36, 0x3f, 0xf7, 0xcc, 0x34, 0xa5, 0xe5, 0xf1, 0x71, 0xd8, 0x31, 0x15, 0x04, 0xc7, 0x23, 0xc3, 0x18, 0x96, 0x05, 0x9a, 0x07, 0x12, 0x80, 0xe2, 0xeb, 0x27, 0xb2, 0x75, 0x09, 0x83, 0x2c, 0x1a, 0x1b, 0x6e, 0x5a, 0xa0, 0x52, 0x3b, 0xd6, 0xb3, 0x29, 0xe3, 0x2f, 0x84, 0x53, 0xd1, 0x00, 0xed, 0x20, 0xfc, 0xb1, 0x5b, 0x6a, 0xcb, 0xbe, 0x39, 0x4a, 0x4c, 0x58, 0xcf, 0xd0, 0xef, 0xaa, 0xfb, 0x43, 0x4d, 0x33, 0x85, 0x45, 0xf9, 0x02, 0x7f, 0x50, 0x3c, 0x9f, 0xa8, 0x51, 0xa3, 0x40, 0x8f, 0x92, 0x9d, 0x38, 0xf5, 0xbc, 0xb6, 0xda, 0x21, 0x10, 0xff, 0xf3, 0xd2, 0xcd, 0x0c, 0x13, 0xec, 0x5f, 0x97, 0x44, 0x17, 0xc4, 0xa7, 0x7e, 0x3d, 0x64, 0x5d, 0x19, 0x73, 0x60, 0x81, 0x4f, 0xdc, 0x22, 0x2a, 0x90, 0x88, 0x46, 0xee, 0xb8, 0x14, 0xde, 0x5e, 0x0b, 0xdb, 0xe0, 0x32, 0x3a, 0x0a, 0x49, 0x06, 0x24, 0x5c, 0xc2, 0xd3, 0xac, 0x62, 0x91, 0x95, 0xe4, 0x79, 0xe7, 0xc8, 0x37, 0x6d, 0x8d, 0xd5, 0x4e, 0xa9, 0x6c, 0x56, 0xf4, 0xea, 0x65, 0x7a, 0xae, 0x08, 0xba, 0x78, 0x25, 0x2e, 0x1c, 0xa6, 0xb4, 0xc6, 0xe8, 0xdd, 0x74, 0x1f, 0x4b, 0xbd, 0x8b, 0x8a, 0x70, 0x3e, 0xb5, 0x66, 0x48, 0x03, 0xf6, 0x0e, 0x61, 0x35, 0x57, 0xb9, 0x86, 0xc1, 0x1d, 0x9e, 0xe1, 0xf8, 0x98, 0x11, 0x69, 0xd9, 0x8e, 0x94, 0x9b, 0x1e, 0x87, 0xe9, 0xce, 0x55, 0x28, 0xdf, 0x8c, 0xa1, 0x89, 0x0d, 0xbf, 0xe6, 0x42, 0x68, 0x41, 0x99, 0x2d, 0x0f, 0xb0, 0x54, 0xbb, 0x16];
    
    const rcon = [0x8d, 0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, 0x80, 0x1b, 0x36, 0x6c, 0xd8, 0xab, 0x4d, 0x9a,
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
    ];
  
    const multMat = [
    [2, 3, 1, 1],
    [1, 2, 3, 1],
    [1, 1, 2, 3],
    [3, 1, 1, 2],
    ];
  
  
      function aesSbox(w) { //
        for (var i = 0; i < 4; i++) 
          w[i] = sbox[w[i]];
        return w;
      };
    
    function aesRoundKeys(key){
      var w = new Array(44);
      var temp = new Array(4);
     
      for (var i = 0; i < 4; i++) {
        var keycol = [key[4 * i], key[4 * i + 1], key[4 * i + 2], key[4 * i + 3]];
        w[i] = keycol;
      }
      
      for (var i = 4; i < 44; i++) {
        w[i] = new Array(4);
        for (var t = 0; t < 4; t++) 
          temp[t] = w[i - 1][t];
        if (i % 4 == 0) {
          temp = [w[i - 1][1], w[i - 1][2], w[i - 1][3], w[i - 1][0]]
          temp = aesSbox(temp);
          for (var t = 0; t < 4; t++)
            if(t == 0)
              temp[t] ^= rcon[i / 4];
        } 
        for (var t = 0; t < 4; t++) 
          w[i][t] = w[i - 4][t] ^ temp[t];
      }  
      return w;    
    }
    
  
  
  
    //XOR
    function aesStateXOR(shex, keyhex){
      var res = new Array(4)
      for (var i = 0; i < 4; i++) {
        res[i] = new Array(4)
      }
      for (var i = 0; i < 4; i++) {
        for (var j = 0; j < 4; j++){
          res[i][j] = shex[i][j] ^ keyhex[i][j];  
      }
    }
    return res;
  }
    //Nibble Sub 
    function aesNibbleSub(hex){
      var res = new Array(4)
      for (var i = 0; i < 4; i++) {
        res[i] = new Array(4)
      }
      for (var i = 0; i < 4; i++) {
        for (var j = 0; j < 4; j++) {
          res[i][j] = sbox[hex[i][j]]
      }
    }
    return res
  }
  
  
  
    //Shift Rows 
    function aesShiftRows(hex)
    {
      var res = new Array(4)
      for (var i = 0; i < 4; i++) {
        res[i] = new Array(4)
      }
      //row 1
      res[0] = hex[0]
      //row 2
      for (var i = 0; i < 3; i++)
        res[1][i] = hex[1][i+1];
      res[1][3] = hex[1][0];
      // Shift row 3.
      for (var i = 0; i < 2; i++)
        res[2][i] = hex[2][i + 2];
      for (var i = 2; i < 4; i++)
        res[2][i] = hex[2][i - 2];
      //Shift row 4.
      res[3][0] = hex[3][3];
      for(var i = 1; i < 4; i++)
        res[3][i] = hex[3][i - 1];
  
      return res;
    }
  
    
  
    function aesMixColumn(inStateHex){
      var result = new Array(4)
      for (var i = 0; i < 4; i++) {
        result[i] = new Array(4)
      }
      var temp = 0;
      for(var col = 0; col < 4; col++){
        for(var row = 0; row < 4; row++){
          result[row][col] = 0;
          for(var it = 0; it < 4; it++){
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
  
    function toHexString(byteArray) {
      return Array.from(byteArray, function(byte) {
        return ('0' + (byte & 0xFF).toString(16)).slice(-2);
      }).join('')
    }
  
  
    function AES(pTextHex, key){
      //key expansion
      const keys = aesRoundKeys(key)
  
  
      //ptext matrix 
      var pTextMat = new Array(4)
      var index = 0
      for (var i = 0; i < 4; i++) {
        pTextMat[i] = new Array(4)
      }
  
      for(var j = 0; j < 4; j++){
        for(var i = 0; i < 4; i++){
          var pTextTemp = pTextHex.substring(index, index + 2);
          pTextMat[i][j] = parseInt(pTextTemp, 16);
          index +=2;
        }
      }
    
  
      //load base key
      var keyHexMat = new Array(4);
      for (var i = 0; i < 4; i++) {
        keyHexMat[i] = new Array(4)
      }
      for (var i = 0; i < 4; i++){
        for(var j = 0; j < 4; j++){
          keyHexMat[i][j] = keys[j][i]
        }
      }
  
          var res = aesStateXOR(pTextMat, keyHexMat)
  
          //Perform rounds 1 - 9.
          for (var round = 1; round < 10; round++){
            //Load round key.
            for(var i = 0; i < 4; i++){
              for(var j = 0; j < 4; j++){
                keyHexMat[j][i] = keys[(4*round) + i][j]
              }
            }
            res = aesNibbleSub(res)
            res = aesShiftRows(res)
            //bug down
            res = aesMixColumn(res)
            res = aesStateXOR(res, keyHexMat)
          }
  
          //Do round 10
          res = aesNibbleSub(res)
          res = aesShiftRows(res)
  
          for(var i = 0; i < 4; i++){
            for(var j = 0; j < 4; j++){
              keyHexMat[j][i] = keys[40 + i][j]
            }
          }
  
          res = aesStateXOR(res, keyHexMat)
          //re rotate Don't ask me why its sideways or how it works at all, i dont know
          res = [res[0][0], res[1][0],res[2][0],res[3][0],res[1][0],res[1][1],res[1][2],res[1][3],res[2][0],
              res[2][1],res[2][2],res[2][3],res[3][0],res[3][1],res[3][2],res[3][3]]
          return toHexString(res)
        }
      
  
  
  
    //var ptext = "54776F204F6E65204E696E652054776F"
    //let x = AES(ptext, key)
    //alert(x)
  
    
  
  
  
   
    
    
    let team = "";
    onSubmit = async () => {
      try {
        if(amt > 0 && team != "")
        {  
          let store = amt + team
          store = store.padEnd(32,'0')
          let storeenc = AES(store, key)
          alert(storeenc)
          await AsyncStorage.setItem('bet', JSON.stringify(storeenc))
          alert("Bet Placed")
        }
        else{
          alert("Invalid Bet")
        }
      } catch (error) {
        console.log(error)
      }
    }
  
    return (
      <View style={{ flex: 1, alignItems: 'center', justifyContent: 'center' }}>
        <Text>Games</Text>
        <Text>Buffalo vs New England </Text>
        <Text> Money Line </Text>
        <View style={styles.container}>
        <Button
          title="Buffalo -245"
          onPress={() => team = "Buffalo"}
        />
        <Button
          title="Miami +185"
          onPress={() => team = "Miami"}
        />
        </View>
        <TextInput 
          style={styles.input}
          onChangeText={onChangeBet}
          placeholder="Wager"
          value = {amt}
          />
        <Button
          title="Place Bet"
          onPress={() => onSubmit()}
        />
        <Button
          title="View Active Bets"
          onPress={() => navigation.push('Bets')}
        />
      </View>
    );
  }

  const styles = StyleSheet.create({
    input: {
      height: 40,
      margin: 12,
      borderWidth: 1,
      padding: 10,
    },
    container: {
      flexDirection: 'row',
    },
  });

  export {HomeScreen}