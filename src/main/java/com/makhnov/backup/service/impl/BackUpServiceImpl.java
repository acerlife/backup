package com.makhnov.backup.service.impl;

import com.makhnov.backup.dao.BackUpDao;
import com.makhnov.backup.model.User;
import com.makhnov.backup.service.BackUpService;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class BackUpServiceImpl implements BackUpService {
    private final RestTemplate restTemplate;
    private final BackUpDao backUpDao;

    @Override
    @Transactional
    public List<User> save() {
        ResponseEntity<List<User>> userResponse =
                restTemplate.exchange("http://localhost:9000/users",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
                        });
        List<User> users = userResponse.getBody();

        List<User> users1 = users.stream().filter(user -> !backUpDao.existsById(Long.valueOf(user.getId()))).collect(Collectors.toList());
        //System.out.println(users1);
        //backUpDao.saveAll(users1);
        return backUpDao.findAll();
    }
}
