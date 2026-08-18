package io.github.wojciechkoziestanski.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnector {
    private static final String DB_DIR = System.getProperty("user.home") + File.separator + "LifeManager";
    private static final String URL = "jdbc:h2:file:" + DB_DIR + File.separator + "lifemanager_db";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void initDatabase() {
        try (Connection conn = getConnection();
            Statement stmt = conn.createStatement()) {
            String createCategoriesTable = "CREATE TABLE IF NOT EXISTS categories (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(255) NOT NULL UNIQUE" +
                    ");";
            stmt.execute(createCategoriesTable);

            String createTasksTable = "CREATE TABLE IF NOT EXISTS tasks (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "category_id INT, " +
                    "name VARCHAR(255) NOT NULL, " +
                    "FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE CASCADE" +
                    ");";
            stmt.execute(createTasksTable);
            System.out.println("Tabele w bazie danych zostały zainicjalizowane!");

        } catch (SQLException e) {
            System.err.println("Błąd podczas tworzenia tabel: " + e.getMessage());
        }
    }
}