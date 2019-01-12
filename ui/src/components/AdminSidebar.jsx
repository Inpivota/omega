import React from "react";
import {Link} from "react-router-dom";
import Drawer from "@material-ui/core/Drawer/Drawer";
import Divider from "@material-ui/core/Divider/Divider";
import List from "@material-ui/core/List/List";
import ListItem from "@material-ui/core/ListItem/ListItem";
import ListItemIcon from "@material-ui/core/ListItemIcon/ListItemIcon";
import ListItemText from "@material-ui/core/ListItemText/ListItemText";
import InboxIcon from "@material-ui/icons/Inbox";
import AssessmentIcon from "@material-ui/icons/Assessment";
import {PATH_TO_FORECASTING} from "../constants/pathConstants";
import {adminStyles} from "../styles/adminStyles";
import {withStyles} from "@material-ui/core";

const AdminSidebar = (props) => {
    const {
        classes,
    } = props;

    return (
        <Drawer
            className={classes.drawer}
            variant="permanent"
            classes={{
                paper: classes.drawerPaper,
            }}
        >
            <div className={classes.toolbar}/>
            <Divider/>
            <List>
                <Link to={PATH_TO_FORECASTING} className={classes.link}>
                    <ListItem button>
                        <ListItemIcon><AssessmentIcon/></ListItemIcon>
                        <ListItemText>Forecasting</ListItemText>
                    </ListItem>
                </Link>
                <Link to={"/testing"} className={classes.link}>
                    <ListItem button>
                        <ListItemIcon><InboxIcon/></ListItemIcon>
                        <ListItemText>Testing</ListItemText>
                    </ListItem>
                </Link>
            </List>
        </Drawer>
    )
};

export default withStyles(adminStyles)(AdminSidebar);