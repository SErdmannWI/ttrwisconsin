package com.serdmannwi.practiceprograms.tickettoridewisconsin.service;

import com.serdmannwi.practiceprograms.tickettoridewisconsin.controller.model.NewPlayerRequest;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.controller.model.PlayerResponse;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.controller.model.UpdatePlayerRequest;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.PlayerRecord;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    private final String[] PLAYER_IDS = {"PL1", "PL2", "PL3", "PL4"};


    @Autowired
    public PlayerService(PlayerRepository playerRepository) { this.playerRepository = playerRepository; }

    /**
     * New Players have an ID generated based on number of current Players.
     * Player record is instantiated with new id and saved to repository
     * @param playerRequest
     * @return
     */
    public PlayerRecord createNewPlayer(NewPlayerRequest playerRequest) {
        String newPlayerId = generatePlayerId();
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

    /**----------------------------------------- Utility Methods -----------------------------------------**/
    private String generatePlayerId() {
        int numPlayers = playerRepository.findAll().size();

        if (numPlayers < PLAYER_IDS.length) {
            return PLAYER_IDS[numPlayers];
        }

        throw new IllegalStateException("Maximum number of Players reached.");
    }
}