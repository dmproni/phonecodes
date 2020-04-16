package ru.rtk.utils;

public class SearchUtils {
    public static boolean containsIgnoreCase(String source, String test) {
        if (source != null && test != null) {
            return source.toLowerCase().contains(test.toLowerCase());
        } else {
            return false;
        }
    }
}
