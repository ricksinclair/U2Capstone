package com.trilogy.cloudgamestoreretail.util.feign;

import com.trilogy.cloudgamestoreretail.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "customer-service")
public interface CustomerServiceClient {
    @RequestMapping(path = "/customer/{customerId}", method = RequestMethod.GET)
    Customer getCustomer(@PathVariable(value = "customerId") int customerId);

}
