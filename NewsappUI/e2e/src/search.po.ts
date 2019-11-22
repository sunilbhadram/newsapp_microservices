import { browser, by, element, ElementFinder, promise } from 'protractor';

export class SearchPage {

    navigateToSearch() {
        return browser.get('/search');
      }

      getCurrentURL() {
        return browser.getCurrentUrl();
      }  

      addSearchKey(){
        this.getSearchInputBox().sendKeys("sunil");
      }

      getSearchInputBox(): ElementFinder {
        return element(by.className('searchbox'));
      }

      clickSubmitButton(): promise.Promise<void> {
        return this.getSubmitButton().click();
      }

      getSubmitButton(): ElementFinder {
        return this.getSearchComponent().element(by.buttonText('Search'));
      }

      getSearchComponent(): ElementFinder {
        return element(by.tagName('app-search'));
      }

      getSearchListComponent(): ElementFinder {
        return element(by.tagName('mat-list'));
      }
      
}