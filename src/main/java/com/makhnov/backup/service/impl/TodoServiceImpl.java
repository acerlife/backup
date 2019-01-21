package com.makhnov.backup.service.impl;

import com.makhnov.backup.dao.TodoDao;
import com.makhnov.backup.model.Todo;
import com.makhnov.backup.service.TodoService;
import com.makhnov.backup.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Iterator;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {
    private final TodoDao todoDao;
    private final UserService userService;

    @Override
    public String getTodos(long id){
        return createTodosCsv(id);
    }

    private String createTodosCsv(long id) {
        StringBuilder csv = new StringBuilder("Username;TodoItemId;Subject;DueDate;Done ");

        todoDao.findAllByBackupId(id).forEach(todo -> addLine(csv, todo));

        return csv.toString();
    }

    private void addLine(StringBuilder csv, Todo todo) {
        addElements(csv, userService.getUserById(todo.getUserId()).get().getUserName(), todo.getSubject(), todo.getDueDate(), todo.isDone());
    }

    private void addElements(StringBuilder csv, Object... elements) {
        Iterator<Object> elementsIterator = Arrays.asList(elements).iterator();
        while (elementsIterator.hasNext()) {
            csv.append(elementsIterator.next());
            if (elementsIterator.hasNext()) {
                csv.append(";");
            } else {
                csv.append(" ");
            }
        }
    }
}
