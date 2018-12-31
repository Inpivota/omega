import {handleErrors} from "./APIUtils";

export const fetchLoginToken = (username, password) => {
   return fetch("api/auth/login", {
       method: 'POST',
       headers: {
           'Accept': 'application/json',
           'Content-Type': 'application/json',
       },
       body: JSON.stringify({username, password})
   })
       .then(handleErrors)
       .then(json => json.accessToken)
};
export const submitSignUp = (values) => {
    return fetch("api/auth/register", {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(values)
    })
        .then(handleErrors)
        .then(json => json.success)
};

export const meFromToken = () => {
};