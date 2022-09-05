import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserAuthService } from './user-auth.service';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  constructor(private httpclient:HttpClient,
    private userAuth: UserAuthService) { }

  BACKEND = "http://localhost:8080";

  requestHeader = new HttpHeaders(
    {"No-Auth":"True"}
  );

  public login(loginData){
    return this.httpclient.post(this.BACKEND + "/authenticate",
     loginData, {headers: this.requestHeader})
  }
  public registerUser(user){
    return this.httpclient.post(this.BACKEND + '/register', user)
  }
  /*public roleMatch(allowedRoles) :boolean{
    let isMatch = false;
    const userRoles:any = this.userAuth.getRoles();

    if(userRoles != null && userRoles){
      for(let i = 0; i < userRoles.length; i++){
        for(let g = 0; g < allowedRoles.length; g++){
          if(userRoles[i].roleName === allowedRoles[g]) isMatch = true
          return isMatch;
        }
        return isMatch;
      }
    }
  }*/
  public allowed(allowedRoles) :boolean{
    const role = this.userAuth.getRoles();
    let isMatch = false;
    for(let i = 0; i < allowedRoles.length; i++){
      if(allowedRoles[i] == role){
        isMatch = true;
        return isMatch;
      }
    }
    return false;
  }
}
