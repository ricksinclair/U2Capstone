package com.trilogy.cloudgamestoreretail.controller;

import com.trilogy.cloudgamestoreretail.model.Product;
import com.trilogy.cloudgamestoreretail.service.ServiceLayer;
import com.trilogy.cloudgamestoreretail.viewmodel.InvoiceViewModel;
import com.trilogy.cloudgamestoreretail.viewmodel.PurchaseViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RefreshScope
public class RetailAPIController {

    @Autowired
    ServiceLayer serviceLayer;


    @RequestMapping(value = "/invoices", method = RequestMethod.POST)
    public InvoiceViewModel submitInvoice(@RequestBody PurchaseViewModel pvm) {
        return serviceLayer.purchaseProduct(pvm);
    }

    @RequestMapping(value = "/invoices/{id}", method = RequestMethod.GET)
    public InvoiceViewModel getInvoiceById(@PathVariable int id) {
        return serviceLayer.retrieveInvoice(id);
    }

    @RequestMapping(value = "/invoices", method = RequestMethod.GET)
    public List<InvoiceViewModel> getAllInvoices() {
        return serviceLayer.retrieveAllInvoices();
    }

    @RequestMapping(value = "/invoices/customer/{id}", method = RequestMethod.GET)
    public List<InvoiceViewModel> getInvoicesByCustomerId(@PathVariable int id) {
        return serviceLayer.retrieveAllInvoicesByCustomerId(id);
    }

    @RequestMapping(value = "/products/inventory", method = RequestMethod.GET)
    public List<Product> getProductsInInventory() {
        return serviceLayer.getAllProductsInInventory();
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public Product getProductById(@PathVariable int id) {
        return serviceLayer.getProduct(id);
    }

    @RequestMapping(value = "/products/invoice/{id}", method = RequestMethod.GET)
    public List<Product> getProductByInvoiceId(@PathVariable int id) {
        return serviceLayer.getAllProductsOnInvoice(id);
    }

    @RequestMapping(value = "/levelup/customer/{id}", method = RequestMethod.GET)
    public int getLevelUpPointsByCustomerId(int id) {
        return serviceLayer.fetchLevelUpPointsByCustomerId(id);
    }
}
