package src.main.java.com.rhomdev.api;

import src.main.java.com.rhomdev.api.data.Produce;
import src.main.java.com.rhomdev.api.data.Task;
import src.main.java.com.rhomdev.api.data.TaskFinish;
import src.main.java.com.rhomdev.api.data.Worker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implémentation SQLite pour la gestion des données de l'API.
 * Assure-toi d'avoir le driver sqlite-jdbc dans ton classpath.
 */
public class SQLiteDataHandler implements API_COM.Client.DataHandler {
    private final String url;

    public SQLiteDataHandler(String dbFileName) {
        this.url = "jdbc:sqlite:" + dbFileName;
        initDatabase();
    }

    private void initDatabase() {
        // Exemple de création de table. Adapte les colonnes à tes objets data.*
        String createTasksTable = "CREATE TABLE IF NOT EXISTS tasks (id INTEGER PRIMARY KEY AUTOINCREMENT, data BLOB);";
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            stmt.execute(createTasksTable);
            // Crée ici les tables pour workers, produce, etc.
        } catch (SQLException e) {
            System.err.println("Erreur initialisation SQLite: " + e.getMessage());
        }
    }

    @Override
    public List<Task> getTasks() throws SQLException {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks";
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                // Ici, extrais les données et crée ton objet Task
                // tasks.add(new Task(...));
            }
        }
        return tasks;
    }

    @Override
    public void updateTasks(List<Task> tasks) throws SQLException {
        // Stratégie simple : on vide et on remplace (ou utilise INSERT OR REPLACE)
        String deleteSql = "DELETE FROM tasks";
        String insertSql = "INSERT INTO tasks (data) VALUES (?)";

        try (Connection conn = DriverManager.getConnection(url)) {
            conn.setAutoCommit(false); // Transaction pour la performance
            try (Statement delStmt = conn.createStatement();
                 PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
                
                delStmt.execute(deleteSql);
                for (Task task : tasks) {
                    // pstmt.setObject(1, task); // Si tes objets sont sérialisables en BLOB
                    pstmt.addBatch();
                }
                pstmt.executeBatch();
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }

    @Override
    public List<TaskFinish> getTasksFinish() throws Exception {
        return new ArrayList<>(); // Implémenter selon le même modèle
    }

    @Override
    public void updateTasksFinish(List<TaskFinish> tasks) throws Exception {
        // Logique SQLite pour TaskFinish
    }

    @Override
    public List<Worker> getWorkers() throws Exception {
        return new ArrayList<>(); // Logique SQLite pour Worker
    }

    @Override
    public void updateWorkers(List<Worker> workers) throws Exception {
        // Logique SQLite pour Worker
    }

    @Override
    public List<Produce> getConsuProd() throws Exception {
        return new ArrayList<>(); // Logique SQLite pour Produce
    }

    @Override
    public void updateConsuProd(List<Produce> items) throws Exception {
        // Logique SQLite pour Produce
    }
}