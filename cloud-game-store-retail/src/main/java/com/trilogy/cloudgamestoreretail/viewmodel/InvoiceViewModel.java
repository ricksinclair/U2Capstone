package com.trilogy.cloudgamestoreretail.viewmodel;

import com.trilogy.cloudgamestoreretail.model.Customer;
import com.trilogy.cloudgamestoreretail.model.Invoice;
import com.trilogy.cloudgamestoreretail.model.InvoiceItem;

import java.util.List;
import java.util.Objects;

public class InvoiceViewModel {

    private Invoice invoice;
    private CustomerViewModel customerViewModel;
    //Perhaps change to List<InvoiceItemView>
    List<InvoiceItem> invoiceItems;

    public InvoiceViewModel() {
    }

    public InvoiceViewModel(Invoice invoice, CustomerViewModel customerViewModel, List<InvoiceItem> invoiceItems) {
        this.invoice = invoice;
        this.customerViewModel = customerViewModel;
        this.invoiceItems = invoiceItems;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public CustomerViewModel getCustomerViewModel() {
        return customerViewModel;
    }

    public void setCustomerViewModel(CustomerViewModel customerViewModel) {
        this.customerViewModel = customerViewModel;
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
        InvoiceViewModel that = (InvoiceViewModel) o;
        return getInvoice().equals(that.getInvoice()) &&
                getCustomerViewModel().equals(that.getCustomerViewModel()) &&
                getInvoiceItems().equals(that.getInvoiceItems());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInvoice(), getCustomerViewModel(), getInvoiceItems());
    }

    @Override
    public String toString() {
        return "InvoiceViewModel{" +
                "invoice=" + invoice +
                ", customerViewModel=" + customerViewModel +
                ", invoiceItems=" + invoiceItems +
                '}';
    }
}
