package com.GWShop.gwshop.repository;

import com.GWShop.gwshop.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long>{
}
