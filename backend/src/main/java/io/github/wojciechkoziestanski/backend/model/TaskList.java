package io.github.wojciechkoziestanski.backend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class TaskList {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column (nullable = false, unique = true)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "task_list_tasks",
            joinColumns = @JoinColumn(name = "task_list_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id")
    )
    private List<Task> tasks;

    public TaskList(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
