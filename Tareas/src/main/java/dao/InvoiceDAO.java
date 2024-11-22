package dao;

import model.Invoice;
import java.sql.*;
import java.math.BigDecimal;

public class InvoiceDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/your_db";
    private static final String DB_USERNAME = "username";
    private static final String DB_PASSWORD = "password";

    // Method to insert an Invoice (Master record)
    public int insertInvoice(Invoice invoice) throws SQLException {
        String query = "INSERT INTO Invoice (CustomerId, InvoiceDate, Currency, TotalAmount, Status) VALUES (?, CURRENT_DATE, ?, ?, 'Pending')";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setInt(1, invoice.getCustomerId());
            stmt.setString(2, invoice.getCurrency());
            stmt.setBigDecimal(3, invoice.getTotalAmount());
            stmt.executeUpdate();
            
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);  // Return the generated invoice ID
                }
            }
        }
        return -1;
    }
}
