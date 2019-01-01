import {applyMiddleware, combineReducers, createStore} from "redux";
import {security} from "./reducers/SecurityReducer";
import thunk from 'redux-thunk'
import {navigation} from "./reducers/NavigationReducer";

const rootReducer = combineReducers({
    security,
    navigation,
});

export const store = createStore(rootReducer, applyMiddleware(thunk));