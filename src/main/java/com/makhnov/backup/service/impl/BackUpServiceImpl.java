package com.makhnov.backup.service.impl;

import com.makhnov.backup.dao.BackUpDao;
import com.makhnov.backup.dao.TodoDao;
import com.makhnov.backup.dao.UserDao;

import com.makhnov.backup.model.BackUp;
import com.makhnov.backup.model.Todo;
import com.makhnov.backup.model.User;
import com.makhnov.backup.service.BackUpService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class BackUpServiceImpl implements BackUpService {
    private final RestTemplate restTemplate;
    private final TodoDao todoDao;
    private final UserDao userDao;
    private final BackUpDao backupDao;

    @Override
    @Transactional
    public Long save() {
        ResponseEntity<List<User>> userResponse =
                restTemplate.exchange("http://localhost:9000/users",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
                        });
        List<User> users = userResponse.getBody();
//        List<User> savedUsers = userDao.findAll();
        BackUp backUp = new BackUp();
        backUp.setDate(LocalDateTime.now());
        backUp.setStatus("In Progress");
        //List<User> users1 = users.stream().filter(user -> !userDao.existsById(Long.valueOf(user.getId()))).collect(Collectors.toList());
        //System.out.println(users1);
        long backUpId = backupDao.saveAndFlush(backUp).getId();
        users.forEach(user -> user.getTodos().forEach(todo -> {
            todo.setBackUpId(backUpId);
            todo.setUserId(user.getId());
        }));
        userDao.saveAll(users);
        backUp.setStatus("Ok");
        backupDao.save(backUp);
        return backUpId;
    }

    @Override
    public List<BackUp> getBackups(){
        return backupDao.findAll();
    }

    @Override
    public List<Todo> getTodos(long id){
        BackUp backUp = new BackUp();
        backUp.setId(id);

        return todoDao.findAllByBackUp(backUp);
    }
}


