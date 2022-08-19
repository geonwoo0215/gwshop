package com.GWShop.gwshop.controller.form;


import lombok.Getter;

import javax.validation.constraints.Min;
import java.util.List;

@Getter
public class OrderForm {

    private List<OrderItemForm> orderItemFormList;

}
