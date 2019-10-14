package com.trilogy.cloudgamestoreinventoryservice.controller;

import com.trilogy.cloudgamestoreinventoryservice.model.Inventory;
import com.trilogy.cloudgamestoreinventoryservice.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class InventoryController {

    @Autowired
    ServiceLayer serviceLayer;

    /***************************************
     * Inventory Service API Controller
     * Path: /inventory
     **************************************/

    /**
     * Saves inventory when passed a valid inventory
     * @param inventory
     * @return
     */
    @PostMapping(value = "/inventory")
    @ResponseStatus(HttpStatus.CREATED)
    public Inventory saveInventory(@RequestBody @Valid Inventory inventory) {
        return serviceLayer.saveInventory(inventory);
    }

    /**
     * Updates an existing inventory when passed a valid inventory
     * @param inventory
     */
    @PutMapping(value = "/inventory")
    @ResponseStatus(HttpStatus.OK)
    public void updateInventory(@RequestBody @Valid Inventory inventory) {
        serviceLayer.updateInventory(inventory);
    }

    /**
     * Retrieves all inventories from the database
     * @return
     */
    @GetMapping(value = "/inventory")
    @ResponseStatus(HttpStatus.OK)
    public List<Inventory> getAllInventories() {
        return serviceLayer.fetchAllInventories();
    }

    /***************************************
     * Inventory Service API Controller
     * Path: /inventory/{inventoryId}
     **************************************/

    /**
     * Retrieves one inventory from the database when given a valid inventory ID
     * @param inventoryId
     * @return
     */
    @GetMapping(value = "/inventory/{inventoryId}")
    @ResponseStatus(HttpStatus.OK)
    public Inventory getInventory(@PathVariable int inventoryId) {
        return serviceLayer.fetchInventory(inventoryId);
    }

    /**
     * Deletes an inventory when passed a a valid inventory ID
     * @param inventoryId
     */
    @DeleteMapping(value = "/inventory/{inventoryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInventory(@PathVariable int inventoryId) {
        serviceLayer.deleteInventory(inventoryId);
    }




    /**
     * Selects inventory when passed a a valid product ID
     * @param productId
     * @return
     */

    @GetMapping(value = "/inventory/product/{productId}")
    @ResponseStatus(HttpStatus.OK)

    public Inventory getInventoryByProductId(@PathVariable int productId){
        return serviceLayer.fetchInventoryByProduct(productId);
    }
}
