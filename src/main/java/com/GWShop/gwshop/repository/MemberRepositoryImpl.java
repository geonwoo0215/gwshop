package com.GWShop.gwshop.repository;

import com.GWShop.gwshop.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class MemberRepositoryImpl implements MemberRepositoryCustom{

    @Autowired
    EntityManager em;

    @Override
    public Optional<Member> findByLoginId(String loginId) {
        return em.createQuery("select m from Member m where m.loginId = :loginId", Member.class)
                .setParameter("loginId", loginId)
                .getResultList()
                .stream().findAny();
    }
}
