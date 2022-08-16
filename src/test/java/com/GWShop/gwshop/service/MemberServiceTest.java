package com.GWShop.gwshop.service;

import com.GWShop.gwshop.domain.Member;
import com.GWShop.gwshop.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;

    @BeforeEach
    private void clear() {
        memberRepository.deleteAll();
    }

    @Test
    @DisplayName("회원 저장")
    void test1() {
        //given
        Member member = Member.builder()
                .nickname("123")
                .loginId("123456789")
                .password("12345678")
                .build();

        //when
        memberService.join(member);

        //then
        Assertions.assertThat(memberRepository.count()).isEqualTo(1L);
        Member saveMember = memberRepository.findAll().get(0);
        Assertions.assertThat(saveMember.getNickname()).isEqualTo("123");
        Assertions.assertThat(saveMember.getLoginId()).isEqualTo("123456789");
        Assertions.assertThat(saveMember.getPassword()).isEqualTo("12345678");

    }

    @Test
    @DisplayName("로그인")
    void test2() {

        //given
        Member member = Member.builder()
                .nickname("123")
                .loginId("123456789")
                .password("12345678")
                .build();

        //when
        memberService.join(member);
        Member loginMember = memberService.login(member.getLoginId(), member.getPassword());

        //then
        Assertions.assertThat(loginMember.getNickname()).isEqualTo("123");
        Assertions.assertThat(loginMember.getLoginId()).isEqualTo("123456789");
        Assertions.assertThat(loginMember.getPassword()).isEqualTo("12345678");

    }

    @Test
    @DisplayName("아이디 오류")
    void test3() {

        //given
        Member member = Member.builder()
                .nickname("123")
                .loginId("123456789")
                .password("12345678")
                .build();

        memberService.join(member);

        //expected
        Assertions.assertThatThrownBy(() -> memberService.login("987654321", member.getPassword()))
                .isInstanceOf(IllegalArgumentException.class).hasMessage("로그인 정보 시스템에 있는 계정과 일치하지 않습니다.");

    }

    @Test
    @DisplayName("비밀번호 오류")
    void test4() {

        //given
        Member member = Member.builder()
                .nickname("123")
                .loginId("123456789")
                .password("12345678")
                .build();

        memberService.join(member);

        //expected
        Assertions.assertThatThrownBy(() -> memberService.login(member.getLoginId(), "987654321"))
                .isInstanceOf(IllegalArgumentException.class).hasMessage("로그인 정보 시스템에 있는 계정과 일치하지 않습니다.");

    }


}