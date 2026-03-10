package io.github.wojciechkoziestanski;

import com.fasterxml.jackson.annotation.JsonBackReference;

public class Task {

    private String name;
    @JsonBackReference
    private Category category;

    public Task (Category category, String name){
        this.category = category;
        this.name = name;
    }
    public Task(){}

    public void setCategory(Category category) {this.category = category;}
    public void setName(String name) {this.name = name;}
    public String getName(){return name;}
    public Category getCategory(){return category;}
}
