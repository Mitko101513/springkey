package com.skey.springkey.service;

import com.skey.springkey.model.Item;
import com.skey.springkey.model.dto.ItemDTO;

public interface ItemService {

    Item getItemForId(String id);

    Item addItem(ItemDTO itemDTO);

    void deleteItem(String id);
}
