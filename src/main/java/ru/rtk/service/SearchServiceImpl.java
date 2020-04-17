package ru.rtk.service;

import org.springframework.stereotype.Service;
import ru.rtk.model.Country;
import ru.rtk.repository.CountryRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import static ru.rtk.utils.SearchUtils.containsIgnoreCase;

@Service
public class CacheSearchService implements SearchService {
    private CountryCacheService countryCacheService;
    private CountryRepository countryRepository;

    public CacheSearchService(CountryCacheService countryCacheService, CountryRepository countryRepository) {
        this.countryCacheService = countryCacheService;
        this.countryRepository = countryRepository;
    }

    @Override
    public Collection<Country> findCountriesByPartOfName(String partOfName) {
        if (Optional.ofNullable(partOfName).isPresent()) {
            return countryCacheService.getCache().stream()
                    .filter(country -> containsIgnoreCase(country.getName(), partOfName))
                    .collect(Collectors.toList());
        } else {
            return countryCacheService.getCache();
        }
    }

}
