package com.trilogy.cloudgamestoreinventoryservice.dao;
import com.trilogy.cloudgamestoreinventoryservice.model.Inventory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.List;
import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InventoryDaoTest {
    @Autowired
    protected InventoryDao inventoryDao;
    @Before
    public void setUp() {
        //Cleans up test DB
        List<Inventory> inventories = inventoryDao.getAllInventories();
        inventories.stream()
                .forEach(inventory -> inventoryDao.deleteInventory(inventory.getInventoryId()));
    }
    @Test
    public void addGetDeleteInventory() {
        Inventory inventory = new Inventory(1,100);
        //Adds inventory to Database and returns with inventory and inventory ID
        inventory = inventoryDao.addInventory(inventory);
        //Tests get all and asserts Inventory was added to the database
        assertEquals(1, inventoryDao.getAllInventories().size());
        //Tests get one inventory from database
        Inventory inventoryRetrieved = inventoryDao.getInventory(inventory.getInventoryId());
        //Asserts the inventory retrieved from database is same as the one previously saved
        assertEquals(inventory, inventoryRetrieved);
        Inventory inventory1 = new Inventory(2, 200);
        inventory1 = inventoryDao.addInventory(inventory1);
        //Asserts second inventory was added and get all is working correctly
        assertEquals(2, inventoryDao.getAllInventories().size());
        //Deletes one inventory
        inventoryDao.deleteInventory(inventory.getInventoryId());
        //Asserts one inventory was successfully deleted
        assertEquals(1, inventoryDao.getAllInventories().size());
    }
    @Test
    public void getAllInventories() {
        Inventory inventory = new Inventory(1,100);
        //Adds inventory to Database and returns with inventory and inventory ID
        inventory = inventoryDao.addInventory(inventory);
        //Adds second inventory
        Inventory inventory1 = new Inventory(2, 200);
        inventory1 = inventoryDao.addInventory(inventory1);
        assertEquals(2, inventoryDao.getAllInventories().size());
    }
    @Test
    public void updateInventory() {
        Inventory inventory = new Inventory(1,100);
        //Adds inventory to Database and returns with inventory and inventory ID
        inventory = inventoryDao.addInventory(inventory);
        //Updates inventory previously saved
        inventory.setProductId(5);
        inventory.setQuantity(500);
        inventoryDao.updateInventory(inventory);
        Inventory updatedInventory = inventoryDao.getInventory(inventory.getInventoryId());
        assertEquals(inventory, updatedInventory);
    }
}
