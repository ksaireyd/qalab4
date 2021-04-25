package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;

public class LoginPage extends BasePage {

    @FindBy(id = "identifierId")
    private WebElement mailField;
    @FindBy(name = "password")
    private WebElement passField;
    public LoginPage(WebDriver driver) throws IOException {
        super(driver);
    }

    public void enterMail(String emai) {
        mailField.sendKeys(emai, Keys.ENTER);
    }

    public void enterPass(String pass) {
        waitVisibility(passField);
        passField.sendKeys(pass, Keys.ENTER);
    }
}
