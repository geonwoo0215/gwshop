package com.GWShop.gwshop.repository;

import com.GWShop.gwshop.controller.form.CartSearchForm;
import com.GWShop.gwshop.domain.Cart;
import com.GWShop.gwshop.domain.QCart;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CartSearchFormImpl implements CartRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Cart> getList(CartSearchForm cartSearchForm) {
        return jpaQueryFactory.selectFrom(QCart.cart)
                .limit(cartSearchForm.getSize())
                .offset(cartSearchForm.getOffset())
                .orderBy(QCart.cart.id.desc())
                .fetch();
    }
}
