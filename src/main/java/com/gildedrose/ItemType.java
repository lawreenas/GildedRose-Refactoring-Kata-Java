package com.gildedrose;

/**
 * @author L.Remeika
 */
public enum ItemType {

    AGED_BRIE("Aged Brie"),
    BACKSTAGE_PASS("Backstage passes to a TAFKAL80ETC concert"),
    SULFURAS("Sulfuras, Hand of Ragnaros"),
    CONJURED("Conjured Mana Cake");

    ItemType(String name) {
        this.name = name;
    }

    public final String name;

}
