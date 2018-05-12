package com.gildedrose;

import com.gildedrose.domain.GenericItemItem;
import com.gildedrose.domain.SulfurasItem;

import java.util.Arrays;

public class GildedRose {

    /* Maximum quality an item can have (does not apply SULFURAS) */
    public static final int ITEM_QUALITY_MAX = 50;

    /* Minimum quality of an item */
    public static final int ITEM_QUALITY_MIN = 0;

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        Arrays.stream(items)
                .filter(item -> !(item instanceof SulfurasItem))
                .map(item -> updateItemSellInDays(item))
                .forEach(item -> ((GenericItemItem) item).updateQuality());

    }

    private Item updateItemSellInDays(Item item) {
        item.sellIn -= 1;
        return item;
    }

}