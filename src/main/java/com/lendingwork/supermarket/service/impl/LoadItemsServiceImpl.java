package com.lendingwork.supermarket.service.impl;

import com.lendingwork.supermarket.domain.ItemInfo;
import com.lendingwork.supermarket.service.LoadItemsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * LoadItemsService loads the CSV file and split the SKU Items, prices and offers
 */

@Service
public class LoadItemsServiceImpl implements LoadItemsService {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    ResourceLoader resourceLoader;

    private static final String PRICE_FILE_NAME = "classpath:item-prices.csv";
    private static final String CSV_FILE_SEPARATOR = ",";

    /**
     * This method loads the CSV file and generate the Map with Item as key and Item Information as Value
     * @return Map<String, ItemInfo>
     */

    @Override
    public Map<String, ItemInfo> loadPrices() {
        Map<String, ItemInfo> itemToItemInfo = new HashMap<>();

        return itemToItemInfo;
    }

}
