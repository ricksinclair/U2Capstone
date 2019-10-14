package com.trilogy.cloudgamestoreadmin.util.feign;

import com.trilogy.cloudgamestoreadmin.model.Inventory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "inventory-service")
public interface InventoryFeignClient {

    @PostMapping(value = "/inventory")
    @ResponseStatus(HttpStatus.CREATED)
     Inventory saveInventory(@RequestBody @Valid Inventory inventory);

    @PutMapping(value = "/inventory")
    @ResponseStatus(HttpStatus.OK)
    void updateInventory(@RequestBody @Valid Inventory inventory);

    @GetMapping(value = "/inventory")
    @ResponseStatus(HttpStatus.OK)
    List<Inventory> getAllInventories();

    @GetMapping(value = "/inventory/{inventoryId}")
    @ResponseStatus(HttpStatus.OK)
    Inventory getInventory(@PathVariable int inventoryId);

    @DeleteMapping(value = "/inventory/{inventoryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteInventory(@PathVariable int inventoryId);

    @GetMapping(value = "/inventory/product/{productId}")
    @ResponseStatus(HttpStatus.OK)
    Inventory getInventoryByProductId(@PathVariable int productId);


}
