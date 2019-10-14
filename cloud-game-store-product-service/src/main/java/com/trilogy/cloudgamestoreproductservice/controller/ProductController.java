package com.trilogy.cloudgamestoreproductservice.controller;

import com.trilogy.cloudgamestoreproductservice.model.Product;
import com.trilogy.cloudgamestoreproductservice.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RefreshScope
@RestController
public class ProductController {

    @Autowired
    ServiceLayer service;

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Product addProduct(@RequestBody @Valid Product product){
        return service.addProduct(product);
    }


    @RequestMapping(value = "/product/{productId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Product getProduct(@PathVariable int productId){
        return service.getProduct(productId);
    }

    @RequestMapping(value= "/product", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAllProducts(){
        return service.getAllProducts();
    }

    @RequestMapping(value = "/product", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateProduct(@RequestBody @Valid Product product){
        service.updateProduct(product);
    }

    @RequestMapping(value = "/product/{productId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable int productId){
        service.deleteProduct(productId);
    }


    @RequestMapping(value = "/product/Invoice/{invoiceId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getProductsByInvoiceId( @PathVariable int invoiceId){
        return service.getProductsByInvoiceId(invoiceId);
    }
}
