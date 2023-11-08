package com.serdmannwi.practiceprograms.tickettoridewisconsin.controller;

import com.serdmannwi.practiceprograms.tickettoridewisconsin.controller.model.CityEconomyRequest;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.controller.model.CityResponse;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.exceptions.CityInitializationException;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.exceptions.CityNotFoundException;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.exceptions.ErrorResponse;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.City;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.service.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/city")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/allCities")
    public ResponseEntity<?> getAllCities() {
        List<City> allCitiesList = cityService.getAllCities();
        if (allCitiesList.isEmpty()) {
            ErrorResponse errorResponse = new ErrorResponse(404, "No Cities were found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(errorResponse);
        }

        List<CityResponse> allCityResponses = allCitiesList.stream()
            .map(this::recordToResponse).toList();

        return ResponseEntity.ok().body(allCityResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCityById(@PathVariable("id") String cityId) {
        City city = cityService.getCityById(cityId);
        if (city == null) {
            ErrorResponse errorResponse = new ErrorResponse(404, "Could not find City with ID: " + cityId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(errorResponse);
        }

        return ResponseEntity.ok(recordToResponse(city));
    }

    @PutMapping("/setAllCityEconomies")
    public ResponseEntity<?> setCityEconomy() {
        List<City> updatedCitiesList = new ArrayList<>();
        try {
            updatedCitiesList = cityService.initializeCityEconomies();
        } catch (CityInitializationException e) {
            ErrorResponse errorResponse = new ErrorResponse(404, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(errorResponse);
        }
        List<CityResponse> updatedCityResponses = updatedCitiesList.stream()
            .map(this::recordToResponse)
            .sorted(Comparator.comparing(CityResponse::getRegionId))
            .toList();
        return ResponseEntity.status(HttpStatus.OK).body(updatedCityResponses);
    }

    /**
     * Used after a Player has chosen a Freight Station. Removes the Product from the FreightStation's City
     * and sets economy roll to 0
     * @param cityId
     * @return
     */
    @PutMapping("/setCityProduct/{cityId}")
    public ResponseEntity<?> setCityProduct(@PathVariable("cityId") String cityId) {
        City updatedCity;
        try {
            updatedCity = cityService.removeCityProduct(cityId);
        } catch (CityNotFoundException e) {
            ErrorResponse errorResponse = new ErrorResponse(404, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(errorResponse);
        }

        return ResponseEntity.ok().body(recordToResponse(updatedCity));
    }

    /**----------------------------------------- Conversion Methods -----------------------------------------**/
    private CityResponse recordToResponse(City city) {
        CityResponse cityResponse = new CityResponse();

        cityResponse.setCityName(city.getName());
        cityResponse.setCityId(city.getCityId());
        cityResponse.setEconomyRoll(city.getEconomyRoll());
        cityResponse.setProductsAvailableJson(city.getProductId());
        cityResponse.setRegionId(city.getRegionId());

        return cityResponse;
    }

}
