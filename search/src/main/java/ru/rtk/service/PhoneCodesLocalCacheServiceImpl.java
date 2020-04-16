package ru.rtk.service;

import org.springframework.stereotype.Service;
import ru.rtk.model.PhoneCode;

import java.util.Arrays;
import java.util.Collection;

@Service
public class PhoneCodesLocalCacheServiceImpl implements PhoneCodesLocalCacheService {
    private Collection<PhoneCode> cache;

    PhoneCodesLocalCacheServiceImpl() {
        PhoneCode russia = new PhoneCode("ru", "Russia", "1");
        PhoneCode france = new PhoneCode("fr", "France", "2");
        PhoneCode latvia = new PhoneCode("la", "Latvia", "3");
        PhoneCode litva = new PhoneCode("li", "Litva", "4");
        PhoneCode usa = new PhoneCode("us", "USA", "5");
        Collection<PhoneCode> collection = Arrays.asList(russia, france, latvia, litva, usa);
        setCache(collection);
    }

    @Override
    public void setCache(Collection<PhoneCode> newCache) {
        cache = newCache;
    }

    @Override
    public Collection<PhoneCode> getCache() {
        return cache;
    }
}
