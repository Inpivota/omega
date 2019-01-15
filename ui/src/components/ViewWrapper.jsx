import React from "react";
import Paper from "@material-ui/core/Paper/Paper";
import Grid from "@material-ui/core/Grid/Grid";
import Typography from "@material-ui/core/Typography/Typography";
import {withStyles} from "@material-ui/core";
import {adminStyles} from "../styles/adminStyles";
import * as PropTypes from "prop-types";

const ViewWrapper = ({title, ...props}) => {
    const {
        classes,
    } = props;
    return (
        <Paper className={classes.viewWrapper}>
            <Grid container justify={"center"}>
                {/*<Grid item>*/}
                    <Typography variant={"h4"}>{title}</Typography>
                {/*</Grid>*/}
            </Grid>
            {props.children}
        </Paper>
    )
};

ViewWrapper.propTypes = {
    title: PropTypes.string,
};

export default withStyles(adminStyles)(ViewWrapper);