import {COMPANY_NAME} from "../constants/companyConstants";
import React from "react";
import Typography from "@material-ui/core/Typography/Typography";
import ViewWrapper from "../components/ViewWrapper";

const HomeView = (props) => {
    return <ViewWrapper>
        <Typography variant={"h2"}>Welcome to {COMPANY_NAME}</Typography>
    </ViewWrapper>
};

export default HomeView;