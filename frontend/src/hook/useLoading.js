import {useEffect, useState } from "react"

const useLoading = (callback) => {
    
    const [isLoading, setLoading] = useState(false)
    const [error, setError] = useState('')

    useEffect(() => {
        if(error){
            setTimeout(() => setError(''), 10000)
        }
    },[error])

    const loadData = async () => {
        try {
            setLoading(true)
         await callback()
        } catch (error) {
            setError(error.message)
        }finally{
            setLoading(false)
        }
    
    }

    return{
        loadData,
        isLoading,
        error
    }

   
}

export default useLoading