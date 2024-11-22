package model;

import java.math.BigDecimal;

public class InvoiceLine {
    private int invoiceLineId;
    private int invoiceId;
    private int trackId;
    private int quantity;
    private BigDecimal unitPriceInCLP;
    private BigDecimal totalInCLP;

    // Getters and Setters
    public int getInvoiceLineId() {
        return invoiceLineId;
    }

    public void setInvoiceLineId(int invoiceLineId) {
        this.invoiceLineId = invoiceLineId;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPriceInCLP() {
        return unitPriceInCLP;
    }

    public void setUnitPriceInCLP(BigDecimal unitPriceInCLP) {
        this.unitPriceInCLP = unitPriceInCLP;
    }

    public BigDecimal getTotalInCLP() {
        return totalInCLP;
    }

    public void setTotalInCLP(BigDecimal totalInCLP) {
        this.totalInCLP = totalInCLP;
    }
}
