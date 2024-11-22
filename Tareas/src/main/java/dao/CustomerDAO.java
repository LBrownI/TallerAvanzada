package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Customer;
import utility.ConnectionDB;

// A DAO (Data Access Object) pattern is used to separate data access logic from business logic in an application.
// This pattern organizes and simplifies database operations such as queries, inserts, updates, and deletions.
public class CustomerDAO implements CustomerInterface {

    // Method to retrieve all customers
    @Override
    public List<Customer> listCustomers() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT Id, FirstName, LastName, Email, Country, Phone, RegistrationDate FROM Customer";

        try (Connection connection = ConnectionDB.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getInt("Id"));
                customer.setFirstName(resultSet.getString("FirstName"));
                customer.setLastName(resultSet.getString("LastName"));
                customer.setEmail(resultSet.getString("Email"));
                customer.setCountry(resultSet.getString("Country"));
                customer.setPhone(resultSet.getString("Phone"));
                customer.setRegistrationDate(resultSet.getString("RegistrationDate")); // Handle registrationDate as String
                customers.add(customer);
            }
        }
        return customers;
    }

    // Method to add a new customer
    @Override
    public boolean addCustomer(Customer customer) throws SQLException {
        String query = "INSERT INTO Customer (FirstName, LastName, Email, Country, Phone) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = ConnectionDB.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, customer.getFirstName());
            statement.setString(2, customer.getLastName());
            statement.setString(3, customer.getEmail());
            statement.setString(4, customer.getCountry());
            statement.setString(5, customer.getPhone());

            return statement.executeUpdate() > 0; // True if at least one row was inserted
        }
    }

    // Method to delete a customer by ID
    @Override
    public boolean deleteCustomer(int id) throws SQLException {
        String query = "DELETE FROM Customer WHERE Id = ?;";

        try (Connection connection = ConnectionDB.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            return statement.executeUpdate() > 0; // Returns true if at least one row was deleted
        }
    }
}
