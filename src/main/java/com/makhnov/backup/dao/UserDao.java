package com.makhnov.backup.dao;

import com.makhnov.backup.model.Backup;
import com.makhnov.backup.model.Todo;
import com.makhnov.backup.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDao extends JpaRepository<User, Long>{
}
