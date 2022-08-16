package com.GWShop.gwshop.repository;

import com.GWShop.gwshop.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepositoryCustom {

    Optional<Member> findByLoginId(String loginId);

}
