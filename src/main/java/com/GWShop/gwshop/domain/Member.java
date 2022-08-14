package com.GWShop.gwshop.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    private String loginId;

    private String password;

    @Builder
    public Member(String nickname, String loginId, String password) {
        this.nickname = nickname;
        this.loginId = loginId;
        this.password = password;
    }
}
