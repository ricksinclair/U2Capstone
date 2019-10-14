package com.trilogy.cloudgamestoreretail.viewmodel;

import java.math.BigDecimal;
import java.util.Objects;

public class InvoiceItemViewModel {

    private int invoiceItemId;
    private int invoiceId;
    //inventory ID?
    private int quantity;
//    private BigDecimal unitPrice;
    private BigDecimal itemSubtotal;
    private InventoryViewModel inventoryViewModel;

    public InvoiceItemViewModel() {
    }

    public InvoiceItemViewModel(InventoryViewModel inventoryViewModel) {
        this.inventoryViewModel = inventoryViewModel;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getItemSubtotal() {
        return itemSubtotal;
    }

    public void setItemSubtotal(BigDecimal itemSubtotal) {
        this.itemSubtotal = itemSubtotal;
    }

    public InventoryViewModel getInventoryViewModel() {
        return inventoryViewModel;
    }

    public void setInventoryViewModel(InventoryViewModel inventoryViewModel) {
        this.inventoryViewModel = inventoryViewModel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceItemViewModel that = (InvoiceItemViewModel) o;
        return getInvoiceItemId() == that.getInvoiceItemId() &&
                getInvoiceId() == that.getInvoiceId() &&
                getQuantity() == that.getQuantity() &&
                getItemSubtotal().equals(that.getItemSubtotal()) &&
                getInventoryViewModel().equals(that.getInventoryViewModel());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInvoiceItemId(), getInvoiceId(), getQuantity(), getItemSubtotal(), getInventoryViewModel());
    }

    @Override
    public String toString() {
        return "InvoiceItemViewModel{" +
                "invoiceItemId=" + invoiceItemId +
                ", invoiceId=" + invoiceId +
                ", quantity=" + quantity +
                ", itemSubtotal=" + itemSubtotal +
                ", inventoryViewModel=" + inventoryViewModel +
                '}';
    }
}
