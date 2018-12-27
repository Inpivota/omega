import React, {useEffect} from "react";
import {Redirect, Route} from "react-router-dom";
import {connect} from "react-redux";
import {getUserAuth} from "../redux/reducers/SecurityReducer";
import {loadUserFromToken} from "../redux/actions/SecurityActions";

const ProtectedRoute = ({
                            component: Component,
                            allowedRoles,
                            isLoggedIn,
                            userRoles,
                            dispatch,
                            ...rest
                        }) => {

    // let isAllowed = false;
    // allowedRoles.forEach(role => userRoles.indexOf(role) > -1? isAllowed = true: "" );

    useEffect(() => {
        dispatch(loadUserFromToken())
    });

    return (
        <Route
            {...rest}
            render={props =>
                isLoggedIn ? (
                    <Component {...props} />
                ) : (
                    <Redirect
                        to={{
                            pathname: "/login",
                            state: {from: props.location}
                        }}
                    />
                )
            }
        />
    );
};

const mapStateToProps = (state) => {
    const {
        isLoggedIn,
        userRoles,
    } = getUserAuth(state);
    return {
        isLoggedIn,
        userRoles,
    }
};

export default connect(mapStateToProps)(ProtectedRoute)