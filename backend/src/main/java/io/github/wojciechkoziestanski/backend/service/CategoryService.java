package io.github.wojciechkoziestanski.backend.service;

import io.github.wojciechkoziestanski.backend.model.Category;
import io.github.wojciechkoziestanski.backend.model.Task;
import io.github.wojciechkoziestanski.backend.repository.CategoryRepository;
import io.github.wojciechkoziestanski.backend.repository.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private final TaskRepository taskRepository;
    private final CategoryRepository categoryRepository;

    public CategoryService(TaskRepository taskRepository, CategoryRepository categoryRepository) {
        this.taskRepository = taskRepository;
        this.categoryRepository = categoryRepository;
    }

    private Category getOrCreateDefaultCategory(){
        return categoryRepository.findByName("Nieskategoryzowane").orElseGet(() -> {
            Category defaultCategory = new Category();
            defaultCategory.setName("Nieskategoryzowane");
            return categoryRepository.save(defaultCategory);
        });
    }

    public void deleteCategory(long id){
            Category category = categoryRepository.findById(id).orElseThrow();
            Category defaultCategory = getOrCreateDefaultCategory();
            for (Task task : category.getTasks()){
                task.setCategory(defaultCategory);
                taskRepository.save(task);
            }
            categoryRepository.delete(category);
    }

    public Category createCategory(String name) {
        if (name == null || name.trim().equals("")) {
            throw new IllegalArgumentException("Nazwa kategorii nie może być pusta");
        }
        String trimName = name.trim();
        if (categoryRepository.existsByNameIgnoreCase(trimName)){
            throw new IllegalArgumentException("Katergoria o tej nazwie istnieje");
        }
        Category category = new Category();
        category.setName(trimName);
        return categoryRepository.save(category);

    }
}
