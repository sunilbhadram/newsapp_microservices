import { Component, OnInit } from '@angular/core';
import { Article } from '../article';
import { RecomService } from '../services/recom.service';

@Component({
  selector: 'app-recom',
  templateUrl: './recom.component.html',
  styleUrls: ['./recom.component.css']
})
export class RecomComponent implements OnInit {

  errorMessage: string = "";
  recomarticles: Article[] = [];
  article: Article = null;
  constructor(private recomService:RecomService) { }

  ngOnInit() {
    this.getRecomArticles();
  }

  getRecomArticles() {
    console.log("in Recom component");

    let obs = this.recomService.getAllRecomedations();

    obs.subscribe(element => {

      element.forEach(elementItem => {
        this.article = new Article();
        this.article.title = elementItem.title;
        this.article.description = elementItem.description;
        this.article.url = elementItem.url;
        this.article.recomCount = elementItem.recomCount;
        this.recomarticles.push(this.article);
      });


    },
      err => {
        console.log(err.error);
        this.errorMessage = err.error.message;
      });

  }

}
