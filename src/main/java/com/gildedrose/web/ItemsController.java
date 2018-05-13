package com.gildedrose.web;

import com.gildedrose.domain.Item;
import com.gildedrose.exceptions.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author L.Remeika
 */
@RestController
@RequestMapping("/v1/items")
public class ItemsController {

    @Autowired
    ItemService itemService;

    @GetMapping()
    public List<Item> getItems() {
       return itemService.getItems();
    }

    @GetMapping("/{id}")
    public Item getItems(@PathVariable("id") String uuid) {
        return itemService.getItem(uuid);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void itemNotFound(ItemNotFoundException e) {}

}
