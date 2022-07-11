package com.lendingwork.supermarket.service;

import com.lendingwork.supermarket.domain.ItemInfo;

import java.util.Map;

public interface LoadItemsService {

    public Map<String, ItemInfo> loadPrices();
}
