package ru.rtk.service;

import ru.rtk.model.PhoneCode;

import java.util.Collection;

public interface PhoneCodesService {
    Collection<PhoneCode> searchByPartOfCountryName(String countryNamePart);
}
