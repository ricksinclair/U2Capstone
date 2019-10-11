package com.trilogy.cloudgamestoreproductservice.dao;

import com.trilogy.cloudgamestoreproductservice.model.Product;

import java.util.List;

public interface ProductDao {

    Product insertProduct(Product product);

    Product selectProduct(Integer productId);

    List<Product> selectAllProducts();

    void updateProduct(Product product);

    void deleteProduct(Integer productId);
}
