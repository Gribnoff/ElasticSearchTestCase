package ru.gribnoff.testcase1.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gribnoff.testcase1.entities.Category;
import ru.gribnoff.testcase1.repositories.CategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public void save(Iterable<Category> category) {
        category.forEach(categoryRepository::save);
    }

    public void delete(Category category) {
        categoryRepository.delete(category);
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow();
    }

    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }

    public List<Category> findByName(String name) {
        return categoryRepository.findByName(name);
    }
}
