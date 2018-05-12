package com.gildedrose.domain;

/**
 * @author L.Remeika
 */
public class SulfurasItem extends GenericItem {

    public SulfurasItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void updateQuality() {
        this.quality = 80;
    }
}
