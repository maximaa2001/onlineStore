const configToken = (token) => {
    const config = {
        headers: {
            "AUTHORIZATION": token
        },
    }
    return config
}

export default configToken;

const configTokenAndImage = (token) => {
    const config = {
        headers: {
            "AUTHORIZATION": token,
            "Content-type": "multipart/form-data; boundary=----------287032381131322"
        },
    }
    return config
}