package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;
    private static final String DATABASE_URL = "jdbc:sqlite:C:/Users/vince/OneDrive/Documentos/NetBeansProjects/TallerAvanzada/TallerAvanzada/Taller.db";

    static {
        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Singleton pattern to ensure only one connection instance is created
    private DatabaseConnection() throws SQLException {
        try {
            // Initialize the connection
            this.connection = DriverManager.getConnection(DATABASE_URL);
        } catch (SQLException e) {
            throw new SQLException("Error connecting to the database: " + e.getMessage());
        }
    }

    // Get the single instance of the database connection
    public static synchronized DatabaseConnection getInstance() throws SQLException {
        if (instance == null || instance.getConnection().isClosed()) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    // Get the current connection
    public Connection getConnection() {
        return connection;
    }

    // Close the database connection
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}