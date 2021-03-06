import React from "react";
import {withStyles} from "@material-ui/core/es/styles";
import LoginForm from "../components/LoginForm";
import Grid from "@material-ui/core/Grid/Grid";
import LoginBackground from "../assets/img/login-background.jpg"
import {connect} from "react-redux";
import {getUserAuth} from "../redux/reducers/SecurityReducer";
import {Redirect} from "react-router-dom";
import Dialog from "@material-ui/core/Dialog/Dialog";
import DialogTitle from "@material-ui/core/DialogTitle/DialogTitle";
import LinearProgress from "@material-ui/core/LinearProgress/LinearProgress";
import DialogContent from "@material-ui/core/DialogContent/DialogContent";
import SignUpForm from "../components/SignUpForm";


const unStyledLoginPage = (props) => {
    const {
        classes,
        location,
        isLoggedIn,
        submittingLogin,
        errorMessage,
        isSignUp,
    } = props;

    let { from } = location.state || { from: { pathname: "/" } };

    if (isLoggedIn) return <Redirect to={from} />;
    const form = isSignUp? <SignUpForm/> : <LoginForm/>;

    return <Grid
        container
        spacing={0}
        direction="column"
        alignItems="center"
        justify="center"
        className={classes.page}
    >

        <Grid item xs={4}>
            {form}
            <Dialog open={submittingLogin || errorMessage}>
                <DialogTitle>{errorMessage? errorMessage : "Logging In"}</DialogTitle>
                <DialogContent>
                    {submittingLogin && <LinearProgress/>}
                </DialogContent>
            </Dialog>
        </Grid>

    </Grid>;
};

const styles = theme => ({
    page: {
        minHeight: '100vh',
        backgroundImage: 'url(' + LoginBackground + ')',
        backgroundPosition: "center center",
    },
});

const mapStateToProps = (state) => {
    const {
        isLoggedIn,
        submittingLogin,
        errorMessage,
        isSignUp,
    } = getUserAuth(state);
    return {
        isLoggedIn,
        submittingLogin,
        errorMessage,
        isSignUp,
    }
};
const LoginPage = withStyles(styles)(unStyledLoginPage);

export default connect(mapStateToProps)(LoginPage)