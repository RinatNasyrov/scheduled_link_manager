package com.scheduledlinkmanager.rules_service.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.scheduledlinkmanager.rules_service.dto.CodeDTO;
import com.scheduledlinkmanager.rules_service.service.CodeService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;


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

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCode(@PathVariable UUID id) {
        codeService.deleteCode(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CodeDTO getCode(@PathVariable UUID id) {
        return codeService.getCode(id);
    }

    @GetMapping("/random/{count}")
    @ResponseStatus(HttpStatus.OK)
    public List<CodeDTO> getRandomCodes(@PathVariable int count) {
        return codeService.getRandomCodes(count);
    }
}
