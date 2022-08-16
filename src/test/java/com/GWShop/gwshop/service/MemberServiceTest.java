package com.GWShop.gwshop.service;

import com.GWShop.gwshop.domain.Member;
import com.GWShop.gwshop.repository.MemberRepository;
import org.assertj.core.api.Assertions;
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

}