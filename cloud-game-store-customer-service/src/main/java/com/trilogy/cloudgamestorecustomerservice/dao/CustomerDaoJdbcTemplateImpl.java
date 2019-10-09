package com.trilogy.cloudgamestorecustomerservice.dao;

import com.trilogy.cloudgamestorecustomerservice.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
public class CustomerDaoJdbcTemplateImpl implements CustomerDao {


    private final String INSERT_CUSTOMER_SQL =
            "INSERT INTO customer (first_name, last_name, street, city, zip, email, phone) VALUES (?,?,?,?,?,?,?)";

    private final String SELECT_CUSTOMER_SQL =
            "SELECT * FROM customer where customer_id = ?";

    private final String SELECT_ALL_CUSTOMERS_SQL =
            "SELECT * FROM customer";

    private final String UPDATE_CUSTOMER_SQL =
            "UPDATE customer SET first_name = ?, last_name = ?, street = ?, city = ?, zip = ?, email = ?, phone = ? WHERE customer_id = ?";

    private final String DELETE_CUSTOMER_SQL =
            "DELETE FROM customer WHERE customer_id = ?";

    private JdbcTemplate jdbcTemplate;


    @Autowired
    public CustomerDaoJdbcTemplateImpl(JdbcTemplate newJdbcTemplate) {
        this.jdbcTemplate = newJdbcTemplate;
    }


    @Override
    @Transactional
    public Customer insertCustomer(Customer customer) {
        jdbcTemplate.update(INSERT_CUSTOMER_SQL,
                customer.getFirstName(),
                customer.getLastName(),
                customer.getStreet(),
                customer.getCity(),
                customer.getZip(),
                customer.getEmail(),
                customer.getPhone()


        );
        int id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        customer.setCustomerId(id);

        return customer;
    }

    @Override
    public Customer selectCustomer(int customerId) {

        try {
            return jdbcTemplate.queryForObject(SELECT_CUSTOMER_SQL, this::mapRowToCustomer, customerId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Customer> selectAllCustomers() {
        return jdbcTemplate.query(SELECT_ALL_CUSTOMERS_SQL, this::mapRowToCustomer);
    }

    @Override
    public void updateCustomer(Customer customer) {

        jdbcTemplate.update(UPDATE_CUSTOMER_SQL,
                customer.getFirstName(),
                customer.getLastName(),
                customer.getStreet(),
                customer.getCity(),
                customer.getZip(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getCustomerId()


        );

    }

    @Override
    public void deleteCustomer(int customerId) {
        jdbcTemplate.update(DELETE_CUSTOMER_SQL, customerId);

    }


    private Customer mapRowToCustomer(ResultSet rs, int rowNum) throws SQLException {

        Customer customer = new Customer();

        customer.setCustomerId(rs.getInt("customer_id"));
        customer.setFirstName(rs.getString("first_name"));
        customer.setLastName(rs.getString("last_name"));
        customer.setStreet(rs.getString("street"));
        customer.setCity(rs.getString("city"));
        customer.setZip(rs.getString("zip"));
        customer.setEmail(rs.getString("email"));
        customer.setPhone(rs.getString("phone"));

        return customer;
    }
}
