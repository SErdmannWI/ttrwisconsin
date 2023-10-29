package com.serdmannwi.practiceprograms.tickettoridewisconsin.controller;

import com.serdmannwi.practiceprograms.tickettoridewisconsin.controller.model.CityEconomyRequest;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.controller.model.CityResponse;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.exceptions.ErrorResponse;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.City;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.service.CityService;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.utils.JsonUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            .map(this::recordToResponse)
            .collect(Collectors.toList());

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

    @PutMapping("/setProducts")
    public ResponseEntity<?> setCityEconomy(@RequestBody CityEconomyRequest cityEconomyRequest) {
        City city = cityService.getCityById(cityEconomyRequest.getCityId());
        if (city == null) {
            ErrorResponse errorResponse = new ErrorResponse(404, "Could not find City with ID: " + cityEconomyRequest.getCityId());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(errorResponse);
        }

        List<String> cityProducts = JsonUtil.deserializeFromJson(cityEconomyRequest.getProductsAvailableJson());

        City updatedCity = cityService.addEconomyInfoToCity(cityEconomyRequest.getCityId(), cityProducts,
            cityEconomyRequest.getEconomyRoll());

        return ResponseEntity.ok(recordToResponse(updatedCity));
    }

    /**----------------------------------------- Conversion Methods -----------------------------------------**/
    private CityResponse recordToResponse(City city) {
        CityResponse cityResponse = new CityResponse();

        cityResponse.setCityName(city.getName());
        cityResponse.setCityId(city.getCityId());
        cityResponse.setEconomyRoll(city.getEconomyRoll());
        cityResponse.setProductsAvailableJson(city.getProductsAvailableJson());

        return cityResponse;
    }

}
