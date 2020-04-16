package ru.rtk.service;

import ru.rtk.model.Country;

import java.util.Collection;

public interface CountryCacheService {
    Collection<Country> getCache();
    void setCache(Collection<Country> newCache);
}
