package io.github.wojciechkoziestanski.backend.controller;

import io.github.wojciechkoziestanski.backend.model.Category;
import io.github.wojciechkoziestanski.backend.repository.CategoryRepository;
import io.github.wojciechkoziestanski.backend.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;

    public CategoryController(CategoryRepository categoryRepository, CategoryService categoryService){
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;

    }

    @GetMapping
    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity <Category> getCategoryById(@PathVariable Long id) {
        return categoryRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Category createCategory(@RequestBody Category category){
        return categoryService.createCategory(category.getName());
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
    }
}
