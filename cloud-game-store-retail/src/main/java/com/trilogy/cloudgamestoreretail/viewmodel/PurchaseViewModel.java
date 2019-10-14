package com.trilogy.cloudgamestoreretail.viewmodel;

import com.trilogy.cloudgamestoreretail.model.Invoice;
import com.trilogy.cloudgamestoreretail.model.InvoiceItem;

import java.util.List;
import java.util.Objects;

public class PurchaseViewModel {

    private Invoice invoice;
    List<InvoiceItem> invoiceItems;

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public List<InvoiceItem> getInvoiceItems() {
        return invoiceItems;
    }

    public void setInvoiceItems(List<InvoiceItem> invoiceItems) {
        this.invoiceItems = invoiceItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseViewModel that = (PurchaseViewModel) o;
        return getInvoice().equals(that.getInvoice()) &&
                getInvoiceItems().equals(that.getInvoiceItems());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInvoice(), getInvoiceItems());
    }

    @Override
    public String toString() {
        return "PurchaseViewModel{" +
                "invoice=" + invoice +
                ", invoiceItems=" + invoiceItems +
                '}';
    }
}
