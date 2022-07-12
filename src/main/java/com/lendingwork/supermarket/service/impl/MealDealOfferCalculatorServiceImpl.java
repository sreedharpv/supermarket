package com.lendingwork.supermarket.service.impl;

import com.lendingwork.supermarket.domain.ItemInfo;
import com.lendingwork.supermarket.domain.ItemPriceProcessor;
import com.lendingwork.supermarket.helper.OfferProcessHelper;
import com.lendingwork.supermarket.service.MealDealOfferCalculatorService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class process the Meal Deal offer
 */
@Service
public class MealDealOfferCalculatorServiceImpl implements MealDealOfferCalculatorService {
    public void priceOfferCalculator(ItemInfo itemInfo, List<ItemPriceProcessor> itemPriceProcessorLst) {
        //Buy D and E for £3
        String offerForItem = itemInfo.getOffer();
        String[] offerInfoArr = offerForItem.split("\\s|£");
        String offerItems1 = offerInfoArr[1];
        String offerItems2 = offerInfoArr[3];
        int offerPrice = Integer.parseInt(offerInfoArr[6]);

        List<ItemPriceProcessor> unProcessedItemPriceProcessorLst =
                getOfferUnProcessedItems(offerItems1, offerItems2, itemPriceProcessorLst);

        if (unProcessedItemPriceProcessorLst.isEmpty()) {
            ItemPriceProcessor itemPriceProcessor = new ItemPriceProcessor();
            itemPriceProcessor.setItem(itemInfo.getItem());
            itemPriceProcessor.setUnitPrice(itemInfo.getUnitPrice());
            itemPriceProcessor.setItemTotalPrice(itemInfo.getUnitPrice());
            itemPriceProcessorLst.add(itemPriceProcessor);
        } else {
            boolean mealDealMatchFound = false;
            for(ItemPriceProcessor itemPriceProcessor : unProcessedItemPriceProcessorLst) {
                if(!itemInfo.getItem().equalsIgnoreCase(itemPriceProcessor.getItem())) {
                    mealDealMatchFound = true;
                    itemPriceProcessor.setItemTotalPrice(0);
                    itemPriceProcessor.setOfferProcessed(true);
                    //add given item
                    ItemPriceProcessor givenItemPriceProcessor = new ItemPriceProcessor();
                    givenItemPriceProcessor.setItem(itemInfo.getItem());
                    givenItemPriceProcessor.setUnitPrice(itemInfo.getUnitPrice());
                    givenItemPriceProcessor.setItemTotalPrice(offerPrice*100);
                    givenItemPriceProcessor.setOfferProcessed(true);
                    unProcessedItemPriceProcessorLst.add(givenItemPriceProcessor);
                    break;
                }
            }

            if (!mealDealMatchFound) {
                ItemPriceProcessor itemPriceProcessor = new ItemPriceProcessor();
                itemPriceProcessor.setItem(itemInfo.getItem());
                itemPriceProcessor.setUnitPrice(itemInfo.getUnitPrice());
                itemPriceProcessor.setItemTotalPrice(itemInfo.getUnitPrice());
                itemPriceProcessor.setOfferProcessed(false);
                unProcessedItemPriceProcessorLst.add(itemPriceProcessor);
            }
        }
        itemPriceProcessorLst.addAll(unProcessedItemPriceProcessorLst);
    }

    private List<ItemPriceProcessor> getOfferUnProcessedItems(String offerItems1, String offerItems2, List<ItemPriceProcessor> itemPriceProcessorLst) {
        List<ItemPriceProcessor> unProcessedItemPriceProcessorLst = new ArrayList<>();
        Iterator<ItemPriceProcessor> iterator = itemPriceProcessorLst.iterator();
        while (iterator.hasNext()) {
            ItemPriceProcessor itemPriceProcessor = iterator.next();
            if ((itemPriceProcessor.getItem().equalsIgnoreCase(offerItems1) ||
                    itemPriceProcessor.getItem().equalsIgnoreCase(offerItems1))
                    && !itemPriceProcessor.getOfferProcessed()) {
                unProcessedItemPriceProcessorLst.add(itemPriceProcessor);
                iterator.remove();
            }
        }
        return unProcessedItemPriceProcessorLst;
    }
}
