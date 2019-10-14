package com.trilogy.cloudgamestoreretail.util.feign;

import com.trilogy.cloudgamestoreretail.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@FeignClient(name = "product-service")
public interface ProductServiceClient {

    @RequestMapping(value = "/product/{productId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    Product getProduct(@PathVariable int productId);

    @RequestMapping(value= "/product", method = RequestMethod.GET)
    List<Product> getAllProducts();
}
