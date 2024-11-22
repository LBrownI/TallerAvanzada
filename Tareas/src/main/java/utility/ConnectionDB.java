package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

    private static ConnectionDB instance;
    private static final String DATABASE_URL = "jdbc:sqlite:C:\\Users\\konko\\OneDrive\\Documentos\\NetBeansProjects\\Tareas\\Taller.db";

    // Private constructor to enforce singleton
    private ConnectionDB() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error al cargar el controlador JDBC para SQLite", e);
        }
    }

    // Thread-safe method to get the singleton instance
    public static synchronized ConnectionDB getInstance() {
        if (instance == null) {
            instance = new ConnectionDB();
        }
        return instance;
    }

    // Provides a new connection for each request
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL);
    }
}
