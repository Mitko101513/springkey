package com.skey.springkey.web;

import com.skey.springkey.model.Item;
import com.skey.springkey.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/item/{id}")
    public Item getItemById(@PathVariable String id) {
        return itemService.getItemForId(id);
    }
}
