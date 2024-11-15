package controller;

import dao.CustomerInterface;
import dao.CustomerDAO;
import model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {
    private CustomerInterface customerDAO;

    @Override
    public void init() {
        customerDAO = new CustomerDAO(); // Instantiate the concrete CustomerDAO
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action == null ? "list" : action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "insert":
                    insertCustomer(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "update":
                    updateCustomer(request, response);
                    break;
                case "delete":
                    deleteCustomer(request, response);
                    break;
                default:
                    listCustomers(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listCustomers(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<Customer> customerList = customerDAO.getAllCustomers();
        request.setAttribute("customerList", customerList);
        request.getRequestDispatcher("list.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("add.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Customer existingCustomer = customerDAO.getCustomerById(id);
        request.setAttribute("customer", existingCustomer);
        request.getRequestDispatcher("add.jsp").forward(request, response);
    }

    private void insertCustomer(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        String country = request.getParameter("country");
        String email = request.getParameter("email");

        Customer newCustomer = new Customer();
        newCustomer.setName(name);
        newCustomer.setCountry(country);
        newCustomer.setEmail(email);

        customerDAO.addCustomer(newCustomer);
        response.sendRedirect("CustomerServlet");
    }

    private void updateCustomer(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String country = request.getParameter("country");
        String email = request.getParameter("email");

        Customer customer = new Customer();
        customer.setCustomerID(id);
        customer.setName(name);
        customer.setCountry(country);
        customer.setEmail(email);

        customerDAO.updateCustomer(customer);
        response.sendRedirect("CustomerServlet");
    }

    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        customerDAO.deleteCustomer(id);
        response.sendRedirect("CustomerServlet");
    }
}
