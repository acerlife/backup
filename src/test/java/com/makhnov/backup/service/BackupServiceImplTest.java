package com.makhnov.backup.service;

import com.makhnov.backup.dao.BackupDao;
import com.makhnov.backup.model.Backup;
import com.makhnov.backup.service.impl.BackupServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static com.makhnov.backup.generator.Generator.createBackups;
import static com.makhnov.backup.generator.Generator.createbackup;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BackupServiceImplTest {
    @InjectMocks
    private BackupServiceImpl backupService;

    @Mock
    private BackupDao backupDao;

    @Test
    public void getBackups() throws Exception {
        List<Backup> backups = createBackups();
        when(backupDao.findAll()).thenReturn(backups);

        assertThat(backupService.getBackups(), is(backups));
    }

    @Test
    public void saveAndFlushBackup() throws Exception {
        Backup backup = createbackup(1, "OK");

        when(backupDao.saveAndFlush(any())).thenReturn(backup);

        assertThat(backupService.saveBackup(), is(backup));
    }

    @Test
    public void saveBackup() throws Exception {
        Backup backup = createbackup(1, "OK");

        backupService.saveBackup(backup);

        verify(backupDao).save(backup);
    }
}