package com.serdmannwi.practiceprograms.tickettoridewisconsin.controller;

import com.serdmannwi.practiceprograms.tickettoridewisconsin.controller.model.NewPlayerRequest;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.controller.model.PlayerResponse;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.controller.model.UpdatePlayerRequest;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.PlayerRecord;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.service.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/player")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) { this.playerService = playerService; }

    @GetMapping ("/allPlayers")
    public ResponseEntity<List<PlayerResponse>> getAllPlayers() {
        List<PlayerRecord> allPlayers = playerService.getAllPlayers();
        if (allPlayers.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<PlayerResponse> allPlayerResponses = allPlayers.stream()
            .map(this::recordToResponse)
            .collect(Collectors.toList());

        return ResponseEntity.ok(allPlayerResponses);
    }

    @GetMapping("/getPlayer/{id}")
    public ResponseEntity<PlayerResponse> getPlayerById(@PathVariable("id") String id) {
        PlayerRecord playerRecord = playerService.getPlayerById(id);
        if (playerRecord == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(recordToResponse(playerRecord));
    }

    @PostMapping("/newPlayer")
    public ResponseEntity<PlayerResponse> createNewPlayer(@RequestBody NewPlayerRequest playerRequest) {
        PlayerRecord newPlayerRecord = playerService.createNewPlayer(playerRequest);
        if (newPlayerRecord == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(recordToResponse(newPlayerRecord));
    }

    @PutMapping("/updatePlayer")
    public ResponseEntity<PlayerResponse> updatePlayer(@RequestBody UpdatePlayerRequest updatePlayerRequest) {
        PlayerRecord updatedPlayer = playerService.updatePlayer(requestToRecord(updatePlayerRequest));

        return ResponseEntity.ok(recordToResponse(updatedPlayer));
    }

    @DeleteMapping("/deletePlayer/{id}")
    public ResponseEntity<PlayerResponse> deletePlayer(@PathVariable("id") String id) {
        PlayerRecord deletedPlayer = playerService.deletePlayer(id);
        if (deletedPlayer == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(recordToResponse(deletedPlayer));
    }

    /**----------------------------------------- Conversion Methods -----------------------------------------**/
    private PlayerResponse recordToResponse(PlayerRecord playerRecord) {
        PlayerResponse playerResponse = new PlayerResponse();

        playerResponse.setPlayerName(playerRecord.getPlayerName());
        playerResponse.setPlayerId(playerRecord.getPlayerId());
        playerResponse.setIconId(playerRecord.getIconId());
        playerResponse.setOwnedFreightStationId(playerRecord.getOwnedFreightStationId());
        playerResponse.setOwnedAbilityId(playerRecord.getOwnedAbilityId());
        playerResponse.setColorId(playerRecord.getColorId());
        playerResponse.setScore(playerRecord.getScore());
        playerResponse.setTrainsRemaining(playerRecord.getTrainsRemaining());
        playerResponse.setFreightContractsCompleted(playerRecord.getFreightContractsCompleted());
        playerResponse.setCompletedRoutes(playerRecord.getCompletedRoutes());
        playerResponse.setActiveFreightContracts(playerRecord.getActiveFreightContracts());
        playerResponse.setActiveEffects(playerRecord.getActiveEffects());

        return playerResponse;
    }

    private PlayerRecord requestToRecord(UpdatePlayerRequest updatePlayerRequest) {
        PlayerRecord playerRecord = new PlayerRecord();

        playerRecord.setPlayerName(updatePlayerRequest.getPlayerName());
        playerRecord.setPlayerId(updatePlayerRequest.getPlayerId());
        playerRecord.setIconId(updatePlayerRequest.getIconId());
        playerRecord.setOwnedFreightStationId(updatePlayerRequest.getOwnedFreightStationId());
        playerRecord.setOwnedAbilityId(updatePlayerRequest.getOwnedAbilityId());
        playerRecord.setColorId(updatePlayerRequest.getColorId());
        playerRecord.setScore(updatePlayerRequest.getScore());
        playerRecord.setTrainsRemaining(updatePlayerRequest.getTrainsRemaining());
        playerRecord.setFreightContractsCompleted(updatePlayerRequest.getFreightContractsCompleted());
        playerRecord.setCompletedRoutes(updatePlayerRequest.getCompletedRoutes());
        playerRecord.setActiveFreightContracts(updatePlayerRequest.getActiveFreightContracts());
        playerRecord.setActiveEffects(updatePlayerRequest.getActiveEffects());

        return playerRecord;
    }
}
