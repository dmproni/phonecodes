package ru.rtk.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.rtk.model.Country;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class SearchServiceTest {
    private static Country russia;
    private static Country finland;
    private static Country poland;
    private static Country latvia;
    private static Country litva;
    private static List<Country> defaultCache;
    @Autowired
    private SearchService searchService;
    @MockBean
    private CountryCacheService cacheService;

    @BeforeAll
    static void setBeforeAll() {
        russia = Country.builder().name("Russia").phoneCode("20").shortCode("RU").build();
        finland = Country.builder().name("Finland").phoneCode("300").shortCode("FI").build();
        poland = Country.builder().name("Poland").phoneCode("500").shortCode("PO").build();
        latvia = Country.builder().name("Latvia").phoneCode("700").shortCode("LA").build();
        litva = Country.builder().name("Litva").phoneCode("800").shortCode("LI").build();

        defaultCache = Arrays.asList(russia, finland, poland, latvia, litva);
    }

    @Test
    void test() {
        Mockito.when(cacheService.getCache()).thenReturn(defaultCache);

        Assertions.assertEquals(Arrays.asList(latvia, litva), searchService.findCountriesByPartOfName("Tv"));
        Assertions.assertEquals(Arrays.asList(finland, poland, latvia), searchService.findCountriesByPartOfName("la"));
        Assertions.assertEquals(Arrays.asList(), searchService.findCountriesByPartOfName("abracardab"));
        Assertions.assertEquals(defaultCache, searchService.findCountriesByPartOfName(""));
        Assertions.assertEquals(defaultCache, searchService.findCountriesByPartOfName(null));
    }
}
