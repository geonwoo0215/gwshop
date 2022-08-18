package com.GWShop.gwshop.controller;

import com.GWShop.gwshop.controller.form.LoginForm;
import com.GWShop.gwshop.controller.form.MemberJoinForm;
import com.GWShop.gwshop.domain.Member;
import com.GWShop.gwshop.repository.MemberRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MemberRepository memberRepository;

    private MockHttpSession mockHttpSession;

    @BeforeEach
    void setUp() {
        memberRepository.deleteAll();
        mockHttpSession = new MockHttpSession();
    }

    @Test
    @DisplayName("가입 정보 정상 전달")
    void test1() throws Exception {

        MemberJoinForm memberJoinForm = MemberJoinForm.builder()
                .nickname("nickname")
                .loginId("12345678")
                .password("12345678")
                .build();

        String json = objectMapper.writeValueAsString(memberJoinForm);

        mockMvc.perform(MockMvcRequestBuilders.post("/member")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("닉네임 검증 실패")
    void test2() throws Exception {

        MemberJoinForm memberJoinForm = MemberJoinForm.builder()
                .nickname("1")
                .loginId("12345678")
                .password("12345678")
                .build();

        String json = objectMapper.writeValueAsString(memberJoinForm);

        mockMvc.perform(MockMvcRequestBuilders.post("/member")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("아이디 검증 실패")
    void test3() throws Exception {

        MemberJoinForm memberJoinForm = MemberJoinForm.builder()
                .nickname("nickname")
                .loginId("1")
                .password("12345678")
                .build();

        String json = objectMapper.writeValueAsString(memberJoinForm);

        mockMvc.perform(MockMvcRequestBuilders.post("/member")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("비밀번호 검증 실패")
    void test4() throws Exception {

        MemberJoinForm memberJoinForm = MemberJoinForm.builder()
                .nickname("nickname")
                .loginId("12345678")
                .password("1")
                .build();

        String json = objectMapper.writeValueAsString(memberJoinForm);

        mockMvc.perform(MockMvcRequestBuilders.post("/member")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("회원 로그인")
    @Transactional
    void test5() throws Exception {

        Member member = Member.builder()
                .nickname("nickname")
                .loginId("12345678")
                .password("12345678")
                .build();

        memberRepository.save(member);


        LoginForm loginForm = LoginForm.builder()
                .loginId("12345678")
                .password("12345678")
                .build();

        String json = objectMapper.writeValueAsString(loginForm);

        mockMvc.perform(MockMvcRequestBuilders.post("/member/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .session(mockHttpSession))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());


        Assertions.assertThat(mockHttpSession.getAttribute(member.getLoginId())).isEqualTo(member);


    }

    @Test
    @DisplayName("회원 로그인 실패")
    void test6() throws Exception {

        Member member = Member.builder()
                .nickname("nickname")
                .loginId("12345678")
                .password("12345678")
                .build();

        memberRepository.save(member);


        LoginForm loginForm = LoginForm.builder()
                .loginId("1234567")
                .password("12345678")
                .build();

        String json = objectMapper.writeValueAsString(loginForm);

        mockMvc.perform(MockMvcRequestBuilders.post("/member/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .session(mockHttpSession))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());



    }

    @Test
    @DisplayName("회원 로그아웃")
    void test7() throws Exception {

        Member member = Member.builder()
                .nickname("nickname")
                .loginId("12345678")
                .password("12345678")
                .build();

        memberRepository.save(member);

        mockMvc.perform(MockMvcRequestBuilders.post("/member/logout")
                        .contentType(MediaType.APPLICATION_JSON)
                        .session(mockHttpSession)
                        .sessionAttr(member.getLoginId(),member))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        Assertions.assertThat(mockHttpSession.isInvalid()).isEqualTo(true);


    }





}