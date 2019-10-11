package com.trilogy.cloudgamestoreretail.util.feign;

import com.trilogy.cloudgamestoreretail.model.Inventory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@FeignClient(name = "inventory-service")
public interface InventoryServiceClient {

    @GetMapping(value = "/inventory")
    @ResponseStatus(HttpStatus.OK)
    List<Inventory> getAllInventories();

    @GetMapping(value = "/inventory/{inventoryId}")
    @ResponseStatus(HttpStatus.OK)
    Inventory getInventory(@PathVariable int inventoryId);


}
