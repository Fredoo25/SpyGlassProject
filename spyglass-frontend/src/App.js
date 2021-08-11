import './App.css';
import { createTheme, ThemeProvider } from '@material-ui/core';
import Colors from './constants/colors';
//import NavBar from './components/landing-navbar';
import ContactForm from "./components/ContactForm";
import React from 'react';

const theme = createTheme({
  palette: {
    primary: {
      main: Colors.primary
    }
  }
})

function App() {
  return (
    <div className="App">
      <ThemeProvider theme={theme} >
           <ContactForm/>
      </ThemeProvider>
    </div>
  );
}

export default App;
