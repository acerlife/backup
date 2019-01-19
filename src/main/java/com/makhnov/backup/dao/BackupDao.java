package com.makhnov.backup.dao;

import com.makhnov.backup.model.Backup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BackupDao extends JpaRepository<Backup, Long> {
}
