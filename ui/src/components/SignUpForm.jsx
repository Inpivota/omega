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

const SignUpForm = ({loginAction, classes, ...props}) => {

    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [email, setEmail] = useState("");
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const handleChange = (field, value) => {
        switch (field) {
            case "firstName":
                break;
            case "lastName":
                break;
            case "email":
                break;
            case "userName":
                break;
            case "password":
                break;
            default:
                break;

        }
    };

    return <Paper className={classes.loginForm}>
        <Grid container
              justify={"center"}
              alignItems={"center"}>
            <form onSubmit={(event) => {
                event.preventDefault();
                // props.dispatch(trySignUp(username,password));
            }}>
                <Grid item style={{textAlign: "center"}}>
                    <Typography variant={"h4"}>{COMPANY_NAME}</Typography>
                    <Typography variant={"h6"}>Sign up form</Typography>
                </Grid>
                <Grid item>
                    <TextField
                        onChange={event => handleChange("firstName", event.target.value)}
                        label={"First Name"}
                        required
                        margin={"normal"}
                        fullWidth
                    />
                </Grid>
                <Grid item>
                    <TextField
                        onChange={event => handleChange("lastName", event.target.value)}
                        label={"Last Name"}
                        required
                        margin={"normal"}
                        fullWidth
                    />
                </Grid>
                <Grid item>
                    <TextField
                        onChange={event => handleChange("email", event.target.value)}
                        label={"Email"}
                        required
                        margin={"normal"}
                        fullWidth
                    />
                </Grid>
                <Grid item>
                    <TextField
                        onChange={event => handleChange("userName", event.target.value)}
                        label={"Choose a Username"}
                        required
                        margin={"normal"}
                        fullWidth
                />
                </Grid>
                <Grid item>
                    <TextField
                        onChange={event => handleChange("password", event.target.value)}
                        type={"password"}
                        label={"Password"}
                        required
                        margin={"normal"}
                        fullWidth
                    />
                </Grid>
                <Grid item>
                    <TextField
                        onChange={event => setPassword(event.target.value)}
                        type={"password"}
                        label={"Confirm Password"}
                        required
                        margin={"normal"}
                        fullWidth
                    />
                </Grid>
                <Grid item xs={12} className={classes.submitButton}>
                    <Grid container justify={"center"}>
                        <Button color={"primary"} variant={"contained"} type={"submit"}>Submit</Button>
                        <Button
                            fullWidth
                            color={"secondary"}
                            // onClick={() => props.dispatch(launchSignUp())}
                        >
                            I already have an account
                        </Button>
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
const styledForm = withStyles(styles)(SignUpForm);
export default connect()(styledForm);