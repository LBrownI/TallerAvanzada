<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Sale</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css">
</head>
<body>
    <section class="section">
        <div class="container">
            <h1 class="title">Add Sale</h1>

            <form action="SaleServlet" method="POST">
                <!-- Customer Selection -->
                <div class="field">
                    <label class="label">Customer</label>
                    <div class="control">
                        <select name="customerId" class="input" required>
                            <!-- Populate customers dynamically (you can use a loop to load customers from DB) -->
                            <option value="1">Customer 1</option>
                            <option value="2">Customer 2</option>
                        </select>
                    </div>
                </div>

                <!-- Currency Selection -->
                <div class="field">
                    <label class="label">Currency</label>
                    <div class="control">
                        <input type="text" name="currency" class="input" required placeholder="USD">
                    </div>
                </div>

                <!-- Total Amount -->
                <div class="field">
                    <label class="label">Total Amount</label>
                    <div class="control">
                        <input type="number" name="totalAmount" class="input" required placeholder="Total Sale Amount">
                    </div>
                </div>

                <!-- Product Details -->
                <div class="field">
                    <label class="label">Products</label>
                    <div class="control">
                        <table class="table is-bordered is-striped is-hoverable" id="productTable">
                            <thead>
                                <tr>
                                    <th>Product</th>
                                    <th>Quantity</th>
                                    <th>Price</th>
                                    <th>Total</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td><input type="text" name="product1" class="input" required placeholder="Product ID or Name"></td>
                                    <td><input type="number" name="quantity1" class="input" required></td>
                                    <td><input type="number" name="price1" class="input" required></td>
                                    <td><input type="number" name="total1" class="input" readonly></td>
                                </tr>
                                <tr>
                                    <td><input type="text" name="product2" class="input"></td>
                                    <td><input type="number" name="quantity2" class="input"></td>
                                    <td><input type="number" name="price2" class="input"></td>
                                    <td><input type="number" name="total2" class="input" readonly></td>
                                </tr>
                                <!-- Add more rows as needed -->
                            </tbody>
                        </table>
                    </div>
                </div>

                <button class="button is-primary" type="submit">Submit Sale</button>
            </form>
        </div>
    </section>
</body>
</html>
