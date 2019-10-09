package com.trilogy.cloudgamestorecustomerservice.service;


import com.netflix.discovery.converters.Auto;
import com.trilogy.cloudgamestorecustomerservice.dao.CustomerDao;
import com.trilogy.cloudgamestorecustomerservice.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServiceLayer {

    @Autowired
    CustomerDao customerDao;


    @Autowired
    public ServiceLayer(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }


    public Customer addCustomer(Customer customer){
       return  customerDao.insertCustomer(customer);
    }

    public Customer getCustomer(int customerId){
        return customerDao.selectCustomer(customerId);
    }

    public List<Customer> getAllCustomers(){
        return customerDao.selectAllCustomers();
    }

    public void updateCustomer(Customer customer){
        customerDao.updateCustomer(customer);
    }

    public void deleteCustomer(int customerId){
        customerDao.deleteCustomer(customerId);
    }

}
