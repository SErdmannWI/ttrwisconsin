package com.serdmannwi.practiceprograms.tickettoridewisconsin.service;

import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.City;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public City getCityById(String id) {
        return cityRepository.findById(id).orElse(null);
    }

    public City addEconomyInfoToCity(String cityId, List<String> productIds, int economyRoll) {
        Optional<City> cityOptional = cityRepository.findById(cityId);
        if (cityOptional.isEmpty()) {
            return null;
        }
        City city = cityOptional.get();
        List<String> cityProducts = new ArrayList<>(productIds);
        city.setProductsAvailable(cityProducts);
        city.setEconomyRoll(economyRoll);

        return cityRepository.save(city);
    }

    public City addEconomyRollToCity(String cityId, int economyRoll) {
        Optional<City> cityOptional = cityRepository.findById(cityId);
        if (cityOptional.isEmpty()) {
            return null;
        }
        City city = cityOptional.get();
        city.setEconomyRoll(economyRoll);

        return cityRepository.save(city);
    }
}
