package ru.rtk.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.rtk.model.PhoneCode;
import ru.rtk.service.PhoneCodesService;

import java.util.Collection;

@RestController
@RequestMapping("/rest")
@Slf4j
public class PhoneCodesController {
    private PhoneCodesService phoneCodesService;

    PhoneCodesController(PhoneCodesService phoneCodesService) {
        this.phoneCodesService = phoneCodesService;
    }

    @GetMapping("/code")
    public Collection<PhoneCode> getCodes(
            @RequestParam(value = "country") String country) {
        log.debug(country);
        return phoneCodesService.searchByPartOfCountryName(country);
    }

}
