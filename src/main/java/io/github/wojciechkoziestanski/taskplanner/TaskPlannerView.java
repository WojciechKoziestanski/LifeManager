package io.github.wojciechkoziestanski.taskplanner;

import io.github.wojciechkoziestanski.core.AppModule;
import javafx.scene.Scene;

public class TaskPlannerView implements AppModule {

    private TaskPlanner taskPlanner;

    public TaskPlannerView(TaskPlanner taskPlanner){
        this.taskPlanner = taskPlanner;
    }

    @Override
    public String getName(){
        return "Task Planner";
    }

    @Override
    public Scene getScene(){
        return null;
    }
}
