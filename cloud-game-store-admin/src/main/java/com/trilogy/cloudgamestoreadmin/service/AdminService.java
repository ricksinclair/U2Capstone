package com.trilogy.cloudgamestoreadmin.service;


import com.trilogy.cloudgamestoreadmin.util.feign.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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






}
