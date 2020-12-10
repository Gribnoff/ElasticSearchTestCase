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
//@XmlRootElement(name = "delivery_service")
//@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class Products {
    @XmlElementWrapper(name = "products")
    @XmlElement(name = "product")
    private List<Product> products = new ArrayList<>();
}
