package ru.gribnoff.testcase1.services;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.gribnoff.testcase1.entities.Category;
import ru.gribnoff.testcase1.entities.Product;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class XMLFacade {
    private final Logger logger = LoggerFactory.getLogger(XMLFacade.class);

    private final ProductService productService;
    private final CategoryService categoryService;

    public List<Product> parseXML(String url) {
        List<Product> products = new ArrayList<>();

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(url);

//            doc.getDocumentElement().normalize();

            indexCategories(doc);

            products = indexProducts(doc);

        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }

        return products;
    }

    private void indexCategories(Document doc) {
        NodeList categoryNodeList = doc.getDocumentElement().getElementsByTagName("category");
        for (int i = 0; i < categoryNodeList.getLength(); i++) {
            Node node = categoryNodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) node;
                Category category = new Category(
                        Long.parseLong(elem.getAttribute("id")),
                        elem.getTextContent()
                );
                categoryService.save(category);
            }
        }
    }

    private List<Product> indexProducts(Document doc) {
        List<Product> products = new ArrayList<>();
        NodeList productNodeList = doc.getDocumentElement().getElementsByTagName("product");

        for (int i = 0; i < productNodeList.getLength(); i++) {
            Node node = productNodeList.item(i);

            if(node.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) node;
                Product product = new Product(
                        Long.parseLong(elem.getAttribute("id")),
                        categoryService.findById(Long.parseLong(elem.getElementsByTagName("category_id").item(0).getTextContent())),
                        elem.getElementsByTagName("name").item(0).getTextContent(),
                        elem.getElementsByTagName("description").item(0).getTextContent(),
                        Integer.parseInt(elem.getElementsByTagName("price").item(0).getTextContent()),
                        elem.getElementsByTagName("picture").item(0).getTextContent()
                );

                productService.save(product);
                products.add(product);
            }
        }
        return products;
    }
}
