package ru.rtk;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.rtk.model.PhoneCode;
import ru.rtk.service.PhoneCodesLocalCacheService;
import ru.rtk.service.PhoneCodesService;
import ru.rtk.service.PhoneCodesServiceImpl;

import java.util.Arrays;
import java.util.Collection;

public class TestService {
    private PhoneCodesService phoneCodesService;
    private PhoneCodesLocalCacheService cacheService;

    private static PhoneCode russia;
    private static PhoneCode france;
    private static PhoneCode latvia;
    private static PhoneCode litva;
    private static PhoneCode usa;
    private static Collection<PhoneCode> collection;
    private static Collection<PhoneCode> emptyCollection = Arrays.asList();

    TestService(PhoneCodesService phoneCodesService) {
        this.phoneCodesService = new PhoneCodesServiceImpl(
                new ca
        );
    }

    @BeforeAll
    static void fill() {
        russia = new PhoneCode("ru", "Russia", "1");
        france = new PhoneCode("fr", "France", "2");
        latvia = new PhoneCode("la", "Latvia", "3");
        litva = new PhoneCode("li", "Litva", "4");
        usa = new PhoneCode("us", "USA", "5");
        collection = Arrays.asList(russia, france, latvia, litva, usa);
    }

    @Test
    void whenCacheIsEmptyCanFindNothing() {
        Assertions.assertEquals(emptyCollection,
                phoneCodesService.searchByPartOfCountryName("u"));
    }

    @Test
    void searchResultShouldBeIgnorantOfCase() {
//        Assertions.assertEquals(Arrays.asList(usa),
//                collection.searchByPartOfCountryName("sa"));
    }

    @Test
    void ifNoResultFoundEmptyListShouldBeReturned() {
//        Assertions.assertEquals(Arrays.asList(),
//                collection.searchByPartOfCountryName("burg"));
    }

    @Test
    void testThatSearchingByPartOfNameReturnsCorrectResult() {
//        Assertions.assertEquals(Arrays.asList(latvia, litva),
//                collection.searchByPartOfCountryName("tv"));
    }
}
