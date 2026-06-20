package io.github.wojciechkoziestanski;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DatabaseCommands {
    boolean save(TaskPlanner taskplanner) {
        try (Connection conn = DatabaseConnector.getConnection()) {
            conn.setAutoCommit(false);
            try {
                try (
                        PreparedStatement cleanTasks = conn.prepareStatement("DELETE FROM tasks");
                        PreparedStatement cleanCategories = conn.prepareStatement("DELETE FROM categories")
                ) {
                    cleanTasks.executeUpdate();
                    cleanCategories.executeUpdate();
                }

                try (
                        PreparedStatement saveCategories = conn.prepareStatement(
                        "INSERT INTO categories (name) VALUES (?)",
                                Statement.RETURN_GENERATED_KEYS
                        );
                        PreparedStatement saveTasks = conn.prepareStatement(
                            "INSERT INTO tasks (category_id, name) VALUES (?, ?)"
                        )
                ) {
                    for (int i = 0; i < taskplanner.getCategories().size(); i++) {
                        Category category = taskplanner.getCategories().get(i);
                        String name = taskplanner.getCategories().get(i).getName();
                        saveCategories.setString(1, name);
                        saveCategories.executeUpdate();
                        int categoryId;
                        try (ResultSet keys = saveCategories.getGeneratedKeys()) {
                            if (!keys.next()) {
                                throw new SQLException("No generated keys for category: " + category.getName());
                            }
                            categoryId = keys.getInt(1);
                        }
                        for (Task task : category.getTasksOfCategory()) {
                            saveTasks.setInt(1, categoryId);
                            saveTasks.setString(2, task.getName());
                            saveTasks.executeUpdate();
                        }
                    }
                }

                conn.commit();
                return true;
            } catch (SQLException e) {
                rollbackQuietly(conn);
                System.out.println("Error: transaction rolled back");
                throw new RuntimeException(e);
            } finally {
                resetAutoCommitQuietly(conn);
            }
        } catch (SQLException e) {
            System.out.println("Error: connection");
            throw new RuntimeException(e);
        }
    }

    boolean load(TaskPlanner taskPlanner){
        try (Connection conn = DatabaseConnector.getConnection()){
            conn.setAutoCommit(false);
            try (
                    PreparedStatement loadTasks = conn.prepareStatement("SELECT * FROM tasks WHERE category_id = ?");
                    PreparedStatement loadCategories = conn.prepareStatement("SELECT * FROM categories ");
                    ){
                ResultSet categories = loadCategories.executeQuery();
                while(categories.next()){
                    int id = categories.getInt("id");
                    String nameCat = categories.getString("name");
                    Category newCategory = new Category(nameCat, id);
                    taskPlanner.getCategories().add(newCategory);
                    loadTasks.setInt(1,id);
                    ResultSet tasks = loadTasks.executeQuery();
                    while (tasks.next()){
                        String nameTask = tasks.getString("name");
                        taskPlanner.addTaskToCategory(newCategory, nameTask);
                    }
                }
                
                conn.commit();
                return true;               

            }catch (SQLException e){
                rollbackQuietly(conn);
                System.out.println("Error: connection");
                throw new RuntimeException(e);
                } finally {
                    resetAutoCommitQuietly(conn);
                }
        } catch (SQLException e){
            System.out.println("Error: connection");
            throw new RuntimeException(e);
        }
    }

    private static void rollbackQuietly(Connection conn) {
        if (conn == null) {
            return;
        }
        try {
            if (!conn.getAutoCommit()) {
                conn.rollback();
            }
        } catch (SQLException ignored) {
            // połączenie i tak może być w złym stanie — nie maskuj pierwotnego wyjątku
        }
    }

    private static void resetAutoCommitQuietly(Connection conn) {
        if (conn == null) {
            return;
        }
        try {
            conn.setAutoCommit(true);
        } catch (SQLException ignored) {
        }
    }
}
