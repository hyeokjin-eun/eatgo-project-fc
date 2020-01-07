package com.fast.eatgo.application;

import com.fast.eatgo.domain.Category;
import com.fast.eatgo.domain.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getCategory() {
        return categoryRepository.findAll();
    }

    public Category addCategory(String name) {
        Category category = Category.builder()
                .name(name)
                .build();

        categoryRepository.save(category);

        return category;
    }
}
