package com.GWShop.gwshop.controller.form;

import com.GWShop.gwshop.domain.Item;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ItemForm {

    @NotBlank(message = "상품 이름은 공백일 수 없습니다.")
    private String name;

    @NotNull(message = "가격을 입력해주세요")
    private int price;

    @NotNull(message = "재고를 입력해주세요")
    private int stock;

    @Builder
    public ItemForm(String name, int price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public Item toItem() {
        return Item.builder()
                .name(name)
                .price(price)
                .stock(stock)
                .build();
    }
}
