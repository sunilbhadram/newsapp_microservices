import { browser, by, element, ElementFinder, promise } from 'protractor';

export class FavPage {

    navigateToFav() {
        return browser.get('/favourite');
      }

      getCurrentURL() {
        return browser.getCurrentUrl();
      }  
}