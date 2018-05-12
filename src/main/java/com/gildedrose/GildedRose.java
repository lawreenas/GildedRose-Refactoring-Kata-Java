package com.gildedrose;

import com.gildedrose.model.BackstagePass;
import com.gildedrose.model.GenericItem;

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
                .filter(item -> !ItemType.SULFURAS.name.equals(item.name))
                .map(item -> updateItemSellInDays(item))
                .forEach(item -> ((GenericItem) item).updateQuality());

    }

    private Item updateItemSellInDays(Item item) {
        item.sellIn -= 1;
        return item;
    }

}