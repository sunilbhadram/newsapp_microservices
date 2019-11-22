import { LoginPage } from './login.po'

describe('login page', () => {
    let page: LoginPage;

    beforeEach(() => {
        page = new LoginPage();
      });

      it('should get username input box', () => {
        page.navigateToLogin();
        expect(page.isUserNameInputBoxPresent())
        .toBeTruthy(`<input class="username" matInput [formControl]='username'> should exist in login.component.html`);
      }); 

      it('should login into the system', () => {
        page.navigateToLogin();
        page.addLoginValues();
        page.clickSubmitButton();
        expect(page.getCurrentURL()).toContain('search', 'Should navigate to dashboard');
        });


      it('should display error if login credentials are not valid', () => {
        page.navigateToLogin();
        page.addWrongLoginValues();
        page.clickSubmitButton();
        expect(page.getErrorText()).toEqual('Unauthorized- Please provide valid credentials');
      });  

  

});