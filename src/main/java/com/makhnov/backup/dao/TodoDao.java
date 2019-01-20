package com.makhnov.backup.dao;

import com.makhnov.backup.model.Backup;
import com.makhnov.backup.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoDao extends JpaRepository<Todo, Long> {
    List<Todo> findAllByBackup(Backup backup);
}
