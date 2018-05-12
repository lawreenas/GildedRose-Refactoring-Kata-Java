package com.gildedrose.domain;

import com.gildedrose.GildedRose;

import static java.lang.Math.min;

/**
 * @author L.Remeika
 */
public class AgedBrieItem extends GenericItem {

    public AgedBrieItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void updateQuality() {
        this.quality = min(this.quality + 1, GildedRose.ITEM_QUALITY_MAX);
    }
}