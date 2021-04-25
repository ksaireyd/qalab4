package business;


import org.openqa.selenium.WebDriver;
import pages.LoginPage;

import java.io.IOException;


public class AuthActions {
    LoginPage loginPage;

    public AuthActions(WebDriver driver) throws IOException {
        loginPage = new LoginPage(driver);
    }

    public void login(String email, String passwd) {
        loginPage.enterMail(email);
        loginPage.implicityWaiter();
        loginPage.enterPass(passwd);
    }
}