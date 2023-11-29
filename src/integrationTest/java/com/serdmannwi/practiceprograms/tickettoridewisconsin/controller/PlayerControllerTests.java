package com.serdmannwi.practiceprograms.tickettoridewisconsin.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jayway.jsonpath.JsonPath;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.IntegrationTest;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.constants.PlayerConstants;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.controller.model.NewPlayerRequest;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.controller.model.PlayerResponse;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.controller.model.UpdatePlayerRequest;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.PlayerRecord;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.service.PlayerService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//import org.testcontainers.containers.MySQLContainer;
//import org.testcontainers.junit.jupiter.Testcontainers;
//import org.testcontainers.junit.jupiter.Container;
//@Testcontainers
@IntegrationTest
public class PlayerControllerTests {


    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PlayerService playerService;
    private ObjectMapper mapper;

//    @Container
//    public static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:5.7")
//        .withDatabaseName("testdb")
//        .withUsername("test")
//        .withPassword("test");

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
    public void createTurnOrder_validPlayerIds_returnsOKResponse() {

    }

    @Test
    public void createTurnOrder_invalidPlayerIds_returnsNotFound() {

    }

    @Test
    public void getNextPlayer_returnsNextPlayer() {

    }

    @Test
    public void deferPlayerTurn_validPlayer_returnsOKResponse() {

    }


    /**------------------------------------------- Player Choices Tests ----------------------------------------------*/

    @Test
    public void chooseFreightStation_correctFreightStation_addsFreightStation() {

    }

    @Test
    public void chooseFreightStation_stationAlreadyChosen_returnsBadRequest() {

    }

    @Test
    public void chooseAbility_validRequest_addsAbility() {

    }

    @Test
    public void chooseAbility_abilityAlreadyChosen_returnsBadRequest() {

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
