import {combineReducers, createStore} from "redux";
import {security} from "./reducers/SecurityReducer";

const rootReducer = combineReducers({
    security,
});

export const store = createStore(rootReducer);