package io.github.wojciechkoziestanski;

import io.github.wojciechkoziestanski.database.DatabaseConnector;

public class Main {
    public static void main(String[] args) {
        DatabaseConnector.initDatabase();
        UI.launch(UI.class, args);
    }
}
