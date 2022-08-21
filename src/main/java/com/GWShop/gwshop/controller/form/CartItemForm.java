package com.GWShop.gwshop.controller.form;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartItemForm {

    private Long id;

    @Min(value = 1, message = "1개보다 적게 담을 수 없습니다.")
    private int quantity;

    @Builder
    public CartItemForm(Long id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }
}
