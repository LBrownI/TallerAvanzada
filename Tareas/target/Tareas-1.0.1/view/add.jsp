<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Customer</title>
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
            <h1 class="text-center mb-4">Add Customer</h1>
            <form id="form-add" action="../CustomerServlet" method="get">
                <!-- Row 1: First Name and Last Name -->
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="firstName" class="form-label">First Name</label>
                        <input class="form-control" type="text" name="firstName" placeholder="Enter First Name" required>
                        <input type="hidden" name="action" value="add">
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="lastName" class="form-label">Last Name</label>
                        <input class="form-control" type="text" name="lastName" placeholder="Enter Last Name" required>
                    </div>
                </div>
                
                <!-- Row 2: Email and Country -->
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="email" class="form-label">Email</label>
                        <input class="form-control" type="email" name="email" placeholder="Enter Email" required>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="country" class="form-label">Country</label>
                        <input class="form-control" type="text" name="country" placeholder="Enter Country" required>
                    </div>
                </div>

                <!-- Row 3: Phone -->
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="phone" class="form-label">Phone</label>
                        <input class="form-control" type="text" name="phone" placeholder="Enter Phone Number" required>
                    </div>
                </div>

                <div class="text-center">
                    <button class="btn btn-primary" type="submit">Add Customer</button>
                </div>
            </form>
        </section>

        <!-- Bootstrap JS (optional, for some interactive components) -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

        <script>
            document.getElementById('form-add').addEventListener('submit', function (event) {
                // Form validations
                let firstName = document.querySelector('[name="firstName"]').value;
                let lastName = document.querySelector('[name="lastName"]').value;
                let email = document.querySelector('[name="email"]').value;
                let country = document.querySelector('[name="country"]').value;
                let phone = document.querySelector('[name="phone"]').value;

                if (firstName.trim() === '') {
                    alert('Please enter a first name.');
                    event.preventDefault();
                } else if (lastName.trim() === '') {
                    alert('Please enter a last name.');
                    event.preventDefault();
                } else if (email.trim() === '') {
                    alert('Please enter an email.');
                    event.preventDefault();
                } else if (country.trim() === '') {
                    alert('Please enter a country.');
                    event.preventDefault();
                } else if (phone.trim() === '') {
                    alert('Please enter a phone number.');
                    event.preventDefault();
                }
            });
        </script>
    </body>
</html>
