package dao;

import model.InvoiceLine;
import java.sql.*;
import java.math.BigDecimal;

public class InvoiceLineDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/your_db";
    private static final String DB_USERNAME = "username";
    private static final String DB_PASSWORD = "password";

    // Method to insert an Invoice Line (Detail record)
    public void insertInvoiceLine(InvoiceLine invoiceLine) throws SQLException {
        String query = "INSERT INTO InvoiceLine (InvoiceId, TrackId, Quantity, UnitPriceInCLP, TotalInCLP) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, invoiceLine.getInvoiceId());
            stmt.setInt(2, invoiceLine.getTrackId());
            stmt.setInt(3, invoiceLine.getQuantity());
            stmt.setBigDecimal(4, invoiceLine.getUnitPriceInCLP());
            stmt.setBigDecimal(5, invoiceLine.getTotalInCLP());
            stmt.executeUpdate();
        }
    }
}
