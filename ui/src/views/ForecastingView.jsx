import React from "react";
import Typography from "@material-ui/core/Typography/Typography";
import ViewWrapper from "../components/ViewWrapper";
import Grid from "@material-ui/core/Grid/Grid";
import Table from "@material-ui/core/Table/Table";
import TableHead from "@material-ui/core/TableHead/TableHead";
import TableRow from "@material-ui/core/TableRow/TableRow";
import TableCell from "@material-ui/core/TableCell/TableCell";
import TableBody from "@material-ui/core/TableBody/TableBody";
import Paper from "@material-ui/core/Paper/Paper";
import {Area, AreaChart} from "recharts";
import TextField from "@material-ui/core/TextField/TextField";
import withStyles from "@material-ui/core/styles/withStyles";

const styles = theme => ({
    root: {
        width: '100%',
        marginTop: theme.spacing.unit * 3,
        overflowX: 'auto',
    },
    table: {
        minWidth: 700,
    },
});

const ForecastingView = (props) => {
    const {classes} = props;
    const graph = (data) => {
        return (
            <AreaChart width={200} height={60} data={data}>
                <Area type='monotone' dataKey='sales' stroke='#8884d8' fill='#8884d8' />
            </AreaChart>
        );
    };
    const salesDataObjects = (sales) => sales.map(value => {return {sales:value}});
    const data = [
        {
            name:"Vitamin C (1 lb) POI",
            salesHistory: salesDataObjects([5,10,14,17,12,8,7,3,14,16,20,26,27,30,34,31,28,25,23,26,29,20,15,21,16,10,18,6,2,1,1]),
            predictedAvg: 19.03,
            manualAverage: 34,
            amazonInventory: 625,
            shipped: 630,
        }
    ];
    return (
        <Paper className={classes.root}>
                <Table className={classes.table}>
                    <TableHead>
                        <TableRow>
                            <TableCell>Name</TableCell> {/*Flag icon will be present right next to the name*/}
                            <TableCell>Sales History</TableCell>
                            <TableCell>Predicted Average</TableCell>
                            <TableCell>Manual Average</TableCell>
                            <TableCell>Amazon Inventory</TableCell>
                            <TableCell>Shipped</TableCell>
                            <TableCell>Actions</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {data.map(item => (
                            <TableRow>
                                <TableCell>{item.name}</TableCell>
                                <TableCell>{graph(item.salesHistory)}</TableCell>
                                <TableCell>{item.predictedAvg}</TableCell>
                                <TableCell>
                                    <TextField
                                        value={item.manualAverage}
                                        // onChange={updateItem}
                                    />
                                </TableCell>
                                <TableCell>{item.amazonInventory}</TableCell>
                                <TableCell>{item.shipped}</TableCell>
                            </TableRow>)
                        )}
                    </TableBody>
                </Table>
        </Paper>
    )
};

export default withStyles(styles)(ForecastingView);
