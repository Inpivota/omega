import React from "react";
import {withStyles} from "@material-ui/core/es/styles";
import LoginForm from "./LoginForm";
import Grid from "@material-ui/core/Grid/Grid";
import LoginBackground from "../assets/img/login-background.jpg"
import {connect} from "react-redux";
import {getUserAuth} from "../redux/reducers/SecurityReducer";
import {Redirect} from "react-router-dom";
import {submitLogin} from "../redux/actions/SecurityActions";


const unStyledLoginPage = (props) => {
    const {
        classes,
        location,
        isLoggedIn,
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
            <LoginForm loginAction={() => dispatch(submitLogin())} />
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
    } = getUserAuth(state);
    return {
        isLoggedIn,
    }
};
const LoginPage = withStyles(styles)(unStyledLoginPage);

export default connect(mapStateToProps)(LoginPage)