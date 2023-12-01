package com.serdmannwi.practiceprograms.tickettoridewisconsin.constants;

import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.City;

import java.util.*;

public class CityConstants {

    /**----------------------------------------------- RegionIDs ----------------------------------------------------**/
    public static final int REGION_ONE_ID = 1;
    public static final int REGION_TWO_ID = 2;
    public static final int REGION_THREE_ID = 3;
    public static final int REGION_FOUR_ID = 4;

    /**--------------------------------------------------- Names ---------------------------------------------------**/
    public static final String APPLETON_NAME = "Appleton";
    public static final String ASHLAND_NAME = "Ashland";
    public static final String BEAVER_DAM_NAME = "Beaver Dam";
    public static final String BELOIT_NAME = "Beloit";
    public static final String BRILLION_NAME = "Brillion";
    public static final String CHICAGO_NAME = "Chicago";
    public static final String CHIPPEWA_FALLS_NAME = "Chippewa Falls";
    public static final String DUBUQUE_NAME = "Dubuque";
    public static final String DULUTH_NAME = "Duluth";
    public static final String EAU_CLAIRE_NAME = "Eau Claire";
    public static final String FOND_DU_LAC_NAME = "Fond du Lac";
    public static final String GREEN_BAY_NAME = "Green Bay";
    public static final String HUDSON_NAME = "Hudson";
    public static final String IRON_MOUNTAIN_NAME = "Iron Mountain";
    public static final String IRONWOOD_NAME = "Ironwood";
    public static final String JANESVILLE_NAME = "Janesville";
    public static final String JOHNSON_CREEK_NAME = "Johnson Creek";
    public static final String KENOSHA_NAME = "Kenosha";
    public static final String LA_CROSSE_NAME = "La Crosse";
    public static final String MADISON_NAME = "Madison";
    public static final String MANITOWOC_NAME = "Manitowoc";
    public static final String MARINETTE_NAME = "Marinette";
    public static final String MILWAUKEE_NAME = "Milwaukee";
    public static final String MINNEAPOLIS_NAME = "Minneapolis";
    public static final String OSHKOSH_NAME = "Oshkosh";
    public static final String PLATTEVILLE_NAME = "Platteville";
    public static final String PORTAGE_NAME = "Portage";
    public static final String RACINE_NAME = "Racine";
    public static final String ROCHESTER_NAME = "Rochester";
    public static final String ROCKFORD_NAME = "Rockford";
    public static final String SHEBOYGAN_NAME = "Sheboygan";
    public static final String STEVENS_POINT_NAME = "Stevens Point";
    public static final String STURGEON_BAY_NAME = "Sturgeon Bay";
    public static final String SUPERIOR_NAME = "Superior";
    public static final String TOMAH_NAME = "Tomah";
    public static final String TOMAHAWK_NAME = "Tomahawk";
    public static final String WAUPACA_NAME = "Waupaca";
    public static final String WAUSAU_NAME = "Wausau";
    public static final String WEST_BEND_NAME = "West Bend";
    public static final String WINONA_NAME = "Winona";
    public static final String WISCONSIN_DELLS_NAME = "Wisconsin Dells";
    public static final String WISCONSIN_RAPIDS_NAME = "Wisconsin Rapids";

    public static final String TEST_CITY_ONE_NAME = "Test City 1";
    public static final String TEST_CITY_TWO_NAME = "Test City 2";
    public static final String TEST_CITY_THREE_NAME = "Test City 3";
    public static final String TEST_CITY_FOUR_NAME = "Test City 4";

