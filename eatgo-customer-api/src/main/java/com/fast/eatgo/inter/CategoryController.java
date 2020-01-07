package com.fast.eatgo.inter;

import com.fast.eatgo.application.CategoryService;
import com.fast.eatgo.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public List<Category> list() {
        return categoryService.getCategory();
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody Category resource) throws URISyntaxException {
        categoryService.addCategory(resource.getName());
        String url = "/category/" + resource.getId();
        return ResponseEntity.created(new URI(url)).body("{}");
    }
}
