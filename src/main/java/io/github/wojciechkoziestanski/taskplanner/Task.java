package io.github.wojciechkoziestanski.taskplanner;

public class Task {

    private String name;
    private Category category;

    @Override
    public String toString() {
        return name;
    }

    public Task (Category category, String name){
        this.category = category;
        this.name = name;
    }
    public void setCategory(Category category) {this.category = category;}
    public void setName(String name) {this.name = name;}
    public String getName(){return name;}
    public Category getCategory(){return category;}
}
