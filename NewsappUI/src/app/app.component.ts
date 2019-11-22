import { Component, OnInit } from '@angular/core';
import { RouterService } from './services/router.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'Newsapp';
  
  constructor(private routerService:RouterService){
    
  }

  ngOnInit(){

  }

  switchToFav(){
      this.routerService.routeToFavView();
  }

  switchToSearch(){
    this.routerService.routeToSearch();
  }
  switchToRecom(){
    this.routerService.routeToRecomView();
  }
  doLogout(){
    sessionStorage.clear();
    this.routerService.routeToLogin();
  }

}
