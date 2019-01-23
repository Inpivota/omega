import React, {useState} from "react";
import Paper from '@material-ui/core/Paper';
import Tab from '@material-ui/core/Tab';
import Tabs from '@material-ui/core/Tabs';
import Typography from "@material-ui/core/Typography/Typography";
import Avatar from '@material-ui/icons/AccountCircle';
import Button from '@material-ui/core/Button';
import Grid from "@material-ui/core/Grid";
import {withStyles} from "@material-ui/core";
import {adminStyles} from "../styles/adminStyles";
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';
import IconButton from "@material-ui/core/es/IconButton/IconButton";


const styles = {
    bigAvatar: {
        padding: 10,
        width: 190,
        height: 190,
    },
    typ:
        {
            padding: "10px"
        },
    input: {
        display: 'none',
    },
};


const UserView = (props) => {
    const testUser = {
        FirstName: "Tester",
        LastName: "Chester",
        Email: "TesterChester@question.com",
        Phone: 5552358888,
        Roles: ["Role1", "Role2"]
    };

    const [open, setOpen] = React.useState(false);

    function handleClickOpen() {
        setOpen(true);
    }

    function handleClose() {
        setOpen(false);
    }

    const Mypopup = (props) => {
        return (
            <Dialog open={open} onClose={handleClose} aria-labelledby="form-dialog-title">
                <DialogTitle id="form-dialog-title">Import</DialogTitle>
                <DialogContent>
                    <DialogContentText>
                        Code to select am image from computer to load as the avatar.
                    </DialogContentText>
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleClose} color="primary">
                        Cancel
                    </Button>
                    <Button onClick={handleClose} color="primary">
                        Select
                    </Button>
                </DialogActions>
            </Dialog>
        );
    };

    const ImageAvatars = (props) => {
        return (
            <div>
                <input
                    accept="image/*"
                    style={styles.input}
                    id="contained-button-file"
                    multiple
                    type="file"
                />
                <label htmlFor="contained-button-file">
                        <Avatar
                            alt="Remy Sharp" src="/static/images/avatar/1.jpg"
                            style={styles.bigAvatar}
                        />
                </label>
                <Mypopup/>
            </div>
        );
    };

    const UserInfo = (props) => {
        return (
            <div>
                <div>
                    <Typography variant="title" style={styles.typ}>
                        Full Name:
                    </Typography>
                    <Typography variant="display1" style={styles.typ}>
                        {testUser.FirstName} {testUser.LastName}
                    </Typography>
                    <Typography variant="title" style={styles.typ}>
                        Email:
                    </Typography>
                    <Typography variant="title" style={styles.typ}>
                        {testUser.Email}
                    </Typography>
                    <Typography variant="title" style={styles.typ}>
                        Phone:
                    </Typography>
                    <Typography variant="title" style={styles.typ}>
                        {testUser.Phone}
                    </Typography>
                </div>
                <Button variant="contained" size="small" style={{margin: "10px",}}>Edit User Info</Button>
            </div>
        )
    };

    const UserRoleInfo = (props) => {
        return (
            <div>
                <div>
                    <Typography variant="display1" style={styles.typ}>
                        Roles:
                    </Typography>
                    <Typography variant="title" style={styles.typ}>
                        <ul>
                            {testUser.Roles.map((role) => <li>{role}</li>)}
                        </ul>
                    </Typography>
                </div>
                <Button variant="contained" size="small" style={{margin: "10px",}}>Edit Roles</Button>
            </div>
        )
    };

    const [currentTab, setCurrentTab] = useState(0);
    const FooterTabs = (props) => {
        return (
            <div square>
                <Tabs value={currentTab} style={{padding: "10px"}}
                      onChange={(event, newValue) => setCurrentTab(newValue)}>
                    <Tab label="User Info"/>
                    <Tab label="Roles"/>
                </Tabs>
            </div>
        );
    };

    return (
        <Grid container justify={"center"} style={{paddingTop: "50px"}}>
            <Grid item xs={6}>
                <Paper>
                    <ImageAvatars/>
                    <FooterTabs/>
                    {currentTab === 0 && <UserInfo style={{padding: "10px"}}/>}
                    {currentTab === 1 && <UserRoleInfo style={{padding: "10px"}}/>}
                </Paper>
            </Grid>
        </Grid>
    );
};
const StyledAdmin = withStyles(adminStyles)(UserView);
export default UserView //(StyledAdmin)

