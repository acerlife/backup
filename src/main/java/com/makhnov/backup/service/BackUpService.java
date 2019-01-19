package com.makhnov.backup.service;

import com.makhnov.backup.model.BackUp;
import com.makhnov.backup.model.User;

import java.util.List;

public interface BackUpService {
    Long save();
    List<BackUp> getBackups();
}
