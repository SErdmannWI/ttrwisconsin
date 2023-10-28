package com.serdmannwi.practiceprograms.tickettoridewisconsin.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class GameService {

    private Random random;

    private int round;
    private int turn;
    private int totalTurns;
    private int numPlayers;

    public GameService() {
        this.random = new Random();
        this.round = 1;
        this.turn = 1;
        this.totalTurns = 1;
        this.numPlayers = 3;
    }

    /**
     * Generates two random ints between 1 - 6.
     * @return two generated ints in a List
     */
    public List<Integer> economyRoll() {
        List<Integer> diceRoll = random.ints(2,1,7)
            .boxed()
            .collect(Collectors.toList());

        return diceRoll;
    }

    public int setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
        return this.numPlayers;
    }

    /**
     * Adds one to the current turn
     * If all Players have played a turn then add one to the round
     * @return List of round, turn, totalTurns
     */
    public List<Integer> incrementTurn() {
        System.out.println("Turns Before-------------------");
        System.out.println("Total Turns: " + totalTurns);
        System.out.println("Round: " + round);
        System.out.println("Turn: " + turn);
        List<Integer> gameCounter = new ArrayList<>();
        totalTurns++;

        //Start a new round if all players have gone
        if (turn == numPlayers) {
            round++;
            gameCounter.add(round);
            gameCounter.add(turn = 1);
            gameCounter.add(totalTurns);

            System.out.println("Turns After----------------");
            System.out.println("Round: " + gameCounter.get(0));
            System.out.println("Turn: " + gameCounter.get(1));
            System.out.println("Total Turns: " + gameCounter.get(2));

            return gameCounter;
        }

        turn++;

        gameCounter.add(round);
        gameCounter.add(turn);
        gameCounter.add(totalTurns);

        System.out.println("Turns After----------------");
        System.out.println("Round: " + gameCounter.get(0));
        System.out.println("Turn: " + gameCounter.get(1));
        System.out.println("Total Turns: " + gameCounter.get(2));

        return gameCounter;
    }

    public int getRound() {
        return round;
    }

    public int getTurn() {
        return turn;
    }

    public int getNumPlayers() {
        return numPlayers;
    }
}
