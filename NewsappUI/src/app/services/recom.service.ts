import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpHeaders, HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RecomService {
   constructor(private http: HttpClient) { }

   getAllRecomedations(): Observable<any>{
    let bearertoken = sessionStorage.getItem("bearertoken");
    console.log(bearertoken);
    let value = 'Bearer:' + bearertoken;
    return this.http.get('http://localhost:8080/recomservice/listrecom/', {
      headers: new HttpHeaders({
        'authorization':value,
        'content-type':'application/json'
      })
    }); 

  }
}
