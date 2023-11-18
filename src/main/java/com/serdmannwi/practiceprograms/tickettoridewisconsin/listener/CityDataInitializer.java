package com.serdmannwi.practiceprograms.tickettoridewisconsin.listener;

import com.serdmannwi.practiceprograms.tickettoridewisconsin.constants.CityConstants;
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

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (cityRepository.count() == 0) {
            //Region 1 Cities
            cityRepository.save(new City(CityConstants.BELOIT_NAME, CityConstants.BELOIT_ID, CityConstants.REGION_ONE_ID));
            cityRepository.save(new City(CityConstants.CHICAGO_NAME, CityConstants.CHICAGO_ID, CityConstants.REGION_ONE_ID));
            cityRepository.save(new City(CityConstants.JANESVILLE_NAME, CityConstants.JANESVILLE_ID, CityConstants.REGION_ONE_ID));
            cityRepository.save(new City(CityConstants.JOHNSON_CREEK_NAME, CityConstants.JOHNSON_CREEK_ID, CityConstants.REGION_ONE_ID));
            cityRepository.save(new City(CityConstants.KENOSHA_NAME, CityConstants.KENOSHA_ID, CityConstants.REGION_ONE_ID));
            cityRepository.save(new City(CityConstants.MADISON_NAME, CityConstants.MADISON_ID, CityConstants.REGION_ONE_ID));
            cityRepository.save(new City(CityConstants.MILWAUKEE_NAME, CityConstants.MILWAUKEE_ID, CityConstants.REGION_ONE_ID));
            cityRepository.save(new City(CityConstants.PORTAGE_NAME, CityConstants.PORTAGE_ID, CityConstants.REGION_ONE_ID));
            cityRepository.save(new City(CityConstants.RACINE_NAME, CityConstants.RACINE_ID, CityConstants.REGION_ONE_ID));
            cityRepository.save(new City(CityConstants.ROCKFORD_NAME, CityConstants.ROCKFORD_ID, CityConstants.REGION_ONE_ID));

            //Region 2 Cities
            cityRepository.save(new City(CityConstants.APPLETON_NAME, CityConstants.APPLETON_ID, CityConstants.REGION_TWO_ID));
            cityRepository.save(new City(CityConstants.BEAVER_DAM_NAME, CityConstants.BEAVER_DAM_ID, CityConstants.REGION_TWO_ID));
            cityRepository.save(new City(CityConstants.BRILLION_NAME, CityConstants.BRILLION_ID, CityConstants.REGION_TWO_ID));
            cityRepository.save(new City(CityConstants.FOND_DU_LAC_NAME, CityConstants.FOND_DU_LAC_ID, CityConstants.REGION_TWO_ID));
            cityRepository.save(new City(CityConstants.GREEN_BAY_NAME, CityConstants.GREEN_BAY_ID, CityConstants.REGION_TWO_ID));
            cityRepository.save(new City(CityConstants.MANITOWOC_NAME, CityConstants.MANITOWOC_ID, CityConstants.REGION_TWO_ID));
            cityRepository.save(new City(CityConstants.OSHKOSH_NAME, CityConstants.OSHKOSH_ID, CityConstants.REGION_TWO_ID));
            cityRepository.save(new City(CityConstants.SHEBOYGAN_NAME, CityConstants.SHEBOYGAN_ID, CityConstants.REGION_TWO_ID));
            cityRepository.save(new City(CityConstants.STURGEON_BAY_NAME, CityConstants.STURGEON_BAY_ID, CityConstants.REGION_TWO_ID));
            cityRepository.save(new City(CityConstants.WEST_BEND_NAME, CityConstants.WEST_BEND_ID, CityConstants.REGION_TWO_ID));

            //Region 3 Cities
            cityRepository.save(new City(CityConstants.CHIPPEWA_FALLS_NAME, CityConstants.CHIPPEWA_FALLS_ID, CityConstants.REGION_THREE_ID));
            cityRepository.save(new City(CityConstants.DUBUQUE_NAME, CityConstants.DUBUQUE_ID, CityConstants.REGION_THREE_ID));
            cityRepository.save(new City(CityConstants.EAU_CLAIRE_NAME, CityConstants.EAU_CLAIRE_ID, CityConstants.REGION_THREE_ID));
            cityRepository.save(new City(CityConstants.LA_CROSSE_NAME, CityConstants.LA_CROSSE_ID, CityConstants.REGION_THREE_ID));
            cityRepository.save(new City(CityConstants.PLATTEVILLE_NAME, CityConstants.PLATTEVILLE_ID, CityConstants.REGION_THREE_ID));
            cityRepository.save(new City(CityConstants.ROCHESTER_NAME, CityConstants.ROCHESTER_ID, CityConstants.REGION_THREE_ID));
            cityRepository.save(new City(CityConstants.MINNEAPOLIS_NAME, CityConstants.MINNEAPOLIS_ID, CityConstants.REGION_THREE_ID));
            cityRepository.save(new City(CityConstants.TOMAH_NAME, CityConstants.TOMAH_ID, CityConstants.REGION_THREE_ID));
            cityRepository.save(new City(CityConstants.WINONA_NAME, CityConstants.WINONA_ID, CityConstants.REGION_THREE_ID));
            cityRepository.save(new City(CityConstants.WISCONSIN_DELLS_NAME, CityConstants.WISCONSIN_DELLS_ID, CityConstants.REGION_THREE_ID));

            //Region 4 Cities
            cityRepository.save(new City(CityConstants.ASHLAND_NAME, CityConstants.ASHLAND_ID, CityConstants.REGION_FOUR_ID));
            cityRepository.save(new City(CityConstants.DULUTH_NAME, CityConstants.DULUTH_ID, CityConstants.REGION_FOUR_ID));
            cityRepository.save(new City(CityConstants.IRON_MOUNTAIN_NAME, CityConstants.IRON_MOUNTAIN_ID, CityConstants.REGION_FOUR_ID));
            cityRepository.save(new City(CityConstants.IRONWOOD_NAME, CityConstants.IRONWOOD_ID, CityConstants.REGION_FOUR_ID));
            cityRepository.save(new City(CityConstants.MARINETTE_NAME, CityConstants.MARINETTE_ID, CityConstants.REGION_FOUR_ID));
            cityRepository.save(new City(CityConstants.STEVENS_POINT_NAME, CityConstants.STEVENS_POINT_ID, CityConstants.REGION_FOUR_ID));
            cityRepository.save(new City(CityConstants.SUPERIOR_NAME, CityConstants.SUPERIOR_ID, CityConstants.REGION_FOUR_ID));
            cityRepository.save(new City(CityConstants.TOMAHAWK_NAME, CityConstants.TOMAHAWK_ID, CityConstants.REGION_FOUR_ID));
            cityRepository.save(new City(CityConstants.WAUSAU_NAME, CityConstants.WAUSAU_ID, CityConstants.REGION_FOUR_ID));
            cityRepository.save(new City(CityConstants.WISCONSIN_RAPIDS_NAME, CityConstants.WISCONSIN_RAPIDS_ID, CityConstants.REGION_FOUR_ID));

            //Test Cities
            cityRepository.save(new City("Test City 1", TEST_CITY_ONE_ID, CityConstants.REGION_ONE_ID));
            cityRepository.save(new City("Test City 2", TEST_CITY_TWO_ID, CityConstants.REGION_TWO_ID));
            cityRepository.save(new City("Test City 3", TEST_CITY_THREE_ID, CityConstants.REGION_THREE_ID));
        }
    }
}
