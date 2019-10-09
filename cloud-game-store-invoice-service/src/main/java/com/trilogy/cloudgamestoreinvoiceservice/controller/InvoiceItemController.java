package com.trilogy.cloudgamestoreinvoiceservice.controller;

import com.trilogy.cloudgamestoreinvoiceservice.model.InvoiceItem;
import com.trilogy.cloudgamestoreinvoiceservice.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class InvoiceItemController {

    @Autowired
    ServiceLayer serviceLayer;

    /***************************************
     * Invoice Item Service API Controller
     * Path: /invoiceItem
     **************************************/

    /**
     * Saves invoice item.  Invoice service API entry point for create invoice.
     * @param invoiceItem
     * @return
     */
    @PostMapping(value = "/invoiceItem")
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceItem createInvoiceItem(@RequestBody @Valid InvoiceItem invoiceItem) {
        return serviceLayer.saveInvoiceItem(invoiceItem);
    }

    /**
     * Returns a list of all invoices items in the database
     * @return
     */
    @GetMapping(value = "/invoiceItem")
    @ResponseStatus(HttpStatus.OK)
    public List<InvoiceItem> getAllInvoiceItems() {
        return serviceLayer.fetchAllInvoiceItems();
    }

    /**
     * Updates an existing invoice item with the provided valid invoice item
     * @param invoiceItem
     */
    @PutMapping(value = "/invoiceItem")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateInvoiceItem(@RequestBody @Valid InvoiceItem invoiceItem) {
        serviceLayer.updateInvoiceItem(invoiceItem);
    }

    /***************************************
     * Invoice Item Service API Controller
     * Path: /invoiceItem/{invoiceItemId}
     **************************************/

    /**
     * Retrieves one invoice item from database given a vaild Invoice Item ID
     * @param invoiceItemId
     * @return
     */
    @GetMapping(value = "/invoiceItem/{invoiceItemId}")
    @ResponseStatus(HttpStatus.OK)
    public InvoiceItem fetchOneInvoiceItem(@PathVariable int invoiceItemId) {
        return serviceLayer.fetchInvoiceItem(invoiceItemId);
    }

    /**
     * Deletes one invoice item from database given a valid Invoice Item ID
     * @param invoiceItemId
     */
    @DeleteMapping(value = "/invoiceItem/{invoiceItemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvoiceItem(@PathVariable int invoiceItemId) {
        serviceLayer.deleteInvoiceItem(invoiceItemId);
    }

    /***************************************
     * Invoice Item Service API Controller
     * Path: /invoiceItemsByInvoiceId/{invoiceId}
     **************************************/

    /**
     * Retrieves a list of invoice items given a valid Invoice ID
     * @param invoiceId
     * @return
     */
    @GetMapping(value = "/invoiceItemsByInvoiceId/{invoiceId}")
    @ResponseStatus(HttpStatus.OK)
    public List<InvoiceItem> fetchInvoiceItemsByInvoiceId(@PathVariable int invoiceId) {
        return serviceLayer.fetchAllInvoiceItemsByInvoiceId(invoiceId);
    }
}
