package io.github.wojciechkoziestanski;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Category {
    private String name;
    ObservableList<Task> tasksOfCategory = FXCollections.observableArrayList();


    public Category(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ObservableList<Task> getTasksOfCategory() {
        return tasksOfCategory;
    }

    @Override
    public String toString() {
        return name;
    }
}
