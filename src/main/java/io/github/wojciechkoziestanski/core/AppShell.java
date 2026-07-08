package io.github.wojciechkoziestanski.core;

import io.github.wojciechkoziestanski.taskplanner.TaskPlanner;
import io.github.wojciechkoziestanski.taskplanner.TaskPlannerView;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class AppShell {
    private Stage primaryStage;
    private TaskPlanner taskPlanner;

    public AppShell(Stage primaryStage, TaskPlanner taskPlanner) {
        this.primaryStage = primaryStage;
        this.taskPlanner = taskPlanner;
    }

    public void show() {
        primaryStage.setTitle("Life Manager");
        primaryStage.setScene(buildChoiceScene());
        primaryStage.show();
    }

    private Scene buildChoiceScene() {
        GridPane choicePage = new GridPane();
        choicePage.setHgap(10);
        choicePage.setVgap(10);

        Label label = new Label("Witaj w Life Manager!");
        choicePage.add(label, 0, 0);

        Button taskButton = new Button("Task Planner");
        taskButton.setPrefWidth(630);
        taskButton.setPrefHeight(200);
        choicePage.add(taskButton, 0, 1);

        // po kliknięciu przełącz na scenę task plannera
        taskButton.setOnAction(e -> {
            TaskPlannerView view = new TaskPlannerView(taskPlanner);
            primaryStage.setScene(view.getScene());
        });

        return new Scene(choicePage,
                Screen.getPrimary().getVisualBounds().getWidth(),
                Screen.getPrimary().getVisualBounds().getHeight());
    }
}