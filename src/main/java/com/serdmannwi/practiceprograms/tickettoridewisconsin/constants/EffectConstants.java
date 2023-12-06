package com.serdmannwi.practiceprograms.tickettoridewisconsin.constants;

import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.effect.ActiveConditionalDurationEffect;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.effect.PassiveEffect;

public class EffectConstants {
    /**---------------------------------------------------- IDs ------------------------------------------------------*/
    public static final String BAILOUT_EFFECT_ID = "Effect01";
    public static final String DERAILMENT_EFFECT_ID = "Effect02";

    /**--------------------------------------------------- Names -----------------------------------------------------*/
    public static final String BAILOUT_EFFECT_NAME = "Government Bailout Reward";
    public static final String DERAILMENT_EFFECT_NAME = "Derailment!";

    /**---------------------------------------- Passive Effect Instructions ------------------------------------------*/
    public static final String BAILOUT_EFFECT_INSTRUCTIONS = "Take 1 free locomotive card";
    public static final String DERAILMENT_EFFECT_INSTRUCTIONS = "Give up 1 locomotive card immediately";

    /**-------------------------------------------------- Effects ----------------------------------------------------*/
    public static final PassiveEffect BAILOUT_EFFECT =  new PassiveEffect(BAILOUT_EFFECT_NAME, BAILOUT_EFFECT_ID,
        EventConstants.BAILOUT_ID, BAILOUT_EFFECT_INSTRUCTIONS);
    public static final ActiveConditionalDurationEffect DERAILMENT_EFFECT = new ActiveConditionalDurationEffect(DERAILMENT_EFFECT_NAME,
        DERAILMENT_EFFECT_ID, EventConstants.DERAILMENT_ID, DERAILMENT_EFFECT_INSTRUCTIONS,
        ActionConstants.RECEIVE_FREIGHT_CONTRACT_POINTS_ID, 1);


}
