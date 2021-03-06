import {
    CANCEL_SIGN_UP,
    CLEAR_LOGIN_STATE,
    LAUNCH_SIGN_UP,
    LOGIN_ERROR,
    LOGIN_SUCCESS,
    LOGOUT,
    SUBMIT_LOGIN
} from "../actions/SecurityActions";

const defaultSecurityState = {
    submittingLogin: false,
    isLoggedIn: false,
    userRoles: [],
    errorMessage: "",
    isSignUp: false,
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
                isSignUp: false,
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
                isSignUp: false,
            };
        case CLEAR_LOGIN_STATE:
            return {
                ...state,
                submittingLogin: false,
                isLoggedIn: false,
                userRoles: [],
                errorMessage: "",
            };
        case LAUNCH_SIGN_UP:
            return {
                ...state,
                isSignUp: true,
            };
        case CANCEL_SIGN_UP:
            return {
                ...state,
                isSignUp: false,
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
        isSignUp,
    } = state.security;
    return {
        submittingLogin,
        isLoggedIn,
        userRoles,
        errorMessage,
        isSignUp,
    }
};
