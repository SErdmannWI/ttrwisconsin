package com.serdmannwi.practiceprograms.tickettoridewisconsin.listener;

import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.City;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CityDataInitializer implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private CityRepository cityRepository;

    private final String TEST_CITY_ONE_ID = "City1";
    private final String TEST_CITY_TWO_ID = "City2";
    private final String TEST_CITY_THREE_ID = "City3";
    private final String TEST_CITY_FOUR_ID = "City 4";
    private final List<String> TEST_CITY_PRODUCTS = new ArrayList<>(Arrays.asList("Product1", "Product2"));

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (cityRepository.count() == 0) {
            cityRepository.save(new City("Test City 1", TEST_CITY_ONE_ID));
            cityRepository.save(new City("Test City 2", TEST_CITY_TWO_ID));
            cityRepository.save(new City("Test City 3", TEST_CITY_THREE_ID));
            cityRepository.save(new City("Test City with Products", TEST_CITY_FOUR_ID, TEST_CITY_PRODUCTS, 6));
        }
    }
}
