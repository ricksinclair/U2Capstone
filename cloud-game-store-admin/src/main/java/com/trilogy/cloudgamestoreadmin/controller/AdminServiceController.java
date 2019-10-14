package com.trilogy.cloudgamestoreadmin.controller;


import com.trilogy.cloudgamestoreadmin.model.*;
import com.trilogy.cloudgamestoreadmin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AdminServiceController {

    @Autowired
    AdminService service;

    //CUSTOMER ENDPOINTS
    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Customer addCustomer(@Valid @RequestBody Customer customer) {
        return service.addCustomer(customer);
    }

    @RequestMapping(value = "/customers/{customerId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Customer getCustomer(@PathVariable int customerId) {
        return service.getCustomer(customerId);
    }

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> getAllCustomers() {
        return service.getAllCustomers();
    }

    @RequestMapping(value = "/customers", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateCustomer(@Valid @RequestBody Customer customer) {
        service.updateCustomer(customer);
    }

    @RequestMapping(value = "/customers/{customerId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable int customerId) {
        service.deleteCustomer(customerId);
    }


    //PRODUCT ENDPOINTS
    @RequestMapping(value = "/products", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Product addProduct(@Valid @RequestBody Product product) {
        return service.addProduct(product);
    }

    @RequestMapping(value = "/products/{productId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Product getProduct(@PathVariable int productId) {
        return service.getProduct(productId);
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAllProducts() {
        return service.getAllProducts();

    }


    @RequestMapping(value = "/products", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateProducts(@Valid @RequestBody Product product) {
        service.updateProduct(product);
    }

    @RequestMapping(value = "/products/{productId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable int productId) {
        service.deleteProduct(productId);
    }


    //LEVEL UP ENDPOINTS


    @RequestMapping(value = "/levelUp", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public LevelUp addLevelUp(@Valid @RequestBody LevelUp levelUp) {
        return service.addLevelUp(levelUp);
    }

    @RequestMapping(value = "/levelUp/{levelUpId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public LevelUp getLevelUp(@PathVariable int levelUpId) {
        return service.getLevelUp(levelUpId);
    }

    @RequestMapping(value = "/levelUp", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<LevelUp> getAllLevelUps() {
        return service.getAllLevelUps();
    }

    @RequestMapping(value = "/levelUp", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateLevelUp(@Valid @RequestBody LevelUp levelUp) {
        service.updateLevelUp(levelUp);
    }


    @RequestMapping(value = "/levelUp/{levelUpId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLevelUp(@PathVariable int levelUpId) {
        service.deleteLevelUp(levelUpId);
    }


    //INVENTORY ENDPOINTS

    @RequestMapping(value = "/inventory", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)

    public Inventory addInventory(@Valid @RequestBody Inventory inventory) {
        return service.addInventory(inventory);
    }


    @RequestMapping(value = "/inventory/{inventoryId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Inventory getInventory(@PathVariable int inventoryId) {
        return service.getInventory(inventoryId);
    }

    @RequestMapping(value = "/inventory", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Inventory> getAllInventories() {

        return service.getAllInventory();
    }

    @RequestMapping(value = "/inventory", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateInventory(@Valid @RequestBody Inventory inventory){
        service.updateInventory(inventory);
    }


    @RequestMapping(value = "/inventory/{inventoryId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInventory(@PathVariable int inventoryId){
        service.deleteInventory(inventoryId);
    }


    //INVOICE ENDPOINTS
    @RequestMapping(value = "/invoice", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice addInvoice(@Valid @RequestBody Invoice invoice) {

        return service.addInvoice(invoice);
    }

    @RequestMapping(value = "/invoice/{invoiceId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Invoice getInvoice(@PathVariable int invoiceId){
        return service.getInvoice(invoiceId);
    }

    @RequestMapping(value = "/invoice", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Invoice> getAllInvoices(){
        return service.getAllInvoices();
    }



    @RequestMapping(value = "/invoice", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateInvoice(@Valid @RequestBody Invoice invoice){
        service.updateInvoice(invoice);
    }


    @RequestMapping(value = "/invoice/{invoiceId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvoice(@PathVariable int invoiceId){
        service.deleteInvoice(invoiceId);
    }




}





