package ru.rtk.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.rtk.model.Country;
import ru.rtk.service.SearchService;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/rest")
public class SearchController {
    private SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/code")
    public Collection<Country> getCountries(
            @RequestParam(value = "country", required = false) String countryCode) {
        return searchService.findCountriesByPartOfName(Optional.ofNullable(countryCode));
    }

}
