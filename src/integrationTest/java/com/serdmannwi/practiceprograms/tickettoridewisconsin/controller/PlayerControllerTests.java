package com.serdmannwi.practiceprograms.tickettoridewisconsin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.IntegrationTest;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.constants.PlayerConstants;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.controller.model.NewPlayerRequest;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.PlayerRecord;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.service.PlayerService;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@IntegrationTest
public class PlayerControllerTests {


    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PlayerService playerService;
    private ObjectMapper mapper;

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

    @Test
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

    /**-------------------------------------------- Utility Methods --------------------------------------------------*/
    private NewPlayerRequest createNewPlayerRequest() {
        NewPlayerRequest testPlayerRequest = new NewPlayerRequest();
        testPlayerRequest.setPlayerName(TEST_PLAYER_NAME);
        testPlayerRequest.setPlayerIconId(TEST_USER_ICON_ID);
        testPlayerRequest.setPlayerColorId(TEST_USER_COLOR_ID);
        return testPlayerRequest;
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
