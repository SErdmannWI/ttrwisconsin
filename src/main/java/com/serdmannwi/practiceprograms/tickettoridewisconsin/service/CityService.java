package com.serdmannwi.practiceprograms.tickettoridewisconsin.service;

import com.serdmannwi.practiceprograms.tickettoridewisconsin.constants.CityConstants;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.constants.GameConstants;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.constants.ProductConstants;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.exceptions.CityInitializationException;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.exceptions.CityNotFoundException;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.City;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.CityRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Creates a new City Repository on instantiation that will be populated with Cities from CityDataInitializer.
 * Creates the 4 Lists of City ID's for each region. Each region will have 12 cities.
 * Creates the 4 Lits of Products ID's for each region. Each region will have 3 Products.
 */
@Service
public class CityService {

    private final CityRepository cityRepository;
    private final List<String> REGION_ONE_CITIES;
    private final List<String> REGION_ONE_PRODUCTS;
    private final List<String> REGION_TWO_CITIES;
    private final List<String> REGION_TWO_PRODUCTS;
    private final List<String> REGION_THREE_CITIES;
    private final List<String> REGION_THREE_PRODUCTS;
    private final List<String> REGION_FOUR_CITIES;
    private final List<String> REGION_FOUR_PRODUCTS;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
        this.REGION_ONE_CITIES = new ArrayList<>(Arrays.asList(CityConstants.MADISON_ID, CityConstants.MILWAUKEE_ID));
        this.REGION_ONE_PRODUCTS = new ArrayList<>(Arrays.asList(ProductConstants.PRODUCT_MACHINERY_ID,
            ProductConstants.PRODUCT_PAPER_ID));

        this.REGION_TWO_CITIES = new ArrayList<>(Arrays.asList(CityConstants.APPLETON_ID, CityConstants.FOND_DU_LAC_ID));
        this.REGION_TWO_PRODUCTS = new ArrayList<>(Arrays.asList(ProductConstants.PRODUCT_CHEESE_ID,
            ProductConstants.PRODUCT_CORN_ID));

        this.REGION_THREE_CITIES = new ArrayList<>(Arrays.asList(CityConstants.EAU_CLAIRE_ID, CityConstants.LA_CROSSE_ID));
        this.REGION_THREE_PRODUCTS = new ArrayList<>(Arrays.asList(ProductConstants.PRODUCT_CRANBERRIES_ID,
            ProductConstants.PRODUCT_SAND_ID));

        this.REGION_FOUR_CITIES = new ArrayList<>(Arrays.asList(CityConstants.WAUSAU_ID, CityConstants.DULUTH_ID));
        this.REGION_FOUR_PRODUCTS = new ArrayList<>(Arrays.asList(ProductConstants.PRODUCT_FORESTRY_ID,
            ProductConstants.PRODUCT_SAUSAGE_ID));
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
    public void initializeCityEconomies() {
        try {
            addEconomyInfoToCity(REGION_ONE_CITIES);
            addEconomyInfoToCity(REGION_TWO_CITIES);
            addEconomyInfoToCity(REGION_THREE_CITIES);
            addEconomyInfoToCity(REGION_FOUR_CITIES);
        } catch (CityNotFoundException e) {
                throw new CityInitializationException("Failed to initialize City economy", e);
        }
    }

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public City getCityById(String id) {
        return cityRepository.findById(id).orElse(null);
    }

    /**
     * For each region list, gets each City from the Repository, gets a new EconomyRoll List and that region's
     * Product List, randomizes and then assigns it to each City, saving each inside the repository
     * @param cityIds
     * @return
     * @throws CityNotFoundException
     */
    //TODO Refactor so that appropriate product list for each region is called.
    public List<City> addEconomyInfoToCity(List<String> cityIds) throws CityNotFoundException {
        List<City> updatedCities = new ArrayList<>();
        City city;
        List<Integer> economyRolls = GameConstants.economyRollList;
        Collections.shuffle(economyRolls);
        Queue<Integer> economyQueue = new LinkedList<>(economyRolls);

        List<String>  productList = new ArrayList<>();
        productList.addAll(REGION_ONE_PRODUCTS);
        Collections.shuffle(productList);
        Queue<String> productQueue = new LinkedList<>(productList);
        for (String cityId : cityIds) {
            city = cityRepository.findById(cityId).orElseThrow(() ->
                new CityNotFoundException("City with ID: " + cityId + " was not found."));
            city.setProductsAvailable(new ArrayList<>(Arrays.asList()));
            city.setEconomyRoll(economyQueue.poll());
            updatedCities.add(city);
        }

        return updatedCities;
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
