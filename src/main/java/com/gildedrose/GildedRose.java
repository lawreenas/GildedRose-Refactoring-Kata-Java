package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {

        for (int i = 0; i < items.length; i++) {

            items[i] = updateItemSellIn(items[i]);

            if (!items[i].name.equals("Aged Brie")
                    && !items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                items[i] = decreaseItemQuality(items[i]);
            } else {

                items[i] = increaseItemQuality(items[i]);

                if (items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                    if (items[i].sellIn < 11) {
                        items[i] = increaseItemQuality(items[i]);
                    }

                    if (items[i].sellIn < 6) {
                        items[i] = increaseItemQuality(items[i]);
                    }
                }
            }

            if (items[i].sellIn < 0) {
                switch (items[i].name) {
                    case "Aged Brie":
                        items[i] = increaseItemQuality(items[i]);
                        return;
                    case "Backstage passes to a TAFKAL80ETC concert":
                        items[i].quality = 0;
                        return;
                    default:
                        items[i] = decreaseItemQuality(items[i]);
                }
            }
        }
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