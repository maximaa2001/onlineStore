import {useEffect, useState } from "react"

const useAlert = (time) => {
    const [show, setShow] = useState(false)

    useEffect(() => {
        if(show){
            setTimeout(() => {
                setShow(false)
            }, time)
          }
    }, [show])

    return {
        show,
        setShow
    }
   
}

export default useAlert