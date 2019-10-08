package com.trilogy.cloudgamestoreinvoiceservice.dao;

import com.trilogy.cloudgamestoreinvoiceservice.model.InvoiceItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class InvoiceItemDaoImpl implements InvoiceItemDao {

    private final String INSERT_INVOICE_ITEM_SQL =
            "insert into invoice_item (invoice_id, inventory_id, quantity, unit_price) " +
                    "values (?, ?, ?, ?)";

    private final String SELECT_INVOICE_ITEM_SQL =
            "select * from invoice_item where invoice_item_id = ?";

    private final String SELECT_ALL_INVOICE_ITEMS_SQL =
            "select * from invoice_item";

    private final String SELECT_INVOICE_ITEMS_BY_INVOICE_ID_SQL =
            "select * from invoice_item where invoice_id = ?";

    private final String UPDATE_INVOICE_ITEM_SQL =
            "update invoice_item set invoice_id = ?, inventory_id = ?, quantity = ?, unit_price = ? " +
                    "where invoice_item_id = ?";

    private final String DELETE_INVOICE_ITEM_SQL =
            "delete from invoice_item where invoice_item_id = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public InvoiceItemDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private InvoiceItem mapRowToInvoiceItem(ResultSet rs, int rowNum) throws SQLException {
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceItemId(rs.getInt("invoice_item_id"));
        invoiceItem.setInvoiceId(rs.getInt("invoice_id"));
        invoiceItem.setInventoryId(rs.getInt("inventory_id"));
        invoiceItem.setQuantity(rs.getInt("quantity"));
        invoiceItem.setUnitPrice(rs.getBigDecimal("unit_price"));
        return invoiceItem;
    }

    @Override
    @Transactional
    public InvoiceItem addInvoiceItem(InvoiceItem invoiceItem) {
        jdbcTemplate.update(INSERT_INVOICE_ITEM_SQL,
                invoiceItem.getInvoiceId(),
                invoiceItem.getInventoryId(),
                invoiceItem.getQuantity(),
                invoiceItem.getUnitPrice());
        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        invoiceItem.setInvoiceItemId(id);
        return invoiceItem;
    }

    @Override
    public InvoiceItem getInvoiceItem(int id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_INVOICE_ITEM_SQL, this::mapRowToInvoiceItem, id);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<InvoiceItem> getAllInvoiceItems() {
        return jdbcTemplate.query(SELECT_ALL_INVOICE_ITEMS_SQL, this::mapRowToInvoiceItem);
    }

    @Override
    public void updateInvoiceItem(InvoiceItem invoiceItem) {
        jdbcTemplate.update(UPDATE_INVOICE_ITEM_SQL,
                invoiceItem.getInvoiceId(),
                invoiceItem.getInventoryId(),
                invoiceItem.getQuantity(),
                invoiceItem.getUnitPrice(),
                invoiceItem.getInvoiceItemId());
    }

    @Override
    public void deleteInvoiceItem(int id) {
        jdbcTemplate.update(DELETE_INVOICE_ITEM_SQL, id);
    }

    @Override
    public List<InvoiceItem> getAllInvoiceItemsByInvoiceId(int invoiceId) {
        return jdbcTemplate.query(SELECT_INVOICE_ITEMS_BY_INVOICE_ID_SQL, this::mapRowToInvoiceItem, invoiceId);
    }
}
