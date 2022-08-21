package com.GWShop.gwshop.repository;

import com.GWShop.gwshop.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long>,CartRepositoryCustom {
}
