package com.gildedrose;

import java.util.Arrays;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {

        Arrays.stream(items).forEach(item -> {

            item = decreaseItemSellIn(item);

//            if (item.sellIn < 0) {
                switch (item.name) {
                    case "Aged Brie":
                        increaseItemQuality(item, 1);
                        return;
                    case "Backstage passes to a TAFKAL80ETC concert":
                        increaseItemQuality(item, 1);

                        if (item.sellIn < 11) {
                            increaseItemQuality(item, 1);
                        }
                        if (item.sellIn < 6) {
                            increaseItemQuality(item,1);
                        }
                        if (item.sellIn < 0) {
                            item.quality = 0;
                        }
                        return;
                    default:
                        if (item.sellIn < 0) {
                            decreaseItemQuality(item, 1);
                        }
                        decreaseItemQuality(item, 1);
                }
//            }
        });
    }


    private Item decreaseItemSellIn(Item item) {
        if ("Sulfuras, Hand of Ragnaros".equals(item.name)) return item;
        item.sellIn -= 1;
        return item;
    }

    private Item increaseItemQuality(Item item, int increaseBy) {
        item.quality = Math.min(item.quality + increaseBy, 50);
        return item;
    }

    private Item decreaseItemQuality(Item item, int decreaseBy) {
        // Sulfuras does not decrease in quality
        if ("Sulfuras, Hand of Ragnaros".equals(item.name)) return item;
        item.quality = Math.max(item.quality - decreaseBy, 0);
        return item;
    }

}