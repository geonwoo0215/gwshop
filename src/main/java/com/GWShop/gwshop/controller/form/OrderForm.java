package com.GWShop.gwshop.controller.form;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderForm {

    private List<OrderItemForm> orderItemFormList;

    @Builder
    public OrderForm(List<OrderItemForm> orderItemFormList) {
        this.orderItemFormList = orderItemFormList;
    }
}
