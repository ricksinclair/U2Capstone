package com.trilogy.cloudgamestoreadmin.service;


import com.trilogy.cloudgamestoreadmin.model.*;
import com.trilogy.cloudgamestoreadmin.util.feign.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.CustomSQLExceptionTranslatorRegistry;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.stream.Collectors;

@Component
public class AdminService {

    @Autowired
    private final CustomerFeignClient customerClient;

    @Autowired
    private final InventoryFeignClient inventoryClient;

    @Autowired
    private final InvoiceFeignClient invoiceClient;

    @Autowired
    private final LevelUpFeignClient levelUpClient;

    @Autowired
    private final ProductFeignClient productClient;


    public AdminService(CustomerFeignClient customerFeignClient,
                        InventoryFeignClient inventoryFeignClient,
                        InvoiceFeignClient invoiceFeignClient,
                        LevelUpFeignClient levelUpFeignClient,
                        ProductFeignClient productFeignClient) {
        this.customerClient = customerFeignClient;
        this.inventoryClient = inventoryFeignClient;
        this.invoiceClient = invoiceFeignClient;
        this.levelUpClient = levelUpFeignClient;
        this.productClient = productFeignClient;

    }


    //Customer Methods

    public Customer getCustomer(int customerId){
        return customerClient.getCustomer(customerId);
    }

    public List<Customer> getAllCustomers() {
        return customerClient.getAllCustomers();
    }
    public Customer addCustomer(Customer customer){
        return customerClient.addCustomer(customer);
    }

    public void updateCustomer(Customer customer){
        customerClient.updateCustomer(customer);
    }

    public void deleteCustomer(int customerId){

       Customer customer =  customerClient.getCustomer(customerId);
       List<Invoice> invoices = invoiceClient.getAllInvoices();

        Predicate<Invoice> byCustomer = invoice -> invoice.getCustomerId() == customerId;
       List<Invoice> customerInvoices = invoices.stream().filter(
           byCustomer
       ).collect(Collectors.toList());


       if(customer == null){
           throw new IllegalArgumentException("Must provide valid customerId. Customer not found");
       }else if(customerInvoices.size()>0){
           throw new IllegalArgumentException("Customer contains invoice records");
       }else{
           customerClient.deleteCustomer(customerId);
       }


    }

    //LevelUp Methods

    public LevelUp getLevelUp(int levelUpId){
        return levelUpClient.fetchLevelUp(levelUpId);
    }

    public List<LevelUp> getAllLevelUps(){
        return levelUpClient.fetchAllLevelUp();
    }

    public LevelUp addLevelUp(LevelUp levelUp){
        return levelUpClient.saveLevelUp(levelUp);
    }

    public void updateLevelUp(LevelUp levelUp){
        levelUpClient.updateLevelUp(levelUp);
    }

    public void deleteLevelUp(int levelUpId){
        levelUpClient.deleteLevelUp(levelUpId);
    }


    //Inventory Methods

    public Inventory getInventory (int inventoryId){
        return inventoryClient.getInventory(inventoryId);
    }

    public List<Inventory> getAllInventory(){
        return inventoryClient.getAllInventories();
    }

    public Inventory addInventory(Inventory inventory){
        return inventoryClient.saveInventory(inventory);
    }

    public void updateInventory(Inventory inventory){
        inventoryClient.updateInventory(inventory);
    }

    public void deleteInventory(int inventoryId){

        List<InvoiceItem> invoiceItems = invoiceClient.getAllInvoiceItems();

        Predicate<InvoiceItem> byInventory = invoiceItem -> invoiceItem.getInventoryId() == inventoryId;

        List<InvoiceItem> invoiceItemsByInventory = invoiceItems.stream().filter(byInventory).collect(Collectors.toList());

        if(inventoryClient.getInventory(inventoryId)==null){
            throw new IllegalArgumentException("No inventory associated with this id");
        }
        else if(invoiceItemsByInventory.size()>0) {
            throw new IllegalArgumentException("Invoices are associated with this invoice item");
        }else{
            inventoryClient.deleteInventory(inventoryId);
        }

    }



    //Invoice Methods

    public Invoice getInvoice(int invoiceId){
        return invoiceClient.fetchOneInvoice(invoiceId);
    }

    public List<Invoice> getAllInvoices(){
        return invoiceClient.getAllInvoices();
    }

    public Invoice addInvoice(Invoice invoice){
        return invoiceClient.createInvoice(invoice);
    }

    public void updateInvoice(Invoice invoice){
        invoiceClient.updateInvoice(invoice);
    }

    public void deleteInvoice(int invoiceId) {

        if (invoiceClient.fetchOneInvoice(invoiceId) == null) {
            throw new IllegalArgumentException("No invoices associated with this invoiceId");
        } else {
            List<InvoiceItem> itemsFromInvoice = invoiceClient.fetchInvoiceItemsByInvoiceId(invoiceId);

            itemsFromInvoice.forEach(invoiceItem -> {
                invoiceClient.deleteInvoiceItem(invoiceItem.getInvoiceItemId());
            });
            invoiceClient.deleteInvoice(invoiceId);
        }
    }
    //Invoice Item Methods

    public InvoiceItem getInvoiceItem(int invoiceItemId){
        return invoiceClient.fetchOneInvoiceItem( invoiceItemId);
    }

    public List<InvoiceItem> getAllInvoiceItems(){
        return invoiceClient.getAllInvoiceItems();
    }

    public List<InvoiceItem> getAllInvoiceItemsByInvoiceId(int invoiceId){
        return invoiceClient.fetchInvoiceItemsByInvoiceId(invoiceId);
    }

    public InvoiceItem addInvoiceItem(InvoiceItem invoiceItem){
        return invoiceClient.createInvoiceItem(invoiceItem);
    }

    public void updateInvoiceItem(InvoiceItem invoiceItem){
        invoiceClient.updateInvoiceItem(invoiceItem);
    }

    public void deleteInvoiceItem (int invoiceItemId){
        invoiceClient.deleteInvoiceItem(invoiceItemId);
    }
    //Product Methods
    public Product getProduct (int productId){
        return productClient.getProduct(productId);
    }

    public List<Product> getAllProducts(){

        return productClient.getAllProducts();
    }

    public List<Product> getProductsByInvoiceId(int invoiceId){
        return productClient.getProductsByInvoiceId(invoiceId);
    }

    public Product addProduct(Product product){
        return productClient.addProduct(product);
    }

    public void updateProduct(Product product){
        productClient.updateProduct(product);
    }

    public void deleteProduct(int productId){
        Product product = productClient.getProduct(productId);
      Inventory productInventory = inventoryClient.getInventoryByProductId(productId);

      if(productInventory == null && product ==  null ){
          throw new IllegalArgumentException("Please provide a valid product");

      }else{
          Predicate<InvoiceItem> byInventoryId = invoiceItem -> {
              assert productInventory != null;
              return invoiceItem.getInventoryId() == productInventory.getInventoryId();
          };
          List<InvoiceItem> invoiceItemsWithInventory = invoiceClient.getAllInvoiceItems().stream().filter(byInventoryId).collect(Collectors.toList());
          if(invoiceItemsWithInventory.size() == 0 ){
              productClient.deleteProduct(productId);
          }else{
              throw new IllegalArgumentException("Database contains invoice items associated with this product. Cannot delete.");
          }


      }









    }



    }

