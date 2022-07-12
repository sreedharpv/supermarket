package com.lendingwork.supermarket.helper;

import com.lendingwork.supermarket.domain.ItemInfo;
import com.lendingwork.supermarket.domain.ItemPriceProcessor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OfferProcessHelper {

    public static List<ItemPriceProcessor> getOfferUnProcessedItems(String offerItems1, String offerItems2, List<ItemPriceProcessor> itemPriceProcessorLst) {
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
