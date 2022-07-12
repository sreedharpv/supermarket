package com.lendingwork.supermarket.controller;

import java.util.function.Function;

public abstract class AbstractSuperMarketController {
    public static final String CALCULATE_PRICE = "/calculate-price";

    public static final Function<String, Boolean> isEmptyString = strValue ->
            (strValue == null || strValue.trim().isEmpty()) ?
                    Boolean.TRUE: Boolean.FALSE;

    static final String ITEMS_VALIDATION_MESSAGE = "Items can not be null";
}
