package com.gildedrose;

import java.util.Arrays;

class GildedRose {

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
                .forEach(item -> {

            switch (item.name) {
                case "Aged Brie":
                    increaseItemQuality(item, 1);
                    return;
                case "Backstage passes to a TAFKAL80ETC concert":
                    if (item.sellIn < 0) {
                        item.quality = 0;
                        return;
                    }
                    if (item.sellIn <= 5) {
                        increaseItemQuality(item,3);
                        return;
                    }
                    if (item.sellIn <= 10) {
                        increaseItemQuality(item, 2);
                        return;
                    }
                    return;
                default:
                    if (item.sellIn < 0) {
                        decreaseItemQuality(item, 2);
                    } else {
                        decreaseItemQuality(item, 1);
                    }
            }
        });
    }

    private Item updateItemSellInDays(Item item) {
        item.sellIn -= 1;
        return item;
    }

    private Item increaseItemQuality(Item item, int increaseBy) {
        item.quality = Math.min(item.quality + increaseBy, ITEM_QUALITY_MAX);
        return item;
    }

    private Item decreaseItemQuality(Item item, int decreaseBy) {
        item.quality = Math.max(item.quality - decreaseBy, ITEM_QUALITY_MIN);
        return item;
    }

}