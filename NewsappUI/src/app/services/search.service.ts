import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class SearchService {

constructor(private http:HttpClient) { }

  searchNews(queryString:String,pageIndex:number): Observable<any> {
    return this.http.get('https://newsapi.org/v2/everything?language=en&apiKey=7558255888814b34b36fbfa33a0e6196&q='+queryString+'&page='+(pageIndex+1));
  }
}
