package ru.gribnoff.testcase1.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gribnoff.testcase1.entities.Product;
import ru.gribnoff.testcase1.repositories.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

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
}
