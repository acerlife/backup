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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;


@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final RestTemplate restTemplate;
    private final BackupService backupService;
    private final UserDao userDao;

    @Override
    @Async
    public Long saveUser() throws InterruptedException {
        List<User> users = getUsers();
        Backup backup = createBackup();

        long backUpId = backupService.saveAndFlushBackup(backup);
        addBackupIdToUsers(users, backUpId);

        try {
            TimeUnit.SECONDS.sleep(30);
            userDao.saveAll(users);
            backup.setStatus("Ok");
            backupService.saveBackup(backup);
        }catch (Exception e){
            backup.setStatus("Fail");
            backupService.saveAndFlushBackup(backup);
        }

        return backUpId;
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

    private Backup createBackup(){
        Backup backup = new Backup();
        backup.setDate(LocalDateTime.now());
        backup.setStatus("In Progress");
        return backup;
    }
}
