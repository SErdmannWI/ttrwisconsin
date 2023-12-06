package com.serdmannwi.practiceprograms.tickettoridewisconsin.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductConstants {

    /**--------------------------------------------------- Names ---------------------------------------------------**/
    public static final String PRODUCT_BEER_NAME = "Beer";
    public static final String PRODUCT_CHEESE_NAME = "Cheese";
    public static final String PRODUCT_CORN_NAME = "Corn";
    public static final String PRODUCT_CRANBERRIES_NAME = "Cranberries";
    public static final String PRODUCT_FORESTRY_NAME = "Forestry Products";
    public static final String PRODUCT_MACHINERY_NAME = "Machinery";
    public static final String PRODUCT_MAPLE_SYRUP_NAME = "Maple Syrup";
    public static final String PRODUCT_MILK_NAME = "Milk";
    public static final String PRODUCT_PAPER_NAME = "Paper Products";
    public static final String PRODUCT_SAND_NAME = "Sand";
    public static final String PRODUCT_SAUSAGE_NAME = "Sausage";
    public static final String PRODUCT_WILD_RICE_NAME = "Wild Rice";

    /**--------------------------------------------------- IDs ---------------------------------------------------**/
    public static final String PRODUCT_BEER_ID = "BEER";
    public static final String PRODUCT_CHEESE_ID = "CHES";
    public static final String PRODUCT_CORN_ID = "CORN";
    public static final String PRODUCT_CRANBERRIES_ID = "CRAN";
    public static final String PRODUCT_FORESTRY_ID = "TREE";
    public static final String PRODUCT_MAPLE_SYRUP_ID = "MSYP";
    public static final String PRODUCT_MACHINERY_ID = "MCHN";
    public static final String PRODUCT_MILK_ID = "MILK";
    public static final String PRODUCT_PAPER_ID = "PAPR";
    public static final String PRODUCT_SAND_ID = "SAND";
    public static final String PRODUCT_SAUSAGE_ID = "SAUS";
    public static final String PRODUCT_WILD_RICE_ID = "WLDR";

    /**-------------------------------------------- Region Product Lists -------------------------------------------**/
    //Southern Wisconsin
    public static final List<String> REGION_ONE_PRODUCTS_IDS = new ArrayList<>(Arrays.asList(PRODUCT_BEER_ID,
        PRODUCT_MACHINERY_ID, PRODUCT_SAUSAGE_ID));
    //Fox Valley
    public static final List<String> REGION_TWO_PRODUCTS_IDS = new ArrayList<>(Arrays.asList(PRODUCT_CORN_ID,
        PRODUCT_MILK_ID, PRODUCT_PAPER_ID));
    //Mississippi River/ Driftless Area
    public static final List<String> REGION_THREE_PRODUCTS_IDS = new ArrayList<>(Arrays.asList(PRODUCT_CHEESE_ID,
        PRODUCT_CRANBERRIES_ID, PRODUCT_SAND_ID));
    //Northern Wisconsin
    public static final List<String> REGION_FOUR_PRODUCTS_IDS = new ArrayList<>(Arrays.asList(PRODUCT_FORESTRY_ID,
        PRODUCT_MAPLE_SYRUP_ID, PRODUCT_WILD_RICE_ID));
    //All Products
    public static final List<String> ALL_PRODUCTS = new ArrayList<>(Arrays.asList(PRODUCT_BEER_ID, PRODUCT_CHEESE_ID,
        PRODUCT_CORN_ID, PRODUCT_CRANBERRIES_ID, PRODUCT_FORESTRY_ID, PRODUCT_MAPLE_SYRUP_ID, PRODUCT_MACHINERY_ID,
        PRODUCT_MILK_ID, PRODUCT_PAPER_ID, PRODUCT_SAND_ID, PRODUCT_SAUSAGE_ID, PRODUCT_WILD_RICE_ID));
}
