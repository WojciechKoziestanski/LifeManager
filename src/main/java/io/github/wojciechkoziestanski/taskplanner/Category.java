package io.github.wojciechkoziestanski.taskplanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Category {
    private String name;
    private int id;
    ObservableList<Task> tasksOfCategory = FXCollections.observableArrayList();


    public Category(String name, int id){
        this.name = name;
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public ObservableList<Task> getTasksOfCategory() {return tasksOfCategory;}
    public void setName(String name) {this.name = name;}

    @Override
    public String toString() {
        return name;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
