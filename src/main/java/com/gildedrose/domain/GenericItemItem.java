package com.gildedrose.domain;

import com.gildedrose.GildedRose;
import com.gildedrose.Item;

/**
 * @author L.Remeika
 */
public class GenericItemItem extends Item {

    public GenericItemItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    public void updateQuality() {
        if (this.sellIn < 0) {
            this.quality = Math.max(this.quality - 2, GildedRose.ITEM_QUALITY_MIN);
        } else {
            this.quality = Math.max(this.quality - 1, GildedRose.ITEM_QUALITY_MIN);
        }
    }
}
