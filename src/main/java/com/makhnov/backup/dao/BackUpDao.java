package com.makhnov.backup.dao;

import com.makhnov.backup.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BackUpDao extends JpaRepository<User, Long>{
}
