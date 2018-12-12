import React from "react";
import AppBar from "@material-ui/core/AppBar/AppBar";
import Toolbar from "@material-ui/core/Toolbar/Toolbar";
import IconButton from "@material-ui/core/IconButton/IconButton";
import Typography from "@material-ui/core/Typography/Typography";
import {adminStyles} from "../styles/adminStyles";
import {withStyles} from "@material-ui/core";
import MenuIcon from '@material-ui/icons/Menu';


const Admin = (props) => {
    const {
        classes,
    } = props;

    return <div>
        <AppBar>
            <Toolbar>
                <IconButton className={classes.menuButton} color="inherit" aria-label="Menu">
                    <MenuIcon />
                </IconButton>
                <Typography variant="h6" color="inherit" className={classes.grow}>
                    Inpivota Admin
                </Typography>
            </Toolbar>
        </AppBar>
    </div>
};

export default withStyles(adminStyles)(Admin);