package tests;


import data.DataReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.xml.sax.SAXException;
import pages.HomePage;
import pages.LoginPage;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class BaseTest {


    private static final ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();
    public DataReader dataReader = new DataReader();


    public BaseTest() throws IOException, SAXException, ParserConfigurationException {
    }

    @BeforeMethod
    public void testSetUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driverPool.set(driver);
        driver.get("https://accounts.google.com/signin/v2/identifier?continue=https%3A%2F%2Fmail.google.com");
    }

    @AfterMethod
    public void tearDown() {
        driverPool.get().close();
    }

    public WebDriver getDriver() {
        return driverPool.get();
    }

    public HomePage getHomePage() throws IOException {
        return new HomePage(getDriver());
    }

    public LoginPage getLoginPage() throws IOException {
        return new LoginPage(getDriver());
    }

    @org.testng.annotations.DataProvider(name = "usersAccounts", parallel = true)
    public Object[][] getData() throws ParserConfigurationException, SAXException, IOException {
        return dataReader.users();
    }


}
