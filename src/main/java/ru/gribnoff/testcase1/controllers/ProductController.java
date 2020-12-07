package ru.gribnoff.testcase1.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.web.bind.annotation.*;
import ru.gribnoff.testcase1.entities.Product;
import ru.gribnoff.testcase1.services.XMLFacade;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/rest")
public class ProductController {
//    private final RestHighLevelClient restHighLevelClient;
    private final ElasticsearchOperations elasticsearchOperations;
    private final XMLFacade xmlFacade;

    @GetMapping("/health")
    public String index() {
        return "Spring Boot OK!";
    }

    @GetMapping("/index")
    public Iterable<Product> indexXML(@RequestParam String url) {
        return xmlFacade.parseXML(url);
    }

    @GetMapping("deleteIndex")
    public void deleteProductIndex() {
        elasticsearchOperations.indexOps(Product.class).delete();
    }

    @GetMapping("/product/{id}")
    public Product findOne(@PathVariable("id") Long id) {
        return elasticsearchOperations.get(id.toString(), Product.class);
    }

    @GetMapping("/find")
    public List<Product> find(@RequestParam String keyword, @RequestParam int categoryId) {
        return xmlFacade.findProducts(keyword, categoryId);
    }
}
