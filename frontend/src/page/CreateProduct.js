
import { observer } from "mobx-react-lite"
import CreateProductForm from "../component/Form/CreateProductForm";
import Header from "../component/Header"

const CreateProduct = observer(() => {

    return<div>

    <Header/>
    <CreateProductForm/>

    </div>
})

export default CreateProduct;