package com.serdmannwi.practiceprograms.tickettoridewisconsin.listener;

import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class CityDataInitializer implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private CityRepository cityRepository;

    private final String TEST_CITY_ONE_ID = "City1";
    private final String TEST_CITY_TWO_ID = "City2";
    private final String TEST_CITY_THREE_ID = "City3";
    private final String TEST_CITY_FOUR_ID = "City 4";

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (cityRepository.count() == 0) {

        }
    }
}
