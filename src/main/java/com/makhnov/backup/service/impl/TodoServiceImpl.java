package com.makhnov.backup.service.impl;

import com.makhnov.backup.dao.TodoDao;
import com.makhnov.backup.model.Backup;
import com.makhnov.backup.model.Backup;
import com.makhnov.backup.model.Todo;
import com.makhnov.backup.service.TodoService;
import com.makhnov.backup.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {
    private final TodoDao todoDao;
    private final UserService userService;

    @Override
    public StringBuilder getTodos(long id){
        return getTodosCsv(id);
    }

    private StringBuilder getTodosCsv(long id){
        Backup backup = new Backup();
        backup.setId(id);
        StringBuilder csv = new StringBuilder();

        todoDao.findAllByBackup(backup).forEach(todo -> csv.append(userService.getUserById(todo.getUserId()).get().getUserName() + ";"
                + todo.getId() + ";" + todo.getSubject() + ";"
                + todo.getDueDate() + ";" + todo.isDone() + ";"));

        return csv;
    }
}
