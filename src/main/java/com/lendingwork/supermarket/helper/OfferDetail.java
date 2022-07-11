package com.lendingwork.supermarket.helper;

import com.lendingwork.supermarket.domain.ItemInfo;
import com.lendingwork.supermarket.domain.ItemPriceProcessor;
import com.lendingwork.supermarket.service.BuyNGet1FreeOfferCalculatorService;
import com.lendingwork.supermarket.service.MealDealOfferCalculatorService;
import com.lendingwork.supermarket.service.MultiPricedOfferCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.EnumSet;
import java.util.List;

/**
 * This class provides offer details like Multi Buy, Buy 1 Get 1
 */
public enum OfferDetail {

    // No Offer items adding to the list
    NOOFFER{
        @Override
        public void priceAndOfferCalculation(ItemInfo itemInfo, List<ItemPriceProcessor> itemPriceProcessorLst) {
            ItemPriceProcessor itemPriceProcessor = new ItemPriceProcessor();
            itemPriceProcessor.setItem(itemInfo.getItem());
            itemPriceProcessor.setUnitPrice(itemInfo.getUnitPrice());
            itemPriceProcessor.setItemTotalPrice(itemInfo.getUnitPrice());
            itemPriceProcessorLst.add(itemPriceProcessor);
        }
    },

    // Multi priced Offer items adding to the list
    MULTIPRICED {
        //@Autowired
        //MultiPricedOfferCalculatorService multiPricedOfferCalculatorService = new MultiPricedOfferCalculatorServiceImpl();
        @Override
        public void priceAndOfferCalculation(ItemInfo itemInfo, List<ItemPriceProcessor> itemPriceProcessorLst) {
            multiPricedOfferCalculatorService.priceOfferCalculator(itemInfo, itemPriceProcessorLst);
        }
    },
    //Buy 3 Get 1 Free offer
    BUYNGET1FREE {
        /*//@Autowired
        BuyNGet1FreeOfferCalculatorService buyNGet1FreeOfferCalculatorService = new BuyNGet1FreeOfferCalculatorServiceImpl();*/
        @Override
        public void priceAndOfferCalculation(ItemInfo itemInfo, List<ItemPriceProcessor> itemPriceProcessorLst) {
            buyNGet1FreeOfferCalculatorService.priceOfferCalculator(itemInfo, itemPriceProcessorLst);
        }
    },
    // Meal Deal Offer
    MEALDEAL {
        /*//@Autowired
        MealDealOfferCalculatorService mealDealOfferCalculatorService = new MealDealOfferCalculatorServiceImpl();*/
        @Override
        public void priceAndOfferCalculation(ItemInfo itemInfo, List<ItemPriceProcessor> itemPriceProcessorLst) {
            mealDealOfferCalculatorService.priceOfferCalculator(itemInfo, itemPriceProcessorLst);

        }
    };

    MultiPricedOfferCalculatorService multiPricedOfferCalculatorService;
    BuyNGet1FreeOfferCalculatorService buyNGet1FreeOfferCalculatorService;
    MealDealOfferCalculatorService mealDealOfferCalculatorService;

    public static OfferDetail getOfferDetail(String offerDetail) {
        if (offerDetail.contains("and")) {
            return MEALDEAL;
        } else if (offerDetail.contains("for")) {
            return MULTIPRICED;
        } else if (offerDetail.contains("free")) {
            return BUYNGET1FREE;
        } else {
            return NOOFFER;
        }
        /*return Arrays.stream(OfferDetail.values())
                .filter(offerDetails -> offerDetails.name().equalsIgnoreCase(offerDetail))
                .findFirst()
                .get();*/
    }

    public abstract void priceAndOfferCalculation(ItemInfo itemInfo, List<ItemPriceProcessor> itemPriceProcessorLst);

    @Component
    public static class PriceOfferCalculatorInjector {
        @Autowired
        MultiPricedOfferCalculatorService multiPricedPriceOfferCalculator;

        @Autowired
        BuyNGet1FreeOfferCalculatorService buyNGet1FreeOfferCalculatorService;
        @Autowired
        MealDealOfferCalculatorService mealDealOfferCalculatorService;

        @PostConstruct
        public void postConstruct() {
            for( OfferDetail offerDetail : EnumSet.allOf(OfferDetail.class)) {
                offerDetail.setMultiPricedPriceOfferCalculator(multiPricedPriceOfferCalculator);
                offerDetail.setBuyNGet1FreeOfferCalculatorService(buyNGet1FreeOfferCalculatorService);
                offerDetail.setMealDealOfferCalculatorService(mealDealOfferCalculatorService);
            }
        }
    }

    protected void setMultiPricedPriceOfferCalculator(MultiPricedOfferCalculatorService multiPricedOfferCalculatorService) {
        this.multiPricedOfferCalculatorService = multiPricedOfferCalculatorService;
    }

    protected void setBuyNGet1FreeOfferCalculatorService(BuyNGet1FreeOfferCalculatorService buyNGet1FreeOfferCalculatorService) {
        this.buyNGet1FreeOfferCalculatorService = buyNGet1FreeOfferCalculatorService;
    }

    public void setMealDealOfferCalculatorService(MealDealOfferCalculatorService mealDealOfferCalculatorService) {
        this.mealDealOfferCalculatorService = mealDealOfferCalculatorService;
    }
}
