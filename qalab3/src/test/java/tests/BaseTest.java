package tests;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.w3c.dom.Document;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import pages.HomePage;
import pages.LoginPage;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

public class BaseTest {


    private static ThreadLocal<WebDriver>  driverPool = new ThreadLocal<>();

    public BaseTest() throws IOException, SAXException, ParserConfigurationException {
    }




    @BeforeMethod
    public void testSetUp(){
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

    public WebDriver getDriver(){return driverPool.get();}
    public HomePage getHomePage() throws IOException { return new HomePage(getDriver()); }
    public LoginPage getLoginPage() throws IOException {return  new LoginPage(getDriver()) ;}


    private String filepath = "src/main/resources/data.xml";

    private Element getList() throws ParserConfigurationException, IOException, SAXException {
        File xmlFile = new File(filepath);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(xmlFile);
        doc.getDocumentElement().normalize();
        return  doc.getDocumentElement();

    }
    private Element nodeList =  getList();
    public String getNode(String nodeName){
        return nodeList.getElementsByTagName(nodeName).item(0).getTextContent();
    }

    @DataProvider(name = "usersAccounts", parallel = true )
    public Object[][] users() throws IOException, SAXException, ParserConfigurationException {

        NodeList usAcc = nodeList.getElementsByTagName("user");
        Object [][] arr = new Object[5][2];
        for (int temp = 0; temp < 5; temp++) {

            Node node = usAcc.item(temp);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String email = element.getElementsByTagName("email").item(0).getTextContent();
                String pass = element.getElementsByTagName("passwd").item(0).getTextContent();
                arr[temp][0] = email;
                arr[temp][1] = pass;
            }
        }
            return arr;


    }
}
