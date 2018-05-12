package com.gildedrose.web;

import com.gildedrose.domain.Item;
import com.gildedrose.exceptions.ItemNotFoundException;
import com.gildedrose.persist.ItemRepository;
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

    public Item getItem(String uuid) {
        Item item = itemRepository.findById(uuid);
        if (item == null) {
            throw new ItemNotFoundException();
        }
        return item;
    }
}
