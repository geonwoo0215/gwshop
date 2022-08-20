package com.GWShop.gwshop.controller;

import com.GWShop.gwshop.controller.form.OrderForm;
import com.GWShop.gwshop.controller.form.OrderItemForm;
import com.GWShop.gwshop.domain.Item;
import com.GWShop.gwshop.domain.Member;
import com.GWShop.gwshop.repository.ItemRepository;
import com.GWShop.gwshop.repository.MemberRepository;
import com.GWShop.gwshop.repository.OrderRepository;
import com.GWShop.gwshop.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    private MockHttpSession mockHttpSession;


    @BeforeEach
    void setUp() {
        mockHttpSession = new MockHttpSession();
    }

    @Test
    @DisplayName("주문")
    void test1() throws Exception {

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

        String json = objectMapper.writeValueAsString(orderForm);

        mockMvc.perform(MockMvcRequestBuilders.post("/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .session(mockHttpSession))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    @DisplayName("주문 삭제")
    void test2() throws Exception {

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

        Long saveOrder = orderRepository.findAll().get(0).getId();

        orderService.order(orderForm.getOrderItemFormList(), member);

        mockMvc.perform(MockMvcRequestBuilders.delete("/order/{orderId}", saveOrder)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}