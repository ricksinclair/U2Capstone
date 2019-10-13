package com.trilogy.cloudgamestoreproductservice.model;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

public class Invoice {

    private int invoiceId;
    @Digits(integer = 11, fraction = 0)
    @NotNull
    private int customerId;
    @NotNull
    private LocalDate purchaseDate;

    public Invoice() {
    }

    public Invoice(@Digits(integer = 11, fraction = 0) @NotNull int customerId, @NotNull LocalDate purchaseDate) {
        this.customerId = customerId;
        this.purchaseDate = purchaseDate;
    }

    public Invoice(int invoiceId, @Digits(integer = 11, fraction = 0) @NotNull int customerId, @NotNull LocalDate purchaseDate) {
        this.invoiceId = invoiceId;
        this.customerId = customerId;
        this.purchaseDate = purchaseDate;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return getInvoiceId() == invoice.getInvoiceId() &&
                getCustomerId() == invoice.getCustomerId() &&
                getPurchaseDate().equals(invoice.getPurchaseDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInvoiceId(), getCustomerId(), getPurchaseDate());
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceId=" + invoiceId +
                ", customerId=" + customerId +
                ", purchaseDate=" + purchaseDate +
                '}';
    }
}
