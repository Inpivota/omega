import React from "react";
import {Link, withRouter} from "react-router-dom";
import Drawer from "@material-ui/core/Drawer/Drawer";
import Divider from "@material-ui/core/Divider/Divider";
import List from "@material-ui/core/List/List";
import ListItem from "@material-ui/core/ListItem/ListItem";
import ListItemIcon from "@material-ui/core/ListItemIcon/ListItemIcon";
import ListItemText from "@material-ui/core/ListItemText/ListItemText";
import StorageIcon from "@material-ui/icons/Storage";
import MathIcon from "@material-ui/icons/Iso";
import AssessmentIcon from "@material-ui/icons/Assessment";
import {PATH_TO_DATABASE_MANAGEMENT, PATH_TO_FORECASTING, PATH_TO_GRAPHQL} from "../constants/pathConstants";
import {adminStyles} from "../styles/adminStyles";
import {withStyles} from "@material-ui/core";

const AdminSidebar = (props) => {
    const {
        classes,
        location
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
                <Link to={PATH_TO_FORECASTING} className={location.pathname !== PATH_TO_FORECASTING? classes.link :""}>
                    <ListItem button>
                        <ListItemIcon>
                            <AssessmentIcon/>
                        </ListItemIcon>
                        <ListItemText>Forecasting</ListItemText>
                    </ListItem>
                </Link>
                <Link to={PATH_TO_DATABASE_MANAGEMENT} className={location.pathname !== PATH_TO_DATABASE_MANAGEMENT? classes.link :""}>
                    <ListItem button>
                        <ListItemIcon>
                            <StorageIcon/>
                        </ListItemIcon>
                        <ListItemText>Database</ListItemText>
                    </ListItem>
                </Link>
                <Link to={PATH_TO_GRAPHQL} className={location.pathname !== PATH_TO_GRAPHQL? classes.link :""}>
                    <ListItem button>
                        <ListItemIcon><MathIcon/></ListItemIcon>
                        <ListItemText>GraphiQL</ListItemText>
                    </ListItem>
                </Link>
            </List>
        </Drawer>
    )
};

export default withRouter(withStyles(adminStyles)(AdminSidebar));