package com.GWShop.gwshop.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ItemEditor {

    private final String name;
    private final int price;
    private final int stock;

    @Builder
    public ItemEditor(String name, int price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
}
