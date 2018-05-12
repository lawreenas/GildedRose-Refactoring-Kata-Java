package com.gildedrose;

import java.util.Arrays;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {

        Arrays.stream(items).forEach(item -> {

            item = updateItemSellIn(item);

            if (!item.name.equals("Aged Brie")
                    && !item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                decreaseItemQuality(item);
            } else {

                increaseItemQuality(item);

                if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                    if (item.sellIn < 11) {
                        increaseItemQuality(item);
                    }

                    if (item.sellIn < 6) {
                        increaseItemQuality(item);
                    }
                }
            }

            if (item.sellIn < 0) {
                switch (item.name) {
                    case "Aged Brie":
                        increaseItemQuality(item);
                        return;
                    case "Backstage passes to a TAFKAL80ETC concert":
                        item.quality = 0;
                        return;
                    default:
                        decreaseItemQuality(item);
                }
            }
        });
    }

    private Item updateItemSellIn(Item item) {
        if ("Sulfuras, Hand of Ragnaros".equals(item.name)) return item;
        item.sellIn -= 1;
        return item;
    }

    private Item increaseItemQuality(Item item) {
        item.quality = Math.min(item.quality + 1, 50);
        return item;
    }

    private Item decreaseItemQuality(Item item) {
        // Sulfuras does not decrease in quality
        if ("Sulfuras, Hand of Ragnaros".equals(item.name)) return item;
        item.quality = Math.max(item.quality - 1, 0);
        return item;
    }

}