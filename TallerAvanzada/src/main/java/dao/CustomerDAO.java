package dao;

import model.Customer;
import utility.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements CustomerInterface { // No longer abstract

    @Override
    public List<Customer> getAllCustomers() throws SQLException {
        String query = "SELECT * FROM Customer";
        List<Customer> customers = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerID(rs.getInt("CustomerID"));
                customer.setName(rs.getString("Name"));
                customer.setAddress(rs.getString("Address"));
                customer.setEmail(rs.getString("Email"));
                customers.add(customer);
            }
        }

        return customers;
    }

    @Override
    public Customer getCustomerById(int id) throws SQLException {
        String query = "SELECT * FROM Customer WHERE CustomerID = ?";
        Customer customer = null;

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    customer = new Customer();
                    customer.setCustomerID(rs.getInt("CustomerID"));
                    customer.setName(rs.getString("Name"));
                    customer.setAddress(rs.getString("Address"));
                    customer.setEmail(rs.getString("Email"));
                }
            }
        }

        return customer;
    }

    @Override
    public boolean addCustomer(Customer customer) throws SQLException {
        String query = "INSERT INTO Customer (Name, Address, Email) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, customer.getName());
            ps.setString(2, customer.getAddress());
            ps.setString(3, customer.getEmail());
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean updateCustomer(Customer customer) throws SQLException {
        String query = "UPDATE Customer SET Name = ?, Address = ?, Email = ? WHERE CustomerID = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, customer.getName());
            ps.setString(2, customer.getAddress());
            ps.setString(3, customer.getEmail());
            ps.setInt(4, customer.getCustomerID());
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean deleteCustomer(int id) throws SQLException {
        String query = "DELETE FROM Customer WHERE CustomerID = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}


