package controller;

import dao.CustomerInterface;
import dao.CustomerDAO;
import model.Customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {
    private CustomerInterface customerDAO;

    @Override
    public void init() {
        customerDAO = new CustomerDAO(); // Instanciar el DAO concreto
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
                case "edit":
                    showEditForm(request, response);
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("insert".equals(action)) {
                insertCustomer(request, response);
            } else if ("update".equals(action)) {
                updateCustomer(request, response);
            } else {
                response.sendRedirect("CustomerServlet");
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listCustomers(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<Customer> customerList = customerDAO.getAllCustomers();
        request.setAttribute("customerList", customerList);
        request.getRequestDispatcher("/vista/list.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("customer", null); // Aseguramos que el cliente esté en el contexto, aunque sea null
        request.getRequestDispatcher("/vista/add.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Customer existingCustomer = customerDAO.getCustomerById(id);
        request.setAttribute("customer", existingCustomer);
        request.getRequestDispatcher("/vista/add.jsp").forward(request, response);
    }

    private void insertCustomer(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        String lastname = request.getParameter("lastname");
        String country = request.getParameter("country");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String registration = request.getParameter("registrationdate");

        Customer newCustomer = new Customer();
        newCustomer.setName(name);
        newCustomer.setLastName(lastname);
        newCustomer.setCountry(country);
        newCustomer.setEmail(email);
        newCustomer.setPhone(phone);
        newCustomer.setRegistrationDate(registration);

        customerDAO.addCustomer(newCustomer);
        response.sendRedirect("CustomerServlet");
    }

    private void updateCustomer(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String lastname = request.getParameter("lastname");
        String country = request.getParameter("country");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String registration = request.getParameter("registrationdate");

        Customer customer = new Customer();
        customer.setCustomerID(id);
        customer.setName(name);
        customer.setLastName(lastname);
        customer.setCountry(country);
        customer.setEmail(email);
        customer.setPhone(phone);
        customer.setRegistrationDate(registration);

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
