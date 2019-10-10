package com.trilogy.cloudgamestorecustomerservice.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.trilogy.cloudgamestorecustomerservice.model.Customer;
import com.trilogy.cloudgamestorecustomerservice.service.ServiceLayer;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

@Autowired
    MockMvc mockMvc;

@MockBean
    private ServiceLayer serviceLayer;


    private ObjectMapper mapper = new ObjectMapper();

    private static final Customer CUSTOMER_TO_SAVE = new Customer(
            "John",
            "Doe",
            "1115 Broadway",
            "New York",
            "10010",
            "John.Doe@gmail.com",
            "212-555-5555");

    private static final Customer SAVED_CUSTOMER_1 =  new Customer(1,
            "John",
            "Doe",
            "1115 Broadway",
            "New York",
            "10010",
            "John.Doe@gmail.com",
            "212-555-5555");

    private static final Customer SAVED_CUSTOMER_2 = new Customer(2,
            "Jane",
            "Doe",
            "1115 Broadway",
            "New York",
            "10010",
            "Jane.Doe@gmail.com",
            "212-222-2000");

    private static final int CUSTOMER_ID = 1;


    private static final List<Customer> CUSTOMER_LIST = new ArrayList<>();

    @Before
    public void setUp(){

        CUSTOMER_LIST.add(SAVED_CUSTOMER_1);
        CUSTOMER_LIST.add(SAVED_CUSTOMER_2);
        when(serviceLayer.addCustomer(CUSTOMER_TO_SAVE)).thenReturn(SAVED_CUSTOMER_1);
        when(serviceLayer.getCustomer(CUSTOMER_ID)).thenReturn(SAVED_CUSTOMER_1);
        when(serviceLayer.getAllCustomers()).thenReturn(CUSTOMER_LIST);


    }

    @Test
    public void testAllowedCreateCustomer() throws Exception{

        String expectedJsonOutput = mapper.writeValueAsString(SAVED_CUSTOMER_1);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/customer")
                .content(mapper.writeValueAsString(CUSTOMER_TO_SAVE))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedJsonOutput))
        ;
    }


    @Test
    public void getAllCustomers() throws Exception{
        String expectedJsonOutput = mapper.writeValueAsString(CUSTOMER_LIST);
        mockMvc.perform(MockMvcRequestBuilders.get("/customer")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(expectedJsonOutput));
    }


    @Test
    public void fetchOneCustomer() throws Exception{
        String expectedJsonOutput = mapper.writeValueAsString(SAVED_CUSTOMER_1);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/customer/{customerId}", CUSTOMER_ID))
                        .andDo(print()).andExpect(content().json(expectedJsonOutput));



    }



}
