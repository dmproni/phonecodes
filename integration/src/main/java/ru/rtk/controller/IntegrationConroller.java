package ru.rtk.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class IntegrationConroller {

    @PostMapping("/refresh")
    void refresh() {

    }

}
