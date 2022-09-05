import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { UserAuthService } from 'src/services/user-auth.service';
import { UserServiceService } from 'src/services/user-service.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(private userRegService: UserServiceService) { }
  registerUser(registerForm:NgForm){
    console.log(registerForm.value);
    
    this.userRegService.registerUser(registerForm.value).subscribe(
      (resp) =>{
        console.log(resp);
        
      },
      (err) =>{
        console.log(err);
      }
    );
  }
  ngOnInit(): void {
  }

}
