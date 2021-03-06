import React from "react";
import {adminStyles} from "../styles/adminStyles";
import {withStyles} from "@material-ui/core";
import {Route, Switch} from "react-router-dom";
import HomeView from "../views/HomeView";
import AdminToolbar from "../components/AdminToolbar";
import MyProfile from "../views/MyProfileView";
import {
    PATH_TO_DATABASE_MANAGEMENT,
    PATH_TO_FORECASTING,
    PATH_TO_FORMULA_MANAGEMENT, PATH_TO_GRAPHQL,
    PATH_TO_HOME,
    PATH_TO_MY_PROFILE
} from "../constants/pathConstants";
import ForecastingView from "../views/ForecastingView";
import AdminSidebar from "../components/AdminSidebar";
import FormulaManager from "../views/FormulaManager";
import DataManager from "../views/DataManager";
import GraphQlView from "../views/GraphQLView";

const Admin = (props) => {
    const {
        classes,
    } = props;

    return <div>
        <AdminToolbar/>
        <AdminSidebar/>
        <div className={classes.pageWrapper}>
            <Switch>
                <Route path={PATH_TO_FORECASTING} component={ForecastingView}/>
                <Route path={PATH_TO_MY_PROFILE} component={MyProfile}/>
                <Route path={PATH_TO_FORMULA_MANAGEMENT} component={FormulaManager}/>
                <Route path={PATH_TO_DATABASE_MANAGEMENT} component={DataManager}/>
                <Route path={PATH_TO_HOME} component={HomeView} exact/>
                <Route path={PATH_TO_GRAPHQL} component={GraphQlView}/>
            </Switch>
        </div>
    </div>
};

export default withStyles(adminStyles)(Admin);

