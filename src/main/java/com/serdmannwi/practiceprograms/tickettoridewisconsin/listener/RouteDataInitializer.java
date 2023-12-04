package com.serdmannwi.practiceprograms.tickettoridewisconsin.listener;

import com.serdmannwi.practiceprograms.tickettoridewisconsin.constants.CityConstants;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.route.Route;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.route.RouteColor;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.route.RouteRepository;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.route.RouteType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class RouteDataInitializer implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private RouteRepository routeRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (routeRepository.count() == 0) {
            //Freight Route
            routeRepository.save(new Route(CityConstants.TEST_CITY_ONE_ID, CityConstants.TEST_CITY_ONE_NAME,
                CityConstants.TEST_CITY_TWO_ID, CityConstants.TEST_CITY_TWO_NAME, RouteColor.BLUE,
                RouteType.FREIGHT_ROUTE, 4));
            //High Speed Route
            routeRepository.save(new Route(CityConstants.HS_TC3_TC4, CityConstants.TEST_CITY_THREE_ID,
                CityConstants.TEST_CITY_THREE_ID, CityConstants.TEST_CITY_FOUR_ID, CityConstants.TEST_CITY_FOUR_NAME,
                RouteColor.BLACK, RouteType.HIGH_SPEED_ROUTE, 5));
            //Spur Route
            routeRepository.save(new Route(CityConstants.TEST_CITY_THREE_SPUR_ONE_ID, CityConstants.TEST_CITY_THREE_ID,
                CityConstants.TEST_CITY_THREE_NAME, CityConstants.TEST_CITY_THREE_ID, CityConstants.TEST_CITY_THREE_NAME,
                RouteColor.RED, RouteType.SPUR_ROUTE, 2));
        }
    }
}
