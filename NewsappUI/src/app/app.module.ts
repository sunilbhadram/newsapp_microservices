import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './material/material.module';
import { SearchService } from './services/search.service';
import { HttpClient } from '@angular/common/http';
import { HttpClientModule } from '@angular/common/http';
import { SearchComponent } from './search/search.component';
import { FavouriteService } from './services/favourite.service';
import { LoginComponent } from './login/login.component';
import { CanActivateRouteGuard } from './services/can-activate-route.guard';
import { RouterService } from './services/router.service';
import { RegisterComponent } from './register/register.component';
import { FavouriteComponent } from './favourite/favourite.component';
import { RecomComponent } from './recom/recom.component';

@NgModule({
  declarations: [
    AppComponent,
    SearchComponent,
    LoginComponent,
    RegisterComponent,
    FavouriteComponent,
    RecomComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    FormsModule,
    HttpClientModule
    
  ],
  providers: [SearchService,HttpClient,FavouriteService,RouterService,
    CanActivateRouteGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
