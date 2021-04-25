package data;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class DataReader {

    private final String filepath = "src/main/resources/data.xml";
    private final Element nodeList = this.getList();

    public DataReader() throws IOException, SAXException, ParserConfigurationException {
    }

    private Element getList() throws ParserConfigurationException, IOException, SAXException {
        File xmlFile = new File(filepath);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(xmlFile);
        doc.getDocumentElement().normalize();
        return doc.getDocumentElement();

    }

    private String getNode(String nodeName) {
        return nodeList.getElementsByTagName(nodeName).item(0).getTextContent();
    }

    public String getAddress() {
        return getNode("sendto");
    }

    public String getTextLetter() {
        return getNode("messText");
    }

    public Object[][] users() throws IOException, SAXException, ParserConfigurationException {
        NodeList usAcc = nodeList.getElementsByTagName("user");
        Object[][] arr = new Object[5][2];
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
