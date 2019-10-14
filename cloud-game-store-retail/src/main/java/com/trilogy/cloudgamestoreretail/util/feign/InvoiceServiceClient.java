package com.trilogy.cloudgamestoreretail.util.feign;

import com.trilogy.cloudgamestoreretail.model.Invoice;
import com.trilogy.cloudgamestoreretail.model.InvoiceItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "invoice-service")
public interface InvoiceServiceClient {

    @PostMapping(value = "/invoice")
    @ResponseStatus(HttpStatus.CREATED)
    Invoice createInvoice(@RequestBody @Valid Invoice invoice);

    @PostMapping(value = "/invoiceItem")
    @ResponseStatus(HttpStatus.CREATED)
    InvoiceItem createInvoiceItem(@RequestBody @Valid InvoiceItem invoiceItem);

    @GetMapping(value = "/invoiceItemsByInvoiceId/{invoiceId}")
    @ResponseStatus(HttpStatus.OK)
    List<InvoiceItem> fetchInvoiceItemsByInvoiceId(@PathVariable int invoiceId);

    @GetMapping(value = "/invoice/{invoiceId}")
    Invoice fetchOneInvoice(@PathVariable int invoiceId);

    @GetMapping(value = "/invoice")
    List<Invoice> getAllInvoices();

    @GetMapping(value = "/invoice/customer/{customerId}")
    List<Invoice> fetchInvoicesByCustomerId(@PathVariable int customerId);
}

