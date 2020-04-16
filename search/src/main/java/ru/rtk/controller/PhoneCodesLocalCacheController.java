package ru.rtk.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rtk.model.PhoneCode;
import ru.rtk.service.PhoneCodesLocalCacheService;

import java.util.Collection;

@RestController
@RequestMapping("/rest")
public class PhoneCodesLocalCacheController {
    private PhoneCodesLocalCacheService cacheService;

    public PhoneCodesLocalCacheController(PhoneCodesLocalCacheService cacheService) {
        this.cacheService = cacheService;
    }

    @PostMapping("/refresh")
    void refresh(@RequestBody Collection<PhoneCode> collection) {
        cacheService.setCache(collection);
    }

}
