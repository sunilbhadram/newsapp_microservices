import { browser, by, element, ElementFinder, promise } from 'protractor';

export class RecomPage {

    navigateToRecom() {
        return browser.get('/recommendation');
      }

      getCurrentURL() {
        return browser.getCurrentUrl();
      }  
}