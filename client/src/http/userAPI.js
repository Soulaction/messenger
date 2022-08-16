import { $host } from ".";


export const authorization = async(email, password) => {

    const {data} = await $host.get('user', {params: {
        email, password
    }})
    return data;
}

export const registration = async(user) => {

    const {data} = await $host.post('user', user)
    return data;
}