package com.trilogy.cloudgamestoreproductservice.service;

import com.trilogy.cloudgamestoreproductservice.dao.ProductDao;
import com.trilogy.cloudgamestoreproductservice.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServiceLayer {

    private ProductDao productDao;

    @Autowired
    public ServiceLayer(ProductDao productDao){
        this.productDao = productDao;
    }

    public Product addProduct(Product product){
        return productDao.insertProduct(product);
    }

    public Product getProduct(int productId){
        return productDao.selectProduct(productId);
    }

    public List<Product> getAllProducts(){
        return productDao.selectAllProducts();
    }

    public void updateProduct(Product product){
        productDao.updateProduct(product);
    }

    public void deleteProduct(int productId){
        productDao.deleteProduct(productId);
    }
}
