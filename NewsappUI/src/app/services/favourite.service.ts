import { Injectable } from '@angular/core';
import { Article } from '../article';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class FavouriteService {

  constructor(private http: HttpClient) { }


  addToFav(article: Article, username: string): Observable<any> {
    let bearertoken = sessionStorage.getItem("bearertoken");
    console.log(bearertoken);
    let value = 'Bearer:' + bearertoken;
    return this.http.post('http://localhost:8080/favservice/addtofav/' + username, article, {
        headers: new HttpHeaders({
          'authorization':value,
          'content-type':'application/json'
        })
      });
  }

  removeFav(article: Article, username: string): Observable<any> {
    let bearertoken = sessionStorage.getItem("bearertoken");
    let value = 'Bearer:' + bearertoken;
    console.log("in remove fav method",value);
    return this.http.post('http://localhost:8080/favservice/delfav/' + username, article, {
        headers: new HttpHeaders({
          'authorization':value,
          'content-type':'application/json'
        })
      });
  }

  getAllFavourites(username:string): Observable<any>{
    console.log("in Fav service",username);
    let bearertoken = sessionStorage.getItem("bearertoken");
    console.log(bearertoken);
    let value = 'Bearer:' + bearertoken;
    return this.http.get('http://localhost:8080/favservice/listfav/' + username, {
      headers: new HttpHeaders({
        'authorization':value,
        'content-type':'application/json'
      })
    });
  } 
}
