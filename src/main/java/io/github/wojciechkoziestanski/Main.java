package io.github.wojciechkoziestanski;

public class Main {
    public static void main(String[] args) {
        DatabaseConnector.initDatabase();
        UI.launch(UI.class, args);
    }
}
