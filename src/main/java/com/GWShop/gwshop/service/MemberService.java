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

}
