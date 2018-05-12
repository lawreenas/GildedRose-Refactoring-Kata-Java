package com.gildedrose.domain;

import com.gildedrose.GildedRose;

import java.util.UUID;

/**
 * @author L.Remeika
 */
public class GenericItem extends Item {

    public final String id;

    public GenericItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
        id = UUID.randomUUID().toString();
    }

    public void updateQuality() {
        if (this.sellIn < 0) {
            this.quality = Math.max(this.quality - 2, GildedRose.ITEM_QUALITY_MIN);
        } else {
            this.quality = Math.max(this.quality - 1, GildedRose.ITEM_QUALITY_MIN);
        }
    }
}
