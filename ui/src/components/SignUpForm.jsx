import Grid from "@material-ui/core/Grid/Grid";
import Paper from "@material-ui/core/Paper/Paper";
import React, {useState} from "react";
import Typography from "@material-ui/core/Typography/Typography";
import TextField from "@material-ui/core/TextField/TextField";
import Button from "@material-ui/core/Button/Button";
import {withStyles} from "@material-ui/core/es/styles";
import {cancelSignUp, trySignUp} from "../redux/actions/SecurityActions";
import {connect} from "react-redux";
import {COMPANY_NAME} from "../constants/companyConstants";

const SignUpForm = ({loginAction, classes, ...props}) => {

    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [email, setEmail] = useState("");
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [passwordMatch, setPasswordMatch] = useState(true);

    const handleChange = (field, value) => {
        switch (field) {
            case "firstName":
                setFirstName(value);
                break;
            case "lastName":
                setLastName(value);
                break;
            case "email":
                setEmail(value);
                break;
            case "userName":
                setUsername(value);
                break;
            case "password":
                setPassword(value);
                break;
            case "confirmPassword":
                if (password !== value) setPasswordMatch(false);
                else setPasswordMatch(true);
                break;
            default:
                break;

        }
    };

    const values = {
        firstName,
        lastName,
        email,
        username,
        password,
    };

    return <Paper className={classes.loginForm}>
        <Grid container
              justify={"center"}
              alignItems={"center"}>
            <form onSubmit={(event) => {
                event.preventDefault();
                props.dispatch(trySignUp(values));
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
                        value={firstName}
                    />
                </Grid>
                <Grid item>
                    <TextField
                        onChange={event => handleChange("lastName", event.target.value)}
                        label={"Last Name"}
                        required
                        margin={"normal"}
                        fullWidth
                        value={lastName}
                    />
                </Grid>
                <Grid item>
                    <TextField
                        onChange={event => handleChange("email", event.target.value)}
                        label={"Email"}
                        required
                        margin={"normal"}
                        fullWidth
                        value={email}
                    />
                </Grid>
                <Grid item>
                    <TextField
                        onChange={event => handleChange("userName", event.target.value)}
                        label={"Choose a Username"}
                        required
                        margin={"normal"}
                        fullWidth
                        value={username}
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
                        value={password}
                    />
                </Grid>
                <Grid item>
                    <TextField
                        onChange={event => handleChange("confirmPassword", event.target.value)}
                        type={"password"}
                        label={"Confirm Password"}
                        required
                        margin={"normal"}
                        fullWidth
                        error={!passwordMatch}
                    />
                </Grid>
                <Grid item xs={12} className={classes.submitButton}>
                    <Grid container justify={"center"}>
                        <Button color={"primary"} variant={"contained"} type={"submit"}>Submit</Button>
                        <Button
                            size={"small"}
                            fullWidth
                            color={"secondary"}
                            onClick={() => props.dispatch(cancelSignUp())}
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