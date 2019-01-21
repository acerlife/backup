package com.makhnov.backup.generator;

import com.makhnov.backup.model.Backup;
import com.makhnov.backup.model.Todo;
import com.makhnov.backup.model.User;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class Generator {

    public static Backup createbackup(long id, String status){
        Backup backup = new Backup();
        backup.setId(id);
        backup.setDate(LocalDateTime.now());
        backup.setStatus(status);

        return backup;
    }

    public static List<Backup> createBackups(){
        return Arrays.asList(createbackup(1, "OK"),
                createbackup(2, "In progress"), createbackup(3, "Fail"));
    }

    public static List<Todo> createTodos(long backupId){
        return Arrays.asList(createTodo(1, "a", backupId, 1),
                createTodo(2, "b", backupId, 1),
                createTodo(3, "c", backupId, 1));
    }

    public static Todo createTodo(long id, String subject, long backuId, long userId){
        Todo todo = new Todo();
        todo.setId(id);
        todo.setSubject(subject);
        todo.setDueDate(LocalDateTime.now());
        todo.setBackupId(backuId);
        todo.setUserId(userId);

        return todo;
    }

    public static User createUser(String userName){
        User user = new User();
        user.setUserName(userName);

        return user;
    }

    public static StringBuilder createCsv(List<Todo> todos, String userName){
        StringBuilder csv = new StringBuilder();
        csv.append("Username;TodoItemId;Subject;DueDate;Done ");

        todos.forEach(todo -> csv.append(userName + ";"
                + todo.getId() + ";" + todo.getSubject() + ";"
                + todo.getDueDate() + ";" + todo.isDone() + ";"));

        return csv;
    }
}
