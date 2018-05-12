package com.gildedrose.web;

import com.gildedrose.Item;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author L.Remeika
 */
@RestController
public class ItemsController {

    @GetMapping("/items")
    public List<Item> getItems() {
       return null;
    }

}
