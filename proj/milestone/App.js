import * as React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { LoginScreen } from './login';
import { BetScreen } from './bet';
import { HomeScreen} from './home';
import { RegisterScreen} from './register';

const Stack = createNativeStackNavigator();

function App() {
  return (
    <NavigationContainer> 
      <Stack.Navigator initialRouteName="Login" screenOptions={{headerShown: false}}>
        <Stack.Screen name="Login" component={LoginScreen} />
        <Stack.Screen name="Home" component={HomeScreen} />
        <Stack.Screen name= "Register" component={RegisterScreen} />
        <Stack.Screen name= "Bets" component={BetScreen}/>
      </Stack.Navigator>
    </NavigationContainer>
  );
}

export default App;
