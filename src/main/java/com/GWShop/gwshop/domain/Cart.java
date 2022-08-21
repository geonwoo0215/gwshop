package com.GWShop.gwshop.domain;

import com.GWShop.gwshop.controller.form.CartResponseForm;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    @OneToOne
    private Member member;

    @OneToMany
    private List<CartItem> cartItemList;

    @Builder
    public Cart(Member member){
        this.member = member;
    }

    public void changeCartItem(CartItem cartItem) {
        cartItemList.add(cartItem);
        cartItem.changeOrder(this);
    }

    public CartResponseForm toCartResponseForm() {
        return CartResponseForm.builder()
                .cartItemList(cartItemList)
                .build();
    }

}
