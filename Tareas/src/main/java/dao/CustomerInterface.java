package dao;

import java.sql.SQLException;
import java.util.List;
import model.Customer;

public interface CustomerInterface {

    public List<Customer> listCustomers() throws SQLException;

    public boolean addCustomer(Customer customer) throws SQLException;

    public boolean deleteCustomer(int id) throws SQLException;

}
