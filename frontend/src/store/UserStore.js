import {makeAutoObservable} from "mobx";

class UserStore {
    role = "UNKNOW";
   constructor(){
       makeAutoObservable(this)
   }

   setRole(role){
       this.role = role;
   }

}

export default UserStore;
