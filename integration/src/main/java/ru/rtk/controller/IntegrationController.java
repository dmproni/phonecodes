package ru.rtk.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rtk.service.IntegrationService;

@RestController
@RequestMapping("/intg")
@Slf4j
public class IntegrationController {
    private IntegrationService integrationService;

    public IntegrationController(IntegrationService integrationService) {
        this.integrationService = integrationService;
    }

    @GetMapping("/refresh")
    String refresh() {
        log.debug("REFRESHING from remote datasources");
        integrationService.refresh();
        return "Done";
    }

}
