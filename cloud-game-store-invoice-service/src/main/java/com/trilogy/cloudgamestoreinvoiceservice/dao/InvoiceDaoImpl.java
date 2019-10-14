package com.trilogy.cloudgamestoreinvoiceservice.dao;

import com.trilogy.cloudgamestoreinvoiceservice.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class InvoiceDaoImpl implements InvoiceDao {

    private final String INSERT_INVOICE_SQL =
            "insert into invoice (customer_id, purchase_date) " +
                    "values (?, ?)";

    private final String SELECT_INVOICE_SQL =
            "select * from invoice where invoice_id = ?";

    private final String SELECT_ALL_INVOICES_SQL =
            "select * from invoice";

    private final String UPDATE_INVOICE_SQL =
            "update invoice set customer_id = ?, purchase_date = ? where invoice_id = ?";

    private final String DELETE_INVOICE_SQL =
            "delete from invoice where invoice_id = ?";

    private final String SELECT_ALL_INVOICES_BY_CUSTOMER_ID_SQL =
            "select * from invoice where customer_id = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public InvoiceDaoImpl(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}

    private Invoice mapRowToInvoice(ResultSet rs, int rowNum) throws SQLException {
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(rs.getInt("invoice_id"));
        invoice.setCustomerId(rs.getInt("customer_id"));
        invoice.setPurchaseDate(rs.getDate("purchase_date").toLocalDate());
        return invoice;
    }
    @Override
    @Transactional
    public Invoice addInvoice(Invoice invoice) {

        jdbcTemplate.update(INSERT_INVOICE_SQL,
                invoice.getCustomerId(),
                invoice.getPurchaseDate());

        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        invoice.setInvoiceId(id);
        return invoice;
    }

    @Override
    public Invoice getInvoice(int id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_INVOICE_SQL, this::mapRowToInvoice, id);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Invoice> getAllInvoices() {
        return jdbcTemplate.query(SELECT_ALL_INVOICES_SQL, this::mapRowToInvoice);
    }

    @Override
    public void updateInvoice(Invoice invoice) {
        jdbcTemplate.update(UPDATE_INVOICE_SQL,
                invoice.getCustomerId(),
                invoice.getPurchaseDate(),
                invoice.getInvoiceId());
    }

    @Override
    public void deleteInvoice(int id) {
        jdbcTemplate.update(DELETE_INVOICE_SQL, id);
    }

    @Override
    public List<Invoice> getAllInvoiceByCustomerId(int customerId) {
        return jdbcTemplate.query(SELECT_ALL_INVOICES_BY_CUSTOMER_ID_SQL, this::mapRowToInvoice, customerId);
    }
}
