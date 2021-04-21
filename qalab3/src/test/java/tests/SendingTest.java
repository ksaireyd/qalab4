package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import org.w3c.dom.Element;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class SendingTest extends BaseTest {
    public SendingTest() throws IOException, SAXException, ParserConfigurationException {
    }
    final String MAIL = getNode("email");
    final String PASS = getNode("passwd");
    final String ADDR = getNode("sendto");
    final String TEXT = getNode("messText");



    @Test(dataProvider = "usersAccounts")
    public void sendingTest(String email,String passwd) throws IOException {
       getLoginPage().enterMail(email);
       getLoginPage().implicityWaiter();
       getLoginPage().enterPass(passwd);
       getHomePage().clickCompose();
       getHomePage().enterAddr(ADDR);
       getHomePage().enterText(TEXT);
       getHomePage().sendMess();
        getHomePage().implicityWaiter();
       getHomePage().openSentFolder();
       getHomePage().implicityWaiter();
       Assert.assertTrue(getHomePage().checkSending());
        getHomePage().removeMess();
    }



}
