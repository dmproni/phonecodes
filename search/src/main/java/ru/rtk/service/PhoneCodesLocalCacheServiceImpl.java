package ru.rtk.service;

import org.springframework.stereotype.Service;
import ru.rtk.model.PhoneCode;

import java.util.Arrays;
import java.util.Collection;

@Service
public class PhoneCodesLocalCacheServiceImpl implements PhoneCodesLocalCacheService {
    private Collection<PhoneCode> cache =
            Arrays.asList(
                    new PhoneCode("ru", "Russia", "1111"),
                    new PhoneCode("de", "Deutchland", "2222"),
                    new PhoneCode("us", "USA", "5555"),
                    new PhoneCode("la", "Latvia", "3333"),
                    new PhoneCode("li", "Litva", "4444"));

    PhoneCodesLocalCacheServiceImpl() {
    }

    @Override
    public void refresh() {

    }

    @Override
    public Collection<PhoneCode> getCache() {
        return cache;
    }
}
