package com.GWShop.gwshop.service;

import com.GWShop.gwshop.controller.form.*;
import com.GWShop.gwshop.domain.*;
import com.GWShop.gwshop.repository.CartRepository;
import com.GWShop.gwshop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;

    public void add(CartItemForm cartItemForm, Member member) {

        Cart cart = member.getCart();

        Item item = itemRepository.findById(cartItemForm.getId())
                .orElseThrow(() -> new IllegalArgumentException("없는 상품 입니다."));

        CartItem cartItem = CartItem.builder()
                .item(item)
                .quantity(cartItemForm.getQuantity())
                .build();

        cartItem.changeOrder(cart);

        cartRepository.save(cart);
    }

    public OrderForm toOrderForm(CartOrderForm cartOrderForm) {
        List<CartItemForm> cartItemFormList = cartOrderForm.getCartItemFormList();
        List<OrderItemForm> orderItemFormList = new LinkedList<>();
        for (CartItemForm cartItemForm : cartItemFormList) {
            OrderItemForm orderItemForm = OrderItemForm.builder()
                    .id(cartItemForm.getId())
                    .quantity(cartItemForm.getQuantity())
                    .build();

            orderItemFormList.add(orderItemForm);
        }
        return OrderForm.builder()
                .orderItemFormList(orderItemFormList)
                .build();
    }

    public List<CartResponseForm> list(CartSearchForm cartSearchForm) {
        return cartRepository.getList(cartSearchForm).stream().map(Cart::toCartResponseForm).collect(Collectors.toList());
    }

    public void delete(Long id) {
        Cart cart = cartRepository.getById(id);
        cartRepository.delete(cart);
    }


}
