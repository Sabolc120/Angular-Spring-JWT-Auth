import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { UserAuthService } from 'src/services/user-auth.service';
import { UserServiceService } from 'src/services/user-service.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private userAuth: UserAuthService,
    private router: Router,
    private userService: UserServiceService){}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    
      if(this.userAuth.getToken() !== null){
        const role = route.data["authorities"];
        
        if(role){
          
          const match = this.userService.allowed(role)
          console.log(match);
          
          if(match) return true;
          else{
            this.router.navigate(['/forbidden'])
            return false;
          } 
        }
      }
      this.router.navigate(['/login']);
      return false;
  }
  
}
