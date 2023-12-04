package com.serdmannwi.practiceprograms.tickettoridewisconsin.controller;

import com.serdmannwi.practiceprograms.tickettoridewisconsin.controller.model.*;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.exceptions.*;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.exceptions.player.AbilityNotFoundException;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.exceptions.player.MaxPlayersException;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.exceptions.player.NoAvailableFreightStationException;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.exceptions.player.PlayerNotFoundException;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.player.PlayerRecord;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.service.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

//TODO Consider refactoring with @ControllerAdvice annotation with a GlobalExceptionHandler

@RestController
@RequestMapping("/player")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) { this.playerService = playerService; }

    /**----------------------------------------- Player Creation Endpoints -----------------------------------------**/
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

    @PutMapping("/chooseFreightStation")
    public ResponseEntity<?> chooseFreightStation(@RequestBody ChooseFreightStationRequest freightStationRequest) {
        PlayerRecord updatedPlayer;

        try {
            updatedPlayer = playerService.chooseFreightStation(freightStationRequest.getPlayerId(), freightStationRequest.getRegionId(),
                freightStationRequest.getFreightStationId());
        } catch (PlayerNotFoundException e) {
            ErrorResponse errorResponse = new ErrorResponse(404, "Player with id: " + freightStationRequest.getPlayerId() +
                " could not be found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(errorResponse);
        } catch (NoAvailableFreightStationException e) {
            ErrorResponse errorResponse = new ErrorResponse(400, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(errorResponse);
        }

        return ResponseEntity.ok(recordToResponse(updatedPlayer));
    }

    @PutMapping("/chooseAbility")
    public ResponseEntity<?> chooseAbility(@RequestBody ChooseAbilityRequest chooseAbilityRequest) {
        PlayerRecord updatedPlayer;

        try {
            updatedPlayer = playerService.chooseAbility(chooseAbilityRequest.getPlayerId(), chooseAbilityRequest.getAbilityId());
        } catch (PlayerNotFoundException e) {
            ErrorResponse errorResponse = new ErrorResponse(404, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(errorResponse);
        } catch (AbilityNotFoundException e) {
            ErrorResponse errorResponse = new ErrorResponse(400, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(errorResponse);
        }

        return ResponseEntity.ok(recordToResponse(updatedPlayer));
    }

    /**---------------------------------------- Player Retrieval Endpoints -----------------------------------------**/
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
        PlayerRecord playerRecord;
        playerRecord = playerService.getPlayerById(id);

        return ResponseEntity.ok(recordToResponse(playerRecord));
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

    /**------------------------------------------- Player Turn Endpoints -------------------------------------------**/
    @PostMapping("/createTurnOrder")
    public ResponseEntity<?> createTurnOrder(@RequestBody String[] playerIds) {
        try {
            playerService.createTurnQueue(playerIds);
        } catch (PlayerNotFoundException e) {
            ErrorResponse errorResponse = new ErrorResponse(404, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(errorResponse);
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getNextPlayer")
    public ResponseEntity<?> getNextPlayer() {
        PlayerRecord playerRecord;
        try {
            playerRecord = playerService.getNextPlayer();
        } catch (PlayerNotFoundException e) {
            ErrorResponse errorResponse = new ErrorResponse(404, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(errorResponse);
        }
        return ResponseEntity.ok(recordToResponse(playerRecord));
    }

    @PutMapping("/deferPlayerTurn/{playerId}")
    public ResponseEntity<String> deferPlayerTurn(@PathVariable("playerId") String playerId) {
        playerService.deferTurn(playerId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/startNextRound") //Possibly not needed. New Rounds automatically started in PlayerService
    public ResponseEntity<?> startNextRound() {
        try {
            playerService.startNextRound();
        } catch (PlayerNotFoundException e) {
            ErrorResponse errorResponse = new ErrorResponse(404, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(errorResponse);
        }
        return ResponseEntity.ok().build();
    }

    /**--------------------------------------------- Exception Handler ---------------------------------------------**/

    /**-------------------------------------------- Conversion Methods ---------------------------------------------**/
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
