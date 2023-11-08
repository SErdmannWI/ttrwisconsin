package com.serdmannwi.practiceprograms.tickettoridewisconsin.service;

import com.serdmannwi.practiceprograms.tickettoridewisconsin.constants.CityConstants;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.constants.FreightStationConstants;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.controller.model.NewPlayerRequest;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.exceptions.MaxPlayersException;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.exceptions.NoAvailableFreightStationException;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.PlayerRecord;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.PlayerRepository;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.service.model.FreightStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    private final String[] PLAYER_IDS = {"PL1", "PL2", "PL3", "PL4"};
    private Map<Integer, Map<String, FreightStation>> unownedFreightStations;
    private Map<String, FreightStation> ownedFreightStations;
    private int numPlayers = 0;


    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
        this.unownedFreightStations = generateFreightStationMap();
        ownedFreightStations = new HashMap<>();
    }

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

    public PlayerRecord getPlayerById(String id) {
        return playerRepository.findById(id).orElse(null);
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

    public int setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
        return getNumPlayers();
    }

    public int getNumPlayers() { return this.numPlayers; }

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
    public PlayerRecord chooseFreightStation(String playerId, int regionId, String freightStationId) throws NoAvailableFreightStationException {
        PlayerRecord playerRecord = playerRepository.findById(playerId).orElse(null);
        if (playerRecord == null) {
            return null;
        }

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

    /**----------------------------------------- Utility Methods -----------------------------------------**/
    private String generatePlayerId() {
        int numPlayers = playerRepository.findAll().size();

        if (numPlayers < PLAYER_IDS.length) {
            return PLAYER_IDS[numPlayers];
        }

        throw new IllegalStateException("Maximum number of Players reached.");
    }

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
