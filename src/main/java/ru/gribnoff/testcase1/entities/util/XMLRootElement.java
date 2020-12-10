package ru.gribnoff.testcase1.entities.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gribnoff.testcase1.entities.Product;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "dc_catalog")
@XmlSeeAlso(Products.class)
//@XmlRootElement(name = "delivery_service")
@XmlAccessorType(XmlAccessType.FIELD)
public class XMLRootElement {
    @XmlElementWrapper(name = "products")
    @XmlElement(name = "product")
    private List<Product> products = new ArrayList<>();

//    @XmlElement(name = "products")
//    private Products products;

//    @XmlElementWrapper(name = "categories")
//    @XmlElement(name = "category")
//    private List<Category> categories = new ArrayList<>();
}
