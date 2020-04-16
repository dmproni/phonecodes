package ru.rtk.service;

import ru.rtk.model.Country;

import java.util.Collection;
import java.util.Optional;

public interface SearchService {
    Collection<Country> findCountriesByPartOfName(Optional<String> partOfName);
}
