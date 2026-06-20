package io.github.wojciechkoziestanski;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;


public class TaskPlanner {
    @JsonIgnore
    private ObservableList<Task> tasks = FXCollections.observableArrayList();
    @JsonIgnore
    private ObservableList<Category> categories = FXCollections.observableArrayList();
    private TaskList toDoList = new TaskList();

    //tworzenie kategorii
    public boolean createCategory(String name){
        if (name == null || name.trim().isEmpty()) return false;
        String cleanedName = name.trim();
        for (Category cat : categories) {
            if (cat.getName().equalsIgnoreCase(cleanedName)) {
                return false;
            }
        }
        categories.add(new Category(cleanedName, categories.size() + 1));
        return true;
    }

    public void setDefaultCategory(){
        categories.add(new Category("Nieskategoryzowane", 0));
    }


    public void addTaskToCategory(Category targetCategory, String taskName) {
        Task newTask = new Task(targetCategory, taskName);
        targetCategory.getTasksOfCategory().add(newTask);
    }



    //gettery i settery i konstruktor
    @JsonGetter("categories")
    public ObservableList<Category> getCategories() {
        return categories;
    }
    @JsonGetter("tasks")
    public ObservableList<Task> getTasks() {return tasks;}
    public TaskPlanner(){}
    public TaskList getToDoList(){return toDoList;}
    @JsonSetter("categories")
    public void setCategories(List<Category> categoriesList){this.categories = FXCollections.observableArrayList(categoriesList);}
    @JsonSetter("tasks")
    public void setTasks(List<Task> tasksList) {
        this.tasks = FXCollections.observableArrayList(tasksList);
    }
    @JsonSetter("toDoList")
    public void setToDoList(TaskList toDoList){this.toDoList = toDoList;}
}