    /**--------------------------------------------------- IDs ---------------------------------------------------**/
    public static final String APPLETON_ID = "APL";
    public static final String ASHLAND_ID = "ALD";
    public static final String BEAVER_DAM_ID = "BVD";
    public static final String BELOIT_ID = "BLT";
    public static final String BRILLION_ID = "BLN";
    public static final String CHICAGO_ID = "CHI";
    public static final String CHIPPEWA_FALLS_ID = "CHF";
    public static final String DUBUQUE_ID = "DBQ";
    public static final String DULUTH_ID = "DTH";
    public static final String EAU_CLAIRE_ID = "ECL";
    public static final String FOND_DU_LAC_ID = "FDL";
    public static final String GREEN_BAY_ID = "GBW";
    public static final String HUDSON_ID = "HDN";
    public static final String IRON_MOUNTAIN_ID = "IMT";
    public static final String IRONWOOD_ID = "IWD";
    public static final String JANESVILLE_ID = "JVL";
    public static final String JOHNSON_CREEK_ID = "JSN";
    public static final String KENOSHA_ID = "KSA";
    public static final String LA_CROSSE_ID = "LCS";
    public static final String MADISON_ID = "MDN";
    public static final String MANITOWOC_ID = "MWC";
    public static final String MARINETTE_ID = "MTE";
    public static final String MILWAUKEE_ID = "MKE";
    public static final String MINNEAPOLIS_ID = "MSP";
    public static final String OSHKOSH_ID = "OSH";
    public static final String PLATTEVILLE_ID = "PLT";
    public static final String PORTAGE_ID = "PTG";
    public static final String RACINE_ID = "RNE";
    public static final String ROCHESTER_ID = "RCR";
    public static final String ROCKFORD_ID = "RFD";
    public static final String SHEBOYGAN_ID = "SHB";
    public static final String STEVENS_POINT_ID = "SVP";
    public static final String STURGEON_BAY_ID = "SBW";
    public static final String SUPERIOR_ID = "SUP";
    public static final String TOMAH_ID = "TMH";
    public static final String TOMAHAWK_ID = "TMK";
    public static final String WAUPACA_ID = "WPA";
    public static final String WAUSAU_ID = "WSA";
    public static final String WEST_BEND_ID = "WBD";
    public static final String WINONA_ID = "WNA";
    public static final String WISCONSIN_DELLS_ID = "WDL";
    public static final String WISCONSIN_RAPIDS_ID = "RPD";

    public static final String TEST_CITY_ONE_ID = "TC1";
    public static final String TEST_CITY_TWO_ID = "TC2";
    public static final String TEST_CITY_THREE_ID = "TC3";
    public static final String TEST_CITY_FOUR_ID = "TC4";

    /**----------------------------------------------- Region Lists -------------------------------------------------**/
    //NOTE: Hudson and Waupaca not currently used to maintain List size of 10 due to economy roll list size of 10.

    //Southern Wisconsin
    public static final List<String> REGION_ONE_CITY_IDS = new ArrayList<>(Arrays.asList(BELOIT_ID, CHICAGO_ID,
        JANESVILLE_ID, JOHNSON_CREEK_ID, KENOSHA_ID, MADISON_ID, MILWAUKEE_ID, PORTAGE_ID, RACINE_ID, ROCKFORD_ID));

    //Fox Valley
    public static final List<String> REGION_TWO_CITY_IDS = new ArrayList<>(Arrays.asList(APPLETON_ID, BEAVER_DAM_ID,
        BRILLION_ID, FOND_DU_LAC_ID, GREEN_BAY_ID, MANITOWOC_ID, OSHKOSH_ID, SHEBOYGAN_ID, STURGEON_BAY_ID, WEST_BEND_ID));

    //Mississippi River/ Driftless Area
    public static final List<String> REGION_THREE_CITY_IDS = new ArrayList<>(Arrays.asList(CityConstants.CHIPPEWA_FALLS_ID,
        CityConstants.DUBUQUE_ID, CityConstants.EAU_CLAIRE_ID, CityConstants.LA_CROSSE_ID, CityConstants.PLATTEVILLE_ID,
        CityConstants.ROCHESTER_ID, CityConstants.MINNEAPOLIS_ID, CityConstants.TOMAH_ID, CityConstants.WINONA_ID,
        CityConstants.WISCONSIN_DELLS_ID));

    //Northern Wisconsin
    public static final List<String> REGION_FOUR_CITY_IDS = new ArrayList<>(Arrays.asList(CityConstants.ASHLAND_ID,
        DULUTH_ID, CityConstants.IRON_MOUNTAIN_ID, CityConstants.IRONWOOD_ID, CityConstants.MARINETTE_ID,
        CityConstants.STEVENS_POINT_ID, CityConstants.SUPERIOR_ID, CityConstants.TOMAHAWK_ID, CityConstants.WAUSAU_ID,
        WISCONSIN_RAPIDS_ID));

    /**---------------------------------------------- Region Map ---------------------------------------------------**/
    //Key: Region ID (1, 2, 3 or 4) Value: List of City IDs for that region
    public static final Map<Integer, List<String>> CITY_MAP = new HashMap<>();
    static {
        CITY_MAP.put(REGION_ONE_ID, REGION_ONE_CITY_IDS);
        CITY_MAP.put(REGION_TWO_ID, REGION_TWO_CITY_IDS);
        CITY_MAP.put(REGION_THREE_ID, REGION_THREE_CITY_IDS);
        CITY_MAP.put(REGION_FOUR_ID, REGION_FOUR_CITY_IDS);
    }

