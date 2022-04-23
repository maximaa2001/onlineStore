
import { observer } from "mobx-react-lite"
import Header from "../component/Header"
import Navigation from "../component/UI/Navigaion/CategoryNavigation"
import {Context} from "../index"
import { useContext, useEffect, useState } from "react"

const Home = observer(() => {

  const [navVisible, setNavVisible] = useState(true)

  const call = (value) => {
    setNavVisible(value)
  }


  const {user} = useContext(Context)

    return<div>

    <Header setNavVisible={(value) => call(value)}/>
    {
      navVisible 
      ?<Navigation />
      : null
    }
    
    {user.role}
    </div>
})

export default Home;