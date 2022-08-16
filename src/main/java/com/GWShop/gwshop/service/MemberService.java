package com.GWShop.gwshop.service;

import com.GWShop.gwshop.domain.Member;
import com.GWShop.gwshop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void join(Member member) {
        log.info("저장!");
        memberRepository.save(member);
    }

    public Member login(String loginId, String password) {

        Member saveMember = memberRepository.findByLoginId(loginId)
                .orElseThrow(() -> new IllegalArgumentException("로그인 정보 시스템에 있는 계정과 일치하지 않습니다."));

        if(saveMember.getPassword().equals(password)) return saveMember;
        else throw new IllegalArgumentException("로그인 정보 시스템에 있는 계정과 일치하지 않습니다.");

    }

}
