import {CLEAR_LOGIN_STATE, LOGIN_ERROR, LOGIN_SUCCESS, LOGOUT, SUBMIT_LOGIN} from "../actions/SecurityActions";

const defaultSecurityState = {
    submittingLogin: false,
    isLoggedIn: false,
    userRoles: [],
    errorMessage: "",
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
        case LOGIN_ERROR:
            return {
                ...state,
                submittingLogin: false,
                isLoggedIn: false,
                errorMessage: action.errorMessage
            };
        case LOGOUT:
            return {
                ...state,
                submittingLogin: false,
                isLoggedIn: false,
            };
        case CLEAR_LOGIN_STATE:
            return {
                ...state,
                submittingLogin: false,
                isLoggedIn: false,
                userRoles: [],
                errorMessage: "",
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
        errorMessage,
    } = state.security;
    return {
        submittingLogin,
        isLoggedIn,
        userRoles,
        errorMessage,
    }
};
