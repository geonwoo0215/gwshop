package com.GWShop.gwshop.controller.form;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemEditForm {

    @NotBlank(message = "상품 이름은 공백일 수 없습니다.")
    private String name;

    @NotNull(message = "가격을 입력해주세요")
    private int price;

    @NotNull(message = "재고를 입력해주세요")
    private int stock;

    @Builder
    public ItemEditForm(String name, int price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
}
