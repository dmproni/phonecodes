package ru.rtk.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SearchUtilsTest {

    @Test
    void containsIgnoreCaseShouldReturnFalseIfAnyOfArgumentsIsNull() {
        Assertions.assertEquals(false, SearchUtils.containsIgnoreCase(null, null));
        Assertions.assertEquals(false, SearchUtils.containsIgnoreCase(null, "some"));
        Assertions.assertEquals(false, SearchUtils.containsIgnoreCase("another", null));
    }

    @Test
    void containsIgnoreCaseShouldSuccessPath() {
        Assertions.assertEquals(true, SearchUtils.containsIgnoreCase("SomeDay", "med"));
        Assertions.assertEquals(true, SearchUtils.containsIgnoreCase("Yo baby", "BA"));
        Assertions.assertEquals(false, SearchUtils.containsIgnoreCase("another", "good"));
    }
}
