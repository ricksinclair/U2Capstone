package com.trilogy.cloudgamestorelevelupservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.trilogy.cloudgamestorelevelupservice.model.LevelUp;
import com.trilogy.cloudgamestorelevelupservice.service.ServiceLayer;
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

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(LevelUpController.class)
public class LevelUpControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServiceLayer serviceLayer;

    private ObjectMapper mapper = new ObjectMapper();

    private static final LevelUp  LEVEL_UP_TO_SAVE = new LevelUp(1,100, LocalDate.now());
    private static final LevelUp SAVED_LEVEL_UP = new LevelUp(1,1,100, LocalDate.now());

    private static final LevelUp SAVED_LEVEL_UP_2 = new LevelUp(2,2,200, LocalDate.now());
    private static final List<LevelUp> SAVED_LEVEL_UPS = new ArrayList<>();

    private static final int LEVEL_UP_ID = 1;
    private static final int CUSTOMER_ID = 1;

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

        SAVED_LEVEL_UPS.add(SAVED_LEVEL_UP);
        SAVED_LEVEL_UPS.add(SAVED_LEVEL_UP_2);

        when(serviceLayer.saveLevelUp(LEVEL_UP_TO_SAVE)).thenReturn(SAVED_LEVEL_UP);
        when(serviceLayer.fetchAllLevelUp()).thenReturn(SAVED_LEVEL_UPS);
        when(serviceLayer.fetchLevelUp(LEVEL_UP_ID)).thenReturn(SAVED_LEVEL_UP);
        when(serviceLayer.fetchLevelUpByCustomerId(CUSTOMER_ID)).thenReturn(SAVED_LEVEL_UP);

    }

    @Test
    public void testAllowedSaveLevelUp() throws Exception {

        String expectedJsonOutput = mapper.writeValueAsString(SAVED_LEVEL_UP);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/levelUp")
                .content(mapper.writeValueAsString(LEVEL_UP_TO_SAVE))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedJsonOutput));

    }

    @Test
    public void fetchAllLevelUp() throws Exception{

        String expectedJsonOutput = mapper.writeValueAsString(SAVED_LEVEL_UPS);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/levelUp")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(expectedJsonOutput));
    }

    @Test
    public void fetchLevelUp() throws Exception{

        String expectedJsonOutput = mapper.writeValueAsString(SAVED_LEVEL_UP);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/levelUp/{levelUpId}", LEVEL_UP_ID)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(expectedJsonOutput));
    }

    @Test
    public void fetchLevelUpByCustomerId() throws Exception {

        String expectedJsonOutput = mapper.writeValueAsString(SAVED_LEVEL_UP);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/levelUp/customerId/{customerId}", CUSTOMER_ID)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(expectedJsonOutput));
    }
}