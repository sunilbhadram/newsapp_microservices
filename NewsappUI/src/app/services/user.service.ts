import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../user';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  doLogin(user: User): Observable<any> {
    console.log(user.username);
    console.log(user.password);
    
    return this.http.post("http://localhost:8080/userservice/login", {
      'username': user.username,
      'password': user.password
    },{
      headers: new HttpHeaders({
        'Content-Type':'application/json'
      })

    });
  }
  
  setSessionStorage(token: string){
   if(token != null){
      const helper = new JwtHelperService();
      const decodedToken = helper.decodeToken(token);
      const sub = decodedToken.sub;
      const username = sub.split('#')[0];
      const fullname = sub.split('#')[1];
      sessionStorage.setItem('bearertoken', token);
      sessionStorage.setItem('username', username);
      sessionStorage.setItem('userfullname', fullname);
   }
  }


  doResgiter(user: User): Observable<any> {
    return this.http.post("http://localhost:8080/userservice/register", {
      'userid':0,
      'username': user.username,
      'password': user.password,
      'fullname': user.userfullname,
      'email': user.useremail
    },{
      headers: new HttpHeaders({
        'Content-Type':'application/json'
      })

    });

  }

}
