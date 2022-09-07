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

export const uploadFile = async (formData) => {
    const {data} = await $host.post('file/upload-file', formData);
    return data;
}

export const getAllUsers = async () => {
    const {data} = await $host.get('user/all');
    return data;
}