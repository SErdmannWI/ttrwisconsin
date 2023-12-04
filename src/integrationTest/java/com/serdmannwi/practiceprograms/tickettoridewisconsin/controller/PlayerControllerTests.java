package com.serdmannwi.practiceprograms.tickettoridewisconsin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jayway.jsonpath.JsonPath;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.IntegrationTest;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.constants.AbilityConstants;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.constants.CityConstants;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.constants.FreightStationConstants;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.constants.PlayerConstants;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.controller.model.*;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.player.PlayerRecord;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.service.PlayerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

//Overrides Spring properties to set up  Environment for test
@TestPropertySource(properties = {
    "spring.datasource.url=${mySQLContainer.getJdbcUrl()}",
    "spring.datasource.username=${mySQLContainer.getUsername()}",
    "spring.datasource.password=${mySQLContainer.getPassword()}"
})

@Testcontainers
@IntegrationTest
public class PlayerControllerTests {


    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PlayerService playerService;
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
    private static final List<PlayerRecord> TEST_PLAYER_RECORDS_SIZE_2 = new ArrayList<>(Arrays.asList(
        new PlayerRecord(), new PlayerRecord()));
    private static final List<PlayerRecord> TEST_PLAYER_RECORDS_SIZE_4 = new ArrayList<>(Arrays.asList(
        new PlayerRecord(), new PlayerRecord(), new PlayerRecord(), new PlayerRecord()));
    private static final String[] TEST_PLAYER_ARRAY = {PlayerConstants.PLAYER_ONE_ID, PlayerConstants.PLAYER_TWO_ID};

