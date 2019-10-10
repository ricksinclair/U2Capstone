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

import static org.mockito.Mockito.when;
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

    private final Inventory INVENTORY_TO_SAVE = new Inventory(1,100);
    private final Inventory SAVED_INVENTORY = new Inventory(1,1,100);

    @Before
    public void setUp() {

        when(serviceLayer.saveInventory(INVENTORY_TO_SAVE)).thenReturn(SAVED_INVENTORY);
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
    public void getAllInventories() {
    }

    @Test
    public void getInventory() {
    }
}