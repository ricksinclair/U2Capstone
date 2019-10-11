package com.trilogy.cloudgamestoreproductservice.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.trilogy.cloudgamestoreproductservice.model.Product;
import com.trilogy.cloudgamestoreproductservice.service.ServiceLayer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
@WebMvcTest(ProductController.class)

public class ProductControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private ServiceLayer serviceLayer;

    private ObjectMapper mapper = new ObjectMapper();

    private static final Product PRODUCT_TO_SAVE = new Product("Call of Duty: Modern Warfare",
            "A reboot of the blockbuster classic FPS",
            new BigDecimal("59.99"),
            new BigDecimal("29.99"));

    private static  final Product SAVED_PRODUCT_1 = new Product(1, "Call of Duty: Modern Warfare",
            "A reboot of the blockbuster classic FPS",
            new BigDecimal("59.99"),
            new BigDecimal("29.99"));

    private static final Product SAVED_PRODUCT_2 = new Product(1,
            "Battlefield 1",
            "A high-fidelity WWI FPS",
            new BigDecimal("59.99"),
            new BigDecimal("29.99"));

    private static final int PRODUCT_ID = 1;

    private static final List<Product> PRODUCT_LIST = new ArrayList<>();

    @Before
    public void setUp(){
        PRODUCT_LIST.add(SAVED_PRODUCT_1);
        PRODUCT_LIST.add(SAVED_PRODUCT_2);
        when(serviceLayer.addProduct(PRODUCT_TO_SAVE)).thenReturn(SAVED_PRODUCT_1);
        when(serviceLayer.getProduct(PRODUCT_ID)).thenReturn(SAVED_PRODUCT_1);
        when(serviceLayer.getAllProducts()).thenReturn(PRODUCT_LIST);
    }



    @Test
    public void testAllowedCreateProduct() throws Exception{
        String expectedJsonOutput = mapper.writeValueAsString(SAVED_PRODUCT_1);

        mockMvc.perform(MockMvcRequestBuilders.post("/product")
                    .content(mapper.writeValueAsString(PRODUCT_TO_SAVE))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated())
                    .andExpect(content().json(expectedJsonOutput));

    }

    @Test public void getAllCustomers() throws Exception{

        String expectedJsonOutput = mapper.writeValueAsString(PRODUCT_LIST);

        mockMvc.perform(MockMvcRequestBuilders.get("/product"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJsonOutput));
    }

    @Test
    public void fetchOneProduct() throws Exception{
        String expectedJsonOutput = mapper.writeValueAsString(SAVED_PRODUCT_1);

        mockMvc.perform(MockMvcRequestBuilders.get("/product/{productId}", PRODUCT_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJsonOutput));
    }


}
