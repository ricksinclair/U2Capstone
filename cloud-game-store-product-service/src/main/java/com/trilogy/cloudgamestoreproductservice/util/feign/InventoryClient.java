package com.trilogy.cloudgamestoreproductservice.util.feign;


import com.trilogy.cloudgamestoreproductservice.model.Inventory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

@FeignClient(name = "inventory-service")
public interface InventoryClient {

    @GetMapping(value = "/inventory/{inventoryId}")
    @ResponseStatus(HttpStatus.OK)
    Inventory getInventory(@PathVariable int inventoryId);
}
