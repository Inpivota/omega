import React from "react";
import Table from "@material-ui/core/Table/Table";
import TableHead from "@material-ui/core/TableHead/TableHead";
import TableRow from "@material-ui/core/TableRow/TableRow";
import TableCell from "@material-ui/core/TableCell/TableCell";
import TableBody from "@material-ui/core/TableBody/TableBody";
import Paper from "@material-ui/core/Paper/Paper";
import {Area, AreaChart, Tooltip, XAxis, YAxis} from "recharts";
import TextField from "@material-ui/core/TextField/TextField";
import withStyles from "@material-ui/core/styles/withStyles";
import IconButton from "@material-ui/core/IconButton/IconButton";
import MuiTooltip from "@material-ui/core/Tooltip/Tooltip";
import InfoIcon from "@material-ui/icons/Info";
import MUIDataTable from "mui-datatables";

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
            <AreaChart width={250} height={80} data={data}>
                <Area type='monotone' dataKey='sales' stroke='#8884d8' fill='#8884d8'/>
                <YAxis />
                <XAxis />
                <Tooltip labelFormatter={(dayNum)=> {{/*`Day Number ${dayNum}`*/}}}/>
            </AreaChart>
        );
    };
    const salesDataObjects = (sales) => sales.map(value => {
        return {sales: value}
    });
    const tableColumns = [
        "Name",
        "Over Under",
        "Manual Avg",
        "Sales - last 30d",
        "Notes",
        "Actions"
    ];
    const tableData = [
        [
            "Vitamin C (1 lb) POI",
            15,
            <TextField value={34}/>,
            graph(salesDataObjects([5, 10, 14, 17, 12, 8, 7, 3, 14, 16, 20, 26, 27, 30, 34, 31, 28, 25, 23, 26, 29, 20, 15, 21, 16, 10, 18, 6, 2, 1, 1])),
            "",
            <MuiTooltip title={"View Details"}>
                <IconButton>
                    <InfoIcon/>
                </IconButton>
            </MuiTooltip>
        ],
        [
            "Calcium Carbonate 16oz",
            1.8,
            <TextField value={6.250}/>,
            graph(salesDataObjects([6, 1, 4, 5, 4, 6, 5, 3, 4, 4, 3, 6, 5, 5, 0, 0, 0, 0, 0, 0, 1, 8, 5, 3, 3, 1, 7, 7, 7, 2, 6])),
            "",
            <MuiTooltip title={"View Details"}>
                <IconButton>
                    <InfoIcon/>
                </IconButton>
            </MuiTooltip>
        ],
        [
            "POI Aluminum Oxide 32oz X23",
            3.3,
            <TextField value={5}/>,
            graph(salesDataObjects([0, 0, 0, 3, 2, 2, 0, 1, 0, 1, 0, 3, 1, 7, 2, 1, 2, 6, 2, 1, 2, 4, 3, 0, 1, 1, 4, 0, 2, 0, 1])),
            "Low Avg",
            <MuiTooltip title={"View Details"}>
                <IconButton>
                    <InfoIcon/>
                </IconButton>
            </MuiTooltip>
        ]
    ];
    // const data = [
    //     {
    //         name: "Vitamin C (1 lb) POI",
    //         overUnder: 15,
    //         manualAverage: 34,
    //         salesHistory: salesDataObjects([5, 10, 14, 17, 12, 8, 7, 3, 14, 16, 20, 26, 27, 30, 34, 31, 28, 25, 23, 26, 29, 20, 15, 21, 16, 10, 18, 6, 2, 1, 1]),
    //         notes: "",
    //         // predictedAvg: 19.03,
    //         // amazonInventory: 625,
    //         // shipped: 630,
    //     },
    //     {
    //         name: "Calcium Carbonate 16oz",
    //         overUnder: 1.8,
    //         manualAverage: 6.250,
    //         salesHistory: salesDataObjects([6, 1, 4, 5, 4, 6, 5, 3, 4, 4, 3, 6, 5, 5, 0, 0, 0, 0, 0, 0, 1, 8, 5, 3, 3, 1, 7, 7, 7, 2, 6]),
    //         notes: "",
    //         // predictedAvg: 4.43,
    //         // amazonInventory: 293,
    //         // shipped: 2,
    //     },
    //     {
    //         name: "POI Aluminum Oxide 32oz X23",
    //         overUnder: 3.3,
    //         manualAverage: 5,
    //         salesHistory: salesDataObjects([0, 0, 0, 3, 2, 2, 0, 1, 0, 1, 0, 3, 1, 7, 2, 1, 2, 6, 2, 1, 2, 4, 3, 0, 1, 1, 4, 0, 2, 0, 1]),
    //         notes: "Low Avg Sales",
    //         // predictedAvg: 1.68,
    //         // amazonInventory: 78,
    //         // shipped: 17,
    //     }
    // ];
    return (
        <Paper className={classes.root}>
            <MUIDataTable title={"Forecasting"} data={tableData} columns={tableColumns}/>
            {/*<Table className={classes.table}>
                <TableHead>
                    <TableRow>
                        <TableCell>Name</TableCell> Flag icon will be present right next to the name
                        <TableCell>Over - Under</TableCell>
                        <TableCell>Manual Average</TableCell>
                        <TableCell>Sales History (30d)</TableCell>
                        <TableCell>Notes</TableCell>
                        <TableCell>Predicted Average</TableCell>
                        <TableCell>Amazon Inventory</TableCell>
                        <TableCell>Shipped</TableCell>
                        <TableCell>Actions</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {data.map(item => (
                        <TableRow>
                            <TableCell>{item.name}</TableCell>
                            <TableCell>{item.overUnder}</TableCell>
                            <TableCell>
                                <TextField
                                    value={item.manualAverage}
                                    // onChange={updateItem}
                                />
                            </TableCell>
                            <TableCell>{graph(item.salesHistory)}</TableCell>
                            <TableCell>
                                <TextField
                                    value={item.notes}
                                    fullWidth
                                />
                            </TableCell>
                            <TableCell>{item.shipped}</TableCell>
                            <TableCell>
                                <Tooltip title={"View Details"}>
                                    <IconButton>
                                        <InfoIcon/>
                                    </IconButton>
                                </Tooltip>
                            </TableCell>
                        </TableRow>)
                    )}
                </TableBody>
            </Table>*/}
        </Paper>
    )
};

export default withStyles(styles)(ForecastingView);
