import React from "react";
import {adminStyles} from "../styles/adminStyles";
import {withStyles} from "@material-ui/core";
import {Route, Switch} from "react-router-dom";
import HomeView from "../views/HomeView";
import TestingView from "../views/TestingView";
import AdminToolbar from "../components/AdminToolbar";
import MyProfile from "../views/MyProfile";
import {PATH_TO_FORECASTING, PATH_TO_MY_PROFILE} from "../constants/pathConstants";
import ForecastingView from "../views/ForecastingView";
import AdminSidebar from "../components/AdminSidebar";

const Admin = (props) => {
    const {
        classes,
    } = props;

    return <div>
        <AdminToolbar/>
        <AdminSidebar/>
        <div className={classes.pageWrapper}>
            <Switch>
                <Route path={"/testing"} component={TestingView}/>
                <Route path={PATH_TO_FORECASTING} component={ForecastingView}/>
                <Route path={PATH_TO_MY_PROFILE} component={MyProfile}/>
                <Route path={"/"} component={HomeView}/>
            </Switch>
        </div>
    </div>
};

export default withStyles(adminStyles)(Admin);

