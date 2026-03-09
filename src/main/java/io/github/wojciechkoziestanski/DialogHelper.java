package io.github.wojciechkoziestanski;

import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class DialogHelper {
    public static void addNewTaskLogicDialog() {
        TextInputDialog addNewTaskLogicDialog = new TextInputDialog();
            addNewTaskLogicDialog.setTitle("Dodaj nowy task!");
            addNewTaskLogicDialog.setHeaderText("Nazwa: ");


        addNewTaskLogicDialog.showAndWait();
    }

    public static String addNewCatLogicDialog() {
        TextInputDialog addNewCatLogicDialog = new TextInputDialog();
            addNewCatLogicDialog.setTitle("Dodaj nową kategorię: ");
            addNewCatLogicDialog.setHeaderText("Nazwa: ");
            Optional<String> result = addNewCatLogicDialog.showAndWait();
        return result.orElse(null);
    }
}
