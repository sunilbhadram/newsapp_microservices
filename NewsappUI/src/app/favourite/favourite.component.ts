import { Component, OnInit } from '@angular/core';
import { FavouriteService } from '../services/favourite.service';
import { Article } from '../article';

@Component({
  selector: 'app-favourite',
  templateUrl: './favourite.component.html',
  styleUrls: ['./favourite.component.css']
})
export class FavouriteComponent implements OnInit {
  errorMessage: string = "";
  favarticles: Article[] = [];
  article: Article = null;
  constructor(private favService: FavouriteService) { }

  ngOnInit() {
    this.getFavArticles();
  }


  getFavArticles() {
    console.log("in Fav component");

    let username = sessionStorage.getItem('username');
    let obs = this.favService.getAllFavourites(username);

    obs.subscribe(element => {

      element.forEach(elementItem => {
        this.article = new Article();
        this.article.title = elementItem.title;
        this.article.description = elementItem.description;
        this.article.url = elementItem.url;
        this.article.urltoImage = elementItem.urltoImage;
        this.favarticles.push(this.article);
      });


    },
      err => {
        console.log(err.error);
        this.errorMessage = err.error.message;
      });

  }

  removeFav(article: Article){
    console.log(article.title);
    let username = sessionStorage.getItem('username');
    let obs = this.favService.removeFav(article,username);
    obs.subscribe(element => {
       console.log(element);
    },
      err => {
        console.log(err.error);
        this.errorMessage = err.error.message;
      });
    this.favarticles.forEach( (item, index) => {
      if(item.title === article.title) this.favarticles.splice(index,1);
    });
  }
}
