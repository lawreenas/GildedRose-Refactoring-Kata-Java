package com.gildedrose.model;

import com.gildedrose.GildedRose;
import com.gildedrose.ItemType;

import static java.lang.Math.min;

/**
 * @author L.Remeika
 */
public class Sulfuras extends GenericItem {

    public Sulfuras(String name, int sellIn, int quality) {
        super(ItemType.SULFURAS.name, sellIn, quality);
    }

    @Override
    public void updateQuality() {
        this.quality = 80;
    }
}
