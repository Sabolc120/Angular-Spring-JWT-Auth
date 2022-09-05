import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserAuthService } from 'src/services/user-auth.service';
import { UserServiceService } from 'src/services/user-service.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private userAuth:UserAuthService,
    private router:Router,
    public userService: UserServiceService) { }

  ngOnInit(): void {
  }

  public isLoggedIn(){
    return this.userAuth.isLoggedIn();
  }
  public logout(){
    this.userAuth.clear();
    this.router.navigate(['home'])
  }
}
