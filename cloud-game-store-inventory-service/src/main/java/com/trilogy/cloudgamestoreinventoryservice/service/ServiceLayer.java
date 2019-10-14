package com.trilogy.cloudgamestoreinventoryservice.service;
import com.trilogy.cloudgamestoreinventoryservice.dao.InventoryDao;
import com.trilogy.cloudgamestoreinventoryservice.model.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ServiceLayer {

    private InventoryDao inventoryDao;

    @Autowired
    public ServiceLayer(InventoryDao inventoryDao) {this.inventoryDao = inventoryDao;}

    /*************************************************
     * Inventory Service API
     *************************************************/
    /**
     * Saves an inventory to the database
     * @param inventory
     * @return
     */
    public Inventory saveInventory(Inventory inventory) {
        return inventoryDao.addInventory(inventory);
    }
    /**
     * Retrieves an inventory from database given a valid Inventory ID
     * @param inventoryId
     * @return
     */
    public Inventory fetchInventory(int inventoryId) {
        return inventoryDao.getInventory(inventoryId);
    }
    /**
     * Retrieves all inventories from the database
     * @return
     */
    public List<Inventory> fetchAllInventories() {
        return inventoryDao.getAllInventories();
    }
    /**
     * Updates an inventory when passed a valid inventory
     * @param inventory
     */
    public void updateInventory(Inventory inventory) {
        inventoryDao.updateInventory(inventory);
    }
    /**
     * Deletes an inventory when given a valid inventory ID
     * @param inventoryId
     */
    public void deleteInventory(int inventoryId) {
        inventoryDao.deleteInventory(inventoryId);
    }


    public Inventory fetchInventoryByProduct(int productId){
        return inventoryDao.getInventoryByProductId(productId);
    }
}
