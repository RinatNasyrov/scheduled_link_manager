package com.scheduledlinkmanager.rules_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.scheduledlinkmanager.rules_service.dto.CodeDTO;
import com.scheduledlinkmanager.rules_service.service.CodeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/code")
@RequiredArgsConstructor
public class CodeController {

    private final CodeService codeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCode(@RequestBody CodeDTO codeDTO) {
        codeService.createCode(codeDTO);
    }
}