    /**-------------------------------------------------Spur Names -------------------------------------------------**/
    //Eau Claire
    public static final String EAU_CLAIRE_SPUR_ONE_NAME = "Eau Claire / Chippewa Falls Industrial Park";
    public static final String EAU_CLAIRE_SPUR_TWO_NAME = "Truax Manufacturing";
    public static final String EAU_CLAIRE_SPUR_THREE_NAME = "Menomonie Steel";

    //Fond du Lac
    public static final String FOND_DU_LAC_SPUR_ONE_NAME = "Lomira- Quad Graphics";
    public static final String FOND_DU_LAC_SPUR_TWO_NAME = "Eden- Quarry";
    public static final String FOND_DU_LAC_SPUR_THREE_NAME = "Fond du Lac Industrial Park";

    //Green Bay
    public static final String GREEN_BAY_SPUR_ONE_NAME = "Luxembourg- Feed Mill";
    public static final String GREEN_BAY_SPUR_TWO_NAME = "Denmark Agricultural";
    public static final String GREEN_BAY_SPUR_THREE_NAME = "Green Bay Industrial Park";

    //Madison
    public static final String MADISON_SPUR_ONE_NAME = "Middleton Agricultural";
    public static final String MADISON_SPUR_TWO_NAME = "Cottage Grove Chemical";
    public static final String MADISON_SPUR_THREE_NAME = "Windsor Construction";

    //Milwaukee
    public static final String MILWAUKEE_SPUR_ONE_NAME = "Port of Milwaukee Terminal";
    public static final String MILWAUKEE_SPUR_TWO_NAME = "Komatsu Mining Corp";
    public static final String MILWAUKEE_SPUR_THREE_NAME = "Butler Train Yard";

    //La Crosse
    public static final String LA_CROSSE_SPUR_ONE_NAME = "West Salem Industrial";
    public static final String LA_CROSSE_SPUR_TWO_NAME = "French Island Industrial";
    public static final String LA_CROSSE_SPUR_THREE_NAME = "La Crosse Steel";

    //Superior
    public static final String SUPERIOR_SPUR_ONE_NAME = "Duluth Harbor Terminal";
    public static final String SUPERIOR_SPUR_TWO_NAME = "Superior Refinery";
    public static final String SUPERIOR_SPUR_THREE_NAME = "Itasca Yard";

    //Wausau
    public static final String WAUSAU_SPUR_ONE_NAME = "Wausau Chemical";
    public static final String WAUSAU_SPUR_TWO_NAME = "Schofield Industrial";
    public static final String WAUSAU_SPUR_THREE_NAME = "Wausau- 3M";

    /**-------------------------------------------------Spur IDs -------------------------------------------------**/
    //Eau Claire
    public static final String EAU_CLAIRE_SPUR_ONE_ID = "ECL-SPR1";
    public static final String EAU_CLAIRE_SPUR_TWO_ID = "ECL-SPR2";
    public static final String EAU_CLAIRE_SPUR_THREE_ID = "ECL-SPR3";

    //Fond du Lac
    public static final String FOND_DU_LAC_SPUR_ONE_ID = "FDL-SPR1";
    public static final String FOND_DU_LAC_SPUR_TWO_ID = "FDL-SPR2";
    public static final String FOND_DU_LAC_SPUR_THREE_ID = "FDL-SPR3";

    //Green Bay
    public static final String GREEN_BAY_SPUR_ONE_ID = "GBW-SPR1";
    public static final String GREEN_BAY_SPUR_TWO_ID = "GBW-SPR2";
    public static final String GREEN_BAY_SPUR_THREE_ID = "GBW-SPR3";

    //Madison
    public static final String MADISON_SPUR_ONE_ID = "MDN-SPR1";
    public static final String MADISON_SPUR_TWO_ID = "MDN-SPR2";
    public static final String MADISON_SPUR_THREE_ID = "MDN-SPR3";

    //Milwaukee
    public static final String MILWAUKEE_SPUR_ONE_ID = "MKE-SPR1";
    public static final String MILWAUKEE_SPUR_TWO_ID = "MKE-SPR2";
    public static final String MILWAUKEE_SPUR_THREE_ID = "MKE-SPR3";

    //La Crosse
    public static final String LA_CROSSE_SPUR_ONE_ID = "LCS-SPR1";
    public static final String LA_CROSSE_SPUR_TWO_ID = "LCS-SPR2";
    public static final String LA_CROSSE_SPUR_THREE_ID = "LCS-SPR3";

