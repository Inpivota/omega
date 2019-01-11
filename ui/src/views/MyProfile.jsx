import React, { Component, Fragment } from "react";
import Paper from '@material-ui/core/Paper';
import Tab from '@material-ui/core/Tab';
import Tabs from '@material-ui/core/Tabs';
import Typography from "@material-ui/core/Typography/Typography";
import Avatar from '@material-ui/icons/AccountCircle';
import Grid from '@material-ui/core/Grid';
import Button from '@material-ui/core/Button';
import Card from '@material-ui/core/Card';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';


const styles = {
    bigAvatar: {
        margin: 10,
        width: 120,
        height: 120,
    },
    card: {
        minWidth: 275,
        marginLeft: 0,
        marginBottom: 15,
        MarginRight: 10
    },
    button:{
        marginBottom: 10,
        marginRight: 10,
        marginTop: 1,
        marginLeft: 10
    },
    tab:{
        margin: 10
    }
};

function ImageAvatars() {
    return (
        <Card style={styles.card}>
            <CardContent>
                <Paper>
                    <Avatar alt="Remy Sharp" src="/static/images/avatar/1.jpg" style={styles.bigAvatar}/>
                </Paper>
            </CardContent>
            <CardActions>
                <Button variant="contained" style={styles.button}>Edit Picture</Button>
            </CardActions>
        </Card>
    );
}

function FooterTabs() {
    return (
        <Paper square>
            <Tabs value={0} style={styles.tab}>
                <Tab label="Account Info"/>
                <Tab label="Roles"/>
            </Tabs>
        </Paper>
    );
}


export default class extends Component {
    render() {
        return (<Fragment>
                    <ImageAvatars/>
                <Paper>
                    <div>
                        <Typography>
                            First Name
                        </Typography>
                        <Typography>
                            Last Name
                        </Typography>
                        <Typography>
                            Email
                        </Typography>
                        <Typography>
                            Phone
                        </Typography>
                    </div>
                </Paper>
                <FooterTabs/>
            </Fragment>
        );
    }
}

