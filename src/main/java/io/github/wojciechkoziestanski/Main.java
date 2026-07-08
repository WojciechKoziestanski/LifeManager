package io.github.wojciechkoziestanski;

import io.github.wojciechkoziestanski.core.AppShell;
import io.github.wojciechkoziestanski.database.DatabaseConnector;
import io.github.wojciechkoziestanski.taskplanner.DatabaseCommands;
import io.github.wojciechkoziestanski.taskplanner.TaskPlanner;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        TaskPlanner taskPlanner = new TaskPlanner();
        DatabaseConnector.initDatabase();
        new DatabaseCommands().load(taskPlanner);
        if (taskPlanner.getCategories().isEmpty()) {
            taskPlanner.setDefaultCategory();
        }
        primaryStage.setOnCloseRequest(event -> {
            new DatabaseCommands().save(taskPlanner);
            javafx.application.Platform.exit();
        });
        new AppShell(primaryStage, taskPlanner).show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

