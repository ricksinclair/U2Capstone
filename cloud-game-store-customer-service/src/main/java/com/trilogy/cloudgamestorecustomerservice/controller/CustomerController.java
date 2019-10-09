package com.trilogy.cloudgamestorecustomerservice.controller;


import com.trilogy.cloudgamestorecustomerservice.model.Customer;
import com.trilogy.cloudgamestorecustomerservice.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RefreshScope
@RestController
public class CustomerController {

    @Autowired
    ServiceLayer service;


    @RequestMapping(path = "/customer", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Customer addCustomer(@RequestBody @Valid Customer customer){
        return service.addCustomer(customer);
    }

    @RequestMapping(path = "/customer/{customerId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Customer getCustomer(@PathVariable int customerId){
        return service.getCustomer(customerId);
    }


    @RequestMapping(path = "/customer", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> getAllCustomers(){
        return service.getAllCustomers();
    }


    @RequestMapping(path = "/customer", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateCustomer(@RequestBody Customer customer){
        service.updateCustomer(customer);
    }

    @RequestMapping(path = "/customer/{customerId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable int customerId){
        service.deleteCustomer(customerId);
    }
}
