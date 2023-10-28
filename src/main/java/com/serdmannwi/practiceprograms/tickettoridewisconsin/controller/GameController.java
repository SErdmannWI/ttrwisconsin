package com.serdmannwi.practiceprograms.tickettoridewisconsin.controller;

import com.serdmannwi.practiceprograms.tickettoridewisconsin.service.GameService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) { this.gameService = gameService; }

    @GetMapping("/getTurn")
    public ResponseEntity<Integer> getTurn() {
        return ResponseEntity.ok(gameService.getTurn());
    }

    @GetMapping("/getRound")
    public ResponseEntity<Integer> getRound() {
        return ResponseEntity.ok(gameService.getRound());
    }

    @GetMapping("/getNumPlayers")
    public ResponseEntity<Integer> getNumPLayers() {
        return ResponseEntity.ok(gameService.getNumPlayers());
    }

    @GetMapping("/nextTurn")
    public ResponseEntity<List<Integer>> nextTurn() {
        return ResponseEntity.ok(gameService.incrementTurn());
    }

    @GetMapping("/economyRoll")
    public ResponseEntity<List<Integer>> getEconomyRoll() {
        return ResponseEntity.ok(gameService.economyRoll());
    }

    @PostMapping("/setNumPlayers/{numberOfPlayers}")
    public ResponseEntity<Integer> setNumPlayers (@PathVariable("numberOfPlayers") String playerNumber) {
        Integer numPlayers = Integer.parseInt(playerNumber);
        return ResponseEntity.ok(gameService.setNumPlayers(numPlayers));
    }
}
