package io.github.wojciechkoziestanski;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TaskList {
    private String name;
    private ObservableList<Task> taskList = FXCollections.observableArrayList();

    public TaskList(String name){
        this.name = name;
    }
    public TaskList(){}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public ObservableList<Task> getTaskList() {return taskList;}


    @Override
    public String toString() {return name;}


}
