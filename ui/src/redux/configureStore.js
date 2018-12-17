import {applyMiddleware, createStore} from "redux";

const configureStore = (preloadedState) => {

    // const middlewares = [loggerMiddleware, thunkMiddleware];
    // const middlewareEnhancer = applyMiddleware(...middlewares);

    // const enhancers = [middlewareEnhancer];
    // const composedEnhancers = compose(...enhancers);
    // return createStore(rootReducer, preloadedState, composedEnhancers);
};

export default configureStore;