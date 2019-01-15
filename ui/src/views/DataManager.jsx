import React from "react";
import ViewWrapper from "../components/ViewWrapper";
import MUIDataTable from "mui-datatables";
import LinearProgress from "@material-ui/core/LinearProgress/LinearProgress";

const DataManager = (props) => {

    const columns = [""];
    const data = [];
    return(
        <ViewWrapper title={"Data Management"}>
            <MUIDataTable title={"no title"} data={data} columns={columns}/>
        </ViewWrapper>
    )
};

export default DataManager;