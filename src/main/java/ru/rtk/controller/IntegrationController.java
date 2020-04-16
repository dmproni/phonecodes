package ru.rtk.controller;

import org.springframework.web.bind.annotation.*;
import ru.rtk.service.IntegrationService;

@RestController
@RequestMapping("/rest")
public class IntegrationController {
    private IntegrationService integrationService;

    public IntegrationController(IntegrationService integrationService) {
        this.integrationService = integrationService;
    }

    @GetMapping("/refresh")
    String refresh() {
        integrationService.refresh();
        return "Ok";
    }
}
