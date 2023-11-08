package com.serdmannwi.practiceprograms.tickettoridewisconsin.constants;

import com.serdmannwi.practiceprograms.tickettoridewisconsin.service.model.FreightStation;

import java.util.HashMap;
import java.util.Map;

public class FreightStationConstants {

    /**--------------------------------------------- Freight Station IDs -------------------------------------------**/
    public static final String MADISON_FREIGHT_ID = "FSMDN";
    public static final String MILWAUKEE_FREIGHT_ID = "FSMKE";
    public static final String FOND_DU_LAC_FREIGHT_ID = "FSFDL";
    public static final String GREEN_BAY_FREIGHT_ID = "FSGBW";
    public static final String LA_CROSSE_FREIGHT_ID = "FSLCS";
    public static final String EAU_CLAIRE_FREIGHT_ID = "FSECL";
    public static final String SUPERIOR_FREIGHT_ID = "FSSUP";
    public static final String WAUSAU_FREIGHT_ID = "FSWSA";

    /**--------------------------------------------- Freight Station Names -----------------------------------------**/
    public static final String MADISON_FREIGHT_NAME = "Madison Freight Yard";
    public static final String MILWAUKEE_FREIGHT_NAME = "Milwaukee Freight Yard";
    public static final String FOND_DU_LAC_FREIGHT_NAME = "Fond du Lac Freight Yard";
    public static final String GREEN_BAY_FREIGHT_NAME = "Green Bay Freight Yard";
    public static final String LA_CROSSE_FREIGHT_NAME = "La Crosse Freight Yard";
    public static final String EAU_CLAIRE_FREIGHT_NAME = "Eau Claire Freight Yard";
    public static final String SUPERIOR_FREIGHT_NAME = "Superior Freight Yard";
    public static final String WAUSAU_FREIGHT_NAME = "Wausau Freight Yard";

    /**------------------------------------------ Freight Station Arrays -------------------------------------------**/
    public static final String[] REGION_ONE_FREIGHT_STATION_IDS = {MADISON_FREIGHT_ID, MILWAUKEE_FREIGHT_ID};
    public static final String[] REGION_TWO_FREIGHT_STATION_IDS = {FOND_DU_LAC_FREIGHT_ID, GREEN_BAY_FREIGHT_ID};
    public static final String[] REGION_THREE_FREIGHT_STATION_IDS = {LA_CROSSE_FREIGHT_ID, EAU_CLAIRE_FREIGHT_ID};
    public static final String[] REGION_FOUR_FREIGHT_STATION_IDS = {SUPERIOR_FREIGHT_ID, WAUSAU_FREIGHT_ID};

    /**-------------------------------------------- Freight Stations -----------------------------------------------**/
    //Region 1 Stations
    public static final FreightStation MADISON_FREIGHT_STATION = new FreightStation(MADISON_FREIGHT_ID, MADISON_FREIGHT_NAME,
        CityConstants.MADISON_ID, CityConstants.REGION_ONE_ID);
    public static final FreightStation MILWAUKEE_FREIGHT_STATION = new FreightStation(MILWAUKEE_FREIGHT_ID, MILWAUKEE_FREIGHT_NAME,
        CityConstants.MILWAUKEE_ID, CityConstants.REGION_ONE_ID);

    //Region 2 Stations
    public static final FreightStation FOND_DU_LAC_FREIGHT_STATION = new FreightStation(FOND_DU_LAC_FREIGHT_ID, FOND_DU_LAC_FREIGHT_NAME,
        CityConstants.FOND_DU_LAC_ID, CityConstants.REGION_TWO_ID);
    public static final FreightStation GREEN_BAY_FREIGHT_STATION = new FreightStation(GREEN_BAY_FREIGHT_ID, GREEN_BAY_FREIGHT_NAME,
        CityConstants.GREEN_BAY_ID, CityConstants.REGION_TWO_ID);

    //Region 3 Stations
    public static final FreightStation LA_CROSSE_FREIGHT_STATION = new FreightStation(LA_CROSSE_FREIGHT_ID, LA_CROSSE_FREIGHT_NAME,
        CityConstants.LA_CROSSE_ID, CityConstants.REGION_THREE_ID);
    public static final FreightStation EAU_CLAIRE_FREIGHT_STATION = new FreightStation(EAU_CLAIRE_FREIGHT_ID, EAU_CLAIRE_FREIGHT_NAME,
        CityConstants.EAU_CLAIRE_ID, CityConstants.REGION_THREE_ID);

    //Region 4 Stations
    public static final FreightStation SUPERIOR_FREIGHT_STATION = new FreightStation(SUPERIOR_FREIGHT_ID, SUPERIOR_FREIGHT_NAME,
        CityConstants.SUPERIOR_ID, CityConstants.REGION_FOUR_ID);
    public static final FreightStation WAUSAU_FREIGHT_STATION = new FreightStation(WAUSAU_FREIGHT_ID, WAUSAU_FREIGHT_NAME,
        CityConstants.WAUSAU_ID, CityConstants.REGION_FOUR_ID);

    /**-------------------------------------- Freight Station Region Maps ------------------------------------------**/
    //Region 1 Map
    public static final Map<String, FreightStation> REGION_ONE_FREIGHT_STATION_MAP = new HashMap<>();
    static{
        REGION_ONE_FREIGHT_STATION_MAP.put(MADISON_FREIGHT_ID, MADISON_FREIGHT_STATION);
        REGION_ONE_FREIGHT_STATION_MAP.put(MILWAUKEE_FREIGHT_ID, MILWAUKEE_FREIGHT_STATION);
    }

    //Region 2 Map
    public static final Map<String, FreightStation> REGION_TWO_FREIGHT_STATION_MAP = new HashMap<>();
    static{
        REGION_TWO_FREIGHT_STATION_MAP.put(FOND_DU_LAC_FREIGHT_ID, FOND_DU_LAC_FREIGHT_STATION);
        REGION_TWO_FREIGHT_STATION_MAP.put(GREEN_BAY_FREIGHT_ID, GREEN_BAY_FREIGHT_STATION);
    }

    //Region 3 Map
    public static final Map<String, FreightStation> REGION_THREE_FREIGHT_STATION_MAP = new HashMap<>();
    static{
        REGION_THREE_FREIGHT_STATION_MAP.put(LA_CROSSE_FREIGHT_ID, LA_CROSSE_FREIGHT_STATION);
        REGION_THREE_FREIGHT_STATION_MAP.put(EAU_CLAIRE_FREIGHT_ID, EAU_CLAIRE_FREIGHT_STATION);
    }

    //Region 4 Map
    public static final Map<String, FreightStation> REGION_FOUR_FREIGHT_STATION_MAP = new HashMap<>();
    static{
        REGION_FOUR_FREIGHT_STATION_MAP.put(SUPERIOR_FREIGHT_ID, SUPERIOR_FREIGHT_STATION);
        REGION_FOUR_FREIGHT_STATION_MAP.put(WAUSAU_FREIGHT_ID, WAUSAU_FREIGHT_STATION);
    }
}
