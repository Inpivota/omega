import React, {useState} from "react";
import {Redirect} from "react-router-dom";
import AppBar from "@material-ui/core/AppBar/AppBar";
import Toolbar from "@material-ui/core/Toolbar/Toolbar";
import Grid from "@material-ui/core/Grid/Grid";
import Typography from "@material-ui/core/Typography/Typography";
import IconButton from "@material-ui/core/IconButton/IconButton";
import ClickAwayListener from "@material-ui/core/ClickAwayListener/ClickAwayListener";
import MenuItem from "@material-ui/core/MenuItem/MenuItem";
import {logout} from "../redux/actions/SecurityActions";
import {COMPANY_NAME} from "../constants/companyConstants";
import {connect} from "react-redux";
import Menu from "@material-ui/core/Menu/Menu";
import {adminStyles} from "../styles/adminStyles";
import {withStyles} from "@material-ui/core/styles";
import AccountIcon from "@material-ui/icons/AccountCircle";
import {PATH_TO_HOME, PATH_TO_MY_PROFILE} from "../constants/pathConstants";
import {redirect} from "../redux/actions/NavigationActions";
import Button from "@material-ui/core/Button/Button";

const AdminToolbar = (props) => {

    const {
        classes,
    } = props;
    const [menuAnchor, setMenuAnchor] = useState(null);
    const [redirect, setRedirect] = useState("");


    const goToProfile = () => {
        setMenuAnchor(null);
        setRedirect(<Redirect to={PATH_TO_MY_PROFILE}/>);
    };

    return (
        <AppBar
            position="fixed"
            className={classes.appBar}
        >
            <Toolbar>
                <Grid container justify={"space-between"} alignItems={"center"}>
                    <Button className={classes.appBarButton}>
                        <Typography variant="h6" color="inherit" className={classes.grow}
                                    onClick={() => props.sendRedirect(PATH_TO_HOME)}>
                            {COMPANY_NAME}
                        </Typography>
                    </Button>
                    <IconButton onClick={(event) => setMenuAnchor(event.currentTarget)}>
                        <AccountIcon className={classes.appBarButton}/>
                    </IconButton>
                    <ClickAwayListener onClickAway={() => setMenuAnchor(null)}>
                        <Menu open={Boolean(menuAnchor)}
                              anchorEl={menuAnchor}
                        >
                            <MenuItem onClick={goToProfile}>
                                My Profile
                            </MenuItem>
                            <MenuItem onClick={props.logout}>
                                Logout
                            </MenuItem>
                        </Menu>
                    </ClickAwayListener>
                </Grid>
            </Toolbar>
            {redirect}
        </AppBar>
    )
};

const mapDispatchToProps = (dispatch) => {
    return {
        logout: () => dispatch(logout()),
        sendRedirect: (path) => dispatch(redirect(path))
    }
};
const styledToolbar = withStyles(adminStyles)(AdminToolbar);
export default connect(undefined, mapDispatchToProps)(styledToolbar);