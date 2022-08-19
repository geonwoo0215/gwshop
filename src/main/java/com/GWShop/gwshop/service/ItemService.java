package com.GWShop.gwshop.service;

import com.GWShop.gwshop.controller.form.ItemEditForm;
import com.GWShop.gwshop.domain.Item;
import com.GWShop.gwshop.domain.ItemEditor;
import com.GWShop.gwshop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void edit(Long id, ItemEditForm itemEditForm) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("없는 상품 입니다."));

        ItemEditor.ItemEditorBuilder itemEditorBuilder = item.toEditor();

        ItemEditor itemEditor = itemEditorBuilder
                .name(itemEditForm.getName())
                .price(itemEditForm.getPrice())
                .stock(itemEditForm.getStock())
                .build();

        item.edit(itemEditor);


    }

}
