package com.gildedrose.model;

import com.gildedrose.GildedRose;
import com.gildedrose.ItemType;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * @author L.Remeika
 */
public class Conjured extends GenericItem {

    public Conjured(String name, int sellIn, int quality) {
        super(ItemType.CONJURED.name, sellIn, quality);
    }

    @Override
    public void updateQuality() {
        this.quality = max(this.quality - 2, GildedRose.ITEM_QUALITY_MIN);
    }
}
