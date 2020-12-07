package ru.gribnoff.testcase1.services;

import lombok.RequiredArgsConstructor;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import ru.gribnoff.testcase1.entities.Product;
import ru.gribnoff.testcase1.repositories.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

import static org.elasticsearch.index.query.QueryBuilders.*;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ElasticsearchRestTemplate esRestTemplate;

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void save(Iterable<Product> products) {
        products.forEach(productRepository::save);
    }

    public void delete(Product product) {
        productRepository.delete(product);
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    public List<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

    public List<Product> findProducts(String keyword, int categoryId) {
        final NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(nestedQuery("category", boolQuery().must(termQuery("category.id", categoryId)), ScoreMode.Total))
                .withQuery((boolQuery()
                                .must(matchQuery("category.id", categoryId))
                                .must(multiMatchQuery(keyword)
                                        .field("name").field("description")
                                        .type(MultiMatchQueryBuilder.Type.BEST_FIELDS))))
                .build();

        return convertSearchHitsToList(esRestTemplate.search(searchQuery, Product.class));
    }

    private List<Product> convertSearchHitsToList(SearchHits<Product> searchHits) {
        return searchHits.stream().map(SearchHit::getContent).collect(Collectors.toList());
    }
}
