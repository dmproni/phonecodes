package ru.rtk.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.rtk.model.Country;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SearchServiceTest {
    @Autowired
    private SearchService searchService;
    @Autowired
    private CountryCacheService cacheService;

    private static Country russia;
    private static Country finland;
    private static Country poland;
    private static Country latvia;
    private static Country litva;
    private static List<Country> defaultCache;

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

        Assertions.assertEquals(Arrays.asList(latvia, litva), searchService.findCountriesByPartOfName(Optional.of("Tv")));
        Assertions.assertEquals(Arrays.asList(finland, poland, latvia), searchService.findCountriesByPartOfName(Optional.of("la")));
        Assertions.assertEquals(Arrays.asList(), searchService.findCountriesByPartOfName(Optional.of("abracardab")));
        Assertions.assertEquals(defaultCache, searchService.findCountriesByPartOfName(Optional.of("")));
        Assertions.assertEquals(defaultCache, searchService.findCountriesByPartOfName(Optional.ofNullable(null)));
    }
}
