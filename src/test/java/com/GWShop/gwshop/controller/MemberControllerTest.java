package com.GWShop.gwshop.controller;

import com.GWShop.gwshop.controller.form.MemberJoinForm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

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





}