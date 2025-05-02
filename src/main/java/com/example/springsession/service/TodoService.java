package com.example.springsession.service;

import com.example.springsession.model.Todo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {
    private List<Todo> todoList = new ArrayList<>();
    private int id = 1;

    //GET
    public List<Todo> allView() {
        return todoList;
    }

    //POST
    public List<Todo> todoAdd(Todo task) {
        task.setId(id++);
        todoList.add(task);
        return todoList;
    }

    //PUT
    public List<Todo> edit(Todo task) {
        for (int i = 0; i < todoList.size(); i++) {
            if (todoList.get(i).getId() == task.getId()) {
                todoList.get(i).setTitle(task.getTitle());
                todoList.get(i).setCompleted(task.isCompleted());
            }
        }
        return todoList;
    }

    //PATCH
    public List<Todo> completedEdit(int id) {
        for (int i = 0; i < todoList.size(); i++) {
            if (todoList.get(i).getId() == id) {
                todoList.get(i).setCompleted(true);
            }
        }
        return todoList;
    }

    //DELETE
    public List<Todo> deleteTodo(int id) {
        for (int i = 0; i < todoList.size(); i++) {
            if (todoList.get(i).getId() == id) {
                todoList.remove(i);
            }
        }
        return todoList;
    }
}
