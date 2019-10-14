package com.trilogy.cloudgamestoreinventoryservice.dao;

import com.trilogy.cloudgamestoreinventoryservice.model.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class InventoryDaoImpl implements InventoryDao {

    private final String INSERT_INVENTORY_SQL =
            "insert into inventory (product_id, quantity) " +
                    "values(?, ?)";

    private final String SELECT_INVENTORY_SQL =
            "select * from inventory where inventory_id = ?";

    private final String SELECT_ALL_INVENTORIES_SQL =
            "select * from inventory";

    private final String UPDATE_INVENTORY_SQL =
            "update inventory set product_id = ?, quantity = ? where inventory_id = ?";

    private final String DELETE_INVENTORY_SQL =
            "delete from inventory where inventory_id = ?";

    private final String SELECT_INVENTORY_BY_PRODUCT_SQL =
            "select * from inventory where product_id = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    InventoryDaoImpl(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}

    private Inventory mapRowToInventory(ResultSet rs, int rowNum) throws SQLException {
        Inventory inventory = new Inventory();
        inventory.setInventoryId(rs.getInt("inventory_id"));
        inventory.setProductId(rs.getInt("product_id"));
        inventory.setQuantity(rs.getInt("quantity"));
        return inventory;
    }

    @Override
    @Transactional
    public Inventory addInventory(Inventory inventory) {
        jdbcTemplate.update(INSERT_INVENTORY_SQL,
                inventory.getProductId(),
                inventory.getQuantity());

        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        inventory.setInventoryId(id);
        return inventory;
    }

    @Override
    public Inventory getInventory(int inventoryId) {
        try {
            return jdbcTemplate.queryForObject(SELECT_INVENTORY_SQL, this::mapRowToInventory, inventoryId);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Inventory> getAllInventories() {
        return jdbcTemplate.query(SELECT_ALL_INVENTORIES_SQL, this::mapRowToInventory);
    }

    @Override
    public void updateInventory(Inventory inventory) {
        jdbcTemplate.update(UPDATE_INVENTORY_SQL,
                inventory.getProductId(),
                inventory.getQuantity(),
                inventory.getInventoryId());
    }

    @Override
    public void deleteInventory(int inventoryId) {
        jdbcTemplate.update(DELETE_INVENTORY_SQL, inventoryId);
    }

    @Override
    public Inventory getInventoryByProductId(int productId) {
        try {
            return jdbcTemplate.queryForObject(SELECT_INVENTORY_BY_PRODUCT_SQL, this::mapRowToInventory, productId);
        } catch (Exception e) {
            return null;
        }
    }
}

