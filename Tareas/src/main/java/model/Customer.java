package model;

import dao.CustomerDAO;
import java.sql.SQLException;
import java.util.List;

public class Customer {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String country;
    private String phone;
    private String registrationDate; // Keep this for retrieving the registration date

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    // Business Logic Methods

    // List all customers
    public List<Customer> listCustomers() throws SQLException {
        CustomerDAO dao = new CustomerDAO();
        return dao.listCustomers();
    }

    // Add a customer (database will set the registrationDate automatically)
    public boolean addCustomer() throws SQLException {
        CustomerDAO dao = new CustomerDAO();
        return dao.addCustomer(this); // No need to set registrationDate here
    }

    // Delete a customer
    public boolean deleteCustomer(int id) throws SQLException {
        CustomerDAO dao = new CustomerDAO();
        return dao.deleteCustomer(id);
    }
}
