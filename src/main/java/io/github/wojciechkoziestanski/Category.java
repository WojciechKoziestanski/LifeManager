package io.github.wojciechkoziestanski;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSetter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class Category {
    private String name;
    private int id;
    @JsonManagedReference
    ObservableList<Task> tasksOfCategory = FXCollections.observableArrayList();


    public Category(String name, int id){
        this.name = name;
        this.id = id;
    }
    public Category(){}
    public String getName() {
        return name;
    }
    public ObservableList<Task> getTasksOfCategory() {return tasksOfCategory;}
    public void setName(String name) {this.name = name;}
    @JsonSetter("tasksOfCategory")
    public void setTasksOfCategory(List<Task> tasksList) {
        if (tasksList != null) {
            this.tasksOfCategory = FXCollections.observableArrayList(tasksList);
        }
    }
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
