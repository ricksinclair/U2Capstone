package com.trilogy.cloudgamestoreinvoiceservice.dao;

import com.trilogy.cloudgamestoreinvoiceservice.model.InvoiceItem;

import java.util.List;

public interface InvoiceItemDao {

    InvoiceItem addInvoiceItem(InvoiceItem invoiceItem);

    InvoiceItem getInvoiceItem(int id);

    List<InvoiceItem> getAllInvoiceItems();

    void updateInvoiceItem(InvoiceItem invoiceItem);

    void deleteInvoiceItem(int id);

    List<InvoiceItem> getAllInvoiceItemsByInvoiceId(int invoiceId);
}
