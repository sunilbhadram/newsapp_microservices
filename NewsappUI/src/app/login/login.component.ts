import { Component, OnInit } from '@angular/core';
import { RouterService } from '../services/router.service';
import { UserService } from '../services/user.service';
import { User } from '../user';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: User = new User();
  submitMessage : string;
  constructor(private routerService: RouterService, private userService: UserService) { }

  ngOnInit() {
  }

  doLogin() {
    this.userService.doLogin(this.user).subscribe(res => {
      console.log(res.token);
      this.userService.setSessionStorage(res.token);
      this.routerService.routeToSearch();
    },error=>{
      if( error.message.indexOf('401') >= 0){
        this.submitMessage = "Unauthorized- Please provide valid credentials";
      }else{
        this.submitMessage = error.message;
      }
  });
}

  switchToLogin() {
    this.routerService.routetoRegister();

  }
}