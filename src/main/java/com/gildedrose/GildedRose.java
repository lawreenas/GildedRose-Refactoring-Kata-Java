package com.gildedrose;

import java.util.Arrays;

class GildedRose {
    public static final int ITEM_QUALITY_MAX = 50;
    public static final int ITEM_QUALITY_MIN = 0;

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {

        Arrays.stream(items).forEach(item -> {

            item = decreaseItemSellIn(item);

            switch (item.name) {
                case "Aged Brie":
                    increaseItemQuality(item, 1);
                    return;
                case "Backstage passes to a TAFKAL80ETC concert":
                    if (item.sellIn < 0) {
                        item.quality = 0;
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
                case "Sulfuras, Hand of Ragnaros":
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


    private Item decreaseItemSellIn(Item item) {
        if (equals(item.name)) return item;
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