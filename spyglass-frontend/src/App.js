import './App.css';
import { createTheme, ThemeProvider } from '@material-ui/core';
import Colors from './constants/colors';
import LandingPage from 'views/landing-page'


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
        <LandingPage />
      </ThemeProvider>
    </div>
  );
}

export default App;
