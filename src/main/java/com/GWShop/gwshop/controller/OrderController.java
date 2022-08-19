package com.GWShop.gwshop.controller;

import com.GWShop.gwshop.controller.form.OrderForm;
import com.GWShop.gwshop.controller.form.OrderItemForm;
import com.GWShop.gwshop.domain.Member;
import com.GWShop.gwshop.service.OrderService;
import com.GWShop.gwshop.session.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @ResponseBody
    @PostMapping("/order")
    public void order(@RequestBody @Valid OrderForm orderForm, HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        Member member = (Member)session.getAttribute(SessionConst.LOGIN_MEMBER);

        List<OrderItemForm> orderItemFormList = orderForm.getOrderItemFormList();

        orderService.order(orderItemFormList, member);
    }



}
