package com.fast.eatgo.application;

import com.fast.eatgo.domain.Category;
import com.fast.eatgo.domain.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

public class CategoryServiceTest {

    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        categoryService = new CategoryService(categoryRepository);
    }

    @Test
    public void getCategory() {
        List<Category> mockCategories = new ArrayList<>();
        mockCategories.add(Category.builder().name("Korean Food").build());

        given(categoryRepository.findAll()).willReturn(mockCategories);

        List<Category> categories = categoryService.getCategory();

        Category category = categories.get(0);

        assertThat(category.getName(), is("Korean Food"));
    }
}