package com.trilogy.cloudgamestoreinventoryservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trilogy.cloudgamestoreinventoryservice.model.Inventory;
import com.trilogy.cloudgamestoreinventoryservice.service.ServiceLayer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
@WebMvcTest(InventoryController.class)
public class InventoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServiceLayer serviceLayer;

    private ObjectMapper mapper = new ObjectMapper();

    private static final Inventory INVENTORY_TO_SAVE = new Inventory(1,100);
    private static final Inventory SAVED_INVENTORY = new Inventory(1,1,100);
    private static final int INVENTORY_ID = 2;

    private static final Inventory SAVED_INVENTORY_2 = new Inventory(2,2,222);
    private static final List<Inventory> SAVED_INVENTORIES = new ArrayList<>();

    @Before
    public void setUp() {

        SAVED_INVENTORIES.add(SAVED_INVENTORY);
        SAVED_INVENTORIES.add(SAVED_INVENTORY_2);

        when(serviceLayer.saveInventory(INVENTORY_TO_SAVE)).thenReturn(SAVED_INVENTORY);
        when(serviceLayer.fetchAllInventories()).thenReturn(SAVED_INVENTORIES);
        when(serviceLayer.fetchInventory(INVENTORY_ID)).thenReturn(SAVED_INVENTORY_2);
    }

    @Test
    public void testAllowedSaveInventory() throws Exception {

        String expectedJsonOutput = mapper.writeValueAsString(SAVED_INVENTORY);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/inventory")
                .content(mapper.writeValueAsString(INVENTORY_TO_SAVE))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedJsonOutput));
    }

    @Test
    public void getAllInventories() throws Exception{

        String expectedJsonOutput = mapper.writeValueAsString(SAVED_INVENTORIES);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/inventory")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(expectedJsonOutput));
    }

    @Test
    public void getInventory() throws Exception{

        String expectedJsonOutput = mapper.writeValueAsString(SAVED_INVENTORY_2);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/inventory/{inventoryId}", INVENTORY_ID))
                .andDo(print()).andExpect(content().json(expectedJsonOutput));
    }

    //Delete and update placeholders
}