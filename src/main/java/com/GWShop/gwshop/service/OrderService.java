package com.GWShop.gwshop.service;

import com.GWShop.gwshop.controller.form.OrderForm;
import com.GWShop.gwshop.controller.form.OrderItemForm;
import com.GWShop.gwshop.domain.Item;
import com.GWShop.gwshop.domain.Member;
import com.GWShop.gwshop.domain.Order;
import com.GWShop.gwshop.domain.OrderItem;
import com.GWShop.gwshop.repository.ItemRepository;
import com.GWShop.gwshop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;


    public void order(List<OrderItemForm> orderItemFormList, Member member) {

        List<OrderItem> orderItemList = new ArrayList<>();
        for (OrderItemForm orderItemForm : orderItemFormList) {
            Item item = itemRepository.findById(orderItemForm.getId())
                    .orElseThrow(() -> new IllegalArgumentException("없는 상품 입니다."));

            OrderItem orderItem = OrderItem.builder()
                    .item(item)
                    .quantity(orderItemForm.getQuantity())
                    .build();

            orderItemList.add(orderItem);
        }

        Order order = Order.builder()
                .member(member)
                .orderItems(orderItemList)
                .build();

        orderRepository.save(order);
    }

}
