package dao;

import model.Customer;
import java.sql.SQLException;
import java.util.List;

public interface CustomerInterface {
    List<Customer> getAllCustomers() throws SQLException;

    Customer getCustomerById(int id) throws SQLException;

    boolean addCustomer(Customer customer) throws SQLException;

    boolean updateCustomer(Customer customer) throws SQLException;

    boolean deleteCustomer(int id) throws SQLException;
}
