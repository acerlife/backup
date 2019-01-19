package com.makhnov.backup.controller;

import com.makhnov.backup.dao.UserDao;
import com.makhnov.backup.service.BackupService;
import com.makhnov.backup.service.TodoService;
import com.makhnov.backup.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("backups")
public class BackupController {
    private final BackupService backupService;
    private final UserService userService;
    private final TodoService todoService;

    @PostMapping("save")
    public ResponseEntity save() throws InterruptedException {
        return ResponseEntity.ok(userService.saveUser());
    }

    @GetMapping
    public ResponseEntity getBackUps(){
        return ResponseEntity.ok(backupService.getBackups());
    }

    @GetMapping("exports/{backupId}")
    public ResponseEntity getTodos(@PathVariable long backupId){
        return ResponseEntity.ok(todoService.getTodos(backupId));
    }
}
