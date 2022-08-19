package com.GWShop.gwshop.controller.form;

import lombok.Getter;

import javax.validation.constraints.Min;

@Getter
public class OrderItemForm {

    private Long id;

    @Min(value = 1, message = "1개보다 적게 주문할 수 없습니다.")
    private int quantity;

}
