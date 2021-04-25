package business;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.HomePage;

import java.io.IOException;

public class MessageActions {
    HomePage homePage;

    public MessageActions(WebDriver driver) throws IOException {
        homePage = new HomePage(driver);
    }

    public void sendNewMessage(String address, String text) {
        homePage.clickCompose();
        homePage.enterAddr(address);
        homePage.enterText(text);
        homePage.sendMessage();
        homePage.implicityWaiter();
    }

    public void checkSending() {
        homePage.openSentFolder();
        homePage.implicityWaiter();
        Assert.assertTrue(homePage.checkSending());
    }
}
