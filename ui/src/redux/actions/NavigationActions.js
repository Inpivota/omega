
export const REDIRECT = "REDIRECT";

export const redirect = (path) => {
    return {
        type: REDIRECT,
        path,
    }
};