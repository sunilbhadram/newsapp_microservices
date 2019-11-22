import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SearchComponent } from './search/search.component';
import { LoginComponent } from './login/login.component';
import { CanActivateRouteGuard } from './services/can-activate-route.guard';
import { RegisterComponent } from './register/register.component';
import { FavouriteComponent } from './favourite/favourite.component';
import { RecomComponent } from './recom/recom.component';

const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'search', component: SearchComponent, canActivate: [CanActivateRouteGuard]},
  {path: 'favourite', component: FavouriteComponent, canActivate: [CanActivateRouteGuard]},
  {path: 'recommendation', component: RecomComponent, canActivate: [CanActivateRouteGuard]},
  {path: '', redirectTo: 'search', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule { }