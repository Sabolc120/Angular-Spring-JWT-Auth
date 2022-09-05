import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ResolveStart, Router } from '@angular/router';
import { UserAuthService } from 'src/services/user-auth.service';
import { UserServiceService } from 'src/services/user-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private userService: UserServiceService, 
    private userAuthService:UserAuthService,
    private router:Router) { }

  ngOnInit(): void {
  }

  login(loginForm:NgForm){
    this.userService.login(loginForm.value).subscribe(
      (resp:any) =>{
        this.userAuthService.setRoles(resp.userEntity.authorities);
        this.userAuthService.setToken(resp.jwtToken)
        const role = resp.userEntity.authorities;
        console.log(resp);
        
        
        if(role === 'USER'){
          this.router.navigate(['/user'])
        }
        else if(role === 'ADMIN'){
          this.router.navigate(['/admin']);
        }
      },
      (err) =>{
        console.log(err);
      }
    );
  }
}
