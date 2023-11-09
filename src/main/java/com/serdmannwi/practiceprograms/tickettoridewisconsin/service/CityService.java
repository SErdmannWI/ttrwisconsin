package com.serdmannwi.practiceprograms.tickettoridewisconsin.service;

import com.serdmannwi.practiceprograms.tickettoridewisconsin.constants.CityConstants;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.constants.GameConstants;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.constants.ProductConstants;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.exceptions.CityInitializationException;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.exceptions.CityNotFoundException;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.City;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.CityRepository;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.service.model.FreightContract;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Creates a new City Repository on instantiation that will be populated with Cities from CityDataInitializer.
 * Creates the 4 Lists of City ID's for each region. Each region will have 12 cities.
 * Creates the 4 Lits of Products ID's for each region. Each region will have 3 Products.
 */
@Service
public class CityService {

    private final CityRepository cityRepository;
    private List<FreightContract> freightContracts;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
        freightContracts = new ArrayList<>();
    }

    /**
     * For each City, this method will call wrapAddEconomyInfoToCity which will modify the records in the database so that
     * each City will contain a ProductID and an EconomyRoll.
     * If a City is not found, @Transactional prevents the database from being updated and an exception is thrown to be
     * caught in CityController which will send an appropriate error response to the frontend.
     * Wrapper method needed for
     */

    //Possibly, each Region should be a HashMap<Integer, List<String>> where the Region number is the ID.
    //When addEconomyInfoToCity is called, the Key can be used to get the appropriate list of products for each region
    @Transactional
    public List<City> initializeCityEconomies() {
        List<City> updatedCities;
        try {
            updatedCities = addEconomyInfoToCity(CityConstants.CITY_MAP);
        } catch (CityNotFoundException e) {
                throw new CityInitializationException("Failed to initialize City economy", e);
        }
        return updatedCities;
    }

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public City getCityById(String id) {
        return cityRepository.findById(id).orElse(null);
    }

    /**
     * Takes in Map<Integer,List<String>> where each key is the region and the List is that region's Cities' IDs
     * Each
     * @param cityMap
     * @return List of Cities from each region
     * @throws CityNotFoundException
     */
    //TODO Refactor so that appropriate product list for each region is called.
    public List<City> addEconomyInfoToCity(Map<Integer, List<String>> cityMap) throws CityNotFoundException {
        List<City> updatedCities = new ArrayList<>();
        List<String> productList = new ArrayList<>();
        City city;
        int regionValue = 0;

//        updatedCities = cityMap.values().stream()
//            .flatMap(List::stream)
//            .map(this::economy)
//            .collect(Collectors.toList());

        //Iterate through each List in cityMap and update the City in the Repository with the economy info
        for (Map.Entry<Integer, List<String>> entry : cityMap.entrySet()) {
            //Get Region's specific products
            regionValue = entry.getKey();
            productList = new ArrayList<>(getRegionProducts(regionValue));
            Collections.shuffle(productList);
            int productIndex = 0;

            //Get new Economy Roll List with each region
            List<Integer> economyRolls = GameConstants.economyRollList;
            Collections.shuffle(economyRolls);
            Queue<Integer> economyQueue = new LinkedList<>(economyRolls);

            //Iterate through each city ID and set productID and economy roll
            for (String cityId : entry.getValue()) {
                city = cityRepository.findById(cityId).orElseThrow(() ->
                    new CityNotFoundException("City with ID: " + cityId + " was not found."));
                city.setProductsAvailable(productList.get(productIndex));
                city.setEconomyRoll(economyQueue.poll());
                city = cityRepository.save(city);
                updatedCities.add(city);
                productIndex = (productIndex + 1) % productList.size();
            }
        }

        return updatedCities;
    }

    public City addEconomyRollToCity(String cityId, int economyRoll) {
        Optional<City> cityOptional = cityRepository.findById(cityId);
        if (cityOptional.isEmpty()) {
            throw new CityNotFoundException("City with id: " + cityId + " could not be found");
        }
        City city = cityOptional.get();
        city.setEconomyRoll(economyRoll);

        return cityRepository.save(city);
    }

    public City removeCityProduct(String cityId) {
        Optional<City> cityOptional = cityRepository.findById(cityId);
        if (cityOptional.isEmpty()) {
            throw new CityNotFoundException("City with id: " + cityId + " could not be found");
        }
        City city = cityOptional.get();
        city.setEconomyRoll(0);
        city.setProductsAvailable("");

        return cityRepository.save(city);
    }

    public List<FreightContract> generateFreightContracts() {
        List<City> cityList = cityRepository.findAll();

        for (City city : cityList) {
            if (!city.getProductId().equals("")) {
                //TODO Adjust how many points each contract gets?
                freightContracts.add(new FreightContract(city.getProductId(), city.getCityId(), 5));
            }
        }
        Collections.shuffle(freightContracts);
        return freightContracts;
    }

    public List<FreightContract> getFreightContracts() {
        return freightContracts;
    }

    /**------------------------------------------------ Helper Methods -----------------------------------------------*/
    private List<String> getRegionProducts(int regionId) {
        List<String> productIds = new ArrayList<>();
        switch (regionId) {
            case 1 -> productIds.addAll(ProductConstants.REGION_ONE_PRODUCTS_IDS);
            case 2 -> productIds.addAll(ProductConstants.REGION_TWO_PRODUCTS_IDS);
            case 3 -> productIds.addAll(ProductConstants.REGION_THREE_PRODUCTS_IDS);
            case 4 -> productIds.addAll(ProductConstants.REGION_FOUR_PRODUCTS_IDS);
            default -> productIds = new ArrayList<>();
        }
        return productIds;
    }
}
