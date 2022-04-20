import React from "react";
import { Nav } from "react-bootstrap";
import { Link } from "react-router-dom";
import SendButton from "../Button/SendButton";


const ProductStatusNav = () =>{



return <div>
<Nav className="justify-content-center" activeKey="/home">
<Nav.Item>
  <Nav.Link ><Link to="/">Активные</Link></Nav.Link>
</Nav.Item>
<Nav.Item>
  <Nav.Link><Link to="/">На модерации</Link></Nav.Link>
</Nav.Item>
<Nav.Item>
  <Nav.Link><Link to="/">Отклоненные</Link></Nav.Link>
</Nav.Item>
<Nav.Item>
  <Nav.Link><Link to="/">Неактивные</Link></Nav.Link>
</Nav.Item>
</Nav>
</div>

}

export default ProductStatusNav;