package com.makhnov.backup.dao;

import com.makhnov.backup.model.BackUp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BackupDao extends JpaRepository<BackUp, Long> {
}
