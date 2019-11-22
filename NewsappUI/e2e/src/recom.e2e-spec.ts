import { SearchPage } from './search.po';
import { FavPage } from './fav.po';
import { RecomPage } from './recom.po';
import { element, by } from 'protractor';

describe('Recommendation page', () => {
    let searchPage: SearchPage;
    let favPage: FavPage;
    let recomPage: RecomPage;
    beforeEach(() => {
        searchPage = new SearchPage();
        favPage = new FavPage();
        recomPage = new RecomPage();
      });


      it('should navigate to Recommendation screen', () => {
        recomPage.navigateToRecom();
        expect(favPage.getCurrentURL()).toContain('recommendation', 'Should navigate to recommendation');
      }); 

      it('should able to add to Recommendations', () => {
        searchPage.navigateToSearch();
        searchPage.addSearchKey();
        searchPage.clickSubmitButton();
        let list = element.all(by.css('.button-item'));
        list.get(0).click();
        recomPage.navigateToRecom();
        let recomlist = element.all(by.css('.recom-container'));
        expect(recomlist.count()).toBe(1);

        });

        it('should delete from recommendation when deleted from favourite', () => {
          favPage.navigateToFav();
          let delList = element.all(by.css('.del-icon'));
          delList.get(0).click();
          let recomlist = element.all(by.css('.recom-container'));
          expect(recomlist.count()).toBe(0);
      });

});