import Grid from "@material-ui/core/Grid/Grid";
import Paper from "@material-ui/core/Paper/Paper";
import React from "react";
import Typography from "@material-ui/core/Typography/Typography";
import TextField from "@material-ui/core/TextField/TextField";
import Button from "@material-ui/core/Button/Button";
import {withStyles} from "@material-ui/core/es/styles";

const LoginForm = ({loginAction, classes, ...rest}) => {

    return <Paper className={classes.loginForm}>
            <Grid container
                  justify={"center"}
                  alignItems={"center"}>
                <form onSubmit={loginAction}>
                    <Grid item style={{textAlign: "center"}}>
                        <Typography variant={"h4"}>Inpivota</Typography>
                        <Typography variant={"h6"}>Please Login</Typography>
                    </Grid>
                    <Grid item>
                        <TextField label={"Username"} required margin={"normal"} fullWidth/>
                    </Grid>
                    <Grid item>
                        <TextField type={"password"} label={"Password"} required margin={"normal"} fullWidth/>
                    </Grid>
                    <Grid item xs={12} className={classes.submitButton}>
                        <Grid container justify={"center"}>
                            <Button variant={"contained"} type={"submit"}>Submit</Button>
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
export default withStyles(styles)(LoginForm)