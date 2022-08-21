package com.GWShop.gwshop.repository;

import com.GWShop.gwshop.controller.form.CartSearchForm;
import com.GWShop.gwshop.domain.Cart;

import java.util.List;

public interface CartRepositoryCustom {
    List<Cart> getList(CartSearchForm cartSearchForm);
}
