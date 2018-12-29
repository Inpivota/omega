import Grid from "@material-ui/core/Grid/Grid";
import Paper from "@material-ui/core/Paper/Paper";
import React, {useState} from "react";
import Typography from "@material-ui/core/Typography/Typography";
import TextField from "@material-ui/core/TextField/TextField";
import Button from "@material-ui/core/Button/Button";
import {withStyles} from "@material-ui/core/es/styles";
import {launchSignUp, tryLogin} from "../redux/actions/SecurityActions";
import {connect} from "react-redux";
import {COMPANY_NAME} from "../constants/companyConstants";

const LoginForm = ({loginAction, classes, ...props}) => {

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    return <Paper className={classes.loginForm}>
            <Grid container
                  justify={"center"}
                  alignItems={"center"}>
                <form onSubmit={(event) => {
                    event.preventDefault();
                    props.dispatch(tryLogin(username,password));
                }}>
                    <Grid item style={{textAlign: "center"}}>
                        <Typography variant={"h4"}>{COMPANY_NAME}</Typography>
                        <Typography variant={"h6"}>Please Login</Typography>
                    </Grid>
                    <Grid item>
                        <TextField
                            onChange={event => setUsername(event.target.value)}
                            label={"Username"}
                            required
                            margin={"normal"}
                            fullWidth
                        />
                    </Grid>
                    <Grid item>
                        <TextField
                            onChange={event => setPassword(event.target.value)}
                            type={"password"}
                            label={"Password"}
                            required
                            margin={"normal"}
                            fullWidth
                        />
                    </Grid>
                    <Grid item xs={12} className={classes.submitButton}>
                        <Grid container justify={"center"}>
                                <Button color={"primary"} variant={"contained"} type={"submit"}>Submit</Button>
                                <Button color={"secondary"} onClick={() => props.dispatch(launchSignUp())}>Sign Up</Button>
                        </Grid>
                    </Grid>
                </form>
                </Grid>
        </Paper>
};

const styles = theme => ({
    loginForm: {
        padding: theme.spacing.unit * 4,
    },
    submitButton: {
        paddingTop: theme.spacing.unit * 2,
    },
});
const styledForm = withStyles(styles)(LoginForm);
export default connect()(styledForm);