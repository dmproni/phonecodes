package ru.rtk;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.rtk.model.PhoneCode;
import ru.rtk.service.PhoneCodesLocalCacheService;
import ru.rtk.service.PhoneCodesLocalCacheServiceImpl;
import ru.rtk.service.PhoneCodesService;
import ru.rtk.service.PhoneCodesServiceImpl;

import java.util.Arrays;
import java.util.Collection;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestPhoneCodesService {
    @Autowired
    private PhoneCodesService phoneCodesService;
    @Autowired
    private PhoneCodesLocalCacheService cacheService;

    private static PhoneCode russia;
    private static PhoneCode france;
    private static PhoneCode latvia;
    private static PhoneCode litva;
    private static PhoneCode usa;
    private static Collection<PhoneCode> collection;
    private static Collection<PhoneCode> emptyCollection = Arrays.asList();

    @BeforeEach
    void fill() {
        russia = new PhoneCode("ru", "Russia", "1");
        france = new PhoneCode("fr", "France", "2");
        latvia = new PhoneCode("la", "Latvia", "3");
        litva = new PhoneCode("li", "Litva", "4");
        usa = new PhoneCode("us", "USA", "5");
        collection = Arrays.asList(russia, france, latvia, litva, usa);
        cacheService.setCache(collection);
    }

    @Test
    void whenCacheIsEmptyCanFindNothing() {
        Mockito.when(cacheService.getCache()).thenReturn(emptyCollection);

        Assertions.assertEquals(emptyCollection,
                phoneCodesService.searchByPartOfCountryName("u"));
    }

    @Test
    void searchResultShouldBeIgnorantOfCase() {
        Mockito.when(cacheService.getCache()).thenReturn(collection);

        Assertions.assertEquals(Arrays.asList(usa),
                phoneCodesService.searchByPartOfCountryName("sa"));
    }

    @Test
    void ifNoResultFoundEmptyListShouldBeReturned() {
        Mockito.when(cacheService.getCache()).thenReturn(collection);

        Assertions.assertEquals(emptyCollection,
                phoneCodesService.searchByPartOfCountryName("burg"));
    }

    @Test
    void testThatSearchingByPartOfNameReturnsCorrectResult() {
        Mockito.when(cacheService.getCache()).thenReturn(collection);

        Assertions.assertEquals(Arrays.asList(latvia, litva),
                phoneCodesService.searchByPartOfCountryName("tv"));
    }
}
