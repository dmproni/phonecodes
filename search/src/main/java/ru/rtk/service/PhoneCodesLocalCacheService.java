package ru.rtk.service;

import ru.rtk.model.PhoneCode;

import java.util.Collection;

public interface PhoneCodesLocalCacheService {
    void refresh();

    Collection<PhoneCode> getCache();
}
