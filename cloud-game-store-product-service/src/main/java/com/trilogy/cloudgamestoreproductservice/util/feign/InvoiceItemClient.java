package com.trilogy.cloudgamestoreproductservice.util.feign;

import com.trilogy.cloudgamestoreproductservice.model.InvoiceItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@FeignClient(name = "invoice-service")
public interface InvoiceItemClient {

    @GetMapping(value = "/invoiceItemsByInvoiceId/{invoiceId}")
    @ResponseStatus(HttpStatus.OK)
    List<InvoiceItem> fetchInvoiceItemsByInvoiceId(@PathVariable int invoiceId);

}
