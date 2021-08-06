import './App.css';
import { createTheme, ThemeProvider } from '@material-ui/core';
import Colors from './constants/colors';
import NavBar from './components/landing-navbar';

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
        <NavBar title="SpyGlass"/>
      </ThemeProvider>
    </div>
  );
}

export default App;
