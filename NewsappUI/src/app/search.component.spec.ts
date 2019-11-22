
import { async, ComponentFixture, TestBed, fakeAsync, tick, inject } from '@angular/core/testing';
import { SearchComponent } from './search/search.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { FormsModule } from '@angular/forms';
import { MatListModule } from '@angular/material/list';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatIconModule } from '@angular/material/icon';
import { MatPaginatorModule } from '@angular/material/paginator';
import { HttpClientModule } from '@angular/common/http';
import { Location } from '@angular/common';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SearchService } from './services/search.service';
import { Observable } from 'rxjs';
import { By } from '@angular/platform-browser';
import 'rxjs/add/observable/of';
import 'rxjs/add/observable/throw';

const testConfig = {
  positive:
  {
    "totalResults": 2,
    "articles": [
      {
        "title": "title_1",
        "description": "desc_1",
        "url": "url_1",
        "urlToImage": "image_1"
      },
      {
        "title": "title_2",
        "description": "desc_2",
        "url": "url_2",
        "urlToImage": "image_2"
      }
    ]
  },
  negative: {
    error:{
    "status": "error",
    "code": "apiKeyInvalid",
    "message": "Your API key is invalid or incorrect. Check your key, or go to https://newsapi.org to create a free API key."
    }
  }
};



describe('SearchComponent', () => {
  let searchComponent: SearchComponent;
  let fixture: ComponentFixture<SearchComponent>;
  let location: Location;
  let searchService: any;
  let positiveResponse: any;
  let negativeResponse: any;
  let debugElement: any;
  let element: any;


  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        SearchComponent
      ], imports: [
        FormsModule,
        MatFormFieldModule,
        MatInputModule,
        MatListModule,
        MatProgressSpinnerModule,
        MatIconModule,
        MatPaginatorModule,
        HttpClientModule,
        BrowserAnimationsModule
      ],
      providers: [
        SearchService,
        { provide: Location, useValue: {} },

      ]
    }).compileComponents();
  }));
  beforeEach(() => {
    fixture = TestBed.createComponent(SearchComponent);
    location = TestBed.get(Location);
    searchComponent = fixture.componentInstance;
    searchService = fixture.debugElement.injector.get(SearchService);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(searchComponent).toBeTruthy();
  });

  it('should search news', () => {
    positiveResponse = testConfig.positive;
    spyOn(searchService, 'searchNews').and.returnValue(Observable.of(positiveResponse));
    searchComponent.searchNews();
    expect(searchComponent.articles[0].title).toBe(positiveResponse.articles[0].title);
  });

  it('should haneld error in search news url', fakeAsync(() => {
    negativeResponse = testConfig.negative;
    searchComponent.errorMessage = ' ';
    fixture.detectChanges();
    debugElement = fixture.debugElement.query(By.css('.error-message'));
    spyOn(searchService, 'searchNews').and.returnValue(Observable.throw(negativeResponse));
    searchComponent.searchNews();
    tick();
    fixture.detectChanges();
    if (debugElement !== null) {
      element = debugElement.nativeElement;
      console.log(element.textContent);
      expect(element.textContent).toBe(negativeResponse.error.message,
        `should store 'err.error.message' in a varibale 'submitMessage' to show error on login page`);
    } else {
      expect(false).toBe(true,
        `should have an element  as <strong *ngIf="submitMessage" class="error-message">{{submitMessage}}</strong>
        in your login.component.html to show server errror response`);
    }

  }));
});