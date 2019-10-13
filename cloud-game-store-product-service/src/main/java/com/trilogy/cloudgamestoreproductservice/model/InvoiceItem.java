package com.trilogy.cloudgamestoreproductservice.model;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

public class InvoiceItem {

    private int invoiceItemId;
    @Digits(integer = 11, fraction = 0)
    @NotNull
    private int invoiceId;
    @Digits(integer = 11, fraction = 0)
    @NotNull
    private int inventoryId;
    @Digits(integer = 11, fraction = 0)
    @NotNull
    private int quantity;
    @Digits(integer = 5, fraction = 5)
    @NotNull
    private BigDecimal unitPrice;

    public InvoiceItem() {
    }

    public InvoiceItem(@Digits(integer = 11, fraction = 0) @NotNull int invoiceId, @Digits(integer = 11,
            fraction = 0) @NotNull int inventoryId, @Digits(integer = 11, fraction = 0) @NotNull int quantity,
                       @Digits(integer = 5, fraction = 5) @NotNull BigDecimal unitPrice) {
        this.invoiceId = invoiceId;
        this.inventoryId = inventoryId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public InvoiceItem(int invoiceItemId, @Digits(integer = 11, fraction = 0) @NotNull int invoiceId,
                       @Digits(integer = 11, fraction = 0) @NotNull int inventoryId,
                       @Digits(integer = 11, fraction = 0) @NotNull int quantity, @Digits(integer = 5, fraction = 5)
                       @NotNull BigDecimal unitPrice) {
        this.invoiceItemId = invoiceItemId;
        this.invoiceId = invoiceId;
        this.inventoryId = inventoryId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public int getInvoiceItemId() {
        return invoiceItemId;
    }

    public void setInvoiceItemId(int invoiceItemId) {
        this.invoiceItemId = invoiceItemId;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceItem that = (InvoiceItem) o;
        return getInvoiceItemId() == that.getInvoiceItemId() &&
                getInvoiceId() == that.getInvoiceId() &&
                getInventoryId() == that.getInventoryId() &&
                getQuantity() == that.getQuantity() &&
                getUnitPrice().equals(that.getUnitPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInvoiceItemId(), getInvoiceId(), getInventoryId(), getQuantity(), getUnitPrice());
    }

    @Override
    public String toString() {
        return "InvoiceItem{" +
                "invoiceItemId=" + invoiceItemId +
                ", invoiceId=" + invoiceId +
                ", inventoryId=" + inventoryId +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                '}';
    }

}