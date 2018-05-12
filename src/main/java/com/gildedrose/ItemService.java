package com.gildedrose;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author L.Remeika
 */
@Service
public class ItemService {

    ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    public Item getItem(Integer id) {
        return itemRepository.findById(id);
    }
}
