package com.gildedrose.model;

import com.gildedrose.GildedRose;
import com.gildedrose.ItemType;

import static java.lang.Math.min;

/**
 * @author L.Remeika
 */
public class AgedBrie extends GenericItem {

    public AgedBrie(String name, int sellIn, int quality) {
        super(ItemType.AGED_BRIE.name, sellIn, quality);
    }

    @Override
    public void updateQuality() {
        this.quality = min(this.quality + 1, GildedRose.ITEM_QUALITY_MAX);
    }
}
