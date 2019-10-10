package com.trilogy.cloudgamestoreproductservice.dao;


import com.trilogy.cloudgamestoreproductservice.model.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProductDaoJdbcTemplateImplTest {

    @Autowired
    ProductDao productDao;

    @Before
    public void setUp() throws Exception{
        List<Product> productList = productDao.selectAllProducts();

        productList.forEach(product ->
        {
            productDao.deleteProduct(product.getProductId());
        });
    }



    @Test
    public void addGetDeleteProduct(){
        Product product = new Product();
        product.setProductName("Call of Duty: Modern Warfare");
        product.setProductDescription("A modern reboot of a classic franchise.");
        product.setListPrice(new BigDecimal("59.99"));
        product.setUnitCost(new BigDecimal("39.99"));
        product = productDao.insertProduct(product);
        Product fromRepo = productDao.selectProduct(product.getProductId());
        assertEquals(fromRepo, product);
        productDao.deleteProduct(product.getProductId());
        fromRepo = productDao.selectProduct(product.getProductId());
        assertNull(fromRepo);
    }


    @Test
    public void getAllProducts(){
        Product product = new Product();
        product.setProductName("Call of Duty: Modern Warfare");
        product.setProductDescription("A modern reboot of a classic franchise.");
        product.setListPrice(new BigDecimal("59.99"));
        product.setUnitCost(new BigDecimal("39.99"));
        product = productDao.insertProduct(product);

        Product product2 = new Product();
        product2.setProductName("Battlefield: MoneyGrab");
        product2.setProductDescription("We're using wheel barrels for our bank deposits at Dice.");
        product2.setListPrice(new BigDecimal("59.99"));
        product2.setUnitCost(new BigDecimal("39.99"));
        product2 = productDao.insertProduct(product2);

        List<Product> productList = new ArrayList<>();

        productList.add(product);
        productList.add(product2);

        List<Product> fromRepo = productDao.selectAllProducts();

        assertEquals(fromRepo, productList);
    }


    @Test
    public void updateProduct(){

        Product product = new Product();
        product.setProductName("Call of Duty: Modern Warfare");
        product.setProductDescription("A modern reboot of a classic franchise.");
        product.setListPrice(new BigDecimal("59.99"));
        product.setUnitCost(new BigDecimal("39.99"));
        product = productDao.insertProduct(product);
        product.setListPrice(new BigDecimal("49.99"));
        productDao.updateProduct(product);
        Product fromRepo = productDao.selectProduct(product.getProductId());
        assertEquals(fromRepo, product);
    }
}
