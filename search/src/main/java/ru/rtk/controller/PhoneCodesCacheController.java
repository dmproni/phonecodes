package ru.rtk.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rtk.service.PhoneCodesLocalCacheService;

@RestController
@RequestMapping("/cache")
public class PhoneCodesCacheController {
    private PhoneCodesLocalCacheService cacheService;

    PhoneCodesCacheController(PhoneCodesLocalCacheService cacheService) {
        this.cacheService = cacheService;
    }

    @PostMapping("/refresh")
    void refresh() {
        cacheService.refresh();
    }
}
