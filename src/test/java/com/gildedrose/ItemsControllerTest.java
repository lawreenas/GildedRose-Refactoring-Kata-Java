package com.gildedrose;

import com.gildedrose.domain.Item;
import com.gildedrose.exceptions.ItemNotFoundException;
import com.gildedrose.web.ItemService;
import com.gildedrose.web.ItemsController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author L.Remeika
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ItemsController.class)
public class ItemsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemService itemService;

    @Test
    public void getItems_ShouldReturnItems() throws Exception {
        given(itemService.getItems()).willReturn(Arrays.asList(new Item("Test", 10, 20)));

        mockMvc.perform(MockMvcRequestBuilders.get("/items"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].name").value("Test"))
                .andExpect(jsonPath("[0].sellIn").value(10))
                .andExpect(jsonPath("[0].quality").value(20));
    }

    @Test
    public void getItems_ShouldReturnEmptyList() throws Exception {
        given(itemService.getItems()).willReturn(Arrays.asList());

        mockMvc.perform(MockMvcRequestBuilders.get("/items"))
                .andExpect(status().isOk());
    }

    @Test
    public void getItem_ShouldReturnItemDetails() throws Exception {
        given(itemService.getItem(anyString())).willReturn(new Item("TestDetails", 15, 25));

        mockMvc.perform(MockMvcRequestBuilders.get("/items/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("TestDetails"))
                .andExpect(jsonPath("sellIn").value(15))
                .andExpect(jsonPath("quality").value(25));
    }

    @Test
    public void getItem_NotFound() throws Exception {
        given(itemService.getItem(anyString())).willThrow(new ItemNotFoundException());

        mockMvc.perform(MockMvcRequestBuilders.get("/items/144"))
                .andExpect(status().isNotFound());
    }


}
