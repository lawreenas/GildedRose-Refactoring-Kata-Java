package com.gildedrose;

import com.gildedrose.domain.Item;
import com.gildedrose.exceptions.ItemNotFoundException;
import com.gildedrose.persist.ItemRepository;
import com.gildedrose.web.ItemService;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

/**
 * @author L.Remeika
 */
@RunWith(SpringRunner.class)
public class ItemServiceTest {

    ItemService itemService;

    @MockBean
    ItemRepository itemRepository;

    @Before
    public void setUp() {
        itemService = new ItemService(itemRepository);
    }

    @Test
    public void getItems_ShouldReturnItems() {
        given(itemRepository.findAll()).willReturn(Arrays.asList(new Item("Item 1", 10, 20)));

        List<Item> items = itemService.getItems();

        assertEquals(items.size(), 1);
    }

    @Test
    public void getItem_ShouldReturnItemDetails() {
        given(itemRepository.findById(anyString())).willReturn(new Item("Item 10", 210, 430));

        Item item = itemService.getItem("144");

        assertEquals(item.name, "Item 10");
        assertEquals(item.sellIn, 210);
        assertEquals(item.quality, 430);
    }

    @Test
    public void getItem_Should404() {
        given(itemRepository.findById(anyString())).willReturn(null);

        try {
            Item item = itemService.getItem("144");
        } catch (ItemNotFoundException e) {
            return;
        }
        TestCase.fail();

    }

}
