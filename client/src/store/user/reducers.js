import { AUTH_USER, SET_AUTH } from "./action"

const defaultStore = {
    name: '',
    surname: '',
    avatar: '',
    isAuth: false
}

export const userReduser = (state = defaultStore, action) => {
    debugger
    switch (action.type) {
        case AUTH_USER:
            return {...state, ...action.payload}
    }
    return state;
}