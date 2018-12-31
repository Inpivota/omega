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
import {connect} from "react-redux";
import {logout} from "../redux/actions/SecurityActions";
import {Route, Switch, Redirect} from "react-router-dom";
import HomeView from "../views/HomeView";
import TestingView from "../views/TestingView";
import AdminToolbar from "./AdminToolbar";
import MyProfile from "../views/MyProfile";
import {PATH_TO_MY_PROFILE} from "../constants/pathConstants";

const Admin = (props) => {
    const {
        classes,
    } = props;

    const [redirect, setRedirect] = useState("");


    const goHome = () => {
        setRedirect(<Redirect to={"/"}/>);
    };
    const goTest = () => {
        setRedirect(<Redirect to={"/testing"}/>);
    };

    // if(redirect) return redirect;
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
                    <ListItem button onClick={goHome}>
                        <ListItemIcon><InboxIcon/></ListItemIcon>
                        <ListItemText>Home</ListItemText>
                    </ListItem>
                    <ListItem button onClick={goTest}>
                        <ListItemIcon><InboxIcon/></ListItemIcon>
                        <ListItemText>Testing</ListItemText>
                    </ListItem>
                </List>
            </Drawer>
        <div className={classes.pageWrapper}>
            <Switch>
                <Route path={"/testing"} component={TestingView}/>
                <Route path={PATH_TO_MY_PROFILE} component={MyProfile}/>
                <Route path={"/"} component={HomeView}/>
            </Switch>
        </div>
    </div>
};

const mapDispatchToProps = (dispatch) => {
    return {
        logout: ()=> dispatch(logout())
    }
};

const StyledAdmin = withStyles(adminStyles)(Admin);
export default connect(undefined, mapDispatchToProps)(StyledAdmin)

