package com.makhnov.backup.controller;

import com.makhnov.backup.model.Backup;
import com.makhnov.backup.service.BackupService;
import com.makhnov.backup.service.TodoService;
import com.makhnov.backup.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.*;


@RestController
@AllArgsConstructor
@RequestMapping("backups")
public class BackupController {
    private final BackupService backupService;
    private final UserService userService;
    private final TodoService todoService;

    @PostMapping
    public ResponseEntity save() throws InterruptedException, ExecutionException, TimeoutException {
        Backup backup = backupService.saveBackup();
        userService.backupUsers(backup);
        return ResponseEntity.ok(backup.getId());
    }

    @GetMapping
    public ResponseEntity getBackups(){
        return ResponseEntity.ok(backupService.getBackups());
    }

    @GetMapping("exports/{backupId}")
    public ResponseEntity getTodos(@PathVariable long backupId){
        return ResponseEntity.ok(todoService.getTodos(backupId));
    }
}
