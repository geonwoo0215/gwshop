package com.GWShop.gwshop.controller.form;

import com.GWShop.gwshop.domain.CartItem;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartResponseForm {

    private List<CartItem> cartItemList;

    @Builder
    public CartResponseForm(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }
}
