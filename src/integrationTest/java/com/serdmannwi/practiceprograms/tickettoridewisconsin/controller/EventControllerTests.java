package com.serdmannwi.practiceprograms.tickettoridewisconsin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.IntegrationTest;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.service.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//Overrides Spring properties to set up  Environment for test
@TestPropertySource(properties = {
    "spring.datasource.url=${mySQLContainer.getJdbcUrl()}",
    "spring.datasource.username=${mySQLContainer.getUsername()}",
    "spring.datasource.password=${mySQLContainer.getPassword()}"
})

@IntegrationTest
@Testcontainers
public class EventControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private EventService eventService;
    private ObjectMapper mapper;

    //Create MySQL Container Docker image
    @Container
    public static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.0")
        .withExposedPorts(3306)
        .withDatabaseName("testdb")
        .withUsername("test")
        .withPassword("test");

    //Registers properties, sets datasource to test container
    @DynamicPropertySource
    static void databaseProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mySQLContainer::getUsername);
        registry.add("spring.datasource.password", mySQLContainer::getPassword);
    }

    /**------------------------------------------------ Test Constants -----------------------------------------------*/

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }

    /**------------------------------------------------ Event Retrieval ----------------------------------------------*/
    public void getAllEvents_returnsEvents() throws Exception {
        MvcResult result = mockMvc.perform(get("/event/allEvents")
            .contentType(MediaType.APPLICATION_JSON))
            .andReturn();
    }


}
