package com.lendingwork.supermarket.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemInfo implements Serializable {
    private static final long serialVersionUID = -12232234531234324L;

    private String item;
    private int unitPrice;
    private String offer;
}
