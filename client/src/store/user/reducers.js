import { AUTH_USER, GET_USERS_ALL } from "./action"

const defaultStore = {
    name: '',
    surname: '',
    avatar: '',
    isAuth: false,
    users: []
}

export const userReduser = (state = defaultStore, action) => {
    
    switch (action.type) {
        case AUTH_USER:
            return {...state, ...action.payload}
        case GET_USERS_ALL:
            return {...state, users: [state.users, ...action.payload]}
    }
    return state;
}