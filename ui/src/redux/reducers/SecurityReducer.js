import {LOGIN_SUCCESS, LOGOUT, SUBMIT_LOGIN} from "../actions/SecurityActions";

const defaultSecurityState = {
    submittingLogin: false,
    isLoggedIn: false,
    userRoles: [],
};

export const security = (state = defaultSecurityState, action) => {

    switch (action.type) {
        case SUBMIT_LOGIN:
            return {
                ...state,
                submittingLogin: true,
                isLoggedIn: false,
            };
        case LOGIN_SUCCESS:
            return {
                ...state,
                submittingLogin: false,
                isLoggedIn: true,
            };
        case LOGOUT:
            return {
                ...state,
                submittingLogin: false,
                isLoggedIn: false,
            };
        default:
            return {...state}
    }
};

export const getUserAuth = (state) => {
    const {
        submittingLogin,
        isLoggedIn,
        userRoles,
    } = state.security;
    return {
        submittingLogin,
        isLoggedIn,
        userRoles,
    }
};
