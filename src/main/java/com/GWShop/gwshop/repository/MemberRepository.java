package com.GWShop.gwshop.repository;

import com.GWShop.gwshop.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {



}
