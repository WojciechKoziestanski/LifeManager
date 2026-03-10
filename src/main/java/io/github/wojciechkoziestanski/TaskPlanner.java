package io.github.wojciechkoziestanski;

import com.fasterxml.jackson.annotation.JsonSetter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;


public class TaskPlanner {
    ObservableList<Task> tasks = FXCollections.observableArrayList();
    ObservableList<Category> categories = FXCollections.observableArrayList();

    //tworzenie kategorii
    public boolean createCategory(String name){
        if (name == null || name.trim().isEmpty()) return false;
        String cleanedName = name.trim();
        for (Category cat : categories) {
            if (cat.getName().equalsIgnoreCase(cleanedName)) {
                return false;
            }
        }
        categories.add(new Category(cleanedName));
        return true;
    }

    public void setDefaultCategory(){
        categories.add(new Category("Nieskategoryzowane"));
    }


    public void addTaskToCategory(Category targetCategory, String taskName) {
        Task newTask = new Task(targetCategory, taskName);
        targetCategory.getTasksOfCategory().add(newTask);
    }



    //gettery i settery i konstruktor
    public ObservableList<Category> getCategories() {
        return categories;
    }
    public ObservableList<Task> getTasks() {return tasks;}
    public TaskPlanner(){}
    @JsonSetter("categories")
    public void setCategories(List<Category> categoriesList){
        this.categories = FXCollections.observableArrayList(categoriesList);
    }
    @JsonSetter("tasks")
    public void setTasks(List<Task> tasksList) {
        this.tasks = FXCollections.observableArrayList(tasksList);
    }
}
