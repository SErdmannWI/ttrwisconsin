package com.serdmannwi.practiceprograms.tickettoridewisconsin.listener;

import com.serdmannwi.practiceprograms.tickettoridewisconsin.constants.EffectConstants;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.effect.EffectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class EffectDataInitializer implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private EffectRepository effectRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (effectRepository.count() == 0) {
            effectRepository.save(EffectConstants.BAILOUT_EFFECT);
            effectRepository.save(EffectConstants.DERAILMENT_EFFECT);
        }
    }
}
