package com.GWShop.gwshop.service;

import com.GWShop.gwshop.controller.form.OrderForm;
import com.GWShop.gwshop.controller.form.OrderItemForm;
import com.GWShop.gwshop.domain.Item;
import com.GWShop.gwshop.domain.Member;
import com.GWShop.gwshop.domain.Order;
import com.GWShop.gwshop.repository.ItemRepository;
import com.GWShop.gwshop.repository.MemberRepository;
import com.GWShop.gwshop.repository.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderServiceTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        memberRepository.deleteAll();
        itemRepository.deleteAll();
        orderRepository.deleteAll();
    }

    @Test
    @DisplayName("주문")
    @Transactional
    void test1() {

        Member member = Member.builder()
                .nickname("123")
                .loginId("123456789")
                .password("12345678")
                .build();

        memberRepository.save(member);

        Item item = Item.builder()
                .name("셔츠")
                .price(10000)
                .stock(10)
                .build();

        Long saveId = itemRepository.save(item).getId();

        OrderItemForm orderItemForm = OrderItemForm.builder()
                .id(saveId)
                .quantity(1)
                .build();

        List<OrderItemForm> orderItemFormList = new ArrayList<>();
        orderItemFormList.add(orderItemForm);

        OrderForm orderForm = OrderForm.builder()
                .orderItemFormList(orderItemFormList)
                .build();


        orderService.order(orderForm.getOrderItemFormList(), member);

        Order order = orderRepository.findAll().get(0);

        Assertions.assertThat(order.getOrderItems().get(0).getQuantity()).isEqualTo(orderItemForm.getQuantity());
        Assertions.assertThat(order.getMember().getId()).isEqualTo(member.getId());
        Assertions.assertThat(order.getOrderItems().get(0).getItem().getName()).isEqualTo(item.getName());

    }

    @Test
    @DisplayName("삭제")
    void test2() {
        Member member = Member.builder()
                .nickname("123")
                .loginId("123456789")
                .password("12345678")
                .build();

        memberRepository.save(member);

        Item item = Item.builder()
                .name("셔츠")
                .price(10000)
                .stock(10)
                .build();

        Long saveId = itemRepository.save(item).getId();

        OrderItemForm orderItemForm = OrderItemForm.builder()
                .id(saveId)
                .quantity(1)
                .build();

        List<OrderItemForm> orderItemFormList = new ArrayList<>();
        orderItemFormList.add(orderItemForm);

        OrderForm orderForm = OrderForm.builder()
                .orderItemFormList(orderItemFormList)
                .build();


        orderService.order(orderForm.getOrderItemFormList(), member);


        Long saveOrder = orderRepository.findAll().get(0).getId();

        orderService.delete(saveOrder);

        Assertions.assertThat(orderRepository.count()).isEqualTo(0);

    }

}