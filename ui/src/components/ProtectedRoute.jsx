import React from "react";
import {Redirect, Route} from "react-router-dom";
import {connect} from "react-redux";
import {getUserAuth} from "../redux/reducers/SecurityReducer";

const ProtectedRoute = ({ component: Component, allowedRoles, isLoggedIn, userRoles, ...rest }) => {

    // let isAllowed = false;
    // allowedRoles.forEach(role => userRoles.indexOf(role) > -1? isAllowed = true: "" );

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
                            state: { from: props.location }
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