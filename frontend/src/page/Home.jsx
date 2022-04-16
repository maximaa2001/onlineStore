
import { observer } from "mobx-react-lite"
import Header from "../component/Header"
import Navigation from "../component/UI/Navigaion/Navigation"
import {Context} from "../index"
import { useContext } from "react"

const Home = observer(() => {


  const {user} = useContext(Context)

    return<div>

    <Header/>
    <Navigation />
    {user.role}
    </div>
})

export default Home;