package com.gildedrose;

import static org.junit.Assert.*;

import com.gildedrose.domain.*;
import org.junit.Test;

/**
 - Once the sell by date has passed, Quality degrades twice as fast
 - The Quality of an item is never negative
 - "Aged Brie" actually increases in Quality the older it gets
 - The Quality of an item is never more than 50
 - "Sulfuras", being a legendary item, never has to be sold or decreases in Quality
 - "Backstage passes", like aged brie, increases in Quality as its SellIn value approaches;
    Quality increases by 2 when there are 10 days or less and by 3 when there are 5 days or less but
    Quality drops to 0 after the concert

 Items:
 - Aged Brie - increase in quality
 - Sulfuras - legendary
 - Backstage passes - increase by sell in date
 */
public class GildedRoseTest {

    @Test
    public void test_Quality_Should_Degrade_Twice_As_Fast_After_SellBy() {
        // Given
        Item[] items = new Item[] { new GenericItemItem("Elixir of the Mongoose", 0, 10) };
        GildedRose app = new GildedRose(items);

        // When
        app.updateQuality();

        // Then
        Item item = app.items[0];
        assertEquals(8, item.quality);
        assertEquals(-1, item.sellIn);
    }


    @Test
    public void test_Quality_Should_Never_Be_Negative() {
        // Given
        Item[] items = new Item[] { new GenericItemItem("Elixir of the Mongoose", -1, 0) };
        GildedRose app = new GildedRose(items);

        // When
        app.updateQuality();

        // Then
        Item item = app.items[0];
        assertEquals(0, item.quality);
        assertEquals(-2, item.sellIn);
    }

    @Test
    public void test_AgedBrie_Should_Increase_In_Quality() {
        // Given
        Item[] items = new Item[] { new AgedBrieItem("Aged Brie", 10, 0) };
        GildedRose app = new GildedRose(items);

        // When
        app.updateQuality();

        // Then
        Item item = app.items[0];
        assertEquals(1, item.quality);
    }

    @Test
    public void test_BackstagePasses_Should_Increase_In_Quality_By_2_When_Sell_In_Less_Than_10() {
        // Given
        Item[] items = new Item[] { new BackstagePassItem("Backstage passes to a TAFKAL80ETC concert", 10, 5) };
        GildedRose app = new GildedRose(items);

        // When
        app.updateQuality();

        // Then
        Item item = app.items[0];
        assertEquals(7, item.quality);
    }

    @Test
    public void test_Quality_Never_Increase_Above_50() {
        // Given
        Item[] items = new Item[] {
                new AgedBrieItem("Aged Brie", 10, 50),
                new BackstagePassItem("Backstage passes to a TAFKAL80ETC concert", 1, 49)
        };
        GildedRose app = new GildedRose(items);

        // When
        app.updateQuality();

        // Then
        assertEquals(50, app.items[0].quality);
        assertEquals(50, app.items[1].quality);
    }

    @Test
    public void test_Backstage_Passes_Should_Increase_In_Quality_By_3_When_Sell_In_Less_Than_5() {
        // Given
        Item[] items = new Item[] { new BackstagePassItem("Backstage passes to a TAFKAL80ETC concert", 4, 5) };
        GildedRose app = new GildedRose(items);

        // When
        app.updateQuality();

        // Then
        Item item = app.items[0];
        assertEquals(8, item.quality);
    }


    @Test
    public void test_Backstage_Passes_Quality_Is_0_After_Concert() {
        // Given
        Item[] items = new Item[] { new BackstagePassItem("Backstage passes to a TAFKAL80ETC concert", 0, 10) };
        GildedRose app = new GildedRose(items);

        // When
        app.updateQuality();

        // Then
        Item item = app.items[0];
        assertEquals(0, item.quality);
    }

    @Test
    public void test_Sulfuras_Should_Not_Decrease_In_Quality() {
        // Given
        Item[] items = new Item[] { new SulfurasItem("Sulfuras, Hand of Ragnaros", 0, 80) };
        GildedRose app = new GildedRose(items);

        // When
        app.updateQuality();

        // Then
        Item item = app.items[0];
        assertEquals(80, item.quality);
    }

    @Test
    public void test_Conjured_Should_Degrade_Twice_As_Fast() {
        // Given
        Item[] items = new Item[] { new ConjuredItem("Conjured Mana Cake", 10, 40) };
        GildedRose app = new GildedRose(items);

        // When
        app.updateQuality();

        // Then
        Item item = app.items[0];
        assertEquals(38, item.quality);
    }

}
