import React from 'react';
import './App.css';
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import LoginPage from "./pages/LoginPage";
import Admin from "./pages/Admin";
import MuiThemeProvider from "@material-ui/core/styles/MuiThemeProvider";
import {theme} from './styles/theme'
import {Provider} from "react-redux";
import {store} from "./redux/rootReducer";
import ProtectedRoute from "./components/ProtectedRoute";
import CssBaseline from "@material-ui/core/CssBaseline/CssBaseline";

const App = () => {
    return (
        <Provider store={store}>
            <MuiThemeProvider theme={theme}>
                <CssBaseline>
                    <Router>
                        <Switch>
                            <Route path={'/login**'} component={LoginPage}/>
                            <ProtectedRoute path={'/'} component={Admin}/>
                        </Switch>
                    </Router>
                </CssBaseline>
            </MuiThemeProvider>
        </Provider>
    );
};

export default App;
