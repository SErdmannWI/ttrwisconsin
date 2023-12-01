package com.serdmannwi.practiceprograms.tickettoridewisconsin.service;

import com.serdmannwi.practiceprograms.tickettoridewisconsin.constants.CityConstants;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.constants.ProductConstants;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.City;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.CityRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.*;
import java.util.stream.Collectors;

import static junit.framework.TestCase.fail;
import static org.mockito.Mockito.*;

public class CityServiceTest {
    private CityService cityService;
    @Mock
    private CityRepository cityRepository;

    /**------------------------------------------------ Test Constants -----------------------------------------------*/

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        cityRepository = mock(CityRepository.class);
        cityService = new CityService(cityRepository);
    }

    /**
     * Conditions to Test:
     * All Cities within a given Region must have a product that matches what the Region offers
     * All three products must be assigned with one product having a maxiumum of 4 assignments
     * All Cities must be assigned an economy roll and that number cannot repeat (no City in the same Region may have
     * a roll of 6, for example)
     */
    @Test
    public void initializeCityEconomies_returnsUpdatedCityList() {
        //GIVEN
        Map<Integer, List<String>> mockedCities = CityConstants.getTestCityMap();

        //Return mocked Test Cities when cityRepository performs findById method
        when(cityRepository.findById("TC11")).thenReturn(Optional.of(CityConstants.TEST_CITY_1_1));
        when(cityRepository.findById("TC12")).thenReturn(Optional.of(CityConstants.TEST_CITY_1_2));
        when(cityRepository.findById("TC13")).thenReturn(Optional.of(CityConstants.TEST_CITY_1_3));
        when(cityRepository.findById("TC14")).thenReturn(Optional.of(CityConstants.TEST_CITY_1_4));
        when(cityRepository.findById("TC15")).thenReturn(Optional.of(CityConstants.TEST_CITY_1_5));
        when(cityRepository.findById("TC16")).thenReturn(Optional.of(CityConstants.TEST_CITY_1_6));
        when(cityRepository.findById("TC17")).thenReturn(Optional.of(CityConstants.TEST_CITY_1_7));
        when(cityRepository.findById("TC18")).thenReturn(Optional.of(CityConstants.TEST_CITY_1_8));
        when(cityRepository.findById("TC19")).thenReturn(Optional.of(CityConstants.TEST_CITY_1_9));
        when(cityRepository.findById("TC110")).thenReturn(Optional.of(CityConstants.TEST_CITY_1_10));

        when(cityRepository.findById("TC21")).thenReturn(Optional.of(CityConstants.TEST_CITY_2_1));
        when(cityRepository.findById("TC22")).thenReturn(Optional.of(CityConstants.TEST_CITY_2_2));
        when(cityRepository.findById("TC23")).thenReturn(Optional.of(CityConstants.TEST_CITY_2_3));
        when(cityRepository.findById("TC24")).thenReturn(Optional.of(CityConstants.TEST_CITY_2_4));
        when(cityRepository.findById("TC25")).thenReturn(Optional.of(CityConstants.TEST_CITY_2_5));
        when(cityRepository.findById("TC26")).thenReturn(Optional.of(CityConstants.TEST_CITY_2_6));
        when(cityRepository.findById("TC27")).thenReturn(Optional.of(CityConstants.TEST_CITY_2_7));
        when(cityRepository.findById("TC28")).thenReturn(Optional.of(CityConstants.TEST_CITY_2_8));
        when(cityRepository.findById("TC29")).thenReturn(Optional.of(CityConstants.TEST_CITY_2_9));
        when(cityRepository.findById("TC210")).thenReturn(Optional.of(CityConstants.TEST_CITY_2_10));

        when(cityRepository.findById("TC31")).thenReturn(Optional.of(CityConstants.TEST_CITY_3_1));
        when(cityRepository.findById("TC32")).thenReturn(Optional.of(CityConstants.TEST_CITY_3_2));
        when(cityRepository.findById("TC33")).thenReturn(Optional.of(CityConstants.TEST_CITY_3_3));
        when(cityRepository.findById("TC34")).thenReturn(Optional.of(CityConstants.TEST_CITY_3_4));
        when(cityRepository.findById("TC35")).thenReturn(Optional.of(CityConstants.TEST_CITY_3_5));
        when(cityRepository.findById("TC36")).thenReturn(Optional.of(CityConstants.TEST_CITY_3_6));
        when(cityRepository.findById("TC37")).thenReturn(Optional.of(CityConstants.TEST_CITY_3_7));
        when(cityRepository.findById("TC38")).thenReturn(Optional.of(CityConstants.TEST_CITY_3_8));
        when(cityRepository.findById("TC39")).thenReturn(Optional.of(CityConstants.TEST_CITY_3_9));
        when(cityRepository.findById("TC310")).thenReturn(Optional.of(CityConstants.TEST_CITY_3_10));

        when(cityRepository.findById("TC41")).thenReturn(Optional.of(CityConstants.TEST_CITY_4_1));
        when(cityRepository.findById("TC42")).thenReturn(Optional.of(CityConstants.TEST_CITY_4_2));
        when(cityRepository.findById("TC43")).thenReturn(Optional.of(CityConstants.TEST_CITY_4_3));
        when(cityRepository.findById("TC44")).thenReturn(Optional.of(CityConstants.TEST_CITY_4_4));
        when(cityRepository.findById("TC45")).thenReturn(Optional.of(CityConstants.TEST_CITY_4_5));
        when(cityRepository.findById("TC46")).thenReturn(Optional.of(CityConstants.TEST_CITY_4_6));
        when(cityRepository.findById("TC47")).thenReturn(Optional.of(CityConstants.TEST_CITY_4_7));
        when(cityRepository.findById("TC48")).thenReturn(Optional.of(CityConstants.TEST_CITY_4_8));
        when(cityRepository.findById("TC49")).thenReturn(Optional.of(CityConstants.TEST_CITY_4_9));
        when(cityRepository.findById("TC410")).thenReturn(Optional.of(CityConstants.TEST_CITY_4_10));

        //When cityRepository.save is called, return the City object that it was given
        when(cityRepository.save(any(City.class))).thenAnswer((Answer<City>) invocation -> (City) invocation.getArguments()[0]);

        //WHEN
        List<City> updatedCities = cityService.addEconomyInfoToCity(mockedCities);

        //THEN
        List<City> region1Cities = new ArrayList<>();
        List<City> region2Cities = new ArrayList<>();
        List<City> region3Cities = new ArrayList<>();
        List<City> region4Cities = new ArrayList<>();

        updatedCities.forEach(city -> {
            switch (city.getRegionId()) {
                case 1 -> region1Cities.add(city);
                case 2 -> region2Cities.add(city);
                case 3 -> region3Cities.add(city);
                case 4 -> region4Cities.add(city);
                default -> Assertions.fail("City contains unexpected regionId: " + city.getRegionId());
            }
        });

        //Check each region for correct and balanced product assignment.
        //------------------------------------------ Region 1 ----------------------------------------------------//
        Map<String, Long> productsAssigned = region1Cities.stream()
            .collect(Collectors.groupingBy(City::getProductId, Collectors.counting()));

        boolean allProductsAssigned = ProductConstants.REGION_ONE_PRODUCTS_IDS.containsAll(productsAssigned.keySet());

        String incorrectProductsInfo = productsAssigned.keySet().stream()
            .filter(productId -> !ProductConstants.REGION_ONE_PRODUCTS_IDS.contains(productId))
            .collect(Collectors.joining(", "));

        Assertions.assertTrue(allProductsAssigned, "City in Region 1 has incorrect product: " + incorrectProductsInfo);

        long countOfThreeAssignments = productsAssigned.values().stream().filter(count -> count == 3).count();
        long countOfFourAssignments = productsAssigned.values().stream().filter(count -> count == 4).count();

        Assertions.assertEquals(2, countOfThreeAssignments, "There should be exactly 2 products assigned 3 times: " +
            formatProductAssignments(productsAssigned));
        Assertions.assertEquals(1, countOfFourAssignments, "There should be exactly 1 product assigned 4 times: " +
            formatProductAssignments(productsAssigned));

        Map<String, Integer> map = new HashMap<>();
        for (City city : region1Cities) {
            if (!ProductConstants.REGION_ONE_PRODUCTS_IDS.contains(city.getProductId())) {
                Assertions.fail("City in Region 1 has incorrect product: " + city.getProductId());
            }
            if (!map.containsKey(city.getProductId())) {
                map.put(city.getProductId(), 1);
            } else {
                map.put(city.getProductId(), map.get(city.getProductId()) + 1);
            }
        }

        if (map.values().size() > 3) {
            Assertions.fail("More than three products were assigned in Region 1: " + map.keySet()
            + "\n" + map.values());
        }

        int regionOneThreeAssignments = 0;
        int regionOneFourAssignments = 0;
        for (Integer assignment : map.values()) {
            if (assignment == 3) {
                regionOneThreeAssignments ++;
            } else if (assignment == 4) {
                regionOneFourAssignments ++;
            }
        }

        Assertions.assertTrue(regionOneThreeAssignments == 2 && regionOneFourAssignments == 1,
            "Region 1's products should be assigned evenly(3, 3 and 4 times)." + "\nProduct Assignments: "
        + map.keySet() + "\n" + map.values());

        //------------------------------------------ Region 2 ----------------------------------------------------//
        map = new HashMap<>();
        for (City city : region2Cities) {
            if (!ProductConstants.REGION_TWO_PRODUCTS_IDS.contains(city.getProductId())) {
                Assertions.fail("City in Region 2 has incorrect product: " + city.getProductId());
            }
            if (!map.containsKey(city.getProductId())) {
                map.put(city.getProductId(), 1);
            } else {
                map.put(city.getProductId(), map.get(city.getProductId()) + 1);
            }
        }

        if (map.values().size() > 3) {
            Assertions.fail("More than three products were assigned in Region 2: " + map.keySet()
                + "\n" + map.values());
        }

        int regionTwoThreeAssignments = 0;
        int regionTwoFourAssignments = 0;
        for (Integer assignment : map.values()) {
            if (assignment == 3) {
                regionTwoThreeAssignments ++;
            } else if (assignment == 4) {
                regionTwoFourAssignments ++;
            }
        }

        Assertions.assertTrue(regionTwoThreeAssignments == 2 && regionTwoFourAssignments == 1,
            "Region Two's products should be assigned evenly(3, 3 and 4 times)." + "\nProduct Assignments: "
                + map.keySet() + "\n" + map.values());

        //------------------------------------------ Region 3 ----------------------------------------------------//
        map = new HashMap<>();
        for (City city : region3Cities) {
            if (!ProductConstants.REGION_THREE_PRODUCTS_IDS.contains(city.getProductId())) {
                Assertions.fail("City in Region 3 has incorrect product: " + city.getProductId());
            }
            if (!map.containsKey(city.getProductId())) {
                map.put(city.getProductId(), 1);
            } else {
                map.put(city.getProductId(), map.get(city.getProductId()) + 1);
            }
        }

        if (map.values().size() > 3) {
            Assertions.fail("More than three products were assigned in Region 3: " + map.keySet()
                + "\n" + map.values());
        }

        int regionThreeThreeAssignments = 0;
        int regionThreeFourAssignments = 0;
        for (Integer assignment : map.values()) {
            if (assignment == 3) {
                regionThreeThreeAssignments ++;
            } else if (assignment == 4) {
                regionThreeFourAssignments ++;
            }
        }

        Assertions.assertTrue(regionThreeThreeAssignments == 2 && regionThreeFourAssignments == 1,
            "Region 3's products should be assigned evenly(3, 3 and 4 times)." + "\nProduct Assignments: "
                + map.keySet() + "\n" + map.values());

        //------------------------------------------ Region 4 ----------------------------------------------------//
        map = new HashMap<>();
        for (City city : region4Cities) {
            if (!ProductConstants.REGION_FOUR_PRODUCTS_IDS.contains(city.getProductId())) {
                fail("City in Region 4 has incorrect product: " + city.getProductId());
            }
            if (!map.containsKey(city.getProductId())) {
                map.put(city.getProductId(), 1);
            } else {
                map.put(city.getProductId(), map.get(city.getProductId()) + 1);
            }
        }

        if (map.values().size() > 3) {
            Assertions.fail("More than three products were assigned in Region 4: " + map.keySet()
                + "\n" + map.values());
        }

        int regionFourThreeAssignments = 0;
        int regionFourFourAssignments = 0;
        for (Integer assignment : map.values()) {
            if (assignment == 3) {
                regionFourThreeAssignments ++;
            } else if (assignment == 4) {
                regionFourFourAssignments ++;
            }
        }

        Assertions.assertTrue(regionFourThreeAssignments == 2 && regionFourFourAssignments == 1,
            "Region 4's products should be assigned evenly(3, 3 and 4 times)." + "\nProduct Assignments: "
                + map.keySet() + "\n" + map.values());
    }

    /**--------------------------------------------- Utility Methods -------------------------------------------------*/
    private String formatProductAssignments(Map<String, Long> assignments) {
        return assignments.entrySet().stream()
            .map(entry -> entry.getKey() + ": " + entry.getValue() + " times")
            .collect(Collectors.joining(", "));
    }
}
