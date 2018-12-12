import React, { Component } from 'react';
import './App.css';
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import LoginPage from "./components/LoginPage";
import Admin from "./components/Admin";
import MuiThemeProvider from "@material-ui/core/styles/MuiThemeProvider";
import {theme} from './styles/theme'

class App extends Component {
  render() {
    return (
        <Router>
            <MuiThemeProvider theme={theme}>
          <Switch>
              <Route path={'/login'} component={LoginPage}/>
              <Route path={'/'} component={Admin}/>
          </Switch>
            </MuiThemeProvider>
        </Router>
    );
  }
}

export default App;
