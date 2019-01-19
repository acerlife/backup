package com.makhnov.backup.service;

import com.makhnov.backup.model.Backup;

import java.util.List;

public interface BackupService {
    List<Backup> getBackups();
    Long saveAndFlushBackup(Backup backup);
    void saveBackup(Backup backup);
}
