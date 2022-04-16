const configToken = (token) => {
    const config = {
        headers: {
            "AUTHORIZATION": token
        },
    }
    return config
}

export default configToken;