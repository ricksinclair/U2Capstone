package com.trilogy.cloudgamestoreretail.util.feign;

import com.trilogy.cloudgamestoreretail.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@FeignClient(name = "customer-service")
public interface CustomerServiceClient {

    @RequestMapping(path = "/customer/{customerId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Customer getCustomer(@PathVariable int customerId);


}
