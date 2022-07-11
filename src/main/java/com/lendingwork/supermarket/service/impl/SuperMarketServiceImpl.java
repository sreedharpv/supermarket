package com.lendingwork.supermarket.service.impl;

import com.lendingwork.supermarket.domain.ItemInfo;
import com.lendingwork.supermarket.domain.ItemPriceProcessor;
import com.lendingwork.supermarket.helper.OfferDetail;
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
    public int calculatePrice(List<String> checkOutItems) {

        Map<String, ItemInfo> itemToItemInfo = loadItemsService.loadPrices();
        return calculateTotalPrice(checkOutItems, itemToItemInfo);
    }

    private int calculateTotalPrice(List<String> checkOutItems, Map<String, ItemInfo> itemToItemInfo) {
        List<ItemPriceProcessor> itemPriceProcessorLst
                = new ArrayList<>();
        for (String checkOutItem : checkOutItems) {
            processPrice(checkOutItem, itemToItemInfo, itemPriceProcessorLst);
        }
        //itemPriceProcessorLst.stream()
        //        .forEach(System.out::println);
        int totalPrice = itemPriceProcessorLst.stream()
                .mapToInt(itemPriceProcessor -> itemPriceProcessor.getItemTotalPrice())
                .sum();
        return totalPrice;
    }

    private void processPrice(String inputItem, Map<String, ItemInfo> itemToItemInfo, List<ItemPriceProcessor> itemPriceProcessorLst) {
        ItemInfo itemInfo = itemToItemInfo.get(inputItem);
        String offer = itemInfo.getOffer();
        OfferDetail offerDetail = OfferDetail.getOfferDetail(offer);

        offerDetail.priceAndOfferCalculation(itemInfo, itemPriceProcessorLst);

        /*ItemPriceProcessor itemPriceProcessor = new ItemPriceProcessor();
        itemPriceProcessor.setItem(itemInfo.getItem());
        itemPriceProcessor.setUnitPrice(itemInfo.getUnitPrice());
        itemPriceProcessor.setItemTotalPrice(itemInfo.getUnitPrice());
        itemPriceProcessorLst.add(itemPriceProcessor);*/
    }
}
