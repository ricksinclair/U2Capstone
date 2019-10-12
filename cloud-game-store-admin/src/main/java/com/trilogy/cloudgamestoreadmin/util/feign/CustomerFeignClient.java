package com.trilogy.cloudgamestoreadmin.util.feign;


import com.trilogy.cloudgamestoreadmin.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "customer-service")
public interface CustomerFeignClient {

    @RequestMapping(path = "/customer", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    Customer addCustomer(@RequestBody @Valid Customer customer);

    @RequestMapping(path = "/customer/{customerId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    Customer getCustomer(@PathVariable int customerId);

    @RequestMapping(path = "/customer", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    List<Customer> getAllCustomers();

    @RequestMapping(path = "/customer", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.ACCEPTED)
    void updateCustomer(@RequestBody Customer customer);

    @RequestMapping(path = "/customer/{customerId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteCustomer(@PathVariable int customerId);
}
