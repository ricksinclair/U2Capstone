package com.trilogy.cloudgamestoreproductservice.service;

import com.trilogy.cloudgamestoreproductservice.dao.ProductDao;
import com.trilogy.cloudgamestoreproductservice.model.Inventory;
import com.trilogy.cloudgamestoreproductservice.model.Invoice;
import com.trilogy.cloudgamestoreproductservice.model.InvoiceItem;
import com.trilogy.cloudgamestoreproductservice.model.Product;
import com.trilogy.cloudgamestoreproductservice.util.feign.InventoryClient;
import com.trilogy.cloudgamestoreproductservice.util.feign.InvoiceClient;
import com.trilogy.cloudgamestoreproductservice.util.feign.InvoiceItemClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ServiceLayer {

    private ProductDao productDao;

    private InvoiceItemClient invoiceItemClient;

    private InvoiceClient invoiceClient;

    private InventoryClient inventoryClient;

    @Autowired
    public ServiceLayer(ProductDao productDao, InvoiceClient invoiceClient, InvoiceItemClient invoiceItemClient, InventoryClient inventoryClient){

        this.productDao = productDao;
        this.invoiceClient = invoiceClient;
        this.invoiceItemClient = invoiceItemClient;
        this.inventoryClient = inventoryClient;
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

    public List<Product> getProductsByInvoiceId(int invoiceId){

        //Check to see if invoice exists

        Invoice invoice = invoiceClient.fetchOneInvoice(invoiceId);
        List<InvoiceItem> invoiceItems = new ArrayList<>();
        List<Product> productList = new ArrayList<>();
        if(invoice == null){
            throw new IllegalArgumentException("No invoice for invoiceId found");
        }else{

          invoiceItems = invoiceItemClient.fetchInvoiceItemsByInvoiceId(invoiceId);
        }

        invoiceItems.forEach(invoiceItem -> {
            Inventory inventory = inventoryClient.getInventory (invoiceItem.getInventoryId());
            Product product = productDao.selectProduct(inventory.getProductId());

            productList.add(product);




        });
        return productList;
    }
}
