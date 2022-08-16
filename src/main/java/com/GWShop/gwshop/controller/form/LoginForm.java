package com.GWShop.gwshop.controller.form;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginForm {

    @Size(min = 8, max = 15)
    @NotBlank(message = "아이디는 공백일 수 없습니다.")
    private String loginId;

    @Size(min = 8, max = 15)
    @NotBlank(message = "비밀번호는 공백일 수 없습니다.")
    private String password;

    @Builder
    public LoginForm(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }
}
