package ru.rtk.service;

import ru.rtk.model.PhoneCode;

import java.util.Collection;

public interface PhoneCodesLocalCacheService {
    void setCache(Collection<PhoneCode> newCache);
    Collection<PhoneCode> getCache();
}
