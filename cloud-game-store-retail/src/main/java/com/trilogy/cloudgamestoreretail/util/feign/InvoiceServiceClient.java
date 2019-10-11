package com.trilogy.cloudgamestoreretail.util.feign;

import com.trilogy.cloudgamestoreretail.model.Invoice;
import com.trilogy.cloudgamestoreretail.model.InvoiceItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;

@FeignClient(name = "invoice-service")
public interface InvoiceServiceClient {

    @PostMapping(value = "/invoice")
    @ResponseStatus(HttpStatus.CREATED)
    Invoice createInvoice(@RequestBody @Valid Invoice invoice);

    @PostMapping(value = "/invoiceItem")
    @ResponseStatus(HttpStatus.CREATED)
    InvoiceItem createInvoiceItem(@RequestBody @Valid InvoiceItem invoiceItem);

}

