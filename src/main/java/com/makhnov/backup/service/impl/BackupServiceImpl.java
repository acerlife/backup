package com.makhnov.backup.service.impl;

import com.makhnov.backup.dao.BackupDao;

import com.makhnov.backup.model.Backup;
import com.makhnov.backup.service.BackupService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class BackupServiceImpl implements BackupService {
    private final BackupDao backupDao;

    @Override
    public List<Backup> getBackups(){
        return backupDao.findAll();
    }

    @Override
    public Backup saveAndFlushBackup() {
        Backup backup = createBackup();

        return backupDao.saveAndFlush(backup);
    }

    @Override
    public void saveBackup(Backup backup) {
        backupDao.save(backup);
    }

    private Backup createBackup(){
        Backup backup = new Backup();
        backup.setDate(LocalDateTime.now());
        backup.setStatus("In Progress");
        return backup;
    }
}


