import { SearchPage } from './search.po';
import { FavPage } from './fav.po';
import { element, by } from 'protractor';

describe('Favourite page', () => {
    let searchPage: SearchPage;
    let favPage: FavPage;
    beforeEach(() => {
        searchPage = new SearchPage();
        favPage = new FavPage();
      });


      it('should navigate to Favourite screen', () => {
        favPage.navigateToFav();
        expect(favPage.getCurrentURL()).toContain('favourite', 'Should navigate to favourite');
      }); 

      it('should able to add to favourites', () => {
        searchPage.navigateToSearch();
        searchPage.addSearchKey();
        searchPage.clickSubmitButton();
        let list = element.all(by.css('.button-item'));
        list.get(0).click();
        favPage.navigateToFav();
        let favlist = element.all(by.css('.fav-list-item'));
        expect(favlist.count()).toBe(1);

        });

        it('should able to delete from favourites', () => {
            favPage.navigateToFav();
            let delList = element.all(by.css('.del-icon'));
            delList.get(0).click();
            let favlist = element.all(by.css('.fav-list-item'));
            expect(favlist.count()).toBe(0);
    
        });

});
