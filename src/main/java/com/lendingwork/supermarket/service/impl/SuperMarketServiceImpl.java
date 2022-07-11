package com.lendingwork.supermarket.service.impl;

import com.lendingwork.supermarket.domain.ItemInfo;
import com.lendingwork.supermarket.domain.ItemPriceProcessor;
import com.lendingwork.supermarket.service.LoadItemsService;
import com.lendingwork.supermarket.service.SuperMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SuperMarketServiceImpl implements SuperMarketService {

    @Autowired
    LoadItemsService loadItemsService;

    @Override
    public int calculatePrice(List<String> items) {

        Map<String, ItemInfo> itemToItemInfo = loadItemsService.loadPrices();
        return calculateTotalPrice(items, itemToItemInfo);
    }

    private int calculateTotalPrice(List<String> inputItems, Map<String, ItemInfo> itemToItemInfo) {

        return 0;
    }
}
