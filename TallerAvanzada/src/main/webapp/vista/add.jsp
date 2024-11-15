<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>${customer != null ? "Edit Customer" : "Add Customer"}</title>
</head>
<body>
    <h1>${customer != null ? "Edit Customer" : "Add Customer"}</h1>
    
    <form action="CustomerServlet?action=${customer != null ? 'update' : 'insert'}" method="post">
        <input type="hidden" name="id" value="${customer.customerID}">
        
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" value="${customer.name}" required><br>
        
        <label for="address">Address:</label>
        <input type="text" id="address" name="address" value="${customer.address}" required><br>
        
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" value="${customer.email}" required><br>
        
        <button type="submit">${customer != null ? "Update" : "Add"} Customer</button>
    </form>

    <!-- Only show the delete button if editing an existing customer -->
    <c:if test="${customer != null}">
        <form action="CustomerServlet?action=delete" method="post" style="margin-top: 10px;">
            <input type="hidden" name="id" value="${customer.customerID}">
            <button type="submit" style="background-color: red; color: white;">Delete Customer</button>
        </form>
    </c:if>

    <a href="CustomerServlet">Back to Customer List</a>
</body>
</html>
