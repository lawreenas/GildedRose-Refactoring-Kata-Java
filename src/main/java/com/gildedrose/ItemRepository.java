package com.gildedrose;

import com.gildedrose.domain.AgedBrieItem;
import com.gildedrose.domain.BackstagePassItem;
import com.gildedrose.domain.GenericItem;
import com.gildedrose.domain.SulfurasItem;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author L.Remeika
 */
@Repository
public class ItemRepository {

    Item[] items = new Item[] {
            new GenericItem("+5 Dexterity Vest", 10, 20), //
            new AgedBrieItem("Aged Brie", 2, 0), //
            new GenericItem("Elixir of the Mongoose", 5, 7), //
            new SulfurasItem("Sulfuras, Hand of Ragnaros", 0, 80), //
            new SulfurasItem("Sulfuras, Hand of Ragnaros", -1, 80),
            new BackstagePassItem("Backstage passes to a TAFKAL80ETC concert", 15, 20),
            new BackstagePassItem("Backstage passes to a TAFKAL80ETC concert", 10, 49),
            new BackstagePassItem("Backstage passes to a TAFKAL80ETC concert", 5, 49),
            // this conjured item does not work properly yet
            new GenericItem("Conjured Mana Cake", 3, 6) };

    public List<Item> findAll() {
        return Arrays.stream(items).collect(Collectors.toList());
    }

    public Item findById(String uuid) {
        return Arrays.stream(items).filter(item -> ((GenericItem) item).id.equals(uuid)).findFirst().get();
    }
}
