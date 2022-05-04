import * as React from 'react';
import { Button, View, Text, TextInput, StyleSheet  } from 'react-native';
import AsyncStorage from '@react-native-async-storage/async-storage';
import CryptoES from "crypto-es";
function LoginScreen({ navigation }) {
    const[username, onChangeUsername] = React.useState(null);
    const[password, onChangePassword] = React.useState(null);
    
    
    
    onSubmit = async () => {
      try {
        let user = await AsyncStorage.getItem('user');
        let parsed = JSON.parse(user);   
        if (parsed.uname == username && parsed.pword == String(CryptoES.SHA1(password + parsed.salt))){
          navigation.push('Home')
        }
        else{
          alert("Invalid Credentials")
        }
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
        <Text>Welcome to Secure Sports Bets</Text>
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
        <Button
          title="LOG IN"
          onPress={() => onSubmit()}
        />
        <Button
          title="REGISTER"
          onPress={() => navigation.navigate('Register')}
        />
      </View>
    );
  }

  export{LoginScreen}