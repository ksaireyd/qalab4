package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;
import java.util.List;

public class HomePage extends BasePage {

    @FindBy(xpath = "//div[@class='T-I T-I-KE L3']")
    private WebElement composeButt;
    @FindBy(css = "textarea.vO")
    private WebElement sendToField;
    @FindBy(css = "div.Am.Al.editable.LW-avf.tS-tW")
    private WebElement textArea;
    @FindBy(css = "div.T-I.J-J5-Ji.aoO.v7.T-I-atl.L3")
    private WebElement sendButt;
    @FindBy(css = "input.gb_gf")
    private WebElement searchField;
    @FindBy(css = "span.bAq")
    private WebElement confirmation;
    @FindBy(xpath = "//table[@role='grid']/tbody/tr")
    private List<WebElement> messagesList;
    @FindBy(xpath = "//li[@class='bqX bru']")
    private WebElement delButt;
    @FindBy(xpath = "//div[@role='checkbox']")
    private WebElement checkBox;
    public HomePage(WebDriver driver) throws IOException {
        super(driver);
    }

    public void clickCompose() {
        waitVisibility(composeButt);
        composeButt.click();
    }

    public void enterAddr(String addr) {
        sendToField.sendKeys(addr);
    }

    public void enterText(String text) {
        textArea.sendKeys(text);
    }

    public void sendMessage() {
        sendButt.click();
        waitVisibility(confirmation);
    }

    public void openSentFolder() {
        searchField.sendKeys("in:sent", Keys.ENTER);
    }

    public boolean checkSending() {
        waitVisibility(messagesList.get(0));
        return messagesList.size() > 0;
    }

    public void removeMessage() {
        waitVisibility(checkBox);
        checkBox.click();
        waitClick(delButt);
        delButt.click();

    }
}
