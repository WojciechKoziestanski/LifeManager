package io.github.wojciechkoziestanski.backend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Task {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToMany(mappedBy = "tasks")
    private List<TaskList> taskLists;


    public Task(){}

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setTaskLists(List<TaskList> taskLists){
        this.taskLists = taskLists;
    }

    public List<TaskList> getTaskLists() {
        return taskLists;
    }
}
