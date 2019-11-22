import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Location } from '@angular/common';

@Injectable()
export class RouterService {

  constructor(public router: Router, private location: Location) { }

  routeToSearch() {
    this.router.navigate(['search']);
    
  }

  routetoRegister() {
    this.router.navigate(['register']);
  }

  routeToLogin() {
    this.router.navigate(['login']);
  }

  routeBack() {
    this.location.back();
  }

  routeToFavView() {
    this.router.navigate(['favourite']);
  }

  routeToRecomView() {
    this.router.navigate(['recommendation']);
  }
}
