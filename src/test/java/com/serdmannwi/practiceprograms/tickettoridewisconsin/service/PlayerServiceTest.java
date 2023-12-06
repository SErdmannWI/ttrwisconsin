package com.serdmannwi.practiceprograms.tickettoridewisconsin.service;

import com.serdmannwi.practiceprograms.tickettoridewisconsin.constants.AbilityConstants;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.constants.CityConstants;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.constants.FreightStationConstants;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.constants.PlayerConstants;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.controller.model.NewPlayerRequest;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.exceptions.player.AbilityNotFoundException;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.exceptions.player.MaxPlayersException;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.exceptions.player.NoAvailableFreightStationException;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.exceptions.player.PlayerNotFoundException;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.player.PlayerRecord;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.player.PlayerRepository;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.model.Ability;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PlayerServiceTest {
    private PlayerService playerService;
    @Mock
    private PlayerRepository playerRepository;

    /**------------------------------------------------ Test Constants -----------------------------------------------*/
    private static final List<PlayerRecord> TEST_PLAYER_RECORDS_SIZE_2 = new ArrayList<>(Arrays.asList(
        new PlayerRecord(), new PlayerRecord()));
    private static final List<PlayerRecord> TEST_PLAYER_RECORDS_SIZE_4 = new ArrayList<>(Arrays.asList(
        new PlayerRecord(), new PlayerRecord(), new PlayerRecord(), new PlayerRecord()));
    private static final String[] TEST_PLAYER_ARRAY = {PlayerConstants.PLAYER_ONE_ID, PlayerConstants.PLAYER_TWO_ID};

    private static final String TEST_USER_ID = "testUser";
    private static final String TEST_USER_NAME= "userName";
    private static final String TEST_USER_COLOR_ID = "testColor";
    private static final String TEST_USER_ICON_ID = "testIcon";

    @BeforeEach
    void setup() {
        playerRepository = mock(PlayerRepository.class);
        playerService = new PlayerService(playerRepository);
    }

    /**------------------------------------------- Player Creation Tests ---------------------------------------------*/

    @Test
    public void createNewPlayer_validRequest_createsNewPlayer() {
        //GIVEN
        NewPlayerRequest playerRequest = new NewPlayerRequest();
        ArgumentCaptor<PlayerRecord> playerRecordCaptor = ArgumentCaptor.forClass(PlayerRecord.class);
        PlayerRecord returnedRecord = new PlayerRecord("", TEST_USER_NAME, TEST_USER_ICON_ID, TEST_USER_COLOR_ID);

        returnedRecord.setPlayerId(PlayerConstants.PLAYER_ONE_ID);
        returnedRecord.setCompletedRoutes(new ArrayList<>());
        returnedRecord.setActiveFreightContracts(new ArrayList<>());
        returnedRecord.setActiveEffects(new ArrayList<>());
        returnedRecord.setOwnedFreightStationId("");
        returnedRecord.setOwnedAbilityId("");
        returnedRecord.setScore(PlayerConstants.STARTING_SCORE);
        returnedRecord.setTrainsRemaining(PlayerConstants.STARTING_TRAINS);
        returnedRecord.setFreightContractsCompleted(0);

        playerRequest.setPlayerName(TEST_USER_NAME);
        playerRequest.setPlayerColorId(TEST_USER_COLOR_ID);
        playerRequest.setPlayerIconId(TEST_USER_ICON_ID);

        //WHEN
        when(playerRepository.findAll()).thenReturn(new ArrayList<>());
        when(playerRepository.save(any())).thenReturn(returnedRecord);
        PlayerRecord playerRecord = playerService.createNewPlayer(playerRequest);

        //THEN
        verify(playerRepository).save(playerRecordCaptor.capture());
        PlayerRecord capturedPlayerRecord = playerRecordCaptor.getValue();

        Assertions.assertEquals(playerRecord.getPlayerId(), PlayerConstants.PLAYER_ONE_ID);
        Assertions.assertEquals(playerRecord.getPlayerName(), TEST_USER_NAME);
        Assertions.assertEquals(playerRecord.getColorId(), TEST_USER_COLOR_ID);
        Assertions.assertEquals(playerRecord.getIconId(), TEST_USER_ICON_ID);
        Assertions.assertEquals(playerRecord.getCompletedRoutes().size(), 0);
        Assertions.assertEquals(playerRecord.getActiveFreightContracts().size(), 0);
        Assertions.assertEquals(playerRecord.getActiveEffects().size(), 0);
        Assertions.assertEquals(playerRecord.getOwnedFreightStationId(), "");
        Assertions.assertEquals(playerRecord.getOwnedAbilityId(), "");
        Assertions.assertEquals(playerRecord.getScore(), PlayerConstants.STARTING_SCORE);
        Assertions.assertEquals(playerRecord.getTrainsRemaining(), PlayerConstants.STARTING_TRAINS);
        Assertions.assertEquals(playerRecord.getFreightContractsCompleted(), 0);

        Assertions.assertEquals(capturedPlayerRecord.getPlayerId(), PlayerConstants.PLAYER_ONE_ID);
        Assertions.assertEquals(capturedPlayerRecord.getPlayerName(), TEST_USER_NAME);
        Assertions.assertEquals(capturedPlayerRecord.getColorId(), TEST_USER_COLOR_ID);
        Assertions.assertEquals(capturedPlayerRecord.getIconId(), TEST_USER_ICON_ID);
        Assertions.assertEquals(capturedPlayerRecord.getCompletedRoutes().size(), 0);
        Assertions.assertEquals(capturedPlayerRecord.getActiveFreightContracts().size(), 0);
        Assertions.assertEquals(capturedPlayerRecord.getActiveEffects().size(), 0);
        Assertions.assertEquals(capturedPlayerRecord.getOwnedFreightStationId(), "");
        Assertions.assertEquals(capturedPlayerRecord.getOwnedAbilityId(), "");
        Assertions.assertEquals(capturedPlayerRecord.getScore(), 0);
        Assertions.assertEquals(capturedPlayerRecord.getTrainsRemaining(), 60);
        Assertions.assertEquals(capturedPlayerRecord.getFreightContractsCompleted(), 0);
    }

    @Test
    public void createNewPlayer_thirdPlayer_assignsCorrectId() {
        //GIVEN
        NewPlayerRequest playerRequest = new NewPlayerRequest();
        ArgumentCaptor<PlayerRecord> playerRecordCaptor = ArgumentCaptor.forClass(PlayerRecord.class);
        PlayerRecord returnedRecord = new PlayerRecord("", TEST_USER_NAME, TEST_USER_ICON_ID, TEST_USER_COLOR_ID);

        returnedRecord.setPlayerId(PlayerConstants.PLAYER_THREE_ID);
        returnedRecord.setCompletedRoutes(new ArrayList<>());
        returnedRecord.setActiveFreightContracts(new ArrayList<>());
        returnedRecord.setActiveEffects(new ArrayList<>());
        returnedRecord.setOwnedFreightStationId("");
        returnedRecord.setOwnedAbilityId("");
        returnedRecord.setScore(PlayerConstants.STARTING_SCORE);
        returnedRecord.setTrainsRemaining(PlayerConstants.STARTING_TRAINS);
        returnedRecord.setFreightContractsCompleted(0);

        playerRequest.setPlayerName(TEST_USER_NAME);
        playerRequest.setPlayerColorId(TEST_USER_COLOR_ID);
        playerRequest.setPlayerIconId(TEST_USER_ICON_ID);

        //WHEN
        when(playerRepository.findAll()).thenReturn(TEST_PLAYER_RECORDS_SIZE_2);
        when(playerRepository.save(any())).thenReturn(returnedRecord);
        PlayerRecord playerRecord = playerService.createNewPlayer(playerRequest);

        //THEN
        verify(playerRepository).save(playerRecordCaptor.capture());
        PlayerRecord capturedPlayerRecord = playerRecordCaptor.getValue();

        Assertions.assertEquals(playerRecord.getPlayerId(), PlayerConstants.PLAYER_THREE_ID);
        Assertions.assertEquals(playerRecord.getPlayerName(), TEST_USER_NAME);
        Assertions.assertEquals(playerRecord.getColorId(), TEST_USER_COLOR_ID);
        Assertions.assertEquals(playerRecord.getIconId(), TEST_USER_ICON_ID);
        Assertions.assertEquals(playerRecord.getCompletedRoutes().size(), 0);
        Assertions.assertEquals(playerRecord.getActiveFreightContracts().size(), 0);
        Assertions.assertEquals(playerRecord.getActiveEffects().size(), 0);
        Assertions.assertEquals(playerRecord.getOwnedFreightStationId(), "");
        Assertions.assertEquals(playerRecord.getOwnedAbilityId(), "");
        Assertions.assertEquals(playerRecord.getScore(), PlayerConstants.STARTING_SCORE);
        Assertions.assertEquals(playerRecord.getTrainsRemaining(), PlayerConstants.STARTING_TRAINS);
        Assertions.assertEquals(playerRecord.getFreightContractsCompleted(), 0);

        Assertions.assertEquals(capturedPlayerRecord.getPlayerId(), PlayerConstants.PLAYER_THREE_ID);
        Assertions.assertEquals(capturedPlayerRecord.getPlayerName(), TEST_USER_NAME);
        Assertions.assertEquals(capturedPlayerRecord.getColorId(), TEST_USER_COLOR_ID);
        Assertions.assertEquals(capturedPlayerRecord.getIconId(), TEST_USER_ICON_ID);
        Assertions.assertEquals(capturedPlayerRecord.getCompletedRoutes().size(), 0);
        Assertions.assertEquals(capturedPlayerRecord.getActiveFreightContracts().size(), 0);
        Assertions.assertEquals(capturedPlayerRecord.getActiveEffects().size(), 0);
        Assertions.assertEquals(capturedPlayerRecord.getOwnedFreightStationId(), "");
        Assertions.assertEquals(capturedPlayerRecord.getOwnedAbilityId(), "");
        Assertions.assertEquals(capturedPlayerRecord.getScore(), 0);
        Assertions.assertEquals(capturedPlayerRecord.getTrainsRemaining(), 60);
        Assertions.assertEquals(capturedPlayerRecord.getFreightContractsCompleted(), 0);
    }

    @Test
    public void createNewPlayer_fullPlayers_throwsMaxPlayersException() {
        //GIVEN
        NewPlayerRequest playerRequest = new NewPlayerRequest();

        playerRequest.setPlayerName(TEST_USER_NAME);
        playerRequest.setPlayerColorId(TEST_USER_COLOR_ID);
        playerRequest.setPlayerIconId(TEST_USER_ICON_ID);

        //WHEN
        when(playerRepository.findAll()).thenReturn(TEST_PLAYER_RECORDS_SIZE_4);

        //THEN
        Assertions.assertThrows(MaxPlayersException.class, () -> playerService.createNewPlayer(playerRequest));
    }

    /**------------------------------------------- Player Retrieval Tests --------------------------------------------*/

    @Test
    public void getPlayerById_playerExists_returnsPlayer() {
        //GIVEN
        PlayerRecord returnedRecord = new PlayerRecord("", TEST_USER_NAME, TEST_USER_ICON_ID, TEST_USER_COLOR_ID);

        returnedRecord.setPlayerId(PlayerConstants.PLAYER_ONE_ID);
        returnedRecord.setCompletedRoutes(new ArrayList<>());
        returnedRecord.setActiveFreightContracts(new ArrayList<>());
        returnedRecord.setActiveEffects(new ArrayList<>());
        returnedRecord.setOwnedFreightStationId("");
        returnedRecord.setOwnedAbilityId("");
        returnedRecord.setScore(PlayerConstants.STARTING_SCORE);
        returnedRecord.setTrainsRemaining(PlayerConstants.STARTING_TRAINS);
        returnedRecord.setFreightContractsCompleted(0);

        //WHEN
        when(playerRepository.findById(PlayerConstants.PLAYER_ONE_ID)).thenReturn(Optional.of(returnedRecord));
        PlayerRecord retrievedRecord = playerService.getPlayerById(PlayerConstants.PLAYER_ONE_ID);

        //THEN
        Assertions.assertEquals(retrievedRecord.getPlayerId(), PlayerConstants.PLAYER_ONE_ID);
        Assertions.assertEquals(retrievedRecord.getPlayerName(), TEST_USER_NAME);
        Assertions.assertEquals(retrievedRecord.getColorId(), TEST_USER_COLOR_ID);
        Assertions.assertEquals(retrievedRecord.getIconId(), TEST_USER_ICON_ID);
        Assertions.assertEquals(retrievedRecord.getCompletedRoutes().size(), 0);
        Assertions.assertEquals(retrievedRecord.getActiveFreightContracts().size(), 0);
        Assertions.assertEquals(retrievedRecord.getActiveEffects().size(), 0);
        Assertions.assertEquals(retrievedRecord.getOwnedFreightStationId(), "");
        Assertions.assertEquals(retrievedRecord.getOwnedAbilityId(), "");
        Assertions.assertEquals(retrievedRecord.getScore(), 0);
        Assertions.assertEquals(retrievedRecord.getTrainsRemaining(), 60);
        Assertions.assertEquals(retrievedRecord.getFreightContractsCompleted(), 0);
    }

    @Test
    public void getPlayerById_playerDoesNotExist_throwsPlayerNotFoundException() {
        //GIVEN
        String nonExistingPlayerID = "test";

        //WHEN
        when(playerRepository.findById(nonExistingPlayerID)).thenReturn(Optional.empty());

        //THEN
        Assertions.assertThrows(PlayerNotFoundException.class, () -> playerService.getPlayerById(nonExistingPlayerID));
    }

    @Test
    public void getAllPlayers_twoPlayers_returnsList() {
        //GIVEN/WHEN
        when(playerRepository.findAll()).thenReturn(TEST_PLAYER_RECORDS_SIZE_2);

        //THEN
        Assertions.assertEquals(playerService.getAllPlayers().size(), 2);
    }

    @Test
    public void getAllPlayers_noPlayers_returnsEmptyList() {
        //GIVEN/WHEN
        when(playerRepository.findAll()).thenReturn(new ArrayList<>());

        //THEN
        Assertions.assertEquals(playerService.getAllPlayers().size(), 0);

    }

    /**---------------------------------------- Player Turn Manager Tests --------------------------------------------*/
    @Test
    public void createTurnQueue_validPlayers_createsValidQueue() {
        //GIVEN
        when(playerRepository.findById(PlayerConstants.PLAYER_ONE_ID)).thenReturn(Optional.of(createTestPlayerOne()));
        when(playerRepository.findById(PlayerConstants.PLAYER_TWO_ID)).thenReturn(Optional.of(createTestPlayerTwo()));

        //WHEN
        playerService.createTurnQueue(TEST_PLAYER_ARRAY);

        //THEN
        PlayerRecord nextPlayer = playerService.getNextPlayer();
        Assertions.assertEquals(nextPlayer.getPlayerId(), PlayerConstants.PLAYER_ONE_ID);
    }

    @Test
    public void deferTurn_validPlayerId_playerIdAddedToDeferredTurns() {


    }

    /**------------------------------------------- Player Choices Tests ----------------------------------------------*/

    @Test
    public void chooseFreightStation_validRequest_returnsUpdatedPlayer() {
        //GIVEN
        int regionId = CityConstants.REGION_ONE_ID;
        String requestedFreightStationId = FreightStationConstants.MADISON_FREIGHT_ID;
        PlayerRecord testPlayerOne = createTestPlayerOne();
        when(playerRepository.findById(PlayerConstants.PLAYER_ONE_ID)).thenReturn(Optional.of(testPlayerOne));
        when(playerRepository.save(any(PlayerRecord.class))).thenReturn(testPlayerOne);

        //WHEN
        PlayerRecord returnedPlayer = playerService.chooseFreightStation(PlayerConstants.PLAYER_ONE_ID, regionId,
            requestedFreightStationId);

        //THEN
        Assertions.assertEquals(returnedPlayer.getPlayerId(), testPlayerOne.getPlayerId());
        Assertions.assertEquals(returnedPlayer.getPlayerName(), testPlayerOne.getPlayerName());
        Assertions.assertEquals(returnedPlayer.getColorId(), testPlayerOne.getColorId());
        Assertions.assertEquals(returnedPlayer.getIconId(), testPlayerOne.getIconId());
        Assertions.assertEquals(returnedPlayer.getCompletedRoutes().size(), 0);
        Assertions.assertEquals(returnedPlayer.getActiveFreightContracts().size(), 0);
        Assertions.assertEquals(returnedPlayer.getActiveEffects().size(), 0);
        Assertions.assertEquals(returnedPlayer.getOwnedFreightStationId(), requestedFreightStationId);
        Assertions.assertEquals(returnedPlayer.getOwnedAbilityId(), "");
        Assertions.assertEquals(returnedPlayer.getScore(), 0);
        Assertions.assertEquals(returnedPlayer.getTrainsRemaining(), 60);
        Assertions.assertEquals(returnedPlayer.getFreightContractsCompleted(), 0);
    }

    @Test
    public void chooseFreightStation_stationAlreadyChosen_throwsNoAvailableFreightStationsException() {
        //GIVEN
        int regionId = CityConstants.REGION_ONE_ID;
        String requestedFreightStationId = FreightStationConstants.MADISON_FREIGHT_ID;
        PlayerRecord testPlayerOne = createTestPlayerOne();
        PlayerRecord testPlayerTwo = createTestPlayerTwo();
        when(playerRepository.findById(PlayerConstants.PLAYER_ONE_ID)).thenReturn(Optional.of(testPlayerOne));
        when(playerRepository.findById(PlayerConstants.PLAYER_TWO_ID)).thenReturn(Optional.of(testPlayerTwo));
        when(playerRepository.save(any(PlayerRecord.class))).thenReturn(testPlayerOne);

        //WHEN
        playerService.chooseFreightStation(PlayerConstants.PLAYER_ONE_ID, regionId,
            requestedFreightStationId);

        //THEN
        Assertions.assertThrows(NoAvailableFreightStationException.class, () -> playerService.chooseFreightStation(
            PlayerConstants.PLAYER_TWO_ID, regionId, requestedFreightStationId));
    }

    @Test
    public void chooseAbility_validAbility_addsAbilityToPlayer() {
        //GIVEN
        ArgumentCaptor<PlayerRecord> playerRecordCaptor = ArgumentCaptor.forClass(PlayerRecord.class);
        PlayerRecord testPlayerOne = createTestPlayerOne();
        when(playerRepository.findById(PlayerConstants.PLAYER_ONE_ID)).thenReturn(Optional.of(testPlayerOne));

        //WHEN
        playerService.chooseAbility(PlayerConstants.PLAYER_ONE_ID, AbilityConstants.DELAY_TURN_ID);
        verify(playerRepository).save(playerRecordCaptor.capture());
        PlayerRecord capturedPlayerRecord = playerRecordCaptor.getValue();

        //THEN
        Assertions.assertEquals(capturedPlayerRecord.getPlayerId(), PlayerConstants.PLAYER_ONE_ID);
        Assertions.assertEquals(capturedPlayerRecord.getPlayerName(), testPlayerOne.getPlayerName());
        Assertions.assertEquals(capturedPlayerRecord.getColorId(), testPlayerOne.getColorId());
        Assertions.assertEquals(capturedPlayerRecord.getIconId(), testPlayerOne.getIconId());
        Assertions.assertEquals(capturedPlayerRecord.getCompletedRoutes().size(), 0);
        Assertions.assertEquals(capturedPlayerRecord.getActiveFreightContracts().size(), 0);
        Assertions.assertEquals(capturedPlayerRecord.getActiveEffects().size(), 0);
        Assertions.assertEquals(capturedPlayerRecord.getOwnedFreightStationId(), "");
        Assertions.assertEquals(capturedPlayerRecord.getOwnedAbilityId(), AbilityConstants.DELAY_TURN_ID);
        Assertions.assertEquals(capturedPlayerRecord.getScore(), 0);
        Assertions.assertEquals(capturedPlayerRecord.getTrainsRemaining(), 60);
        Assertions.assertEquals(capturedPlayerRecord.getFreightContractsCompleted(), 0);
    }

    @Test
    public void chooseAbility_validAbility_addsAbilityToOwnedAbilitiesMap() {
        //GIVEN
        PlayerRecord testPlayerOne = createTestPlayerOne();
        when(playerRepository.findById(PlayerConstants.PLAYER_ONE_ID)).thenReturn(Optional.of(testPlayerOne));

        //WHEN
        playerService.chooseAbility(PlayerConstants.PLAYER_ONE_ID, AbilityConstants.DELAY_TURN_ID);
        Ability chosenAbility = playerService.getOwnedAbilities().get(PlayerConstants.PLAYER_ONE_ID);

        //THEN
        Assertions.assertNotNull(chosenAbility);
        Assertions.assertEquals(chosenAbility.getAbilityId(), AbilityConstants.DELAY_TURN_ID);
        Assertions.assertEquals(chosenAbility.getOwnerId(), PlayerConstants.PLAYER_ONE_ID);
        Assertions.assertEquals(chosenAbility.getAbilityName(), AbilityConstants.DELAY_TURN_ABILITY.getAbilityName());
        Assertions.assertEquals(chosenAbility.getDescription(), AbilityConstants.DELAY_TURN_ABILITY.getDescription());
        Assertions.assertEquals(chosenAbility.getUsesRemaining(), AbilityConstants.ABILITY_STARTING_USES);
        Assertions.assertEquals(chosenAbility.getBonusPoints(), AbilityConstants.DELAY_TURN_ABILITY.getBonusPoints());
        Assertions.assertTrue(chosenAbility.isActive());
        Assertions.assertFalse(chosenAbility.isUnlimited());
    }

    @Test
    public void chooseAbility_invalidAbilityId_throwsAbilityNotFoundException() {
        //GIVEN
        PlayerRecord testPlayerOne = createTestPlayerOne();
        when(playerRepository.findById(PlayerConstants.PLAYER_ONE_ID)).thenReturn(Optional.of(testPlayerOne));

        //WHEN/THEN
        Assertions.assertThrows(AbilityNotFoundException.class, () -> playerService.chooseAbility(
            PlayerConstants.PLAYER_ONE_ID, UUID.randomUUID().toString()));
    }

    @Test
    public void chooseAbility_abilityAlreadyChosen_throwsAbilityNotFoundException() {
        //GIVEN
        PlayerRecord testPlayerOne = createTestPlayerOne();
        PlayerRecord testPlayerTwo = createTestPlayerTwo();
        when(playerRepository.findById(PlayerConstants.PLAYER_ONE_ID)).thenReturn(Optional.of(testPlayerOne));
        when(playerRepository.findById(PlayerConstants.PLAYER_TWO_ID)).thenReturn(Optional.of(testPlayerTwo));

        playerService.chooseAbility(PlayerConstants.PLAYER_ONE_ID, AbilityConstants.DELAY_TURN_ID);

        //WHEN/THEN
        Assertions.assertThrows(AbilityNotFoundException.class, () -> playerService.chooseAbility(
            PlayerConstants.PLAYER_TWO_ID, AbilityConstants.DELAY_TURN_ID));
    }

    /**-------------------------------------------- Utility Methods --------------------------------------------------*/
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
