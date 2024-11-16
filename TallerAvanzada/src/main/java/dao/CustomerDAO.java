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
                customer.setCustomerID(rs.getInt("Id"));
                customer.setName(rs.getString("FirstName"));
                customer.setLastName(rs.getString("LastName"));
                customer.setCountry(rs.getString("Country"));
                customer.setEmail(rs.getString("Email"));
                customer.setPhone(rs.getString("Phone"));
                customer.setRegistrationDate(rs.getString("RegistrationDate"));
                customers.add(customer);
            }
        }

        return customers;
    }

    @Override
    public Customer getCustomerById(int id) throws SQLException {
        String query = "SELECT * FROM Customer WHERE Id = ?";
        Customer customer = null;

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    customer = new Customer();
                    customer.setCustomerID(rs.getInt("Id"));
                    customer.setName(rs.getString("FirstName"));
                    customer.setLastName(rs.getString("LastName"));
                    customer.setCountry(rs.getString("Country"));
                    customer.setEmail(rs.getString("Email"));
                    customer.setPhone(rs.getString("Phone"));
                    customer.setRegistrationDate(rs.getString("RegistrationDate"));
                }
            }
        }

        return customer;
    }

    @Override
    public boolean addCustomer(Customer customer) throws SQLException {
        String query = "INSERT INTO Customer (FirstName, LastName, Country, Email, Phone, RegistrationDate) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, customer.getName());
            ps.setString(2, customer.getLastName());
            ps.setString(3, customer.getCountry());
            ps.setString(4, customer.getEmail());
            ps.setString(5, customer.getPhone());
            ps.setString(6, customer.getRegistrationDate());
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean updateCustomer(Customer customer) throws SQLException {
        String query = "UPDATE Customer SET FirstName = ?, LastName = ?, Country = ?, Email = ?, Phone = ?  WHERE Id = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, customer.getName());
            ps.setString(2, customer.getLastName());
            ps.setString(3, customer.getCountry());
            ps.setString(4, customer.getEmail());
            ps.setString(5, customer.getPhone());
            ps.setInt(6, customer.getCustomerID());
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean deleteCustomer(int id) throws SQLException {
        String query = "DELETE FROM Customer WHERE Id = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}


