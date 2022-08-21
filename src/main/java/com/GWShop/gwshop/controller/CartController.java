package com.GWShop.gwshop.controller;

import com.GWShop.gwshop.controller.form.CartItemForm;
import com.GWShop.gwshop.controller.form.CartOrderForm;
import com.GWShop.gwshop.controller.form.CartResponseForm;
import com.GWShop.gwshop.controller.form.CartSearchForm;
import com.GWShop.gwshop.domain.Member;
import com.GWShop.gwshop.service.CartService;
import com.GWShop.gwshop.session.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;


    @ResponseBody
    @PostMapping("/cart")
    public void add(@RequestBody @Valid CartItemForm cartItemForm, HttpServletRequest request) {

        HttpSession session = request.getSession();

        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        cartService.add(cartItemForm, member);

    }

    @ResponseBody
    @GetMapping("/cart")
    public List<CartResponseForm> getList(@ModelAttribute CartSearchForm cartSearchForm) {
        return cartService.list(cartSearchForm);
    }

    @ResponseBody
    @DeleteMapping("/cart/{cartId}")
    public void delete(@PathVariable Long cartId) {
        cartService.delete(cartId);
    }

    @ResponseBody
    @PostMapping("/cart/order")
    public String order(@RequestBody CartOrderForm cartOrderForm, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("orderForm", cartService.toOrderForm(cartOrderForm));
        return "redirect:/order";
    }
}

