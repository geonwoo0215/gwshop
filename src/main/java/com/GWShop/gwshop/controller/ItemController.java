package com.GWShop.gwshop.controller;

import com.GWShop.gwshop.controller.form.ItemEditForm;
import com.GWShop.gwshop.controller.form.ItemForm;
import com.GWShop.gwshop.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @ResponseBody
    @PostMapping("/item")
    public void post(@Valid @RequestBody ItemForm itemForm) {
        log.info("itemForm={}", itemForm.toString());
        itemService.save(itemForm.toItem());
    }

    @ResponseBody
    @GetMapping("/item/{itemId}")
    public void get(@PathVariable Long itemId) {
        log.info("itemId={}", itemId);
    }

    @ResponseBody
    @PatchMapping("/item/{itemId}")
    public void edit(@PathVariable Long itemId, @RequestBody @Valid ItemEditForm itemEditForm) {
        itemService.edit(itemId,itemEditForm);
    }

}
