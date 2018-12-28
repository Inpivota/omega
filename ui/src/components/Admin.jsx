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
import AccountIcon from "@material-ui/icons/AccountCircle";
import IconButton from "@material-ui/core/IconButton/IconButton";
import ClickAwayListener from "@material-ui/core/ClickAwayListener/ClickAwayListener";
import Menu from "@material-ui/core/Menu/Menu";
import MenuItem from "@material-ui/core/MenuItem/MenuItem";
import Grid from "@material-ui/core/Grid/Grid";
import {connect} from "react-redux";
import {logout} from "../redux/actions/SecurityActions";

const Admin = (props) => {
    const {
        classes,
    } = props;

    const [menuAnchor, setMenuAnchor] = useState(null);

    const logout = () => {
        setMenuAnchor(null);
        props.logout();
    };

    return <div>
        <AppBar
            position="fixed"
            className={classes.appBar}
        >
            <Toolbar>
                <Grid container justify={"space-between"} alignItems={"center"}>
                <Typography variant="h6" color="inherit" className={classes.grow}>
                    Inpivota Admin
                </Typography>
                <IconButton onClick={(event) => setMenuAnchor(event.currentTarget)}>
                    <AccountIcon className={classes.appBarButton}/>
                </IconButton>
                <ClickAwayListener onClickAway={() => setMenuAnchor(null)}>
                    <Menu open={Boolean(menuAnchor)}
                          anchorEl={menuAnchor}
                    >
                        <MenuItem onClick={()=>{setMenuAnchor(null)}}>
                            My Profile
                        </MenuItem>
                        <MenuItem onClick={logout}>
                            Logout
                        </MenuItem>
                    </Menu>
                </ClickAwayListener>
                </Grid>
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

const mapDispatchToProps = (dispatch) => {
    return {
        logout: ()=> dispatch(logout())
    }
};

const StyledAdmin = withStyles(adminStyles)(Admin);
export default connect(undefined, mapDispatchToProps)(StyledAdmin)

