package com.makhnov.backup.dao;

import com.makhnov.backup.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long>{
}
