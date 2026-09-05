package io.github.wojciechkoziestanski.backend.controller;

import io.github.wojciechkoziestanski.backend.model.TaskList;
import io.github.wojciechkoziestanski.backend.repository.TaskListRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasklists")
public class TaskListController {
    private final TaskListRepository taskListRepository;

    public TaskListController(TaskListRepository taskListRepository){
        this.taskListRepository = taskListRepository;
    }

    @GetMapping
    public List<TaskList> getAllTaskLists(){
        return taskListRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskList> getTaskListById(@PathVariable Long id){
        return taskListRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public TaskList createTaskList(@RequestBody TaskList taskList){
        return taskListRepository.save(taskList);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id){
        taskListRepository.deleteById(id);
    }


}
