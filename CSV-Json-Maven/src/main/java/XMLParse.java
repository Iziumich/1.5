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
import java.util.ArrayList;
import java.util.List;

public class XMLParse {
    public static List<Employee> parseXML(String fileName) {
        List<Employee> list = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(fileName));
            Node root = doc.getDocumentElement();
            NodeList nodes = root.getChildNodes();

            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    Employee employee = new Employee();
                    employee.id = Long.parseLong(element.getElementsByTagName("id").item(0).getTextContent());
                    employee.firstName = element.getElementsByTagName("firstName").item(0).getTextContent();
                    employee.lastName = element.getElementsByTagName("lastName").item(0).getTextContent();
                    employee.country = element.getElementsByTagName("country").item(0).getTextContent();
                    employee.age = Integer.parseInt(element.getElementsByTagName("age").item(0).getTextContent());
                    list.add(employee);
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        return list;
    }
}
