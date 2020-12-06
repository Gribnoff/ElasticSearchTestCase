package ru.gribnoff.testcase1.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.gribnoff.testcase1.entities.Product;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.List;

@Service
public class XMLService {

    private final Logger logger = LoggerFactory.getLogger(XMLService.class);

    public List<Product> parseProducts(String url) {
        List<Product> products = new ArrayList<>();

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(url);

//            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getDocumentElement().getElementsByTagName("product");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if(node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) node;
                    Product product = new Product(
                            Long.parseLong(elem.getAttribute("id")),
                            Integer.parseInt(elem.getElementsByTagName("category_id").item(0).getTextContent()),
                            elem.getElementsByTagName("name").item(0).getTextContent(),
                            elem.getElementsByTagName("description").item(0).getTextContent(),
                            Integer.parseInt(elem.getElementsByTagName("price").item(0).getTextContent()),
                            elem.getElementsByTagName("picture").item(0).getTextContent()
                    );
                    products.add(product);
                }
            }

        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }

        return products;
    }
}
