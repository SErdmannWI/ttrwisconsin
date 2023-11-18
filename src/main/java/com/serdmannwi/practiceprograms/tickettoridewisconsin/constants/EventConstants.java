package com.serdmannwi.practiceprograms.tickettoridewisconsin.constants;

import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.EventCondition;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.SinglePlayerActiveEvent;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.SinglePlayerPassiveEvent;

public class EventConstants {
    /**
     * --------------------------------------------------- Names ---------------------------------------------------
     **/
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

    /**
     * ---------------------------------------------------- IDs ----------------------------------------------------
     **/
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

    /**-------------------------------------------------- Events ---------------------------------------------------**/


    /**------------------------------------------------ Conditions -------------------------------------------------**/

}
