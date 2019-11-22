
import { async, ComponentFixture, TestBed, fakeAsync, tick, inject} from '@angular/core/testing';
import { LoginComponent } from './login/login.component';
import { By } from '@angular/platform-browser';
import { UserService } from './services/user.service';
import { RouterService } from './services/router.service';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { MatInputModule } from '@angular/material/input';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { Observable } from 'rxjs';
import 'rxjs/add/observable/of';
import 'rxjs/add/observable/throw';


const testConfig = {
  positive: {
      message: "User Successfully Logged In",
      token: "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdW5pbCNTdW5pbCBCaGFkcmFtIiwiaWF0IjoxNTY5NTgwNzA1LCJleHAiOjE1Njk1ODEzMDV9.HQ0yVCzKNuUOzjyE0XwDrGxlu0MeByxHkdq_X4v1HZ0",
      statusCode: 200
  },
  negative: {
    message: "Unauthorized- Please provide valid credentials",
    token: null,
    statusCode: 401
}
};
describe('LoginComponent', () => {
  let routerService: any;
  let loginComponent: LoginComponent;
  let userService: UserService;
  const routerSpy: any = {};
  let location: Location;
  let fixture: ComponentFixture<LoginComponent>;
  let positiveResponse: any;
  let negativeResponse: any;
  let debugElement: any;
  let element: any;


  beforeEach(async(() => {
   
    TestBed.configureTestingModule({
      declarations: [
        LoginComponent
      ],imports: [
        FormsModule,
        MatFormFieldModule,
        HttpClientModule,
        MatInputModule,
        BrowserAnimationsModule
        ],
      providers: [
        UserService,
        RouterService,
        { provide: Location, useValue: {} },
        { provide: Router, useValue: routerSpy }

      ]
    }).compileComponents();
  }));
  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent);
    location = TestBed.get(Location);
    loginComponent = fixture.componentInstance;
    userService = fixture.debugElement.injector.get(UserService);
    routerService = fixture.debugElement.injector.get(RouterService);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(loginComponent).toBeTruthy();
  });

  it('should handle to login into the system', fakeAsync(() => {
    positiveResponse = testConfig.positive;
    spyOn(userService, 'doLogin').and.returnValue(Observable.of(positiveResponse));
    spyOn(routerService,'routeToSearch').and.callFake(function(){});
    loginComponent.doLogin();
    expect(sessionStorage.getItem('bearertoken')).toBe(positiveResponse.token, 'Bearer Token is not Matching');
  }));

  it('should handle to wrong username and password', fakeAsync(() => {
    negativeResponse = testConfig.negative;
    loginComponent.submitMessage = ' ';
    fixture.detectChanges();
    debugElement = fixture.debugElement.query(By.css('.error-message'));
    spyOn(userService, 'doLogin').and.returnValue(Observable.throw(negativeResponse));
    spyOn(userService,'setSessionStorage').and.callFake(function(){})
    spyOn(routerService,'routeToSearch').and.callFake(function(){});
    loginComponent.doLogin();
    tick();
    fixture.detectChanges();
    if (debugElement !== null) {
      element = debugElement.nativeElement;
      expect(element.textContent).toBe(negativeResponse.message,
        `should store 'err.error.message' in a varibale 'submitMessage' to show error on login page`);
    } else {
      expect(false).toBe(true,
        `should have an element  as <strong *ngIf="submitMessage" class="error-message">{{submitMessage}}</strong>
        in your login.component.html to show server errror response`);
    }
  }));



});