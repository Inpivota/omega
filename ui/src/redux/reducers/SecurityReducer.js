import {SUBMIT_LOGIN} from "../actions/SecurityActions";

const defaultSecurityState = {
    isLoggedIn: false,
    userRoles: [],
};

export const security = (state = defaultSecurityState, action) => {

    switch (action.type) {
        case SUBMIT_LOGIN:
            return {
                ...state,
                isLoggedIn: true,
                userRoles:["USER"],
            };
        default:
            return {...state}
    }
};

export const getUserAuth = (state) => {
    return state.security
};
