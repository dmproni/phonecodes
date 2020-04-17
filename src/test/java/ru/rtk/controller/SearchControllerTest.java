package ru.rtk.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.rtk.model.Country;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = SearchController.class)
public class SearchControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private SearchController searchController;

    @Test
    void test() throws Exception {
        Mockito.when(searchController.getCountries(""))
                .thenReturn(Arrays.asList(
                        Country.builder()
                                .name("Russia")
                                .phoneCode("200")
                                .shortCode("RU").build()));

        mockMvc.perform(get("/rest/code?country=us"))
                .andExpect(status().isOk());
    }
}
