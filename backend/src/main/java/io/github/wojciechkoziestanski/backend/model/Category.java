package io.github.wojciechkoziestanski.backend.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    //@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    //private List<Task> tasks;

    public Category(){};

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

}
