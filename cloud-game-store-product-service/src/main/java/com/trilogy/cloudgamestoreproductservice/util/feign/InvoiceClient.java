package com.trilogy.cloudgamestoreproductservice.util.feign;


import com.trilogy.cloudgamestoreproductservice.model.Invoice;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@FeignClient(name = "invoice-service")
public interface InvoiceClient {

    @GetMapping(value = "/invoice")
    @ResponseStatus(HttpStatus.OK)
    List<Invoice> getAllInvoices();

    @GetMapping(value = "/invoice/{invoiceId}")
    @ResponseStatus(HttpStatus.OK)
     Invoice fetchOneInvoice(@PathVariable int invoiceId);
}
