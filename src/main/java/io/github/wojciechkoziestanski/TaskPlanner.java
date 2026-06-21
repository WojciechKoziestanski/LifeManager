package io.github.wojciechkoziestanski;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;




public class TaskPlanner {
    private ObservableList<Task> tasks = FXCollections.observableArrayList();
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
    public ObservableList<Category> getCategories() {
        return categories;
    }
    public ObservableList<Task> getTasks() {return tasks;}
    public TaskPlanner(){}
    public TaskList getToDoList(){return toDoList;}
    public void setToDoList(TaskList toDoList){this.toDoList = toDoList;}
}
