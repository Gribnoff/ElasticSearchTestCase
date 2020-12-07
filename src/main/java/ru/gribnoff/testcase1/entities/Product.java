package ru.gribnoff.testcase1.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Document(indexName = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    private Long id;
    @ManyToOne
//    @Field(type = FieldType.Nested)
    private Category category;
//    private int categoryId;
    private String name;
    private String description;
    private int price;
    private String picture;
}
