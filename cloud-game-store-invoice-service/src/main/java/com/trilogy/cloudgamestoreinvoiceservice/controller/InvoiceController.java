package com.trilogy.cloudgamestoreinvoiceservice.controller;

import com.trilogy.cloudgamestoreinvoiceservice.model.Invoice;
import com.trilogy.cloudgamestoreinvoiceservice.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class InvoiceController {

    @Autowired
    ServiceLayer serviceLayer;

    /***************************************
     * Invoice Service API Controller
     * Path: /invoice
     **************************************/

    /**
     * Saves invoice.  Invoice service API entry point for create invoice.
     * @param invoice
     * @return
     */
    @PostMapping(value = "/invoice")
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice createInvoice(@RequestBody @Valid Invoice invoice) {
        return serviceLayer.saveInvoice(invoice);
    }

    /**
     * Returns a list of all invoices in the database
     * @return
     */
    @GetMapping(value = "/invoice")
    @ResponseStatus(HttpStatus.OK)
    public List<Invoice> getAllInvoices() {
        return serviceLayer.fetchAllInvoices();
    }

    /**
     * Updates an existing invoice with the provided valid invoice
     * @param invoice
     */
    @PutMapping(value = "/invoice")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateInvoice(@RequestBody @Valid Invoice invoice) {
        serviceLayer.updateInvoice(invoice);
    }

    /***************************************
     * Invoice Service API Controller
     * Path: /invoice/{invoiceId}
     **************************************/

    /**
     * Retrieves one invoice from database given a vaild Invoice ID
     * @param invoiceId
     * @return
     */
    @GetMapping(value = "/invoice/{invoiceId}")
    @ResponseStatus(HttpStatus.OK)
    public Invoice fetchOneInvoice(@PathVariable int invoiceId) {
        return serviceLayer.fetchInvoice(invoiceId);
    }

    /**
     * Deletes one invoice from database given a valid Invoice ID
     * @param invoiceId
     */
    @DeleteMapping(value = "/invoice/{invoiceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvoice(@PathVariable int invoiceId) {
        serviceLayer.deleteInvoice(invoiceId);
    }

    @GetMapping(value = "/invoice/customer/{customerId}")
    @ResponseStatus(HttpStatus.OK)
     List<Invoice> fetchInvoicesByCustomerId(@PathVariable int customerId) {
        return serviceLayer.fetchAllInvoicesByCustomerId(customerId);
    }
}
