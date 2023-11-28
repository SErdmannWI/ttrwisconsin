package com.serdmannwi.practiceprograms.tickettoridewisconsin.constants;

import com.serdmannwi.practiceprograms.tickettoridewisconsin.service.model.Ability;

import java.util.HashMap;
import java.util.Map;

public class AbilityConstants {
    /**----------------------------------------------- Ability Names -------------------------------------------------*/
    public static final String CHOOSE_FACE_UP = "Choose Face-Up Locomotives";
    public static final String VIEW_FACE_DOWN_CARDS = "View Face-Down Cards";
    public static final String CANCEL_FREIGHT_CONTRACT = "Cancel Freight Contract";
    public static final String CLAIM_ROUTE_SAME_TURN = "Claim a Route After Picking";
    public static final String RESOLVE_EVENT = "Automatically Resolve an Event";
    public static final String TAKE_TURN_EARLY = "Immediately Take Your Next Round's Turn";
    public static final String DELAY_TURN = "Defer Your Turn to the Next Round";
    public static final String ECONOMY_SWAP = "Swap Economy Roll for Two Cities";

    /**------------------------------------------------ Ability IDs --------------------------------------------------*/
    public static final String CHOOSE_FACE_UP_ID = "AB1";
    public static final String VIEW_FACE_DOWN_CARDS_ID = "AB2";
    public static final String CANCEL_FREIGHT_CONTRACT_ID = "AB3";
    public static final String CLAIM_ROUTE_SAME_TURN_ID = "AB4";
    public static final String RESOLVE_EVENT_ID = "AB5";
    public static final String TAKE_TURN_EARLY_ID = "AB6";
    public static final String DELAY_TURN_ID = "AB7";
    public static final String ECONOMY_SWAP_ID = "AB8";

    /**------------------------------------------- Ability Descriptions ----------------------------------------------*/
    public static final String CHOOSE_FACE_UP_DESCRIPTION = "Choose a face-up locomotive and pick an additional card as normal. (Can also be a locomotive)";
    public static final String VIEW_FACE_DOWN_CARDS_DESCRIPTION = "View the next two face-down cards without having to select them.";
    public static final String CANCEL_FREIGHT_CONTRACT_DESCRIPTION = "Cancel a Freight Contract without penalty.";
    public static final String CLAIM_ROUTE_SAME_TURN_DESCRIPTION = "Immediately claim a Route after picking your cards";
    public static final String RESOLVE_EVENT_DESCRIPTION = "Automatically resolve an Event that is affecting you without needing to meet the conditions";
    public static final String TAKE_TURN_EARLY_DESCRIPTION = "Immediately take you turn for the next round. Must be decalred before performing your action for the current round.";
    public static final String DELAY_TURN_DESCRIPTION = "Delay your turn until the next round. Must be decalred before performing your action for the current turn.";
    public static final String ECONOMY_SWAP_DESCRIPTION = "Swap the economy rolls between any two Cities within a region.";

    /**------------------------------------------------ Abilities ----------------------------------------------------*/
    public static final Ability CHOOSE_FACE_UP_ABILITY = new Ability(CHOOSE_FACE_UP, CHOOSE_FACE_UP_ID, CHOOSE_FACE_UP_DESCRIPTION, 0);
    public static final Ability VIEW_FACE_DOWN_CARDS_ABILITY = new Ability(VIEW_FACE_DOWN_CARDS, VIEW_FACE_DOWN_CARDS_ID, VIEW_FACE_DOWN_CARDS_DESCRIPTION, 0);
    public static final Ability CANCEL_FREIGHT_CONTRACT_ABILITY = new Ability(CANCEL_FREIGHT_CONTRACT, CANCEL_FREIGHT_CONTRACT_ID, CANCEL_FREIGHT_CONTRACT_DESCRIPTION, 0);
    public static final Ability CLAIM_ROUTE_SAME_TURN_ABILITY = new Ability(CLAIM_ROUTE_SAME_TURN, CLAIM_ROUTE_SAME_TURN_ID, CLAIM_ROUTE_SAME_TURN_DESCRIPTION, 0);
    public static final Ability RESOLVE_EVENT_ABILITY = new Ability(RESOLVE_EVENT, RESOLVE_EVENT_ID, RESOLVE_EVENT_DESCRIPTION, 0);
    public static final Ability TAKE_TURN_EARLY_ABILITY = new Ability(TAKE_TURN_EARLY, TAKE_TURN_EARLY_ID, TAKE_TURN_EARLY_DESCRIPTION, 0);
    public static final Ability DELAY_TURN_ABILITY = new Ability(DELAY_TURN, DELAY_TURN_ID, DELAY_TURN_DESCRIPTION, 0);
    public static final Ability ECONOMY_SWAP_ABILITY = new Ability(ECONOMY_SWAP, ECONOMY_SWAP_ID, ECONOMY_SWAP_DESCRIPTION, 0);

    /**----------------------------------------------- Abilities Map -------------------------------------------------*/
    public static final Map<String, Ability> ABILITY_MAP = new HashMap<>();
    static {
        ABILITY_MAP.put(CHOOSE_FACE_UP_ID, CHOOSE_FACE_UP_ABILITY);
        ABILITY_MAP.put(VIEW_FACE_DOWN_CARDS_ID, VIEW_FACE_DOWN_CARDS_ABILITY);
        ABILITY_MAP.put(CANCEL_FREIGHT_CONTRACT_ID, CANCEL_FREIGHT_CONTRACT_ABILITY);
        ABILITY_MAP.put(CLAIM_ROUTE_SAME_TURN_ID, CLAIM_ROUTE_SAME_TURN_ABILITY);
        ABILITY_MAP.put(RESOLVE_EVENT_ID, RESOLVE_EVENT_ABILITY);
        ABILITY_MAP.put(TAKE_TURN_EARLY_ID, TAKE_TURN_EARLY_ABILITY);
        ABILITY_MAP.put(DELAY_TURN_ID, DELAY_TURN_ABILITY);
        ABILITY_MAP.put(ECONOMY_SWAP_ID, ECONOMY_SWAP_ABILITY);
    }

    /**---------------------------------------------- Abilities Values -----------------------------------------------*/
    public static final int ABILITY_STARTING_USES = 3;
}
