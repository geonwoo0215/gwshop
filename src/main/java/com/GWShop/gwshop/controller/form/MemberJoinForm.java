package com.GWShop.gwshop.controller.form;


import com.GWShop.gwshop.domain.Member;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class MemberJoinForm {

    @Size(min = 3, max = 15)
    @NotBlank(message = "닉네임은 공백일 수 없습니다.")
    private String nickname;

    @Size(min = 8, max = 15)
    @NotBlank(message = "아이디는 공백일 수 없습니다.")
    private String loginId;

    @Size(min = 8, max = 15)
    @NotBlank(message = "비밀번호는 공백일 수 없습니다.")
    private String password;

    @Builder
    public MemberJoinForm(String nickname, String loginId, String password) {
        this.nickname = nickname;
        this.loginId = loginId;
        this.password = password;
    }

    public Member toMember() {

        return Member.builder()
                .nickname(nickname)
                .loginId(loginId)
                .password(password)
                .build();
    }

}
