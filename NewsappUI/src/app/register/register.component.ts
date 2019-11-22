import { Component, OnInit } from '@angular/core';
import { RouterService } from '../services/router.service';
import { User } from '../user';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  user: User = new User();
  errorMessage:string = "";
  successMessage:string = ""
  constructor(private routerService:RouterService,private userService:UserService) { }

  ngOnInit() {
  }

  
  switchToLogin(){
    this.routerService.routeToLogin();
  }

  doRegister(){
    if(this.user.password != this.user.repassword){
      this.errorMessage="Passwords do not match";    
    }else{
    let obs = this.userService.doResgiter(this.user);
    obs.subscribe(res => {
      this.errorMessage = "";
      this.successMessage = "Welcome to NewsApp "+this.user.userfullname+". please login with your username and password";
      this.user = new User();
    },
    error => {
      console.log(error);
      this.successMessage = "";
      this.errorMessage = error.error;
      console.log("sunil ",this.errorMessage);
    });
  }
  }
}

