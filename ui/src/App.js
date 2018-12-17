import React, {Component} from 'react';
import './App.css';
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import LoginPage from "./components/LoginPage";
import Admin from "./components/Admin";
import MuiThemeProvider from "@material-ui/core/styles/MuiThemeProvider";
import {theme} from './styles/theme'
import {Provider} from "react-redux";
import {store} from "./redux/rootReducer";
import ProtectedRoute from "./components/ProtectedRoute";

class App extends Component {
    render() {
        return (
            <Provider store={store}>
                <MuiThemeProvider theme={theme}>
                    <Router>
                        <Switch>
                            <Route path={'/login'} component={LoginPage}/>
                            <ProtectedRoute path={'/'} component={Admin}/>
                        </Switch>
                    </Router>
                </MuiThemeProvider>
            </Provider>
        );
    }
}

export default App;
