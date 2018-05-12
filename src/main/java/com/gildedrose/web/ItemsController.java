package com.gildedrose.web;

import com.gildedrose.Item;
import com.gildedrose.ItemNotFoundException;
import com.gildedrose.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/items/{id}")
    public Item getItems(@PathVariable("id") Integer id) {
        return itemService.getItem(id);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void itemNotFound(ItemNotFoundException e) {}

}
