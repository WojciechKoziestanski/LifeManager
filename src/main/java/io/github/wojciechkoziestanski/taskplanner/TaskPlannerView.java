package io.github.wojciechkoziestanski.taskplanner;

import io.github.palexdev.materialfx.controls.MFXListView;
import io.github.wojciechkoziestanski.core.AppModule;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.Optional;

public class TaskPlannerView implements AppModule {

    private TaskPlanner taskPlanner;
    private Stage stage;

    public TaskPlannerView(TaskPlanner taskPlanner){
        this.taskPlanner = taskPlanner;
    }

    @Override
    public String getName(){
        return "Task Planner";
    }

    @Override
    public Scene getScene(){

        BorderPane taskPlannerPage = new BorderPane();
        // scena
        Scene taskPlannerScene = new Scene(taskPlannerPage, Screen.getPrimary().getVisualBounds().getWidth(),
                Screen.getPrimary().getVisualBounds().getHeight());
        // label
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

        // buttons
        Button addCatQuickButton = new Button("+");
        addCatQuickButton.setStyle("-fx-background-radius: 20; -fx-min-width: 30px;");

        Button addTaskQuickButton = new Button("+");
        addTaskQuickButton.setStyle("-fx-background-radius: 20; -fx-min-width: 30px;");

        SVGPath pdfIcon = new SVGPath();
        pdfIcon.setContent("M19 3H5C3.9 3 3 3.9 3 5V19C3 20.1 3.9 21 5 21H19C20.1 21 21 20.1 21 19V5C21 3.9 20.1 3 19 3M9.5 11.5C9.5 12.3 8.8 13 8 13H7V15H5.5V9H8C8.8 9 9.5 9.7 9.5 10.5V11.5M14.5 13.5C14.5 14.3 13.8 15 13 15H10.5V9H13C13.8 9 14.5 9.7 14.5 10.5V13.5M18.5 10.5H17V11.5H18.5V13H17V15H15.5V9H18.5V10.5M12 10.5H13V13.5H12V10.5M7 10.5H8V11.5H7V10.5Z");
        pdfIcon.setFill(Color.valueOf("#555555"));

        SVGPath addAllTasksToToDoListIcon = new SVGPath();
        addAllTasksToToDoListIcon.setContent("M640-121v-120H520v-80h120v-120h80v120h120v80H720v120h-80ZM160-240v-80h283q-3 21-2.5 40t3.5 40H160Zm0-160v-80h386q-23 16-41.5 36T472-400H160Zm0-160v-80h600v80H160Zm0-160v-80h600v80H160Z");
        addAllTasksToToDoListIcon.setFill(Color.valueOf("#555555"));

        Button exportToPdfButton = new Button();
        exportToPdfButton.setGraphic(pdfIcon);
        exportToPdfButton.setStyle("-fx-background-color: transparent;");

        addAllTasksToToDoListIcon.setScaleX(0.025);
        addAllTasksToToDoListIcon.setScaleY(0.025);
        Group iconWrapper = new Group(addAllTasksToToDoListIcon);
        Button addAllTasksToToDoList = new Button();
        addAllTasksToToDoList.setGraphic(iconWrapper);
        addAllTasksToToDoList.setStyle("-fx-background-color: transparent;");

        // top panel
        VBox topCointainer = new VBox();
        taskPlannerPage.setTop(topCointainer);
            BorderPane topBar = new BorderPane();
            topBar.setCenter(taskPlannerLabel);
            topBar.setStyle("-fx-background-color: #f8f9fa; -fx-border-color: #dee2e6; -fx-border-width: 0 0 2 0;");

                //menubar
                MenuBar menubar = new MenuBar();
                    Menu fileMenu = new Menu("File");
                    MenuItem exportPdf = new MenuItem("Export to PDF");
                    fileMenu.getItems().add(exportPdf);

                    Menu viewMenu = new Menu("View");
                    Menu fontsize = new Menu("Font size");
                        MenuItem small = new MenuItem("Small");
                        MenuItem medium = new MenuItem("Medium");
                        MenuItem large = new MenuItem("Large");
                        fontsize.getItems().addAll(small, medium, large);
                    viewMenu.getItems().add(fontsize);

        menubar.getMenus().addAll(fileMenu, viewMenu);
        topCointainer.getChildren().addAll(menubar, topBar);

        // main panel
        GridPane mainPanel = new GridPane();
        mainPanel.setHgap(20);
        mainPanel.setPadding(new javafx.geometry.Insets(20));

        // column constraints - metoda ktora pozwala manipulowac wewnetrznymi zasadami kolumn gridpane
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
        tasksHeaderBox.getChildren().addAll(tasksHeader, addAllTasksToToDoList, addTaskQuickButton);

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
        catView.getChildren().addAll(catHeaderBox, categoryList);

        VBox taskListView = new VBox();
        HBox taskListHeaderBox = new HBox(10);
        HBox.setHgrow(taskListHeader, Priority.ALWAYS);
        taskListHeader.setMaxWidth(Double.MAX_VALUE);
        taskListHeaderBox.getChildren().addAll(taskListHeader, exportToPdfButton);

        MFXListView<Task> toDoList = new MFXListView<>();
        toDoList.setItems(taskPlanner.getToDoList().getTaskList());
        VBox.setVgrow(toDoList, Priority.ALWAYS);
        toDoList.setMaxWidth(Double.MAX_VALUE);
        toDoList.setMaxHeight(Double.MAX_VALUE);
        taskListView.getChildren().clear();
        taskListView.getChildren().addAll(taskListHeaderBox, toDoList);

        /*
        „OBIEKCIE CATEGORY LIST" – Hej, Ty, biały prostokącie po prawej!
        „ODWOŁUJĘ SIĘ DO TWOJEGO SELECTION MODEL" – Daj mi pogadać z Twoim „mózgiem od kliknięć" (tak, domyślnie pozwala na jedno zaznaczenie, choć można go przestawić na wiele).
        „SELECTION PROPERTY" – Chcę obserwować konkretnie cechę pod tytułem: „co jest teraz podświetlone".
        „TWORZĘ TUTAJ CZUJKĘ" – I jak tylko ta cecha drgnie (ktoś kliknie myszką), to masz natychmiast wykonać to, co Ci napisałem w lambdzie
         */
        categoryList.getSelectionModel().selectionProperty().addListener((observable, oldSelection, newSelection) -> {
            if (newSelection != null && !newSelection.isEmpty()) {
                Category selected = newSelection.values().stream().findFirst().orElse(null);
                if (selected != null) {
                    taskList.setItems(selected.getTasksOfCategory());
                }
            }
        });

        // DODAWANIE DO SIATKI
        mainPanel.add(tasksView, 1, 0);
        mainPanel.add(catView, 2, 0);
        mainPanel.add(taskListView, 0, 0);

        taskPlannerPage.setCenter(mainPanel);
        GridPane.setVgrow(tasksView, Priority.ALWAYS);

        // akcje na przyciskach
        // dodawanie taska
        addTaskQuickButton.setOnAction(e -> {
            Optional<DialogHelper.TaskResult> result = DialogHelper.addNewTaskLogicDialog(taskPlanner.getCategories());
            result.ifPresent(taskData -> {
                if (taskData.getName() != null && !taskData.getName().trim().isEmpty()) {
                    taskPlanner.addTaskToCategory(taskData.getCategory(), taskData.getName());
                    Category targetCategory = taskData.getCategory();
                    int index = taskPlanner.getCategories().indexOf(targetCategory);
                    taskPlanner.getCategories().remove(index);
                    taskPlanner.getCategories().add(index, targetCategory);
                }
            });
        });

        // dodawanie wszystkich taskow do listy zadan
        addAllTasksToToDoList.setOnAction(e -> {
            for (Task task : taskList.getItems()){
                taskPlanner.getToDoList().getTaskList().add(task);
            }
        });

        // dodawanie kategorii
        addCatQuickButton.setOnAction(e -> {
            String catNameInput = DialogHelper.addNewCatLogicDialog();
            if (catNameInput != null) {
                boolean success = taskPlanner.createCategory(catNameInput);
                if (!success) {
                    System.out.println("Błąd! Nie udało się dodać kategorii.");
                }
            }
        });

        // dodawanie taska do listy zadan
        taskList.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                Task selected = taskList.getSelectionModel().getSelectedValues().stream().findFirst().orElse(null);
                if (selected != null) {
                    taskPlanner.getToDoList().getTaskList().add(selected);
                }
            }
        });

        // usuwanie taska z listy
        toDoList.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                Task selected = toDoList.getSelectionModel().getSelectedValues().stream().findFirst().orElse(null);
                if (selected != null) {
                    taskPlanner.getToDoList().getTaskList().remove(selected);
                }
            }
        });

        // rightClick menu
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

        deleteItemCat.setOnAction(e -> {
            Category selected = categoryList.getSelectionModel().getSelectedValues().stream().findFirst().orElse(null);
            if(selected != null) {
                taskPlanner.getCategories().remove(selected);
            }
        });

        editItemCat.setOnAction(e -> {
            Category selected = categoryList.getSelectionModel().getSelectedValues().stream().findFirst().orElse(null);
            if (selected != null) {
                String catNameInput = DialogHelper.addNewCatLogicDialog();
                if (catNameInput != null) {
                    selected.setName(catNameInput);
                    int index = taskPlanner.getCategories().indexOf(selected);
                    taskPlanner.getCategories().remove(index);
                    taskPlanner.getCategories().add(index, selected);
                }
            }
        });

        deleteItemTask.setOnAction(e -> {
            Task selected = taskList.getSelectionModel().getSelectedValues().stream().findFirst().orElse(null);
            if (selected != null) {
                Category targetCategory = selected.getCategory();
                targetCategory.getTasksOfCategory().remove(selected);
                int index = taskPlanner.getCategories().indexOf(targetCategory);
                taskPlanner.getCategories().remove(index);
                taskPlanner.getCategories().add(index, targetCategory);
            }
        });

        editItemTask.setOnAction(e -> {
            Task selected = taskList.getSelectionModel().getSelectedValues().stream().findFirst().orElse(null);
            if (selected != null) {
                Category oldCategory = selected.getCategory();
                Optional<DialogHelper.TaskResult> result = DialogHelper.addNewTaskLogicDialog(taskPlanner.getCategories());
                result.ifPresent(taskData -> {
                    if (taskData.getName() != null && !taskData.getName().trim().isEmpty()) {
                        oldCategory.getTasksOfCategory().remove(selected); // usuń ze starej PRZED zmianą
                        selected.setName(taskData.getName());
                        selected.setCategory(taskData.getCategory());
                        taskData.getCategory().getTasksOfCategory().add(selected); // dodaj do nowej (lub starej jeśli nie zmieniono)
                    }
                });
            }
        });

        exportToPdfButton.setOnAction(e -> {
            PdfExporter exporter = new PdfExporter();
            exporter.export(taskPlanner.getToDoList());
        });
        return taskPlannerScene;
    }
}
