package com.trilogy.cloudgamestoreadmin.util.feign;


import com.trilogy.cloudgamestoreadmin.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "product-service")
public interface ProductFeignClient {
    @RequestMapping(value = "/product", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    Product addProduct(@RequestBody @Valid Product product);

    @RequestMapping(value = "/product/{productId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    Product getProduct(@PathVariable int productId);

    @RequestMapping(value= "/product", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    List<Product> getAllProducts();

    @RequestMapping(value = "/product", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.ACCEPTED)
    void updateProduct(@RequestBody @Valid Product product);

    @RequestMapping(value = "/product/{productId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteProduct(@PathVariable int productId);

    @RequestMapping(value = "/product/Invoice/{invoiceId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
     List<Product> getProductsByInvoiceId(@PathVariable int invoiceId);

}
