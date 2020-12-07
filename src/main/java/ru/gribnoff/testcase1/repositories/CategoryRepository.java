package ru.gribnoff.testcase1.repositories;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import ru.gribnoff.testcase1.entities.Category;

import java.util.List;

public interface CategoryRepository extends ElasticsearchRepository<Category, Long> {
    List<Category> findByName(String name);
}