    //Superior
    public static final String SUPERIOR_SPUR_ONE_ID = "SUP-SPR1";
    public static final String SUPERIOR_SPUR_TWO_ID = "SUP-SPR2";
    public static final String SUPERIOR_SPUR_THREE_ID = "SUP-SPR3";

    //Wausau
    public static final String WAUSAU_SPUR_ONE_ID = "WSA";
    public static final String WAUSAU_SPUR_TWO_ID = "SCH";
    public static final String WAUSAU_SPUR_THREE_ID = "WSA";

    //Test City 3
    public static final String TEST_CITY_THREE_SPUR_ONE_ID = "TC3-SPR1";

    /**--------------------------------------------High Speed IDs -------------------------------------------------**/
    public static final String HS_CHI_MKE = "CHI-MKE";
    public static final String HS_MKE_MDN = "MKE-MDN";
    public static final String HS_MDN_TMH = "MDN-TMH";
    public static final String HS_TMH_LCS = "TMH-LCS";
    public static final String HS_TMH_ECL = "TMH-ECL";
    public static final String HS_LCS_RCR = "LCS-RCR";
    public static final String HS_RCR_MSP = "RCR-MSP";
    public static final String HS_ECL_MSP = "ECL-MSP";
    public static final String HS_MSP_DTH = "MSP-DTH";

    public static final String HS_TC3_TC4 = "TC3-TC4";

    /**--------------------------------------------Testing City IDs -------------------------------------------------**/
    public static final City TEST_CITY_1_1 = new City("Test City OneOne", "TC11", CityConstants.REGION_ONE_ID);
    public static final City TEST_CITY_1_2 = new City("Test City OneTwo", "TC12", CityConstants.REGION_ONE_ID);
    public static final City TEST_CITY_1_3 = new City("Test City OneThree", "TC13", CityConstants.REGION_ONE_ID);
    public static final City TEST_CITY_1_4 = new City("Test City OneFour", "TC14", CityConstants.REGION_ONE_ID);
    public static final City TEST_CITY_1_5 = new City("Test City OneFive", "TC15", CityConstants.REGION_ONE_ID);
    public static final City TEST_CITY_1_6 = new City("Test City OneSix", "TC16", CityConstants.REGION_ONE_ID);
    public static final City TEST_CITY_1_7 = new City("Test City OneSeven", "TC17", CityConstants.REGION_ONE_ID);
    public static final City TEST_CITY_1_8 = new City("Test City OneEight", "TC18", CityConstants.REGION_ONE_ID);
    public static final City TEST_CITY_1_9 = new City("Test City OneNine", "TC19", CityConstants.REGION_ONE_ID);
    public static final City TEST_CITY_1_10 = new City("Test City OneTen", "TC110", CityConstants.REGION_ONE_ID);

    public static final City TEST_CITY_2_1 = new City("Test City TwoOne", "TC21", CityConstants.REGION_TWO_ID);
    public static final City TEST_CITY_2_2 = new City("Test City TwoTwo", "TC22", CityConstants.REGION_TWO_ID);
    public static final City TEST_CITY_2_3 = new City("Test City TwoThree", "TC23", CityConstants.REGION_TWO_ID);
    public static final City TEST_CITY_2_4 = new City("Test City TwoFour", "TC24", CityConstants.REGION_TWO_ID);
    public static final City TEST_CITY_2_5 = new City("Test City TwoFive", "TC25", CityConstants.REGION_TWO_ID);
    public static final City TEST_CITY_2_6 = new City("Test City TwoSix", "TC26", CityConstants.REGION_TWO_ID);
    public static final City TEST_CITY_2_7 = new City("Test City TwoSeven", "TC27", CityConstants.REGION_TWO_ID);
    public static final City TEST_CITY_2_8 = new City("Test City TwoEight", "TC28", CityConstants.REGION_TWO_ID);
    public static final City TEST_CITY_2_9 = new City("Test City TwoNine", "TC29", CityConstants.REGION_TWO_ID);
    public static final City TEST_CITY_2_10 = new City("Test City TwoTen", "TC210", CityConstants.REGION_TWO_ID);

