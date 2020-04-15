package ru.rtk.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.rtk.model.PhoneCode;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PhoneCodesServiceImpl implements PhoneCodesService {
    PhoneCodesLocalCacheService cacheService;

    public PhoneCodesServiceImpl(PhoneCodesLocalCacheService cacheService) {
        this.cacheService = cacheService;
    }

    @Override
    public Collection<PhoneCode> searchByPartOfCountryName(String countryNamePart) {
        log.debug("searching2: " + countryNamePart);
        final Collection<PhoneCode> result =
                cacheService.getCache().stream()
                        .filter(x -> x.getCountry().toLowerCase()
                                .contains(countryNamePart.toLowerCase()))
                        .collect(Collectors.toSet());
        return result;
    }
}
