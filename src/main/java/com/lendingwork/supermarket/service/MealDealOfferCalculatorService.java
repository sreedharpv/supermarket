package com.lendingwork.supermarket.service;

import com.lendingwork.supermarket.domain.ItemInfo;
import com.lendingwork.supermarket.domain.ItemPriceProcessor;

import java.util.List;

public interface MealDealOfferCalculatorService {

    public void priceOfferCalculator(ItemInfo itemInfo, List<ItemPriceProcessor> itemPriceProcessorLst);

}
