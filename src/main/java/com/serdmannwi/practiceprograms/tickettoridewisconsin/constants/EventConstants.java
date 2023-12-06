package com.serdmannwi.practiceprograms.tickettoridewisconsin.constants;

import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.event.ConditionDetermination;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.eventcondition.EventCondition;

public class EventConstants {
    /**--------------------------------------------------- Names -----------------------------------------------------*/
    public static final String BAILOUT_NAME = "Government Bailout";
    public static final String DERAILMENT_NAME = "Derailment!";

    /**
     * --------------------------------------------------- Types ---------------------------------------------------
     **/
    public static final String SINGLE_PLAYER_PASSIVE_EVENT = "Single Player Passive";
    public static final String SINGLE_PLAYER_ACTIVE_EVENT = "Single Player Active";
    public static final String SINGLE_PLAYER_TIMED_EVENT = "Single Player Timed";
    public static final String MULTI_PLAYER_PASSIVE_EVENT = "Multi Player Passive";
    public static final String MULTI_PLAYER_ACTIVE_EVENT = "Multi Player Active";
    public static final String MULTI_PLAYER_TIMED_EVENT = "Multi Player Timed";

    /**---------------------------------------------------- IDs ------------------------------------------------------*/
    public static final String BAILOUT_ID = "EVENT01";
    public static final String DERAILMENT_ID = "EVENT02";


    /**
     * ----------------------------------------------- Descriptions ------------------------------------------------
     **/
    public static final String BAILOUT_DESC = "The Player with the current lowest score receives a locomotive.\nIf" +
        " multiple Players are tied for the lowest score, no locomotives are awarded.";
    public static final String DERAILMENT_DESC = "You have suffered a derailment along one of your routes!\n You" +
        " must now pay a fine of one locomotive card or else you will not receive any points from Freight Contracts" +
        " until your next turn.";

    /**----------------------------------------------- Condition IDs ------------------------------------------------**/
    public static final String BAILOUT_CONDITION_ID = "EventCondition01";
    public static final String DERAILMENT_CONDITION_ID = "EventCondition02";

    /**------------------------------------------ Condition Descriptions --------------------------------------------**/
    public static final String BAILOUT_CONDITION_DESCRIPTION = "There is only 1 Player with the lowest score";
    public static final String DERAILMENT_CONDITION_DESCRIPTION = "Current Player has given up 1 locomotive card";

    /**------------------------------------------------ Condition ---------------------------------------------------**/
    public static final EventCondition BAILOUT_CONDITION = new EventCondition(BAILOUT_CONDITION_ID,
        BAILOUT_CONDITION_DESCRIPTION, EffectConstants.BAILOUT_EFFECT_ID, ConditionDetermination.PLAYER_WITH_LOWEST_SCORE);
    public static final EventCondition DERAILMENT_CONDITION = new EventCondition(DERAILMENT_ID,
        DERAILMENT_CONDITION_DESCRIPTION, EffectConstants.DERAILMENT_EFFECT_ID, ConditionDetermination.CURRENT_PLAYER);

}
