package com.serdmannwi.practiceprograms.tickettoridewisconsin.service;

import com.serdmannwi.practiceprograms.tickettoridewisconsin.constants.PlayerConstants;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.controller.model.NewPlayerRequest;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.controller.model.PlayerResponse;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.controller.model.UpdatePlayerRequest;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.exceptions.MaxPlayersException;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.exceptions.NoAvailableFreightStationException;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.PlayerRecord;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.PlayerRepository;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.service.model.FreightStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    private final String[] PLAYER_IDS = {"PL1", "PL2", "PL3", "PL4"};
    private Map<Integer, Map<String, FreightStation>> freightStationMap;
    private int numPlayers = 0;


    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
        this.freightStationMap = generateFreightStationMap();
    }

    /**
     * New Players have an ID generated based on number of current Players.
     * Player record is instantiated with new id and saved to repository
     * @param playerRequest
     * @return
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

    public PlayerRecord chooseFreightStation(String playerId, int regionId, String freightStationId) throws NoAvailableFreightStationException {
        System.out.println(freightStationMap.values());
        PlayerRecord playerRecord = playerRepository.findById(playerId).orElse(null);
        if (playerRecord == null) {
            return null;
        }

        if (!freightStationMap.containsKey(regionId)) {
            throw new NoAvailableFreightStationException("Two Players cannot own Freight Stations in the same Region.");
        }else if (!freightStationMap.get(regionId).containsKey(freightStationId)) {
            throw new NoAvailableFreightStationException("This freight station is not available. Please select a different one.");
        }

        freightStationMap.remove(regionId);

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

    private Map<Integer, Map<String, FreightStation>> generateFreightStationMap() {
        Map<Integer, Map<String, FreightStation>> freightStationMap = new HashMap<>();

        return freightStationMap;
    }
}