    public static final City TEST_CITY_3_1 = new City("Test City ThreeOne", "TC31", CityConstants.REGION_THREE_ID);
    public static final City TEST_CITY_3_2 = new City("Test City ThreeTwo", "TC32", CityConstants.REGION_THREE_ID);
    public static final City TEST_CITY_3_3 = new City("Test City ThreeThree", "TC33", CityConstants.REGION_THREE_ID);
    public static final City TEST_CITY_3_4 = new City("Test City ThreeFour", "TC34", CityConstants.REGION_THREE_ID);
    public static final City TEST_CITY_3_5 = new City("Test City ThreeFive", "TC35", CityConstants.REGION_THREE_ID);
    public static final City TEST_CITY_3_6 = new City("Test City ThreeSix", "TC36", CityConstants.REGION_THREE_ID);
    public static final City TEST_CITY_3_7 = new City("Test City ThreeSeven", "TC37", CityConstants.REGION_THREE_ID);
    public static final City TEST_CITY_3_8 = new City("Test City ThreeEight", "TC38", CityConstants.REGION_THREE_ID);
    public static final City TEST_CITY_3_9 = new City("Test City ThreeNine", "TC39", CityConstants.REGION_THREE_ID);
    public static final City TEST_CITY_3_10 = new City("Test City ThreeTen", "TC310", CityConstants.REGION_THREE_ID);

    public static final City TEST_CITY_4_1 = new City("Test City FourOne", "TC41", CityConstants.REGION_FOUR_ID);
    public static final City TEST_CITY_4_2 = new City("Test City FourTwo", "TC42", CityConstants.REGION_FOUR_ID);
    public static final City TEST_CITY_4_3 = new City("Test City FourThree", "TC43", CityConstants.REGION_FOUR_ID);
    public static final City TEST_CITY_4_4 = new City("Test City FourFour", "TC44", CityConstants.REGION_FOUR_ID);
    public static final City TEST_CITY_4_5 = new City("Test City FourFive", "TC45", CityConstants.REGION_FOUR_ID);
    public static final City TEST_CITY_4_6 = new City("Test City FourSix", "TC46", CityConstants.REGION_FOUR_ID);
    public static final City TEST_CITY_4_7 = new City("Test City FourSeven", "TC47", CityConstants.REGION_FOUR_ID);
    public static final City TEST_CITY_4_8 = new City("Test City FourEight", "TC48", CityConstants.REGION_FOUR_ID);
    public static final City TEST_CITY_4_9 = new City("Test City FourNine", "TC49", CityConstants.REGION_FOUR_ID);
    public static final City TEST_CITY_4_10 = new City("Test City FourTen", "TC410", CityConstants.REGION_FOUR_ID);

    public static Map<Integer, List<String>> getTestCityMap() {
        Map<Integer, List<String>> testCityMap = new HashMap<>();

        List<String> region1CityIds = Arrays.asList(
            TEST_CITY_1_1.getCityId(), TEST_CITY_1_2.getCityId(), TEST_CITY_1_3.getCityId(),
            TEST_CITY_1_4.getCityId(), TEST_CITY_1_5.getCityId(), TEST_CITY_1_6.getCityId(),
            TEST_CITY_1_7.getCityId(), TEST_CITY_1_8.getCityId(), TEST_CITY_1_9.getCityId(),
            TEST_CITY_1_10.getCityId());

        List<String> region2CityIds = Arrays.asList(
            TEST_CITY_2_1.getCityId(), TEST_CITY_2_2.getCityId(), TEST_CITY_2_3.getCityId(),
            TEST_CITY_2_4.getCityId(), TEST_CITY_2_5.getCityId(), TEST_CITY_2_6.getCityId(),
            TEST_CITY_2_7.getCityId(), TEST_CITY_2_8.getCityId(), TEST_CITY_2_9.getCityId(),
            TEST_CITY_2_10.getCityId());

        List<String> region3CityIds = Arrays.asList(
            TEST_CITY_3_1.getCityId(), TEST_CITY_3_2.getCityId(), TEST_CITY_3_3.getCityId(),
            TEST_CITY_3_4.getCityId(), TEST_CITY_3_5.getCityId(), TEST_CITY_3_6.getCityId(),
            TEST_CITY_3_7.getCityId(), TEST_CITY_3_8.getCityId(), TEST_CITY_3_9.getCityId(),
            TEST_CITY_3_10.getCityId());

        List<String> region4CityIds = Arrays.asList(
            TEST_CITY_4_1.getCityId(), TEST_CITY_4_2.getCityId(), TEST_CITY_4_3.getCityId(),
            TEST_CITY_4_4.getCityId(), TEST_CITY_4_5.getCityId(), TEST_CITY_4_6.getCityId(),
            TEST_CITY_4_7.getCityId(), TEST_CITY_4_8.getCityId(), TEST_CITY_4_9.getCityId(),
            TEST_CITY_4_10.getCityId());

        testCityMap.put(1, region1CityIds);
        testCityMap.put(2, region2CityIds);
        testCityMap.put(3, region3CityIds);
        testCityMap.put(4, region4CityIds);

        return testCityMap;
    }

}
