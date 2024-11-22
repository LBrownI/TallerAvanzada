package controller;

import dao.InvoiceDAO;
import dao.InvoiceLineDAO;
import model.Invoice;
import model.InvoiceLine;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SaleServlet")
public class SaleServlet extends HttpServlet {

    private InvoiceDAO invoiceDAO = new InvoiceDAO();
    private InvoiceLineDAO invoiceLineDAO = new InvoiceLineDAO();

    // Process the form data and save it to the database
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get form data
        int customerId = Integer.parseInt(request.getParameter("customerId"));
        String currency = request.getParameter("currency");
        BigDecimal totalAmount = new BigDecimal(request.getParameter("totalAmount"));

        // Step 1: Create and insert the Invoice (Master record)
        Invoice invoice = new Invoice();
        invoice.setCustomerId(customerId);
        invoice.setCurrency(currency);
        invoice.setTotalAmount(totalAmount);

        try {
            int invoiceId = invoiceDAO.insertInvoice(invoice);

            // Step 2: Insert Invoice Line Items (Detail records)
            for (int i = 1; i <= 5; i++) { // Assuming up to 5 products (adjust as needed)
                String productParam = "product" + i;
                String quantityParam = "quantity" + i;
                String priceParam = "price" + i;

                if (request.getParameter(productParam) != null && !request.getParameter(productParam).isEmpty()) {
                    int trackId = Integer.parseInt(request.getParameter(productParam));
                    int quantity = Integer.parseInt(request.getParameter(quantityParam));
                    BigDecimal price = new BigDecimal(request.getParameter(priceParam));
                    BigDecimal totalInCLP = price.multiply(new BigDecimal(quantity));

                    InvoiceLine invoiceLine = new InvoiceLine();
                    invoiceLine.setInvoiceId(invoiceId);
                    invoiceLine.setTrackId(trackId);
                    invoiceLine.setQuantity(quantity);
                    invoiceLine.setUnitPriceInCLP(price);
                    invoiceLine.setTotalInCLP(totalInCLP);

                    invoiceLineDAO.insertInvoiceLine(invoiceLine);
                }
            }

            // Redirect to the sales list page after successfully processing the sale
            response.sendRedirect("salesList.jsp");

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error occurred while processing the sale.");
            request.getRequestDispatcher("/addSale.jsp").forward(request, response);
        }
    }
}
