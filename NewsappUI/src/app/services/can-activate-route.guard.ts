import { RouterService } from './router.service';
import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable()
export class CanActivateRouteGuard implements CanActivate {

  constructor(private routerService: RouterService) {}

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): boolean {
      const token = sessionStorage.getItem("bearertoken");
      const helper = new JwtHelperService();
      const isExpired = helper.isTokenExpired(token);
      console.log(isExpired);
      if(isExpired){
        this.routerService.routeToLogin();
      }else{
        return true;
      }
   }
}
