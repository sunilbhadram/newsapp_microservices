import { Component, OnInit } from '@angular/core';
import { Article } from '../article';
import { SearchService } from '../services/search.service';
import { PageEvent } from '@angular/material/paginator';
import { FavouriteService } from '../services/favourite.service';


@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  serarcquery: String = "";
  articles: Article[] = [];
  favArticles: Article[] = [];
  favTitles: string[] = [];
  article: Article = null;
  totalResults: number = 0;
  pageIndex: number = 0;
  pageSize: number = 20;
  pageSizeOptions: number[] = [5, 10, 25, 100];
  pageEvent: PageEvent;
  spinnerFlag: boolean = false;
  errorMessage: string = null;
  fullname = sessionStorage.getItem('userfullname');
  usernmae = sessionStorage.getItem('username');


  constructor(private searchService: SearchService, private favService: FavouriteService) {

  }


  ngOnInit() {
    this.favService.getAllFavourites(this.usernmae).subscribe(response => {
      console.log(response);
      this.favArticles = response;
      console.log(this.favArticles);
      this.favArticles.forEach(fa => {
        this.favTitles.push(fa.title);
      })
    },
      err => {
        console.log(err.error);
        //this.errorMessage = err.error.message;
      });
  }

  onPageChange(e) {
    this.pageIndex = e.pageIndex;
    this.searchNews();
  }

  searchNews() {
    this.spinnerFlag = true;
    this.errorMessage = null;
    this.articles = [];
    let obs = this.searchService.searchNews(this.serarcquery, this.pageIndex);
    obs.subscribe(response => {
      this.totalResults = response.totalResults;
      response.articles.forEach(element => {
        this.article = new Article();
        this.article.title = element.title;
        this.article.description = element.description;
        this.article.url = element.url;
        this.article.urltoImage = element.urltoImage;
        this.articles.push(this.article);

      });

    },
      err => {
        console.log(err.error);
        this.errorMessage = err.error.message;
      }
    )
    this.spinnerFlag = false;
  }



  addToFav(article: Article) {
    this.favArticles = new Array;
    this.favArticles.push(article);
    this.favTitles.push(article.title);
    let obs = this.favService.addToFav(article, this.usernmae);
    obs.subscribe(res => {
      console.log(res);
    }, error => {
      console.log(error);
      this.favArticles.pop();
      this.favTitles.pop();
    })
  }

}
