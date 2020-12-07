package ru.gribnoff.testcase1.repositories;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import ru.gribnoff.testcase1.entities.Product;

import java.util.List;

public interface ProductRepository extends ElasticsearchRepository<Product, Long> {
    List<Product> findByName(String name);

//    Product findById(Long id);
}
