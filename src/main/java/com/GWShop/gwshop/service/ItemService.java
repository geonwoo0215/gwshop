package com.GWShop.gwshop.service;

import com.GWShop.gwshop.domain.Item;
import com.GWShop.gwshop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public void save(Item item) {
        log.info("저장!");
        itemRepository.save(item);
    }

    public Item read(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("없는 상품 입니다."));
    }

}
