package ru.rtk.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.rtk.model.Country;
import ru.rtk.repository.CountryRepository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;

@Service
public class SimpleCountryCacheService implements CountryCacheService {
    private Collection<Country> cache = new ArrayList<>();
    private CountryRepository countryRepository;

    public SimpleCountryCacheService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @PostConstruct
    private void populateCache() {
        setCache(countryRepository.findAll());
    }



    @Override
    public Collection<Country> getCache() {
        return cache;
    }

    @Override
    public void setCache(Collection<Country> newCache) {
        if (newCache != null && !newCache.isEmpty()) {
            Collection<Country> buff = new ArrayList<>();
            newCache.forEach(country -> buff.add(Country.builder()
                    .name(country.getName())
                    .phoneCode(country.getPhoneCode())
                    .shortCode(country.getShortCode())
                    .build()));
            cache = buff;
        }
    }
}
