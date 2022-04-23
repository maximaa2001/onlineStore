import React, { useContext, useEffect } from "react";
import {Navbar, Container, Row, Col, Form, Button } from "react-bootstrap";
import Modal from "./UI/Modal/Modal";
import logo from '../img/logo.ico';
import useShowModal from "../hook/useShowModal";
import ModalButton from "./UI/Button/ModalButton";
import RegForm from "./Form/RegForm";
import LoginForm from "./Form/LoginForm";
import {Context} from "../index"
import Const from "../const/Const"
import UserMenu from "./UI/Menu/UserMenu";
import { observer } from "mobx-react-lite";
import { Link } from "react-router-dom";

const Header = (props) =>{

 const regModal = useShowModal(false);

 const authModal = useShowModal(false);

 const {user} = useContext(Context)


 useEffect(() => {
   if(regModal.isVisible || authModal.isVisible){
     if(props.setNavVisible){
      props.setNavVisible(false)
     }
 
    
   } else{

    if(props.setNavVisible){
      props.setNavVisible(true)
     }
   }

 }, [regModal.isVisible, authModal.isVisible])
 

  
    return(
      <div>
      <Navbar bg="dark" expand="dark" className="w-100">
      <Container className="d-flex justify-content-start w-100" fluid>
      <Row className="w-100">
      <Col lg={2}><Link to={Const.HOME}>
      <Navbar.Brand>
      <img
        src={logo}
        width="100"
        height="50"
        className="d-inline-block align-top ms-3"
        alt="React Bootstrap logo"
      />
    </Navbar.Brand>
      </Link>
      
    </Col>
    <Col lg={5}>
    <Form >
  <Form.Group className=" d-flex justify-content-start mt-2">
    <Form.Control className="w-75" placeholder="Товар, услуга" />
    <Button  className="w-25"variant="outline-success">Find</Button>
  </Form.Group>
  </Form>
  </Col>
  <Col lg= {5}>
  
  {
    user.role === Const.USER_ROLE
    ? 
      <div className="mt-2 ms-1 d-flex justify-content-end">
      <UserMenu/>
      </div>
    : user.role === Const.ADMIN_ROLE
    ?  <div className="mt-2 d-flex justify-content-end">
      <button>ADMIN</button>
      </div>
    : <div className="mt-2 d-flex justify-content-end">
      <ModalButton onClick={() => regModal.show(true)}>Зарегистрироваться</ModalButton>
      <ModalButton clazz="ms-3" onClick={() => authModal.show(true)}>Войти</ModalButton>
      </div>
  }
  


  </Col>
  </Row>
      </Container>
    </Navbar>
    <Modal className="w-100" visible={regModal.isVisible} setVisible={() => regModal.show(false)}>
    <RegForm/>
      </Modal>
    <Modal visible={authModal.isVisible} setVisible={() => authModal.show(false)}>
    <LoginForm setVisible={() => authModal.show(false)}></LoginForm></Modal>
    </div>
  )
}

export default Header;

