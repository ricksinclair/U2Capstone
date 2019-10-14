package com.trilogy.cloudgamestoreinventoryservice.dao;

import com.trilogy.cloudgamestoreinventoryservice.model.Inventory;

import java.util.List;

public interface InventoryDao {

    Inventory addInventory(Inventory inventory);

    Inventory getInventory(int inventoryId);

    List<Inventory> getAllInventories();

    void updateInventory(Inventory inventory);

    void deleteInventory(int inventoryId);

    Inventory getInventoryByProductId(int productId);
}
