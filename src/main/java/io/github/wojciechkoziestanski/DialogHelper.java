package io.github.wojciechkoziestanski;

import io.github.wojciechkoziestanski.taskplanner.Category;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;

import java.util.Optional;

public class DialogHelper {
    public static class TaskResult {
        String name;
        Category category;

        public TaskResult(String name, Category category) {
            this.name = name;
            this.category = category;
        }
    }

    public static Optional<TaskResult> addNewTaskLogicDialog(ObservableList<Category> categories) {
        Dialog<TaskResult> addNewTaskLogicDialog = new Dialog<>();
            addNewTaskLogicDialog.setTitle("Dodaj nowy task!");
            addNewTaskLogicDialog.setHeaderText("Nazwa: ");
            //przyciski
            ButtonType addButtonType = new ButtonType("Dodaj", ButtonBar.ButtonData.OK_DONE);
            addNewTaskLogicDialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);
        //uklad
        GridPane grid = new GridPane();
        grid.setHgap(10); // Odstęp poziomy
        grid.setVgap(10); // Odstęp pionowy
        grid.setPadding(new Insets(20, 150, 10, 10));
        //pole tekstowe
        TextField taskNameField = new TextField();
        taskNameField.setPromptText("Wpisz nazwę zadania...");
        //rozwijalna lista kategorii
        ComboBox<Category> categoryComboBox = new ComboBox<>();
        categoryComboBox.setItems(categories);
            if (!categories.isEmpty()) {
                categoryComboBox.getSelectionModel().selectFirst();
            }
        //implementacja elementów w siatkę układu
        grid.add(new Label("Nazwa zadania:"), 0, 0);
        grid.add(taskNameField, 1, 0);
        grid.add(new Label("Wybierz kategorię:"), 0, 1);
        grid.add(categoryComboBox, 1, 1);
        addNewTaskLogicDialog.getDialogPane().setContent(grid);

        addNewTaskLogicDialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                return new TaskResult(taskNameField.getText(), categoryComboBox.getValue());
            }
            return null;
        });

        return addNewTaskLogicDialog.showAndWait();
    }





    public static String addNewCatLogicDialog() {
        TextInputDialog addNewCatLogicDialog = new TextInputDialog();
            addNewCatLogicDialog.setTitle("Dodaj nową kategorię: ");
            addNewCatLogicDialog.setHeaderText("Nazwa: ");
            Optional<String> result = addNewCatLogicDialog.showAndWait();
        return result.orElse(null);
    }
}
