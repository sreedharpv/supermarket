package com.lendingwork.supermarket.service.impl;

import com.lendingwork.supermarket.domain.ItemInfo;
import com.lendingwork.supermarket.domain.ItemPriceProcessor;
import com.lendingwork.supermarket.service.MultiPricedOfferCalculatorService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class process the multi price offer
 */
@Service
public class MultiPricedOfferCalculatorServiceImpl implements MultiPricedOfferCalculatorService {
    public void priceOfferCalculator(ItemInfo itemInfo, List<ItemPriceProcessor> itemPriceProcessorLst) {
        List<ItemPriceProcessor> unProcessedItemPriceProcessorLst =
                getOfferUnProcessedItems(itemInfo, itemPriceProcessorLst);

        //2 for £1
        String offerForItem = itemInfo.getOffer();
        String[] offerInfoArr = offerForItem.split(" for £");
        int offerItemsCount = Integer.parseInt(offerInfoArr[0]);
        int offerPrice = Integer.parseInt(offerInfoArr[1]);

        if (unProcessedItemPriceProcessorLst.size() < offerItemsCount-1) {
            ItemPriceProcessor itemPriceProcessor = new ItemPriceProcessor();
            itemPriceProcessor.setItem(itemInfo.getItem());
            itemPriceProcessor.setUnitPrice(itemInfo.getUnitPrice());
            itemPriceProcessor.setItemTotalPrice(itemInfo.getUnitPrice());
            itemPriceProcessorLst.add(itemPriceProcessor);
        } else {
            for(ItemPriceProcessor itemPriceProcessor : unProcessedItemPriceProcessorLst) {
                itemPriceProcessor.setItemTotalPrice(0);
                itemPriceProcessor.setOfferProcessed(true);
            }

            ItemPriceProcessor itemPriceProcessor = new ItemPriceProcessor();
            itemPriceProcessor.setItem(itemInfo.getItem());
            itemPriceProcessor.setUnitPrice(itemInfo.getUnitPrice());
            itemPriceProcessor.setItemTotalPrice(offerPrice*100);
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
