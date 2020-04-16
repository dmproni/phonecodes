package ru.rtk.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.rtk.model.Country;

import java.util.*;

@SpringBootTest
public class CountryCacheServiceTest {
    @Autowired
    private CountryCacheService countryCacheService;

    private static Country russia;
    private static Country finland;
    private static List<Country> defaultCache;
    private List<Country> testCache;

    @BeforeAll
    static void setBeforeAll() {
        russia = Country.builder().name("Russia").phoneCode("20").shortCode("RU").build();
        finland = Country.builder().name("Finland").phoneCode("300").shortCode("FI").build();
        defaultCache = Arrays.asList(russia, finland);
    }

    @BeforeEach
    void setBeforeEach() {
        testCache = new ArrayList<>(defaultCache);
        countryCacheService.setCache(testCache);
    }

    @Test
    void testingSettingAndGettingNonNullAndNonEmptyCache() {
        Assertions.assertEquals(testCache, countryCacheService.getCache());
    }

    @Test
    void settingNullCacheDoesntAffectOldCache() {
        countryCacheService.setCache(null);
        Assertions.assertEquals(testCache, countryCacheService.getCache());
    }

    @Test
    void settingEmptyCacheDoesntAffectOldCache() {
        countryCacheService.setCache(Arrays.asList());
        Assertions.assertEquals(testCache, countryCacheService.getCache());
    }

    @Test
    void changingLocalCacheObjectDoesntAffectSetCache() {
        countryCacheService.setCache(testCache);
        Country poland = Country.builder().name("Poland").phoneCode("400").shortCode("PO").build();
        testCache.add(poland);

        Assertions.assertNotEquals(testCache, countryCacheService.getCache());
        Assertions.assertEquals(defaultCache, countryCacheService.getCache());

        countryCacheService.setCache(testCache);
        Assertions.assertEquals(testCache, countryCacheService.getCache());

        poland.setPhoneCode("5555");
        Assertions.assertNotEquals(testCache, countryCacheService.getCache());
    }

}
