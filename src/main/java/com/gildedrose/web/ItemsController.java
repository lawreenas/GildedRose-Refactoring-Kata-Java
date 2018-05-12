package com.gildedrose.web;

import com.gildedrose.Item;
import com.gildedrose.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author L.Remeika
 */
@RestController
public class ItemsController {

    @Autowired
    ItemService itemService;

    @GetMapping("/items")
    public List<Item> getItems() {
       return itemService.getItems();
    }

}
