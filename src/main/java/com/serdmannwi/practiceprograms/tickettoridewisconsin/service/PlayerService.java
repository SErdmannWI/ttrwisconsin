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
import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.model.FreightStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private Deque<String> playerQueue; //Queue of Player IDs in order of turns
    private List<String> deferredTurns; //Used for if a Player has the Ability to defer turns to next round
    private List<String> chosenAbilities; //Used for IDs of chosen Abilities
    private String[] playerOrderMaster; //Master order for Player turns
    private Map<Integer, Map<String, FreightStation>> unownedFreightStations;
    private Map<String, FreightStation> ownedFreightStations;
    private Map<String, Ability> ownedAbilities;
    private int numPlayers = 0;


    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
        this.unownedFreightStations = generateFreightStationMap();
        ownedFreightStations = new HashMap<>();
        ownedAbilities = new HashMap<>();
        deferredTurns = new ArrayList<>();
        chosenAbilities = new ArrayList<>();
    }

    /**------------------------------------------- Player Creation ---------------------------------------------------*/

    /**
     * New Players have an ID generated based on number of current Players.
     * Player record is instantiated with new id and saved to repository
     * @param playerRequest- contains name, icon id, color id
     * @return saved PlayerRecord
     */
    public PlayerRecord createNewPlayer(NewPlayerRequest playerRequest) throws MaxPlayersException {
        String newPlayerId = "";
        try {
            newPlayerId = generatePlayerId();
        } catch (IllegalStateException e) {
            throw new MaxPlayersException("The maximum number of players (4) has been reached.");
        }
        PlayerRecord newPlayerRecord = new PlayerRecord(newPlayerId, playerRequest.getPlayerName(), playerRequest.getPlayerIconId(),
            playerRequest.getPlayerColorId());

        return playerRepository.save(newPlayerRecord);
    }

    /**---------------------------------------- Database Interactions ------------------------------------------------*/

    public PlayerRecord getPlayerById(String id) throws PlayerNotFoundException {
        return playerRepository.findById(id)
            .orElseThrow(() -> new PlayerNotFoundException("Could not find Player with id: " + id + "."));
    }

    public List<PlayerRecord> getAllPlayers() {
        return playerRepository.findAll();
    }

    public PlayerRecord updatePlayer(PlayerRecord playerToUpdate) {
        return playerRepository.save(playerToUpdate);
    }

    public PlayerRecord deletePlayer(String id) {
        Optional<PlayerRecord> playerToBeDeleted = playerRepository.findById(id);
        if (playerToBeDeleted.isEmpty()) {
            return null;
        }
        playerRepository.deleteById(id);

        return playerToBeDeleted.get();
    }

    /**----------------------------------------- Player Turn Manager ------------------------------------------------*/
    public void createTurnQueue(String[] playerIds) throws PlayerNotFoundException {
        playerOrderMaster = playerIds;
        playerQueue = new LinkedList<>();
        for (String playerId : playerOrderMaster) {
            getPlayerById(playerId);
            playerQueue.add(playerId);
        }
    }

    public void startNextRound() throws PlayerNotFoundException {
        for (String playerId : playerOrderMaster) {
            getPlayerById(playerId);
            playerQueue.add(playerId);
        }
    }

    public PlayerRecord getNextPlayer() throws PlayerNotFoundException {
        if (playerQueue.isEmpty()) {
            startNextRound();
        }
        String playerId = playerQueue.poll();
        //Check if deferredTurns contains any Players. If so, check if the current Player matches. If so, add the
        //Player back into the queue
        if (!deferredTurns.isEmpty()) {
            System.out.println("Player ID: " + playerId + "\nDeferred Player: " + deferredTurns.get(0));
            if (playerId.equals(deferredTurns.get(0))) {
                playerQueue.addFirst(playerId);
                deferredTurns.clear();
                return playerRepository.findById(playerId).orElseThrow(() -> new PlayerNotFoundException
                    ("Player could not be found with id: " + playerId + "."));
            }
        }
        return playerRepository.findById(playerId).orElseThrow(() -> new PlayerNotFoundException
            ("Player could not be found with id: " + playerId + "."));
    }

    public void deferTurn(String currentPlayerId) {
        deferredTurns.add(currentPlayerId);
    }

    /**------------------------------------------- Player Choices ---------------------------------------------------*/

    /**
     * Checks that the Player exists, that the region contains a choose-able FreightStation
     * PlayerRecord has the FreightStation ID added and the FreightStation is added to the Map of owned FreightStations
     * where the key is the Player ID and the value is the Player's FreightStation itself
     * @param playerId
     * @param regionId
     * @param freightStationId
     * @return updated PlayerRecord with FreightStationID added
     * @throws NoAvailableFreightStationException
     */
    public PlayerRecord chooseFreightStation(String playerId, int regionId, String freightStationId)
        throws NoAvailableFreightStationException, PlayerNotFoundException {
        PlayerRecord playerRecord = playerRepository.findById(playerId).orElseThrow(() ->
            new PlayerNotFoundException("Player could not be found with id: " + playerId + "."));

        if (!unownedFreightStations.containsKey(regionId)) {
            throw new NoAvailableFreightStationException("Two Players cannot own Freight Stations in the same Region.");
        }else if (!unownedFreightStations.get(regionId).containsKey(freightStationId)) {
            throw new NoAvailableFreightStationException("This freight station is not available. Please select a different one.");
        }

        String chosenCityId = unownedFreightStations.get(regionId).get(freightStationId).getCityId();
        String chosenFreightStationName = unownedFreightStations.get(regionId).get(freightStationId).getFreightStationName();

        unownedFreightStations.remove(regionId);

        FreightStation chosenFreightStation = new FreightStation(freightStationId, chosenFreightStationName, playerId,
            chosenCityId, regionId);

        ownedFreightStations.put(playerId, chosenFreightStation);

        playerRecord.setOwnedFreightStationId(freightStationId);

        return playerRepository.save(playerRecord);
    }

    /**
     * Checks that Player exists and that the Ability chosen is available. Adds the ability to the local Map where the
     * key is the playerID and the Ability is the value. Adds the abilityId to the PlayerRecord and saves to repository.
     * @param playerId
     * @param abilityId
     * @return updated PlayerRecord
     * @throws AbilityNotFoundException
     */

    public PlayerRecord chooseAbility(String playerId, String abilityId) throws AbilityNotFoundException, PlayerNotFoundException {
        PlayerRecord playerRecord = playerRepository.findById(playerId).orElseThrow(() -> new PlayerNotFoundException(
            "Player could not be found with id: " + playerId + "."));

        if (!AbilityConstants.ABILITY_MAP.containsKey(abilityId)) {
            throw new AbilityNotFoundException("Cannot choose ability with ID: " + abilityId + ".");
        }

        if (chosenAbilities.contains(abilityId)) {
            throw new AbilityNotFoundException("Ability already chosen with ID: " + abilityId + ".");
        }

        Ability ability = AbilityConstants.ABILITY_MAP.get(abilityId);
        Ability chosenAbility = new Ability(ability.getAbilityName(), ability.getAbilityId(), ability.getDescription(),
            ability.getBonusPoints(), playerId);

        ownedAbilities.put(playerId, chosenAbility);
        chosenAbilities.add(chosenAbility.getAbilityId());
        playerRecord.setOwnedAbilityId(abilityId);

        return playerRepository.save(playerRecord);
    }

    /**----------------------------------------- Utility Methods -----------------------------------------**/
    private String generatePlayerId() {
        int numPlayers = playerRepository.findAll().size();

        if (numPlayers < PlayerConstants.PLAYER_IDS.length) {
            return PlayerConstants.PLAYER_IDS[numPlayers];
        }

        throw new IllegalStateException("Maximum number of Players reached.");
    }

    public int setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
        return getNumPlayers();
    }

    public int getNumPlayers() { return this.numPlayers; }

    public List<String> getChosenAbilities() { return this.chosenAbilities; }

    public Map<String, Ability> getOwnedAbilities() { return this.ownedAbilities; }

    /**
     * Method runs at startup
     * All Freight Stations will be stored in a Map with the Integer key being Region ID and the value
     * being another map with the key being the freight station id and the value being the freight station itself
     * @return Map of all available FreightStations
     */
    private Map<Integer, Map<String, FreightStation>> generateFreightStationMap() {
        Map<Integer, Map<String, FreightStation>> freightStationMap = new HashMap<>();
        freightStationMap.put(CityConstants.REGION_ONE_ID, FreightStationConstants.REGION_ONE_FREIGHT_STATION_MAP);
        freightStationMap.put(CityConstants.REGION_TWO_ID, FreightStationConstants.REGION_TWO_FREIGHT_STATION_MAP);
        freightStationMap.put(CityConstants.REGION_THREE_ID, FreightStationConstants.REGION_THREE_FREIGHT_STATION_MAP);
        freightStationMap.put(CityConstants.REGION_FOUR_ID, FreightStationConstants.REGION_FOUR_FREIGHT_STATION_MAP);

        return freightStationMap;
    }
}
