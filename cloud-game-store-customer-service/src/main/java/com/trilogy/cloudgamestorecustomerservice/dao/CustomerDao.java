package com.trilogy.cloudgamestorecustomerservice.dao;

import com.trilogy.cloudgamestorecustomerservice.model.Customer;

import java.util.List;

public interface CustomerDao {

    Customer insertCustomer(Customer customer);

    Customer selectCustomer(int customerId);

    List<Customer> selectAllCustomers();

    void updateCustomer(Customer customer);

    void deleteCustomer(int customerId);
}
