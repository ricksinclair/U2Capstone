package com.trilogy.cloudgamestoreinvoiceservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trilogy.cloudgamestoreinvoiceservice.model.InvoiceItem;
import com.trilogy.cloudgamestoreinvoiceservice.service.ServiceLayer;
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(InvoiceItemController.class)
public class InvoiceItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServiceLayer serviceLayer;

    private ObjectMapper mapper = new ObjectMapper();
    private static final InvoiceItem INVOICE_ITEM_TO_SAVE = new InvoiceItem(1, 1, 1, BigDecimal.valueOf(1.11));
    private static final InvoiceItem SAVED_INVOICE_ITEM = new InvoiceItem(1, 1, 1, 1, BigDecimal.valueOf(1.11));
    private static final InvoiceItem SAVED_INVOICE_ITEM_2 = new InvoiceItem(2, 2, 2, 2, BigDecimal.valueOf(2.22));
    private static final InvoiceItem SAVED_INVOICE_ITEM_3 = new InvoiceItem(3, 1, 3, 3, BigDecimal.valueOf(3.33));
    private static final int INVOICE_ITEM_ID = 1;
    private static final int INVOICE_ID = 1;
    private static final List<InvoiceItem> INVOICE_ITEM_LIST = new ArrayList<>();
    private static final List<InvoiceItem> INVOICE_ITEM_LIST_INVOICE_1 = new ArrayList<>();

    @Before
    public void setUp() {

        INVOICE_ITEM_LIST.add(SAVED_INVOICE_ITEM);
        INVOICE_ITEM_LIST.add((SAVED_INVOICE_ITEM_2));
        INVOICE_ITEM_LIST.add(SAVED_INVOICE_ITEM_3);

        INVOICE_ITEM_LIST_INVOICE_1.add(SAVED_INVOICE_ITEM);
        INVOICE_ITEM_LIST_INVOICE_1.add(SAVED_INVOICE_ITEM_3);

        when(serviceLayer.saveInvoiceItem(INVOICE_ITEM_TO_SAVE)).thenReturn(SAVED_INVOICE_ITEM);
        when(serviceLayer.fetchAllInvoiceItems()).thenReturn(INVOICE_ITEM_LIST);
        when(serviceLayer.fetchInvoiceItem(INVOICE_ITEM_ID)).thenReturn(SAVED_INVOICE_ITEM);
        when(serviceLayer.fetchAllInvoiceItemsByInvoiceId(INVOICE_ID)).thenReturn(INVOICE_ITEM_LIST_INVOICE_1);
    }

    @Test
    public void testAllowedCreateInvoiceItem() throws Exception {

        String expectedJsonOutput = mapper.writeValueAsString(SAVED_INVOICE_ITEM);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/invoiceItem")
                .content(mapper.writeValueAsString(INVOICE_ITEM_TO_SAVE))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedJsonOutput));
    }

    @Test
    public void getAllInvoiceItems() throws Exception {

        String expectedJsonOutput = mapper.writeValueAsString(INVOICE_ITEM_LIST);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/invoiceItem")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(expectedJsonOutput));
    }

    @Test
    public void fetchOneInvoiceItem() throws Exception {

        String expectedJsonOutput = mapper.writeValueAsString(SAVED_INVOICE_ITEM);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/invoiceItem/{invoiceItemId}", INVOICE_ITEM_ID))
                .andDo(print()).andExpect(content().json(expectedJsonOutput));
    }

    @Test
    public void fetchInvoiceItemsByInvoiceId() throws Exception {

        String expectedJsonOutput = mapper.writeValueAsString(INVOICE_ITEM_LIST_INVOICE_1);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/invoiceItemsByInvoiceId/{invoiceId}", INVOICE_ID))
                .andDo(print()).andExpect(content().json(expectedJsonOutput));
    }

    //Update and Delete place holder
    //Invalid invoice item creation
}