import {fetchLoginToken, meFromToken} from "../../utilAPIs/securityAPI";

export const SUBMIT_LOGIN = "SUBMIT_LOGIN";
export const LOGIN_SUCCESS = "LOGIN_SUCCESS";
export const LOGIN_ERROR = "LOGIN_ERROR";
export const LOGOUT = "LOGOUT";
export const CLEAR_LOGIN_STATE = "CLEAR_LOGIN_STATE";
export const ME_FROM_TOKEN_SUCCESS = "ME_FROM_TOKEN_SUCCESS";

const submitLogin = () => {
    return {
        type: SUBMIT_LOGIN,
    }
};
const loginSuccess = () => {
    return {
        type: LOGIN_SUCCESS,
    }
};
const loginError = (errorMessage) => {
    return {
        type: LOGIN_ERROR,
        errorMessage,
    }
};
const doLogout = () => {
    return {
        type: LOGOUT,
    }
};
const clearLoginState = () => {
    return {
        type: CLEAR_LOGIN_STATE,
    }
};
export const tryLogin = (username, password) => {
    return (dispatch) => {
        dispatch(submitLogin());
        fetchLoginToken(username, password).then(token =>
        {
            localStorage.setItem("jwtToken", token);
            dispatch(loginSuccess());
        })
            .catch(error => {
                dispatch(loginError(error.message));
                setTimeout(() => dispatch(clearLoginState()), 2000)
            });
    }
};
export const logout = () => {
    return dispatch => {
        localStorage.removeItem("jwtToken");
        dispatch(doLogout());
    }
};
const meFromTokenSuccess = (payload) => {
    return dispatch => {
        dispatch(loginSuccess())
    }
};
const meFromTokenFailure = () => {
    return dispatch => {
        dispatch(loginError("Token was Invalid"))
    }
};

export const loadUserFromToken = () => {
    return dispatch => {
        let token = localStorage.getItem("jwtToken");
        if (!token || token === "") {//if there is no token, don't do anything
            return;
        }
        // meFromToken(token)
        //     .then((response) => {
                // if (!response.error) {
                //     sessionStorage.setItem("jwtToken", response.payload.data.token);
                    dispatch(meFromTokenSuccess("fakePayload"))
                    // dispatch(meFromTokenSuccess(response.payload))
                // } else {
                //     sessionStorage.removeItem("jwtToken");
                //     dispatch(meFromTokenFailure(response.payload));
                // }
            // });
    }
};