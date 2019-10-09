package com.trilogy.cloudgamestoreinvoiceservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.trilogy.cloudgamestoreinvoiceservice.model.Invoice;
import com.trilogy.cloudgamestoreinvoiceservice.service.ServiceLayer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(InvoiceController.class)
public class InvoiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServiceLayer serviceLayer;

    private ObjectMapper mapper = new ObjectMapper();

    private static final int INVOICE_ID_TO_SAVE = 1;
    private static final LocalDate INVOICE_DATE_TO_SAVE = LocalDate.of(2019, 10, 31);
    private static final Invoice INVOICE_TO_SAVE = new Invoice(INVOICE_ID_TO_SAVE, INVOICE_DATE_TO_SAVE);
    private static final Invoice SAVED_INVOICE = new Invoice(1, INVOICE_ID_TO_SAVE, INVOICE_DATE_TO_SAVE);

    private static final Invoice INVOICE_GROUP_1 = new Invoice(1, 1, LocalDate.of(2019, 10, 31));
    private static final Invoice INVOICE_GROUP_2 = new Invoice(2, 2, LocalDate.of(2019, 10, 10));
    private static final List<Invoice> INVOICE_LIST = new ArrayList<>();

    private static final int INVOICE_ID_TO_FETCH = 3;
    private static final Invoice INVOICE_TO_FETCH = new Invoice(INVOICE_ID_TO_FETCH,3, LocalDate.now());

    @Before
    public void setUp() {

        //Next 5 statements allows for deserialization of LocalDate by Jackson object mapper
        JavaTimeModule module = new JavaTimeModule();
        LocalDateDeserializer localDateDeserializer = new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        SimpleModule simpleModule = module.addDeserializer(LocalDate.class, localDateDeserializer);
        mapper = Jackson2ObjectMapperBuilder.json()
                .modules(module)
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .build();

        INVOICE_LIST.add(INVOICE_GROUP_1);
        INVOICE_LIST.add(INVOICE_GROUP_2);

        when(serviceLayer.saveInvoice(INVOICE_TO_SAVE)).thenReturn(SAVED_INVOICE);
        when(serviceLayer.fetchAllInvoices()).thenReturn(INVOICE_LIST);
        when(serviceLayer.fetchInvoice(INVOICE_ID_TO_FETCH)).thenReturn(INVOICE_TO_FETCH);
    }

    @Test
    public void testAllowedCreateInvoice() throws Exception {

        String expectedJsonOutput = mapper.writeValueAsString(SAVED_INVOICE);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/invoice")
                .content(mapper.writeValueAsString(INVOICE_TO_SAVE))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedJsonOutput));

    }

    @Test
    public void testGetAllInvoices() throws Exception {

        String expectedJsonOutput = mapper.writeValueAsString(INVOICE_LIST);

        mockMvc.perform(get("/invoice"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(expectedJsonOutput));
    }

    @Test
    public void fetchOneInvoice() throws Exception{

        String expectedJsonOutput = mapper.writeValueAsString(INVOICE_TO_FETCH);

        mockMvc.perform(get("/invoice/{invoiceId}", INVOICE_ID_TO_FETCH))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(expectedJsonOutput));
    }

    //Update and Delete place holder
    //Invalid invoice creation
}