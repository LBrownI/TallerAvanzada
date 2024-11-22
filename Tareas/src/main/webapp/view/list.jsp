<%@page import="java.util.List"%>
<%@page import="model.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Customer List</title>
        <!-- Bootstrap CDN -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <% 
            String message = (String) request.getAttribute("message");
            if (message != null && !message.isBlank()) {
        %>
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            <%= message %>
        </div>
        <% 
            } 
        %>
        <section class="container mt-4">
            <div class="d-flex justify-content-between align-items-center mb-3">
                <h1 class="h3">Customer List</h1>
                <a href="view/add.jsp" class="btn btn-primary">Add Customer</a>
            </div>

            <% List<Customer> customerList = (List<Customer>) request.getAttribute("customerList"); %>
            <% if (customerList == null || customerList.isEmpty()) { %>
            <div class="card">
                <div class="card-body">
                    <p class="card-text">No data available.</p>
                </div>
            </div>
            <% } else { %>
            <div class="table-responsive">
                <table class="table table-striped table-bordered">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Email</th>
                            <th>Country</th>
                            <th>Phone</th>
                            <th>Registration Date</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (Customer customer : customerList) { %>
                        <tr>
                            <td><%= customer.getId() %></td>
                            <td><%= customer.getFirstName() %></td>
                            <td><%= customer.getLastName() %></td>
                            <td><%= customer.getEmail() %></td>
                            <td><%= customer.getCountry() %></td>
                            <td><%= customer.getPhone() %></td>
                            <td><%= customer.getRegistrationDate() %></td>
                            <td>
                                <form action="CustomerServlet" method="get" style="display:inline;">
                                    <input type="hidden" name="id" value="<%= customer.getId() %>">
                                    <input type="hidden" name="action" value="delete">
                                    <button class="btn btn-danger btn-sm" type="submit">Delete</button>
                                </form>
                            </td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
            <% } %>
        </section>

        <!-- Bootstrap JS (optional, for interactive components like modal, alert dismiss) -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
