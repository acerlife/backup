package com.makhnov.backup.service.impl;

import com.makhnov.backup.dao.UserDao;
import com.makhnov.backup.model.Backup;
import com.makhnov.backup.model.User;
import com.makhnov.backup.service.BackupService;
import com.makhnov.backup.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final RestTemplate restTemplate;
    private final BackupService backupService;
    private final UserDao userDao;

    @Override
    @Async
    public void saveUser(Backup backup) {
        List<User> users = getUsers();
        addBackupIdToUsers(users, backup.getId());

        try {
            userDao.saveAll(users);
            backup.setStatus("OK");
            backupService.saveBackup(backup);
        }catch (Exception e){
            backup.setStatus("Failed");
            backupService.saveBackup(backup);
        }
    }

    @Override
    public Optional<User> getUserById(long id) {
        return userDao.findById(id);
    }

    private List<User> getUsers(){
        ResponseEntity<List<User>> userResponse =
                restTemplate.exchange("http://localhost:9000/users",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
                        });
        return userResponse.getBody();
    }

    private void addBackupIdToUsers(List<User> users, long backUpId){
        users.forEach(user -> user.getTodos().forEach(todo -> {
            todo.setBackupId(backUpId);
            todo.setUserId(user.getId());
        }));
    }
}
