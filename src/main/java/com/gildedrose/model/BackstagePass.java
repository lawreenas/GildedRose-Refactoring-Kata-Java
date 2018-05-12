package com.gildedrose.model;

import com.gildedrose.GildedRose;
import com.gildedrose.Item;
import com.gildedrose.ItemType;

import static java.lang.Math.min;

/**
 * @author L.Remeika
 */
public class BackstagePass extends GenericItem {

    public BackstagePass(String name, int sellIn, int quality) {
        super(ItemType.BACKSTAGE_PASS.name, sellIn, quality);
    }

    @Override
    public void updateQuality() {
        if (this.sellIn < 0) {
            this.quality = 0;
            return;
        }
        if (this.sellIn <= 5) {
            this.quality = min(this.quality + 3, GildedRose.ITEM_QUALITY_MAX);
            return;
        }
        if (this.sellIn <= 10) {
            this.quality = min(this.quality + 2, GildedRose.ITEM_QUALITY_MAX);
            return;
        }
    }
}
