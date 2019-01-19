package com.makhnov.backup.dao;

import com.makhnov.backup.model.BackUp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BackUpDao extends JpaRepository<BackUp, Long> {
}
