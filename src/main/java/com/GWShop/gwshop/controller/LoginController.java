package com.GWShop.gwshop.controller;

import com.GWShop.gwshop.controller.form.MemberJoinForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Slf4j
@Controller
public class LoginController {

    @ResponseBody
    @GetMapping("/member")
    public String login() {
        return "hello";
    }

    @ResponseBody
    @PostMapping("/member")
    public String join(@Valid @RequestBody MemberJoinForm memberJoinForm) {
        log.info("memberJoinForm={}",memberJoinForm.toString());
        return "hello";
    }
}
