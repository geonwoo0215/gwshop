package com.GWShop.gwshop.controller.form;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartOrderForm {

    private List<CartItemForm> cartItemFormList;

    @Builder
    public CartOrderForm(List<CartItemForm> cartItemFormList) {
        this.cartItemFormList = cartItemFormList;
    }
}
