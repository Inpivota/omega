import React from "react";
import {Redirect} from "react-router-dom";
import {REDIRECT} from "../actions/NavigationActions";

export const navigation = (state, action) => {
    switch (action.type) {
        case REDIRECT:
            return {
                ...state,
                redirect: <Redirect to={action.path}/>
            };
        default:
            return {...state}
    }
};

export const getRedirect = (state) => {
    return state.navigation.redirect;
};