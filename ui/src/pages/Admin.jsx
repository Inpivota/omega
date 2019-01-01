import React, {useState} from "react";
import {adminStyles} from "../styles/adminStyles";
import {withStyles} from "@material-ui/core";
import Drawer from "@material-ui/core/Drawer/Drawer";
import Divider from "@material-ui/core/Divider/Divider";
import List from "@material-ui/core/List/List";
import ListItem from "@material-ui/core/ListItem/ListItem";
import ListItemIcon from "@material-ui/core/ListItemIcon/ListItemIcon";
import ListItemText from "@material-ui/core/ListItemText/ListItemText";
import InboxIcon from "@material-ui/icons/Inbox";
import AssessmentIcon from "@material-ui/icons/Assessment";
import {connect} from "react-redux";
import {logout} from "../redux/actions/SecurityActions";
import {Route, Switch, Redirect} from "react-router-dom";
import HomeView from "../views/HomeView";
import TestingView from "../views/TestingView";
import AdminToolbar from "../components/AdminToolbar";
import MyProfile from "../views/MyProfile";
import {PATH_TO_FORECASTING, PATH_TO_HOME, PATH_TO_MY_PROFILE} from "../constants/pathConstants";
import ForecastingView from "../views/ForecastingView";
import {redirect} from "../redux/actions/NavigationActions";
import {getRedirect} from "../redux/reducers/NavigationReducer";

const Admin = (props) => {
    const {
        classes,
        redirect
    } = props;



    return <div>
        {redirect}
        <AdminToolbar/>
            <Drawer
                className={classes.drawer}
                variant="permanent"
                classes={{
                    paper: classes.drawerPaper,
                }}
            >
                <div className={classes.toolbar}/>
                <Divider />
                <List>
                    <ListItem button onClick={()=> props.sendRedirect(PATH_TO_FORECASTING)}>
                        <ListItemIcon><AssessmentIcon/></ListItemIcon>
                        <ListItemText>Forecasting</ListItemText>
                    </ListItem>
                    <ListItem button onClick={()=> props.sendRedirect("/testing")}>
                        <ListItemIcon><InboxIcon/></ListItemIcon>
                        <ListItemText>Testing</ListItemText>
                    </ListItem>
                </List>
            </Drawer>
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

const mapDispatchToProps = (dispatch) => {
    return {
        logout: () => dispatch(logout()),
        sendRedirect: (path) => dispatch(redirect(path))
    }
};

const mapStateToProps = (state) => {
    return {
        redirect: getRedirect(state)
    }
};

const StyledAdmin = withStyles(adminStyles)(Admin);
export default connect(mapStateToProps, mapDispatchToProps)(StyledAdmin)

