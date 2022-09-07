export const AUTH_USER = 'AUTH_USER'
export const GET_USERS_ALL = 'GET_USERS_ALL'

export const setUser = user => ({
    type: AUTH_USER,
    payload: user
})

export const setUsers = users => ({
    type: AUTH_USER,
    payload: users
})