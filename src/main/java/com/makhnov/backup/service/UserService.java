package com.makhnov.backup.service;


import com.makhnov.backup.model.User;

import java.util.Optional;

public interface UserService {
    Long saveUser() throws InterruptedException;
    Optional<User> getUserById(long id);
}
