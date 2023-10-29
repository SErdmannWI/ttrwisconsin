package com.serdmannwi.practiceprograms.tickettoridewisconsin.listener;

import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.Effect;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.EffectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class EffectDataInitializer implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private EffectRepository effectRepository;
    private final List<String> TEST_CONDITION_IDS = new ArrayList<>(Arrays.asList("condition11", "condition22",
        "condition33"));
    private final List<String> TEST_RESOURCE_IDS = new ArrayList<>(Arrays.asList("resource11", "resource22",
        "resource33"));
    private final List<String> TEST_ACTION_IDS = new ArrayList<>(Arrays.asList("action11", "action22", "action33"));


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (effectRepository.count() == 0) {
            effectRepository.save(new Effect("testEffect1", "TESTEF1", "TESTEVENT1", 3, 1, 0, 0, true, true, true,
                TEST_CONDITION_IDS, TEST_RESOURCE_IDS, TEST_ACTION_IDS));
            effectRepository.save(new Effect("testEffect2", "TESTEF2", "TESTEVENT2", 2, 2, 0, 0, false, true, true,
                TEST_CONDITION_IDS, TEST_RESOURCE_IDS, TEST_ACTION_IDS));
            effectRepository.save(new Effect("testEffect3", "TESTEF3", "TESTEVENT3", 1, .5, 0, 0, true, true, false,
                TEST_CONDITION_IDS, TEST_RESOURCE_IDS, TEST_ACTION_IDS));
        }
    }
}
