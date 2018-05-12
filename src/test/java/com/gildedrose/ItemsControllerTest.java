package com.gildedrose;

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

import static org.mockito.BDDMockito.given;
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
                .andExpect(status().isOk());
    }

}
