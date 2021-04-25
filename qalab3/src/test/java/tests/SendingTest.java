package tests;

import business.AuthActions;
import business.MessageActions;
import data.DataReader;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class SendingTest extends BaseTest {
    DataReader dataReader = new DataReader();
    final String ADDR = dataReader.getAddress();
    final String TEXT = dataReader.getTextLetter();

    public SendingTest() throws IOException, SAXException, ParserConfigurationException {
    }

    @Test(dataProvider = "usersAccounts")
    public void sendingTest(String email, String passwd) throws IOException {
        AuthActions authActions = new AuthActions(getDriver());
        MessageActions messageActions = new MessageActions(getDriver());

        authActions.login(email, passwd);

        messageActions.sendNewMessage(ADDR, TEXT);

        messageActions.checkSending();

        getHomePage().removeMessage();


    }


}
