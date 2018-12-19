import React, {useState} from "react";
import AppBar from "@material-ui/core/AppBar/AppBar";
import Toolbar from "@material-ui/core/Toolbar/Toolbar";
import Typography from "@material-ui/core/Typography/Typography";
import {adminStyles} from "../styles/adminStyles";
import {withStyles} from "@material-ui/core";
import Drawer from "@material-ui/core/Drawer/Drawer";
import Divider from "@material-ui/core/Divider/Divider";
import List from "@material-ui/core/List/List";
import ListItem from "@material-ui/core/ListItem/ListItem";
import ListItemIcon from "@material-ui/core/ListItemIcon/ListItemIcon";
import ListItemText from "@material-ui/core/ListItemText/ListItemText";
import InboxIcon from "@material-ui/icons/Inbox";

const Admin = (props) => {
    const {
        classes,
    } = props;

    return <div>
        <AppBar
            position="fixed"
            className={classes.appBar}
        >
            <Toolbar>
                <Typography variant="h6" color="inherit" className={classes.grow}>
                    Inpivota Admin
                </Typography>
            </Toolbar>
        </AppBar>
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
                    <ListItem button>
                        <ListItemIcon><InboxIcon/></ListItemIcon>
                        <ListItemText>Testing Item</ListItemText>
                    </ListItem>
                </List>
            </Drawer>
    </div>
};

export default withStyles(adminStyles)(Admin);

