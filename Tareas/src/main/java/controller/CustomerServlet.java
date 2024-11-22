package controller;

import javax.servlet.RequestDispatcher;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import model.Customer;

@WebServlet(name = "CustomerServlet", urlPatterns = {"/CustomerServlet"})
public class CustomerServlet extends HttpServlet {

    private final String listView = "view/list.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        try {
            switch (action.toLowerCase()) {
                case "add":
                    handleAddCustomer(request, response);
                    break;

                case "delete":
                    handleDeleteCustomer(request, response);
                    break;

                default:
                    handleListCustomers(request, response);
                    break;
            }
        } catch (Exception ex) {
            request.setAttribute("message", "An error occurred: " + ex.getMessage());
            ex.printStackTrace(); // Consider replacing with logging
            RequestDispatcher view = request.getRequestDispatcher(listView);
            view.forward(request, response);
        }
    }

    private void handleAddCustomer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        String phone = request.getParameter("phone");

        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.setCountry(country);
        customer.setPhone(phone);

        customer.addCustomer();
        response.sendRedirect(request.getContextPath() + "/CustomerServlet");
    }

    private void handleDeleteCustomer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Customer customer = new Customer();
        customer.deleteCustomer(id);

        response.sendRedirect(request.getContextPath() + "/CustomerServlet");
    }

    private void handleListCustomers(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Customer> customerList = new Customer().listCustomers();

        request.setAttribute("customerList", customerList);
        RequestDispatcher view = request.getRequestDispatcher(listView);
        view.forward(request, response);
    }
}
