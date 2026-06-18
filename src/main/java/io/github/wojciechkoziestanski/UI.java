package io.github.wojciechkoziestanski;

import io.github.palexdev.materialfx.controls.MFXListView;
import javafx.application.Application;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.shape.SVGPath;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Scene;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.scene.paint.Color;
import java.util.Optional;

public class UI extends Application {
    private TaskPlanner taskPlanner;

    @Override
    public void start(Stage primaryStage) {
        JsonStorage storage = new JsonStorage();
        this.taskPlanner = storage.load();
        if (this.taskPlanner == null) {
            this.taskPlanner = new TaskPlanner();
            this.taskPlanner.setDefaultCategory();
        }

        //narzedzia globalne
        //ikona back
        SVGPath backIcon = new SVGPath();
        backIcon.setContent("M15 0 L0 10 L15 20");
        backIcon.setStroke(Color.BLACK);
        backIcon.setStrokeWidth(3);
        backIcon.setFill(null);
        //back button
        MFXButton backButton = new MFXButton();
        backButton.setGraphic(backIcon);
        backButton.setStyle("-fx-background-color: transparent;");



        //choice page
        GridPane choicePage = new GridPane(); //kontener aplikacji
        choicePage.setHgap(10); // odstęp HORYZONTALNY między elementami
        choicePage.setVgap(10); // odstęp VERTYKALNY miedzy elementami

        Label choicePageLabel = new Label("Witaj w Life Manager!");
        choicePage.getChildren().add(choicePageLabel);

        primaryStage.setTitle("Life Manager"); //nazwa na pasku zadan

        Button taskButton = new Button("Task Planner");
        taskButton.setPrefWidth(630);
        taskButton.setPrefHeight(200);
        Button budgetButton = new Button("Budget Planner");
        budgetButton.setPrefWidth(630);
        budgetButton.setPrefHeight(200);
        Button dreamButton = new Button("Dream Planner");
        dreamButton.setPrefWidth(630);
        dreamButton.setPrefHeight(200);
        Button emptyButton = new Button("empty");
        emptyButton.setPrefWidth(630);
        emptyButton.setPrefHeight(200);

        //przyciski w siatce: kolumna, wiersz
        choicePage.add(taskButton, 0, 0);
        choicePage.add(budgetButton, 1, 0);
        choicePage.add(dreamButton, 0, 1);
        choicePage.add(emptyButton, 2, 0);

        //Scena jest dopasowana do ekranu screen.getprimary.getbounds to zwroc ekran->zwroc prostokat->o wymiarach
        Scene choicePageScene = new Scene(choicePage, Screen.getPrimary().getBounds().getWidth(),
                                      Screen.getPrimary().getBounds().getHeight());
        primaryStage.setScene(choicePageScene);



        // TASK PLANNER SCENE
        //root panel
        BorderPane taskPlannerPage = new BorderPane();
        //scena
        Scene taskPlannerScene = new Scene(taskPlannerPage, Screen.getPrimary().getBounds().getWidth(),
                                            Screen.getPrimary().getBounds().getHeight());
        //label
        Label taskPlannerLabel = new Label("Task planner!");
        taskPlannerLabel.setStyle(
                "-fx-font-size: 28px; " +
                        "-fx-font-weight: bold; " +
                        "-fx-text-fill: #333; " +
                        "-fx-font-family: 'Segoe UI', Arial;"
        );

        Label tasksHeader = new Label("Taski");
        tasksHeader.setMaxWidth(Double.MAX_VALUE);
        tasksHeader.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-padding: 0 0 10 0; -fx-alignment: center");

        Label categoryHeader = new Label("Kategoria");
        categoryHeader.setMaxWidth(Double.MAX_VALUE);
        categoryHeader.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-padding: 0 0 10 0; -fx-alignment: center");

        Label taskListHeader = new Label("Lista zadan");
        taskListHeader.setMaxWidth(Double.MAX_VALUE);
        taskListHeader.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-padding: 0 0 10 0; -fx-alignment: center");


        //buttons

        Button addCatQuickButton = new Button("+");
        addCatQuickButton.setStyle("-fx-background-radius: 20; -fx-min-width: 30px;");

        Button addTaskQuickButton = new Button("+");
        addTaskQuickButton.setStyle("-fx-background-radius: 20; -fx-min-width: 30px;");


        //top panel
        BorderPane topBar = new BorderPane();
        HBox leftButtons = new HBox(15);
        leftButtons.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        leftButtons.setPadding(new javafx.geometry.Insets(10, 0, 10, 20));
        Button extraFuncButton = new Button("F");
        leftButtons.getChildren().addAll(backButton, extraFuncButton);
        topBar.setLeft(leftButtons);
        topBar.setCenter(taskPlannerLabel);
        taskPlannerPage.setTop(topBar);
        topBar.setStyle("-fx-background-color: #f8f9fa; -fx-border-color: #dee2e6; -fx-border-width: 0 0 2 0;");

        //main panel
        GridPane mainPanel = new GridPane();
        mainPanel.setHgap(20);
        mainPanel.setPadding(new javafx.geometry.Insets(20));


        //coulmn constraints - metoda ktora pozwala manipulowac wewnetrznymi zasadami kolumn gridpane
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(33.3);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(33.3);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(33.3);
        mainPanel.getColumnConstraints().addAll(col1, col2, col3);

        VBox tasksView = new VBox();
            HBox tasksHeaderBox = new HBox(10);
            HBox.setHgrow(tasksHeader, Priority.ALWAYS);
            tasksHeader.setMaxWidth(Double.MAX_VALUE);
            tasksHeaderBox.getChildren().addAll(tasksHeader, addTaskQuickButton);

        MFXListView<Task> taskList = new MFXListView<>();
        taskList.setItems(taskPlanner.getTasks());
        VBox.setVgrow(taskList, Priority.ALWAYS);
        taskList.setMaxWidth(Double.MAX_VALUE);
        taskList.setMaxHeight(Double.MAX_VALUE);
        taskList.setPrefHeight(Control.USE_COMPUTED_SIZE);
        tasksView.getChildren().clear();
        tasksView.getChildren().addAll(tasksHeaderBox, taskList);

        VBox catView = new VBox();
            HBox catHeaderBox = new HBox(10);
            HBox.setHgrow(categoryHeader, Priority.ALWAYS);
            categoryHeader.setMaxWidth(Double.MAX_VALUE);
            catHeaderBox.getChildren().addAll(categoryHeader, addCatQuickButton);

        MFXListView<Category> categoryList = new MFXListView<>();
        categoryList.setItems(taskPlanner.getCategories());
        VBox.setVgrow(categoryList, Priority.ALWAYS);
        categoryList.setMaxWidth(Double.MAX_VALUE);
        categoryList.setMaxHeight(Double.MAX_VALUE);
        catView.getChildren().clear();
        catView.getChildren().addAll(catHeaderBox ,categoryList);

        VBox taskListView = new VBox();
            HBox taskListHeaderBox = new HBox(10);
            HBox.setHgrow(taskListHeaderBox, Priority.ALWAYS);
            taskListHeaderBox.setMaxWidth(Double.MAX_VALUE);
            taskListHeaderBox.getChildren().addAll(taskListHeader);
        MFXListView<Task> toDoList = new MFXListView<>();
        toDoList.setItems(taskPlanner.getToDoList().getTaskList());
        VBox.setVgrow(toDoList, Priority.ALWAYS);
        toDoList.setMaxWidth(Double.MAX_VALUE);
        toDoList.setMaxHeight(Double.MAX_VALUE);
        taskListView.getChildren().clear();
        taskListView.getChildren().addAll(taskListHeaderBox, toDoList);


        /*
        „OBIEKCIE CATEGORY LIST” – Hej, Ty, biały prostokącie po prawej!
        „ODWOŁUJĘ SIĘ DO TWOJEGO SELECTION MODEL” – Daj mi pogadać z Twoim „mózgiem od kliknięć” (tak, domyślnie pozwala na jedno zaznaczenie, choć można go przestawić na wiele).
        „SELECTION PROPERTY” – Chcę obserwować konkretnie cechę pod tytułem: „co jest teraz podświetlone”.
        „TWORZĘ TUTAJ CZUJKĘ” – I jak tylko ta cecha drgnie (ktoś kliknie myszką), to masz natychmiast wykonać to, co Ci napisałem w lambdzie
         */

        categoryList.getSelectionModel().selectionProperty().addListener((observable, oldSelection, newSelection)-> {
            if (newSelection != null && !newSelection.isEmpty()) {
                Category selected = newSelection.values().stream().findFirst().orElse(null);
                if (selected != null) {
                    taskList.setItems(selected.getTasksOfCategory());
                }
            }
        });

        //DODAWANIE DO SIATKI
        mainPanel.add(tasksView, 1, 0);
        mainPanel.add(catView, 2, 0);
        mainPanel.add(taskListView, 0, 0);

        taskPlannerPage.setCenter(mainPanel);
        GridPane.setVgrow(tasksView, Priority.ALWAYS);











        //akcje na klawiszach
        taskButton.setOnAction(e -> primaryStage.setScene(taskPlannerScene));
        backButton.setOnAction(e -> primaryStage.setScene(choicePageScene));
            //dodawanie taska
        addTaskQuickButton.setOnAction(e -> {
                    Optional<DialogHelper.TaskResult> result = DialogHelper.addNewTaskLogicDialog(taskPlanner.getCategories());
                    result.ifPresent(taskData -> {
                        if (taskData.name != null && !taskData.name.trim().isEmpty()) {
                            taskPlanner.addTaskToCategory(taskData.category, taskData.name);
                        }
                    });
                });

            //dodawanie kategorii
        addCatQuickButton.setOnAction(e -> {
            String catNameInput = DialogHelper.addNewCatLogicDialog();
            if (catNameInput != null) {
                boolean success = taskPlanner.createCategory(catNameInput);
                if (!success) {
                    System.out.println("Błąd! Nie udało się dodać kategorii: ");
                }
            }
        });

        //dodawanie taska do listy zadan
        taskList.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2){
                Task selected = taskList.getSelectionModel().getSelectedValues().stream().findFirst().orElse(null);
                if (selected != null){
                    taskPlanner.getToDoList().getTaskList().add(selected);
                }
            }
        });
        //usuwanie taska z listy
        toDoList.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2){
                Task selected = toDoList.getSelectionModel().getSelectedValues().stream().findFirst().orElse(null);
                if (selected != null){
                    taskPlanner.getToDoList().getTaskList().remove(selected);
                }
            }
        });

        //rightClick menu

        ContextMenu rightClickMenuCat = new ContextMenu();
            MenuItem editItemCat = new MenuItem("Edytuj");
            MenuItem deleteItemCat = new MenuItem("Usuń");
                rightClickMenuCat.getItems().addAll(editItemCat, deleteItemCat);
                    categoryList.setContextMenu(rightClickMenuCat);

        ContextMenu rightClickMenuTask = new ContextMenu();
            MenuItem editItemTask = new MenuItem("Edytuj");
            MenuItem deleteItemTask = new MenuItem("Usuń");
                rightClickMenuTask.getItems().addAll(editItemTask, deleteItemTask);
                    taskList.setContextMenu(rightClickMenuTask);



        primaryStage.setOnCloseRequest(event -> {
            storage.save(taskPlanner);
            new DatabaseCommands().save(taskPlanner);
            javafx.application.Platform.exit();
        });

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
