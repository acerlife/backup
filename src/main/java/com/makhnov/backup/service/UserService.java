package com.makhnov.backup.service;


import com.makhnov.backup.model.Backup;
import com.makhnov.backup.model.User;

import java.util.Optional;

public interface UserService {
    void saveUser(Backup backup);
    Optional<User> getUserById(long id);
}
