package com.serdmannwi.practiceprograms.tickettoridewisconsin.service;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.exceptions.player.InvalidNumberOfPlayersException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GameServiceTest {
    private GameService gameService;

    @BeforeEach
    void setup() {
        gameService = new GameService();
    }

    @Test
    public void gameServiceCreation_createsValidGameService() {
        Assertions.assertEquals(1, gameService.getRound());
        Assertions.assertEquals(1, gameService.getTurn());
        Assertions.assertEquals(1, gameService.getTotalTurns());
        Assertions.assertTrue(gameService.isInitialized());
    }

    @Test
    public void setNumPlayers_validNumber_correctlySetsPlayers() {
        //GIVEN
        int numPlayers = 3;

        //WHEN/ THEN
        Assertions.assertEquals(3, gameService.setNumPlayers(numPlayers), "Expected assignment of valid number of Players.");
    }

    @Test
    public void setNumPlayers_invalidNumber_throwsInvalidNumberOfPlayersException() {
        //GIVEN
        int numPlayers = 5;

        //WHEN/ THEN
        Assertions.assertThrows(InvalidNumberOfPlayersException.class, () -> gameService.setNumPlayers(numPlayers),
            "Number of Players allowed should be 2, 3 or 4.");
    }

    @Test
    public void economyRoll_rolls1000Times_validRolls() {
        //GIVEN/ WHEN
        List<Integer> rollList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            rollList.addAll(gameService.economyRoll());
        }

        //THEN
        List<Integer> filteredRollList = rollList.stream()
            .filter(roll -> roll < 1 || roll > 6).toList();

        Assertions.assertEquals(0, filteredRollList.size(), "Economy rolls should only contain values between 1 and 6.");
    }

    @Test
    public void incrementTurn_fourPlayerGame_incrementsTurnsAndRounds() {
        //GIVEN
        int numPlayers = 4;
        gameService.setNumPlayers(4);

        //WHEN
        //Run game for 17 turns
        for (int i = 0; i < 16; i++) {
            gameService.incrementTurn();
        }

        //THEN
        Assertions.assertEquals(17, gameService.getTotalTurns());
        Assertions.assertEquals(5, gameService.getRound());
        Assertions.assertEquals(1, gameService.getTurn());
    }
}
