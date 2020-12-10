package ru.gribnoff.testcase1.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@Entity
@Document(indexName = "product")
//@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @XmlAttribute
    private Long id;
    @ManyToOne
//    @Field(type = FieldType.Nested)
    @XmlElement
    private Category category;
//    private int categoryId;
    @XmlElement
    private String name;
    @XmlElement
    private String description;
    @XmlElement
    private int price;
    @XmlElement
    private String picture;
}
