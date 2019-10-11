package com.trilogy.cloudgamestoreretail.service;

import com.trilogy.cloudgamestoreretail.model.Inventory;
import com.trilogy.cloudgamestoreretail.model.Invoice;
import com.trilogy.cloudgamestoreretail.util.feign.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceLayer {

    private RabbitTemplate rabbitTemplate;
    private static final String EXCHANGE = "level-up-exchange";
    private static final String ROUTING_KEY = "level-up.#";

    @Autowired
    private LevelUpClient levelUpClient;
    @Autowired
    private InvoiceServiceClient invoiceServiceClient;
    @Autowired
    private InventoryServiceClient inventoryServiceClient;
    @Autowired
    private CustomerServiceClient customerServiceClient;
    @Autowired
    private ProductServiceClient productServiceClient;

    @Autowired
    ServiceLayer(RabbitTemplate rabbitTemplate, LevelUpClient levelUpClient, InvoiceServiceClient invoiceServiceClient,
                 InventoryServiceClient inventoryServiceClient, CustomerServiceClient customerServiceClient,
                 ProductServiceClient productServiceClient) {
        this.rabbitTemplate = rabbitTemplate;
        this.levelUpClient = levelUpClient;
        this.invoiceServiceClient = invoiceServiceClient;
        this.inventoryServiceClient = inventoryServiceClient;
        this.customerServiceClient = customerServiceClient;
        this.productServiceClient = productServiceClient;
    }

    //Search inventory
    //Return View Model?
    //
    public Inventory searchInventory(String productName) {

        //Search Inventory via feign client

        Inventory inventory = new Inventory();

        return inventory;
    }

    //Purchase product
    //Return view Model?
    //Pass view model?
    public Invoice purchaseProduct(Invoice invoice) {

        return invoice;
    }

    //Pass view model?
    private int calculateLevelUpPointsForPurchase(Invoice invoice) {
        //Calculate level up points per purchase
        int totalLevelUpPointsForPurchase = 0;
        return totalLevelUpPointsForPurchase;
    }

    private void submitLevelUpPoints(Invoice invoice) {
        //Send LevelUpPoints once purchase is completed via queue
    }

    //Circuit breaker pattern place holder
    private int getLevelUpPointsTotal(Invoice invoice) {

        //Circuit breaker read from Level up Client
        int levelUpPointsFromDatabase = 0;

        return levelUpPointsFromDatabase;
    }

    //Validate Customer
    private void validateCustomer(Invoice invoice) {
        //validate customer via customerClient or throw exception
    }

    private void validateProduct(Invoice invoice) {
        //validate product via productClient
    }
}
