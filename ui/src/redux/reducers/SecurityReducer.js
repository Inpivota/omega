import {LOGIN_SUCCESS, SUBMIT_LOGIN} from "../actions/SecurityActions";

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
        default:
            return {...state}
    }
};

export const getUserAuth = (state) => {
    return state.security
};
