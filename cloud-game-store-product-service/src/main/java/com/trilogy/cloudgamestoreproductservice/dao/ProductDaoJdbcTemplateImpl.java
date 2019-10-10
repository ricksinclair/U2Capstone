package com.trilogy.cloudgamestoreproductservice.dao;

import com.trilogy.cloudgamestoreproductservice.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductDaoJdbcTemplateImpl implements ProductDao {

    private JdbcTemplate   jdbcTemplate;

    @Autowired
    public ProductDaoJdbcTemplateImpl(JdbcTemplate newJdbcTemplate){
        this.jdbcTemplate = newJdbcTemplate;
    }


    private final static String INSERT_PRODUCT_SQL =
            "INSERT INTO product (product_name, product_description, list_price, unit_cost) VALUES (?, ?, ?, ?)";

    private final static String SELECT_PRODUCT_SQL =
            "SELECT * FROM product WHERE product_id = ?";

    private final static String SELECT_ALL_PRODUCTS_SQL =
            "SELECT * FROM product";

    private final static String UPDATE_PRODUCT_SQL =
            "UPDATE product SET product_name = ?, product_description = ?, list_price = ?, unit_cost = ? WHERE product_id = ?";

    private final static String DELETE_PRODUCT_SQL =
            "DELETE FROM product WHERE product_id = ?";



    private Product mapRowToProduct(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();

        product.setProductId(rs.getInt("product_id"));
        product.setProductName(rs.getString("product_name"));
        product.setProductDescription(rs.getString("product_description"));
        product.setListPrice(rs.getBigDecimal("list_price"));
        product.setUnitCost(rs.getBigDecimal("unit_cost"));
        return product;
    }

    @Override
    @Transactional
    public Product insertProduct(Product product) {
        jdbcTemplate.update(INSERT_PRODUCT_SQL,
                            product.getProductName(),
                            product.getProductDescription(),
                            product.getListPrice(),
                            product.getUnitCost()
                );

        int id = jdbcTemplate.queryForObject("SELECT last_insert_id()", Integer.class);

        product.setProductId(id);

        return product;
    }

    @Override
    public Product selectProduct(Integer productId) {

        try{
       return  jdbcTemplate.queryForObject(SELECT_PRODUCT_SQL, this::mapRowToProduct, productId);
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public List<Product> selectAllProducts() {
        return jdbcTemplate.query(SELECT_ALL_PRODUCTS_SQL, this::mapRowToProduct);
    }

    @Override
    public void updateProduct(Product product) {

        jdbcTemplate.update(UPDATE_PRODUCT_SQL, product.getProductName(),
                product.getProductDescription(),
                product.getListPrice(),
                product.getUnitCost(),
                product.getProductId()
                );

    }

    @Override
    public void deleteProduct(Integer productId) {

        jdbcTemplate.update(DELETE_PRODUCT_SQL, productId);
    }
}
