package com.serdmannwi.practiceprograms.tickettoridewisconsin.controller;

import com.serdmannwi.practiceprograms.tickettoridewisconsin.controller.model.ChooseFreightStationRequest;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.controller.model.NewPlayerRequest;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.controller.model.PlayerResponse;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.controller.model.UpdatePlayerRequest;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.exceptions.ErrorResponse;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.exceptions.MaxPlayersException;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.exceptions.NoAvailableFreightStationException;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.PlayerRecord;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.service.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    public ResponseEntity<?> createNewPlayer(@RequestBody NewPlayerRequest playerRequest) {
        PlayerRecord newPlayerRecord;
        try {
            newPlayerRecord = playerService.createNewPlayer(playerRequest);
        } catch (MaxPlayersException e) {
            ErrorResponse errorResponse = new ErrorResponse(400, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(errorResponse);
        }
        if (newPlayerRecord == null) {
            ErrorResponse errorResponse = new ErrorResponse(400, "Unknown error took place while attempting to create a new Player.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(errorResponse);
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

    @PutMapping("/chooseFreightStation")
    public ResponseEntity<?> chooseFreightStation(@RequestBody ChooseFreightStationRequest freightStationRequest) {
        if (playerService.getPlayerById(freightStationRequest.getPlayerId()) == null) {
            ErrorResponse errorResponse = new ErrorResponse(404, "Player with id: " + freightStationRequest.getPlayerId() +
                " could not be found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(errorResponse);
        }
        PlayerRecord updatedPlayer;

        try {
            updatedPlayer = playerService.chooseFreightStation(freightStationRequest.getPlayerId(), freightStationRequest.getRegionId(),
                freightStationRequest.getFreightStationId());
        } catch (NoAvailableFreightStationException e) {
            ErrorResponse errorResponse = new ErrorResponse(400, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(errorResponse);
        }

        return ResponseEntity.ok(recordToResponse(updatedPlayer));
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
