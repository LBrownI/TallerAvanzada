<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Address</th>
        <th>Email</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="customer" items="${customerList}">
        <tr>
            <td>${customer.customerID}</td>
            <td>${customer.name}</td>
            <td>${customer.address}</td>
            <td>${customer.email}</td>
            <td>
                <a href="CustomerServlet?action=edit&id=${customer.customerID}">Edit</a>
                <a href="CustomerServlet?action=delete&id=${customer.customerID}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="CustomerServlet?action=new">Add New Customer</a>
