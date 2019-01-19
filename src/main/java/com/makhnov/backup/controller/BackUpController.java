package com.makhnov.backup.controller;

import com.makhnov.backup.service.BackUpService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@AllArgsConstructor
@RequestMapping("backups")
public class BackUpController {
    private final BackUpService backUpService;

    @GetMapping("save")
    public ResponseEntity save(){
        return ResponseEntity.ok(backUpService.save());
    }

    @GetMapping
    public ResponseEntity getBackUps(){
        return ResponseEntity.ok(backUpService.getBackups());
    }
}
