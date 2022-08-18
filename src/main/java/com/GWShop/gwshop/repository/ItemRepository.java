package com.GWShop.gwshop.repository;

import com.GWShop.gwshop.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
