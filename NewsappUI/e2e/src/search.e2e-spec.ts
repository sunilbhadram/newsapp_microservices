import { SearchPage } from './search.po';
import { element, by } from 'protractor';

describe('Search page', () => {
    let page: SearchPage;
    beforeEach(() => {
        page = new SearchPage();
      });


      it('should navigate to search screen', () => {
        page.navigateToSearch();
        expect(page.getCurrentURL()).toContain('search', 'Should navigate to Search');
      }); 


      it('should return search results', () => {
        page.navigateToSearch();
        page.addSearchKey();
        page.clickSubmitButton();
        let list = element.all(by.css('.list-item'));
        expect(list.count()).toBe(20);
        });



});
