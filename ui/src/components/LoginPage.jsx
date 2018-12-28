import React from "react";
import {withStyles} from "@material-ui/core/es/styles";
import LoginForm from "./LoginForm";
import Grid from "@material-ui/core/Grid/Grid";
import LoginBackground from "../assets/img/login-background.jpg"
import {connect} from "react-redux";
import {getUserAuth} from "../redux/reducers/SecurityReducer";
import {Redirect} from "react-router-dom";
import {tryLogin} from "../redux/actions/SecurityActions";
import Dialog from "@material-ui/core/Dialog/Dialog";
import DialogTitle from "@material-ui/core/DialogTitle/DialogTitle";
import LinearProgress from "@material-ui/core/LinearProgress/LinearProgress";
import DialogContent from "@material-ui/core/DialogContent/DialogContent";


const unStyledLoginPage = (props) => {
    const {
        classes,
        location,
        isLoggedIn,
        submittingLogin,
        dispatch,
    } = props;

    let { from } = location.state || { from: { pathname: "/" } };

    if (isLoggedIn) return <Redirect to={from} />;

    return <Grid
        container
        spacing={0}
        direction="column"
        alignItems="center"
        justify="center"
        className={classes.page}
    >

        <Grid item xs={4}>
            <LoginForm loginAction={(event) => {
                event.preventDefault();
                dispatch(tryLogin("test","test"));
            }} />
            <Dialog open={submittingLogin}>
                <DialogTitle>Logging In</DialogTitle>
                <DialogContent>
                    <LinearProgress/>
                </DialogContent>
            </Dialog>
        </Grid>

    </Grid>;
};

const styles = theme => ({
    page: {
        minHeight: '100vh',
        backgroundImage: 'url(' + LoginBackground + ')' }
    ,
});

const mapStateToProps = (state) => {
    const {
        isLoggedIn,
        submittingLogin,
    } = getUserAuth(state);
    return {
        isLoggedIn,
        submittingLogin,
    }
};
const LoginPage = withStyles(styles)(unStyledLoginPage);

export default connect(mapStateToProps)(LoginPage)