    private static final String TEST_USER_ID = "testUser";
    private static final String TEST_PLAYER_NAME = "userName";
    private static final String TEST_USER_COLOR_ID = "testColor";
    private static final String TEST_USER_ICON_ID = "testIcon";

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }

    /**------------------------------------------- Player Creation Tests ---------------------------------------------*/

    @Test
    @Transactional
    public void createNewPlayer_createsNewPlayer_returnsNewPlayer() throws Exception {
        //GIVEN
        NewPlayerRequest newPlayerRequest = createNewPlayerRequest();

        //WHEN
        mockMvc.perform(post("/player/newPlayer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(newPlayerRequest)))

        //THEN
            .andExpect(status().isOk())
            .andExpect(jsonPath("playerName").value(TEST_PLAYER_NAME))
            .andExpect(jsonPath("playerId").value(PlayerConstants.PLAYER_ONE_ID))
            .andExpect(jsonPath("iconId").value(TEST_USER_ICON_ID))
            .andExpect(jsonPath("color").value(TEST_USER_COLOR_ID))
            .andExpect(jsonPath("ownedFreightStation").value(""))
            .andExpect(jsonPath("ownedAbility").value(""))
            .andExpect(jsonPath("score").value(PlayerConstants.STARTING_SCORE))
            .andExpect(jsonPath("trainsRemaining").value(PlayerConstants.STARTING_TRAINS))
            .andExpect(jsonPath("freightContractsCompleted").value(0));
    }

    @Test
    @Transactional
    public void createNewPlayer_tooManyPlayers_returnsBadRequest() throws Exception {
        //GIVEN
        NewPlayerRequest newPlayerRequest = createNewPlayerRequest();

        for (int i = 0; i < 4; i++) {
            mockMvc.perform(post("/player/newPlayer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(newPlayerRequest)));
        }

        //WHEN
        mockMvc.perform(post("/player/newPlayer")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(newPlayerRequest)))

        //THEN
            .andExpect(status().isBadRequest());
    }

    /**------------------------------------------- Player Retrieval Tests --------------------------------------------*/

    @Test
    @Transactional
    public void getPlayer_validId_retrievesPlayer() throws Exception {
        //GIVEN
        NewPlayerRequest newPlayerRequest = createNewPlayerRequest();

        mockMvc.perform(post("/player/newPlayer")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(newPlayerRequest)));

        //WHEN
        mockMvc.perform(get("/player/getPlayer/{id}", PlayerConstants.PLAYER_ONE_ID)
                    .accept(MediaType.APPLICATION_JSON))

        //THEN
            .andExpect(status().isOk())
            .andExpect(jsonPath("playerName").value(TEST_PLAYER_NAME))
            .andExpect(jsonPath("playerId").value(PlayerConstants.PLAYER_ONE_ID))
            .andExpect(jsonPath("iconId").value(TEST_USER_ICON_ID))
            .andExpect(jsonPath("color").value(TEST_USER_COLOR_ID))
            .andExpect(jsonPath("ownedFreightStation").value(""))
            .andExpect(jsonPath("ownedAbility").value(""))
            .andExpect(jsonPath("score").value(PlayerConstants.STARTING_SCORE))
            .andExpect(jsonPath("trainsRemaining").value(PlayerConstants.STARTING_TRAINS))
            .andExpect(jsonPath("freightContractsCompleted").value(0));
    }

    @Test
    @Transactional
    public void getPlayer_invalidId_returnsBadRequest() throws Exception {
        //GIVEN
        String invalidTestId = UUID.randomUUID().toString();

        //WHEN
        mockMvc.perform(get("/player/getPlayer/{id}", invalidTestId)
            .accept(MediaType.APPLICATION_JSON))

        //THEN
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void getAllPlayers_returnsAllPlayers() throws Exception {
        //GIVEN
        NewPlayerRequest newPlayerRequest = createNewPlayerRequest();

        for (int i = 0; i < 4; i++) {
            mockMvc.perform(post("/player/newPlayer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(newPlayerRequest)));
        }

        //WHEN
        mockMvc.perform(get("/player/allPlayers")
                .contentType(MediaType.APPLICATION_JSON))

        //THEN
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].playerId").exists())
            .andExpect(jsonPath("$[1].playerId").exists())
            .andExpect(jsonPath("$[2].playerId").exists())
            .andExpect(jsonPath("$[3].playerId").exists())

            .andExpect(jsonPath("$[0].playerName").value(TEST_PLAYER_NAME))
            .andExpect(jsonPath("$[0].playerId").value(PlayerConstants.PLAYER_ONE_ID))
            .andExpect(jsonPath("$[0].iconId").value(TEST_USER_ICON_ID))
            .andExpect(jsonPath("$[0].color").value(TEST_USER_COLOR_ID))
            .andExpect(jsonPath("$[0].ownedFreightStation").value(""))
            .andExpect(jsonPath("$[0].ownedAbility").value(""))
            .andExpect(jsonPath("$[0].score").value(PlayerConstants.STARTING_SCORE))
            .andExpect(jsonPath("$[0].trainsRemaining").value(PlayerConstants.STARTING_TRAINS))
            .andExpect(jsonPath("$[0].freightContractsCompleted").value(0))

            .andExpect(jsonPath("$[1].playerName").value(TEST_PLAYER_NAME))
            .andExpect(jsonPath("$[1].playerId").value(PlayerConstants.PLAYER_TWO_ID))
            .andExpect(jsonPath("$[1].iconId").value(TEST_USER_ICON_ID))
            .andExpect(jsonPath("$[1].color").value(TEST_USER_COLOR_ID))
            .andExpect(jsonPath("$[1].ownedFreightStation").value(""))
            .andExpect(jsonPath("$[1].ownedAbility").value(""))
            .andExpect(jsonPath("$[1].score").value(PlayerConstants.STARTING_SCORE))
            .andExpect(jsonPath("$[1].trainsRemaining").value(PlayerConstants.STARTING_TRAINS))
            .andExpect(jsonPath("$[1].freightContractsCompleted").value(0))

            .andExpect(jsonPath("$[2].playerName").value(TEST_PLAYER_NAME))
            .andExpect(jsonPath("$[2].playerId").value(PlayerConstants.PLAYER_THREE_ID))
            .andExpect(jsonPath("$[2].iconId").value(TEST_USER_ICON_ID))
            .andExpect(jsonPath("$[2].color").value(TEST_USER_COLOR_ID))
            .andExpect(jsonPath("$[2].ownedFreightStation").value(""))
            .andExpect(jsonPath("$[2].ownedAbility").value(""))
            .andExpect(jsonPath("$[2].score").value(PlayerConstants.STARTING_SCORE))
            .andExpect(jsonPath("$[2].trainsRemaining").value(PlayerConstants.STARTING_TRAINS))
            .andExpect(jsonPath("$[2].freightContractsCompleted").value(0))

            .andExpect(jsonPath("$[3].playerName").value(TEST_PLAYER_NAME))
            .andExpect(jsonPath("$[3].playerId").value(PlayerConstants.PLAYER_FOUR_ID))
            .andExpect(jsonPath("$[3].iconId").value(TEST_USER_ICON_ID))
            .andExpect(jsonPath("$[3].color").value(TEST_USER_COLOR_ID))
            .andExpect(jsonPath("$[3].ownedFreightStation").value(""))
            .andExpect(jsonPath("$[3].ownedAbility").value(""))
            .andExpect(jsonPath("$[3].score").value(PlayerConstants.STARTING_SCORE))
            .andExpect(jsonPath("$[3].trainsRemaining").value(PlayerConstants.STARTING_TRAINS))
            .andExpect(jsonPath("$[3].freightContractsCompleted").value(0));
    }

    @Test
    @Transactional
    public void updatePlayer_validRequest_returnsUpdatedPlayer() throws Exception {
        //GIVEN
        NewPlayerRequest newPlayerRequest = createNewPlayerRequest();

        MvcResult result = mockMvc.perform(post("/player/newPlayer")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(newPlayerRequest)))
            .andReturn();

        String responseContent = result.getResponse().getContentAsString();
        String playerId = JsonPath.read(responseContent, "$.playerId");

        //WHEN
        UpdatePlayerRequest updatePlayerRequest = createUpdatePlayerRequest(playerId);

        mockMvc.perform(put("/player/updatePlayer")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(updatePlayerRequest)))

        //THEN
            .andExpect(status().isOk())
            .andExpect(jsonPath("playerName").value(TEST_PLAYER_NAME))
            .andExpect(jsonPath("playerId").value(PlayerConstants.PLAYER_ONE_ID))
            .andExpect(jsonPath("iconId").value(TEST_USER_ICON_ID))
            .andExpect(jsonPath("color").value(TEST_USER_COLOR_ID))
            .andExpect(jsonPath("ownedFreightStation").value(""))
            .andExpect(jsonPath("ownedAbility").value(""))
            .andExpect(jsonPath("score").value(10))
            .andExpect(jsonPath("trainsRemaining").value(55))
            .andExpect(jsonPath("freightContractsCompleted").value(1));
    }

    @Test
    @Transactional
    public void deletePlayer_validId_deletesPlayer() throws Exception {
        //GIVEN
        NewPlayerRequest newPlayerRequest = createNewPlayerRequest();

        MvcResult result = mockMvc.perform(post("/player/newPlayer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(newPlayerRequest)))
            .andReturn();

        String responseContent = result.getResponse().getContentAsString();
        String playerId = JsonPath.read(responseContent, "$.playerId");

        //WHEN
        mockMvc.perform(delete("/player/deletePlayer/{id}", playerId)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

        //THEN
        mockMvc.perform(get("/player/getPlayer/{id}", playerId))
            .andExpect(status().isNotFound());
    }

    /**---------------------------------------- Player Turn Manager Tests --------------------------------------------*/

    @Test
    @Transactional
    public void createTurnOrder_validPlayerIds_returnsOKResponse() throws Exception {
        //GIVEN
        NewPlayerRequest newPlayerRequest = createNewPlayerRequest();
        String[] playerIds = new String[4];

        for (int i = 0; i < 4; i++) {
            MvcResult result = mockMvc.perform(post("/player/newPlayer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(newPlayerRequest)))
                .andReturn();

            String responseContent = result.getResponse().getContentAsString();
            playerIds[i] = JsonPath.read(responseContent, "$.playerId");
        }

        //WHEN
        mockMvc.perform(post("/player/createTurnOrder", playerIds)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(playerIds))
                .accept(MediaType.APPLICATION_JSON))

        //THEN
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void getNextPlayer_returnsNextPlayer() throws Exception {
        //GIVEN
        NewPlayerRequest newPlayerRequest = createNewPlayerRequest();
        String[] playerIds = new String[4];

        for (int i = 0; i < 4; i++) {
            MvcResult result = mockMvc.perform(post("/player/newPlayer")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(newPlayerRequest)))
                .andReturn();

            String responseContent = result.getResponse().getContentAsString();
            playerIds[i] = JsonPath.read(responseContent, "$.playerId");
        }

        mockMvc.perform(post("/player/createTurnOrder", playerIds)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(playerIds))
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

        //WHEN
        mockMvc.perform(get("/player/getNextPlayer")
            .accept(MediaType.APPLICATION_JSON))

        //THEN
            .andExpect(status().isOk())
            .andExpect(jsonPath("playerName").value(TEST_PLAYER_NAME))
            .andExpect(jsonPath("playerId").value(PlayerConstants.PLAYER_ONE_ID))
            .andExpect(jsonPath("iconId").value(TEST_USER_ICON_ID))
            .andExpect(jsonPath("color").value(TEST_USER_COLOR_ID))
            .andExpect(jsonPath("ownedFreightStation").value(""))
            .andExpect(jsonPath("ownedAbility").value(""))
            .andExpect(jsonPath("score").value(PlayerConstants.STARTING_SCORE))
            .andExpect(jsonPath("trainsRemaining").value(PlayerConstants.STARTING_TRAINS))
            .andExpect(jsonPath("freightContractsCompleted").value(0));
    }

    /**
     * Tests that the game will successfully progress to the next round and repeat turns for any player who has deferred.
     * @throws Exception
     */
    @Test
    @Transactional
    public void deferPlayerTurn_validPlayer_returnsOKResponse() throws Exception {
        //GIVEN
        NewPlayerRequest newPlayerRequest = createNewPlayerRequest();
        String[] playerIds = new String[4];

        for (int i = 0; i < 4; i++) {
            MvcResult result = mockMvc.perform(post("/player/newPlayer")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(newPlayerRequest)))
                .andReturn();

            String responseContent = result.getResponse().getContentAsString();
            playerIds[i] = JsonPath.read(responseContent, "$.playerId");
        }

        mockMvc.perform(post("/player/createTurnOrder", playerIds)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(playerIds))
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

        //Progress through two rounds
        for (int i = 0; i < 8; i++) {
            mockMvc.perform(get("/player/getNextPlayer")
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        }

        //WHEN
        MvcResult result = mockMvc.perform(get("/player/getNextPlayer")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();

        String responseContent = result.getResponse().getContentAsString();
        String playerId = JsonPath.read(responseContent, "$.playerId");

        mockMvc.perform(put("/player/deferPlayerTurn/{playerId}", playerId)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

        //Progress through next round
        for (int i = 0; i < 3; i++) {
            mockMvc.perform(get("/player/getNextPlayer")
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        }

        //Expect next two Players to be the same Player
        MvcResult player1Result = mockMvc.perform(get("/player/getNextPlayer")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();

        String player1Response = player1Result.getResponse().getContentAsString();
        PlayerResponse player1 = mapper.readValue(player1Response, PlayerResponse.class);

        MvcResult player2Result = mockMvc.perform(get("/player/getNextPlayer")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();

        String player2Response = player2Result.getResponse().getContentAsString();
        PlayerResponse player2 = mapper.readValue(player2Response, PlayerResponse.class);

        Assertions.assertEquals(player1, player2);

        //Should be different Player
        MvcResult player3Result = mockMvc.perform(get("/player/getNextPlayer")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();

        String player3Response = player3Result.getResponse().getContentAsString();
        PlayerResponse player3 = mapper.readValue(player3Response, PlayerResponse.class);

        Assertions.assertNotEquals(player1, player3);
    }


    /**------------------------------------------- Player Choices Tests ----------------------------------------------*/

    @Test
    @Transactional
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void chooseFreightStation_correctFreightStation_addsFreightStation() throws Exception {
        //GIVEN
        NewPlayerRequest newPlayerRequest = createNewPlayerRequest();

        MvcResult result = mockMvc.perform(post("/player/newPlayer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(newPlayerRequest)))
            .andExpect(status().isOk())
            .andReturn();

        String responseContent = result.getResponse().getContentAsString();
        String playerId = JsonPath.read(responseContent, "$.playerId");

        ChooseFreightStationRequest freightStationRequest = new ChooseFreightStationRequest();
        freightStationRequest.setPlayerId(playerId);
        freightStationRequest.setRegionId(CityConstants.REGION_ONE_ID);
        freightStationRequest.setFreightStationId(FreightStationConstants.MADISON_FREIGHT_ID);

        //WHEN
        mockMvc.perform(put("/player/chooseFreightStation", freightStationRequest)
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(freightStationRequest)))

        //THEN
            .andExpect(status().isOk())
            .andExpect(jsonPath("playerName").value(TEST_PLAYER_NAME))
            .andExpect(jsonPath("playerId").value(PlayerConstants.PLAYER_ONE_ID))
            .andExpect(jsonPath("iconId").value(TEST_USER_ICON_ID))
            .andExpect(jsonPath("color").value(TEST_USER_COLOR_ID))
            .andExpect(jsonPath("ownedFreightStation").value(FreightStationConstants.MADISON_FREIGHT_ID))
            .andExpect(jsonPath("ownedAbility").value(""))
            .andExpect(jsonPath("score").value(PlayerConstants.STARTING_SCORE))
            .andExpect(jsonPath("trainsRemaining").value(PlayerConstants.STARTING_TRAINS))
            .andExpect(jsonPath("freightContractsCompleted").value(0));
    }

    @Test
    @Transactional
    public void chooseFreightStation_stationAlreadyChosen_returnsBadRequest() throws Exception {
        //GIVEN
        NewPlayerRequest newPlayerRequest1 = createNewPlayerRequest();
        NewPlayerRequest newPlayerRequest2 = createNewPlayerRequest();

        MvcResult player1Result = mockMvc.perform(post("/player/newPlayer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(newPlayerRequest1)))
            .andExpect(status().isOk())
            .andReturn();

        String player1ResponseContent = player1Result.getResponse().getContentAsString();
        String player1Id = JsonPath.read(player1ResponseContent, "$.playerId");

        ChooseFreightStationRequest player1FreightStationRequest = new ChooseFreightStationRequest();
        player1FreightStationRequest.setPlayerId(player1Id);
        player1FreightStationRequest.setRegionId(CityConstants.REGION_ONE_ID);
        player1FreightStationRequest.setFreightStationId(FreightStationConstants.MADISON_FREIGHT_ID);

        mockMvc.perform(put("/player/chooseFreightStation", player1FreightStationRequest)
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(player1FreightStationRequest)))
            .andExpect(status().isOk());

        MvcResult result = mockMvc.perform(post("/player/newPlayer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(newPlayerRequest2)))
            .andExpect(status().isOk())
            .andReturn();

        String player2ResponseContent = result.getResponse().getContentAsString();
        String playerId = JsonPath.read(player2ResponseContent, "$.playerId");

        ChooseFreightStationRequest player2FreightStationRequest = new ChooseFreightStationRequest();
        player2FreightStationRequest.setPlayerId(playerId);
        player2FreightStationRequest.setRegionId(CityConstants.REGION_ONE_ID);
        player2FreightStationRequest.setFreightStationId(FreightStationConstants.MADISON_FREIGHT_ID);

        mockMvc.perform(put("/player/chooseFreightStation", player2FreightStationRequest)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(player2FreightStationRequest)))
            .andExpect(status().isBadRequest());
    }

    @Test
    @Transactional
    public void chooseAbility_validRequest_addsAbility() throws Exception {
        //GIVEN
        NewPlayerRequest newPlayerRequest = createNewPlayerRequest();

        MvcResult result = mockMvc.perform(post("/player/newPlayer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(newPlayerRequest)))
            .andExpect(status().isOk())
            .andReturn();

        String responseContent = result.getResponse().getContentAsString();
        String playerId = JsonPath.read(responseContent, "$.playerId");

        //WHEN
        ChooseAbilityRequest chooseAbilityRequest = new ChooseAbilityRequest();
        chooseAbilityRequest.setPlayerId(playerId);
        chooseAbilityRequest.setAbilityId(AbilityConstants.DELAY_TURN_ID);

        mockMvc.perform(put("/player/chooseAbility", chooseAbilityRequest)
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(chooseAbilityRequest)))

        //THEN
            .andExpect(status().isOk())
            .andExpect(jsonPath("playerName").value(TEST_PLAYER_NAME))
            .andExpect(jsonPath("playerId").value(PlayerConstants.PLAYER_ONE_ID))
            .andExpect(jsonPath("iconId").value(TEST_USER_ICON_ID))
            .andExpect(jsonPath("color").value(TEST_USER_COLOR_ID))
            .andExpect(jsonPath("ownedFreightStation").value(""))
            .andExpect(jsonPath("ownedAbility").value(AbilityConstants.DELAY_TURN_ID))
            .andExpect(jsonPath("score").value(PlayerConstants.STARTING_SCORE))
            .andExpect(jsonPath("trainsRemaining").value(PlayerConstants.STARTING_TRAINS))
            .andExpect(jsonPath("freightContractsCompleted").value(0));
    }

    @Test
    @Transactional
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void chooseAbility_abilityAlreadyChosen_returnsBadRequest() throws Exception {
        //GIVEN
        NewPlayerRequest newPlayerRequest1 = createNewPlayerRequest();
        NewPlayerRequest newPlayerRequest2 = createNewPlayerRequest();

        MvcResult player1Result = mockMvc.perform(post("/player/newPlayer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(newPlayerRequest1)))
            .andExpect(status().isOk())
            .andReturn();

        String player1ResponseContent = player1Result.getResponse().getContentAsString();
        String player1Id = JsonPath.read(player1ResponseContent, "$.playerId");

        ChooseAbilityRequest chooseAbilityRequest = new ChooseAbilityRequest();
        chooseAbilityRequest.setPlayerId(player1Id);
        chooseAbilityRequest.setAbilityId(AbilityConstants.DELAY_TURN_ID);

        mockMvc.perform(put("/player/chooseAbility", chooseAbilityRequest)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(chooseAbilityRequest)))
            .andExpect(status().isOk());

        MvcResult result = mockMvc.perform(post("/player/newPlayer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(newPlayerRequest2)))
            .andExpect(status().isOk())
            .andReturn();

        String player2ResponseContent = result.getResponse().getContentAsString();
        String player2Id = JsonPath.read(player2ResponseContent, "$.playerId");

        ChooseAbilityRequest player2AbilityRequest = new ChooseAbilityRequest();
        player2AbilityRequest.setPlayerId(player2Id);
        player2AbilityRequest.setAbilityId(AbilityConstants.DELAY_TURN_ID);

        mockMvc.perform(put("/player/chooseAbility", player2AbilityRequest)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(player2AbilityRequest)))
            .andExpect(status().isBadRequest());

    }

    /**-------------------------------------------- Utility Methods --------------------------------------------------*/
    private NewPlayerRequest createNewPlayerRequest() {
        NewPlayerRequest testPlayerRequest = new NewPlayerRequest();
        testPlayerRequest.setPlayerName(TEST_PLAYER_NAME);
        testPlayerRequest.setPlayerIconId(TEST_USER_ICON_ID);
        testPlayerRequest.setPlayerColorId(TEST_USER_COLOR_ID);
        return testPlayerRequest;
    }

    private UpdatePlayerRequest createUpdatePlayerRequest(String playerId) {
        UpdatePlayerRequest updatePlayerRequest = new UpdatePlayerRequest();
        updatePlayerRequest.setPlayerId(playerId);
        updatePlayerRequest.setPlayerName(TEST_PLAYER_NAME);
        updatePlayerRequest.setIconId(TEST_USER_ICON_ID);
        updatePlayerRequest.setColorId(TEST_USER_COLOR_ID);
        updatePlayerRequest.setOwnedFreightStationId("");
        updatePlayerRequest.setOwnedAbilityId("");
        updatePlayerRequest.setScore(10);
        updatePlayerRequest.setTrainsRemaining(55);
        updatePlayerRequest.setFreightContractsCompleted(1);
        updatePlayerRequest.setCompletedRoutes(new ArrayList<>());
        updatePlayerRequest.setActiveFreightContracts(new ArrayList<>());
        updatePlayerRequest.setActiveFreightContracts(new ArrayList<>());

        return updatePlayerRequest;
    }

    private PlayerRecord createTestPlayerOne() {
        return new PlayerRecord(PlayerConstants.PLAYER_ONE_ID, "Player 1", TEST_USER_ICON_ID,
            TEST_USER_COLOR_ID);
    }

    private PlayerRecord createTestPlayerTwo() {
        return new PlayerRecord(PlayerConstants.PLAYER_TWO_ID, "Player 2", TEST_USER_ICON_ID,
            TEST_USER_COLOR_ID);
    }

    private PlayerRecord createTestPlayerThree() {
        return new PlayerRecord(PlayerConstants.PLAYER_THREE_ID, "Player 2", TEST_USER_ICON_ID,
            TEST_USER_COLOR_ID);
    }

    private PlayerRecord createTestPlayerFour() {
        return new PlayerRecord(PlayerConstants.PLAYER_FOUR_ID, "Player 1", TEST_USER_ICON_ID,
            TEST_USER_COLOR_ID);
    }

}
