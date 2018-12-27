import {applyMiddleware, combineReducers, createStore} from "redux";
import {security} from "./reducers/SecurityReducer";
import thunk from 'redux-thunk'

const rootReducer = combineReducers({
    security,
});

export const store = createStore(rootReducer, applyMiddleware(thunk));