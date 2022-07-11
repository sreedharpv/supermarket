package com.lendingwork.supermarket.domain;

import lombok.Data;

@Data
public class ItemPriceProcessor {
    private String item;
    private int unitPrice;
    private int itemTotalPrice;
    private Boolean offerProcessed = false;
}
