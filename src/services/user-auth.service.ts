import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserAuthService {

  constructor() { }

  public setRoles(authorities:[]){
    localStorage.setItem("authorities",JSON.stringify(authorities));
  }
  public getRoles(){
    return JSON.parse(localStorage.getItem("authorities"));
  }
  public setToken(jwtToken:string){
    localStorage.setItem("jwtToken", jwtToken);
  }
  public getToken():string{
   return localStorage.getItem("jwtToken");
  }
  public clear(){
    localStorage.clear();
  }
  public isLoggedIn(){
    return this.getRoles() && this.getToken();
  }
}
