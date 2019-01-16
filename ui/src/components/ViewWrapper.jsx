import React from "react";
import Paper from "@material-ui/core/Paper/Paper";
import Grid from "@material-ui/core/Grid/Grid";
import {withStyles} from "@material-ui/core";
import {adminStyles} from "../styles/adminStyles";
import * as PropTypes from "prop-types";

const ViewWrapper = ({title, ...props}) => {
    const {
        classes,
    } = props;
    return (
        <Grid container justify={"center"}>
            <Grid item xs={12} sm={10} md={8} lg={7} xl={6}>
                <Paper className={classes.viewWrapper}>
                    {props.children}
                </Paper>
            </Grid>
        </Grid>
    )
};

ViewWrapper.propTypes = {
    title: PropTypes.string,
};

export default withStyles(adminStyles)(ViewWrapper);