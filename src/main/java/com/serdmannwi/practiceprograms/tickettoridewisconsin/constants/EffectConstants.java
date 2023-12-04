package com.serdmannwi.practiceprograms.tickettoridewisconsin.constants;

import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.effect.PassiveEffect;

public class EffectConstants {
    /**---------------------------------------------------- IDs ------------------------------------------------------*/
    public static final String BAILOUT_EFFECT_ID = "EFFECT01";

    /**--------------------------------------------------- Names -----------------------------------------------------*/
    public static final String BAILOUT_EFFECT_NAME = "Government Bailout Reward";

    /**---------------------------------------- Passive Effect Instructions ------------------------------------------*/
    public static final String BAILOUT_EFFECT_INSTRUCTIONS = "Take 1 free locomotive card";

    /**-------------------------------------------------- Effects ----------------------------------------------------*/
    public static final PassiveEffect BAILOUT_EFFECT =  new PassiveEffect(BAILOUT_EFFECT_NAME, BAILOUT_EFFECT_ID,
        EventConstants.BAILOUT_ID, BAILOUT_EFFECT_INSTRUCTIONS);


}
