package com.trilogy.cloudgamestoreadmin.util.feign;

import com.trilogy.cloudgamestoreadmin.model.Invoice;
import com.trilogy.cloudgamestoreadmin.model.InvoiceItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "invoice-service")
public interface InvoiceFeignClient {
    @PostMapping(value = "/invoice")
    @ResponseStatus(HttpStatus.CREATED)
    Invoice createInvoice(@RequestBody @Valid Invoice invoice);

    @GetMapping(value = "/invoice")
    @ResponseStatus(HttpStatus.OK)
    List<Invoice> getAllInvoices();

    @PutMapping(value = "/invoice")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void updateInvoice(@RequestBody @Valid Invoice invoice);

    @GetMapping(value = "/invoice/{invoiceId}")
    @ResponseStatus(HttpStatus.OK)
    Invoice fetchOneInvoice(@PathVariable int invoiceId);

    @DeleteMapping(value = "/invoice/{invoiceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteInvoice(@PathVariable int invoiceId);

    @PostMapping(value = "/invoiceItem")
    @ResponseStatus(HttpStatus.CREATED)
    InvoiceItem createInvoiceItem(@RequestBody @Valid InvoiceItem invoiceItem);

    @GetMapping(value = "/invoiceItem")
    @ResponseStatus(HttpStatus.OK)
    List<InvoiceItem> getAllInvoiceItems();

    @PutMapping(value = "/invoiceItem")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void updateInvoiceItem(@RequestBody @Valid InvoiceItem invoiceItem);

    @GetMapping(value = "/invoiceItem/{invoiceItemId}")
    @ResponseStatus(HttpStatus.OK)
    InvoiceItem fetchOneInvoiceItem(@PathVariable int invoiceItemId);

    @DeleteMapping(value = "/invoiceItem/{invoiceItemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteInvoiceItem(@PathVariable int invoiceItemId);

    @GetMapping(value = "/invoiceItemsByInvoiceId/{invoiceId}")
    @ResponseStatus(HttpStatus.OK)
    List<InvoiceItem> fetchInvoiceItemsByInvoiceId(@PathVariable int invoiceId);
}
