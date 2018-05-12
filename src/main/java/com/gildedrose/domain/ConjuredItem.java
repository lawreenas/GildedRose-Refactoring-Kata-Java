package com.gildedrose.domain;

import com.gildedrose.GildedRose;

import static java.lang.Math.max;

/**
 * @author L.Remeika
 */
public class ConjuredItem extends GenericItem {

    public ConjuredItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void updateQuality() {
        this.quality = max(this.quality - 2, GildedRose.ITEM_QUALITY_MIN);
    }
}
