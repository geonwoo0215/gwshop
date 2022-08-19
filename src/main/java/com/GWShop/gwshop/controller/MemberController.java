package com.GWShop.gwshop.controller;

import com.GWShop.gwshop.controller.form.LoginForm;
import com.GWShop.gwshop.controller.form.MemberJoinForm;
import com.GWShop.gwshop.domain.Member;
import com.GWShop.gwshop.service.MemberService;
import com.GWShop.gwshop.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @ResponseBody
    @GetMapping("/member")
    public String joinForm() {
        return "hello";
    }

    @ResponseBody
    @PostMapping("/member")
    public String join(@Valid @RequestBody MemberJoinForm memberJoinForm) {
        log.info("memberJoinForm={}", memberJoinForm.toString());

        memberService.join(memberJoinForm.toMember());

        return "hello";
    }

    @ResponseBody
    @GetMapping("/member/login")
    public String loginForm() {
        return "hello";
    }

    @ResponseBody
    @PostMapping("/member/login")
    public void login(@Valid @RequestBody LoginForm loginForm, HttpServletRequest request) {
        log.info("loginForm={}", loginForm.toString());
        Member member = memberService.login(loginForm.getLoginId(), loginForm.getPassword());
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, member);
    }

    @ResponseBody
    @PostMapping("/member/logout")
    public void logout(HttpServletRequest request) {
        log.info("로그아웃");
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

    }

}
