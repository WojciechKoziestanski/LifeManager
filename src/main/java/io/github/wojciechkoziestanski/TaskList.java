package io.github.wojciechkoziestanski;

import com.fasterxml.jackson.annotation.JsonSetter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class TaskList {
    private String name;
    private ObservableList<Task> taskList = FXCollections.observableArrayList();

    public TaskList(String name){
        this.name = name;
    }

    public TaskList(){};
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public ObservableList<Task> getTaskList() {return taskList;}
    @JsonSetter("tasks")
    public void setTasks(List<Task> tasksList) {
        this.taskList = FXCollections.observableArrayList(tasksList);
    }
    @Override
    public String toString() {return name;}

}
