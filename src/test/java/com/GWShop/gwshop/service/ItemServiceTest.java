package com.GWShop.gwshop.service;

import com.GWShop.gwshop.domain.Item;
import com.GWShop.gwshop.repository.ItemRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemRepository itemRepository;

    @BeforeEach
    public void setUp() {
        itemRepository.deleteAll();
    }


    @Test
    @DisplayName("상품 저장")
    void test1() {

        Item item = Item.builder()
                .name("셔츠")
                .price(10000)
                .stock(10)
                .build();

        itemService.save(item);

        Item saveItem = itemRepository.findAll().get(0);

        Assertions.assertThat(saveItem.getName()).isEqualTo("셔츠");
        Assertions.assertThat(saveItem.getPrice()).isEqualTo(10000);
        Assertions.assertThat(saveItem.getStock()).isEqualTo(10);

    }

    @Test
    @DisplayName("상품 상세")
    void test2() {

        Item item = Item.builder()
                .name("셔츠")
                .price(10000)
                .stock(10)
                .build();

        Long saveId = itemRepository.save(item).getId();
        Item saveItem = itemService.read(saveId);

        Assertions.assertThat(saveItem.getName()).isEqualTo("셔츠");
        Assertions.assertThat(saveItem.getPrice()).isEqualTo(10000);
        Assertions.assertThat(saveItem.getStock()).isEqualTo(10);

    }

}