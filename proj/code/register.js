import * as React from 'react';
import { Button, View, Text, TextInput, StyleSheet  } from 'react-native';
import AsyncStorage from '@react-native-async-storage/async-storage';
import CryptoES from "crypto-es";

function RegisterScreen({ navigation }) {
    const[username, onChangeUsername] = React.useState(null);
    const[password, onChangePassword] = React.useState(null);
    const[passwordv, onChangePasswordv] = React.useState(null);
    const[name, onChangeName] = React.useState(null);
    const[age, onChangeAge] = React.useState(null);
  
  
    onSubmit = async () => {
      try {
        //SHA-1 Hash and salt
        const salt = String(CryptoES.lib.WordArray.random(128/8))
        const hash = String(CryptoES.SHA1(password + salt))
        let user = {
          uname: username,
          pword: hash,
          salt: salt
        }
        await AsyncStorage.setItem('user', JSON.stringify(user))
        navigation.push('Login')
      } catch (error) {
        console.log(error)
      }
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
  
    
    return (
      <View style={{ flex: 1, alignItems: 'center', justifyContent: 'center' }}>
        <Text>Please create an account</Text>
        <TextInput 
          style={styles.input}
          onChangeText={onChangeName}
          placeholder="Name"
          value = {name}
          />
        <TextInput 
          style={styles.input}
          onChangeText={onChangeAge}
          placeholder="Age"
          value = {age}
          />
        <TextInput 
          style={styles.input}
          onChangeText={onChangeUsername}
          placeholder="Username"
          value = {username}
          />
        <TextInput 
          style={styles.input}
          onChangeText={onChangePassword}
          placeholder="Password"
          value = {password}
          />
         <TextInput 
          style={styles.input}
          onChangeText={onChangePasswordv}
          placeholder="Verify Password"
          value = {passwordv}
          />
  
        <Button
          title="CREATE ACCOUNT"
          onPress={() => onSubmit()}
        />
      </View>
    );
  }

  export {RegisterScreen}