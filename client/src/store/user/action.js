export const AUTH_USER = 'AUTH_USER'
export const SET_AUTH = 'SET_AUTH'

export const setUser = user => ({
    type: AUTH_USER,
    payload: user
})
