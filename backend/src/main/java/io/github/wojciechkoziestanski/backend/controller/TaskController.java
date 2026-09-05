package io.github.wojciechkoziestanski.backend.controller;

import io.github.wojciechkoziestanski.backend.model.Task;
import io.github.wojciechkoziestanski.backend.repository.TaskRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    @GetMapping
    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id){
        return taskRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Task createTask(@RequestBody Task task){
        return taskRepository.save(task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id){taskRepository.deleteById(id);}

    @GetMapping("/category/{categoryId}")
    public List<Task> getTasksByCategory(@PathVariable Long categoryId){
        return taskRepository.findByCategoryId(categoryId);
    }
}
