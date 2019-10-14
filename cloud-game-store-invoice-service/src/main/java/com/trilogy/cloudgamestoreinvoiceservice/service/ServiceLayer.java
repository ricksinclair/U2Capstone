package com.trilogy.cloudgamestoreinvoiceservice.service;

import com.trilogy.cloudgamestoreinvoiceservice.dao.InvoiceDao;
import com.trilogy.cloudgamestoreinvoiceservice.dao.InvoiceItemDao;
import com.trilogy.cloudgamestoreinvoiceservice.model.Invoice;
import com.trilogy.cloudgamestoreinvoiceservice.model.InvoiceItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServiceLayer {

    private InvoiceDao invoiceDao;
    private InvoiceItemDao invoiceItemDao;

    @Autowired
    public ServiceLayer(InvoiceDao invoiceDao, InvoiceItemDao invoiceItemDao) {
        this.invoiceDao = invoiceDao;
        this.invoiceItemDao = invoiceItemDao;
    }

    /*************************************************
     * Invoice Service API
     *************************************************/

    /**
     * Saves an invoice to database
     *
     * @param invoice
     * @return
     */
    public Invoice saveInvoice(Invoice invoice) {
        return invoiceDao.addInvoice(invoice);
    }

    /**
     * Retrieves an invoice from database given an invoice ID
     *
     * @param invoiceId
     * @return
     */
    public Invoice fetchInvoice(int invoiceId) {
        return invoiceDao.getInvoice(invoiceId);
    }

    /**
     * Retrieve all invoices from the database
     *
     * @return
     */
    public List<Invoice> fetchAllInvoices() {
        return invoiceDao.getAllInvoices();
    }

    /**
     * Updates an invoice provided a valid invoice
     *
     * @param invoice
     */
    public void updateInvoice(Invoice invoice) {
        invoiceDao.updateInvoice(invoice);
    }

    /**
     * Deletes an invoice from database given a valid invoice ID
     *
     * @param invoiceId
     */
    public void deleteInvoice(int invoiceId) {
        invoiceDao.deleteInvoice(invoiceId);
    }

    /*************************************************
     * Invoice Item Service API
     *************************************************/

    /**
     * Saves an invoice item to the database
     * @param invoiceItem
     * @return
     */
    public InvoiceItem saveInvoiceItem(InvoiceItem invoiceItem) {
        return invoiceItemDao.addInvoiceItem(invoiceItem);
    }

    /**
     * Retrieves one invoice item given a valid invoice item ID
     * @param invoiceItemId
     * @return
     */
    public InvoiceItem fetchInvoiceItem(int invoiceItemId){
        return invoiceItemDao.getInvoiceItem(invoiceItemId);
    }

    /**
     * Retrieves all invoice items from the database
     * @return
     */
    public List<InvoiceItem> fetchAllInvoiceItems(){
        return invoiceItemDao.getAllInvoiceItems();
    }

    /**
     * Retrieves all invoice items from the database given a valid invoice ID
     * @param invoiceId
     * @return
     */
    public List<InvoiceItem> fetchAllInvoiceItemsByInvoiceId(int invoiceId){
        return invoiceItemDao.getAllInvoiceItemsByInvoiceId(invoiceId);
    }

    /**
     * Updates an invoice item entry given a valid invoice item
     * @param invoiceItem
     */
    public void updateInvoiceItem(InvoiceItem invoiceItem){
        invoiceItemDao.updateInvoiceItem(invoiceItem);
    }

    /**
     * Deletes an invoice item entry given a valid invoice item ID
     * @param invoiceItemId
     */
    public void deleteInvoiceItem(int invoiceItemId) {
        invoiceItemDao.deleteInvoiceItem(invoiceItemId);
    }

    public List<Invoice> fetchAllInvoicesByCustomerId(int customerId) {
        return invoiceDao.getAllInvoiceByCustomerId(customerId);
    }
}
