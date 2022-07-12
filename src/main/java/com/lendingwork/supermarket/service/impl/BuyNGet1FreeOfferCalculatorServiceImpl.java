package com.lendingwork.supermarket.service.impl;

import com.lendingwork.supermarket.domain.ItemInfo;
import com.lendingwork.supermarket.domain.ItemPriceProcessor;
import com.lendingwork.supermarket.helper.OfferProcessHelper;
import com.lendingwork.supermarket.service.BuyNGet1FreeOfferCalculatorService;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * This class process Buy N and get 1 Free Offer. This logic works for 1 to 5 items.
 */
@Service
public class BuyNGet1FreeOfferCalculatorServiceImpl implements BuyNGet1FreeOfferCalculatorService {

    Map<String, Integer> wordToNumber = new HashMap<>(){{
        put("one", 1);put("two", 2);put("three", 3);put("four", 4);put("five", 5);
    }};

    /**
     * process the Buy N Get 1 offer
     * @param itemInfo
     * @param itemPriceProcessorLst
     */
    public void priceOfferCalculator(ItemInfo itemInfo, List<ItemPriceProcessor> itemPriceProcessorLst) {
        List<ItemPriceProcessor> unProcessedItemPriceProcessorLst =
                getOfferUnProcessedItems(itemInfo, itemPriceProcessorLst);

        //Buy 3, get one free
        String offerForItem = itemInfo.getOffer();
        String[] offerInfoArr = offerForItem.split("\\s|,");
        int offerItemsCount = Integer.parseInt(offerInfoArr[1]);
        int freeItemTotalCount = wordToNumber.get(offerInfoArr[3]);

        if (unProcessedItemPriceProcessorLst.size() < offerItemsCount-1) {
            ItemPriceProcessor itemPriceProcessor = new ItemPriceProcessor();
            itemPriceProcessor.setItem(itemInfo.getItem());
            itemPriceProcessor.setUnitPrice(itemInfo.getUnitPrice());
            itemPriceProcessor.setItemTotalPrice(itemInfo.getUnitPrice());
            itemPriceProcessorLst.add(itemPriceProcessor);
        } else {
            int freeItemCount = 1;
            for(ItemPriceProcessor itemPriceProcessor : unProcessedItemPriceProcessorLst) {
                itemPriceProcessor.setOfferProcessed(true);
                if (freeItemTotalCount != freeItemCount) {
                    freeItemCount++;
                    itemPriceProcessor.setItemTotalPrice(0);
                }
            }
            ItemPriceProcessor itemPriceProcessor = new ItemPriceProcessor();
            itemPriceProcessor.setItem(itemInfo.getItem());
            itemPriceProcessor.setUnitPrice(itemInfo.getUnitPrice());
            itemPriceProcessor.setItemTotalPrice(0);
            itemPriceProcessor.setOfferProcessed(true);
            unProcessedItemPriceProcessorLst.add(itemPriceProcessor);
        }
        itemPriceProcessorLst.addAll(unProcessedItemPriceProcessorLst);
    }

    private List<ItemPriceProcessor> getOfferUnProcessedItems(ItemInfo itemInfo, List<ItemPriceProcessor> itemPriceProcessorLst) {
        List<ItemPriceProcessor> unProcessedItemPriceProcessorLst = new ArrayList<>();
        Iterator<ItemPriceProcessor> iterator = itemPriceProcessorLst.iterator();
        while (iterator.hasNext()) {
            ItemPriceProcessor itemPriceProcessor = iterator.next();
            if (itemPriceProcessor.getItem().equalsIgnoreCase(itemInfo.getItem()) &&
                    !itemPriceProcessor.getOfferProcessed()) {
                unProcessedItemPriceProcessorLst.add(itemPriceProcessor);
                iterator.remove();
            }
        }
        return unProcessedItemPriceProcessorLst;
    }
}