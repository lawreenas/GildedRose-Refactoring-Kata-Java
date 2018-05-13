package com.gildedrose;

import com.gildedrose.persist.ItemRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author L.Remeika
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ItemRepository itemRepository;

    @Before
    public void cleanup() throws Exception {
        itemRepository.deleteAll();
    }

    @Test
    public void should_Create_Item() throws Exception {
        mockMvc.perform(post("/items")
                .content("{\"name\": \"Beer\", \"sellIn\": 10, \"quality\": 14 }"))
                .andExpect(status().isCreated());
    }

    @Test
    public void should_Not_Create_Invalid_Item() throws Exception {
        mockMvc.perform(post("/items")
                .content("{\"name\": \"Beer\", \"sellIn\": \"something\", \"quality\": 14 }"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void should_Return_All_Items() throws Exception {

        mockMvc.perform(post("/items")
                .content("{\"name\": \"Beer\", \"sellIn\": 10, \"quality\": 14 }"))
                .andExpect(status().isCreated())
                .andReturn();
        mockMvc.perform(post("/items")
                .content("{\"name\": \"Salad\", \"sellIn\": 3, \"quality\": 40 }"))
                .andExpect(status().isCreated())
                .andReturn();

        mockMvc.perform(get("/items"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.items[0].name").value("Beer"))
                .andExpect(jsonPath("$._embedded.items[0].sellIn").value(10))
                .andExpect(jsonPath("$._embedded.items[0].quality").value(14))
                .andExpect(jsonPath("$._embedded.items[1].name").value("Salad"))
                .andExpect(jsonPath("$._embedded.items[1].sellIn").value(3))
                .andExpect(jsonPath("$._embedded.items[1].quality").value(40));
    }

    @Test
    public void should_Return_Specific_Item() throws Exception {

        MvcResult mvcResult = mockMvc.perform(post("/items")
                .content("{\"name\": \"Beer\", \"sellIn\": 10, \"quality\": 14}"))
                .andExpect(status().isCreated())
                .andReturn();

        String location = mvcResult.getResponse().getHeader("Location");

        mockMvc.perform(get(location))
                .andExpect(status().isOk())
                .andExpect(
                        jsonPath("$.name").value("Beer"))
                .andExpect(
                        jsonPath("$.sellIn").value(10))
                .andExpect(
                        jsonPath("$.quality").value(14));
    }

    @Test
    public void should_Return_NotFound_When_Item_NotExists() throws Exception {
        
        mockMvc.perform(get("/items/123"))
                .andExpect(status().isNotFound());
    }
}
