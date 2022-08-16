import { combineReducers, createStore } from "redux";
import { composeWithDevTools } from "redux-devtools-extension";
import { userReduser } from "./user/reducers";


const rootReducer = combineReducers({
    user: userReduser,
})

export const store = createStore(rootReducer, composeWithDevTools())