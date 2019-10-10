package com.trilogy.cloudgamestoreproductservice.controller;

import com.trilogy.cloudgamestoreproductservice.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RestController;


@RefreshScope
@RestController
public class ProductController {

    @Autowired
    ServiceLayer serviceLayer;
}
