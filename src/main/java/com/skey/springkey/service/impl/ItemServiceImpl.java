package com.skey.springkey.service.impl;

import com.skey.springkey.model.Item;
import com.skey.springkey.model.dto.ItemDTO;
import com.skey.springkey.repository.ItemRepository;
import com.skey.springkey.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Cacheable(value = "itemCache")
    @Override
    public Item getItemForId(String id) {
        return itemRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    @CachePut(cacheNames = "itemCache", key = "#itemDTO.id")
    @Override
    public Item addItem(final ItemDTO itemDTO) {
        return null;
    }

    @CacheEvict(value = "itemCache", key = "#id")
    @Override
    public void deleteItem(String id) {
        itemRepository.deleteById(id);
    }
}
