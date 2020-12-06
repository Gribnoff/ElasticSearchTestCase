package ru.gribnoff.testcase1.controllers;

import lombok.RequiredArgsConstructor;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.web.bind.annotation.*;
import ru.gribnoff.testcase1.entities.Product;
import ru.gribnoff.testcase1.services.XMLService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/rest")
public class ProductController {
    private final RestHighLevelClient restHighLevelClient;
    private final ElasticsearchOperations elasticsearchOperations;
    private final XMLService xmlService;

    @GetMapping("/health")
    public String index() {
        return "Spring Boot OK!";
    }

    private List<Product> parseXML(String url) {
        return xmlService.parseProducts(url);
    }

    @GetMapping("/index")
    public Iterable<Product> indexParsed(@RequestParam String url) {
        return elasticsearchOperations.save(parseXML(url));
    }

    @GetMapping("/product/{id}")
    public Product findOne(@PathVariable("id") Long id) {
        return elasticsearchOperations.get(id.toString(), Product.class);
    }
}
