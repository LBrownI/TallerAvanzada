<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Customer" %>

<%
    // Asumiendo que pasaste el objeto "customer" como un atributo de request
    Customer customer = (Customer) request.getAttribute("customer");
    String action = (customer != null) ? "update" : "insert";
    String buttonLabel = (customer != null) ? "Update" : "Add";
%>

<form action="CustomerServlet?action=<%= action %>" method="post">
    <input type="hidden" name="id" value="<%= (customer != null) ? customer.getCustomerID() : "" %>">

    <label for="name">Name:</label>
    <input type="text" id="name" name="name" value="<%= (customer != null) ? customer.getName() : "" %>" required><br>

    <label for="lastname">Last Name:</label>
    <input type="text" id="lastname" name="lastname" value="<%= (customer != null) ? customer.getLastName() : "" %>" required><br>

    <label for="country">Country:</label>
    <input type="text" id="country" name="country" value="<%= (customer != null) ? customer.getCountry() : "" %>" required><br>

    <label for="email">Email:</label>
    <input type="email" id="email" name="email" value="<%= (customer != null) ? customer.getEmail() : "" %>" required><br>

    <label for="phone">Phone:</label>
    <input type="text" id="phone" name="phone" value="<%= (customer != null) ? customer.getPhone() : "" %>" required><br>

    <label for="registrationdate">Registration Date:</label>
    <input type="text" id="registrationdate" name="registrationdate" value="<%= (customer != null) ? customer.getRegistrationDate() : "" %>"><br>

    <button type="submit"><%= buttonLabel %> Customer</button>
</form>
