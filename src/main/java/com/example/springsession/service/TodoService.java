package com.example.springsession.service;

import com.example.springsession.domain.Todo;
import com.example.springsession.dto.createTodo;
import com.example.springsession.dto.patchTodo;
import com.example.springsession.dto.putTodo;
import com.example.springsession.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TodoService {
    private final TodoRepository todoRepository;
    private long seq = 0L;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo create(createTodo newTodo) {
        Todo todo = new Todo();
        todo.setId(++seq);
        todo.setTitle(newTodo.getTitle());
        todo.setDescription(newTodo.getDescription());
        todo.setCompleted(false);
        return todoRepository.save(todo);
    }

    public List<Todo> getAll() {
        return todoRepository.findAll();
    }

    public Todo getById(Long id) {
        Todo todo = todoRepository.findById(id);
        return todo;
    }

    public Todo patchTodo(Long id, patchTodo patchtodo) {
        Todo change = todoRepository.findById(id);
        if(change == null) throw new NoSuchElementException("todo 존재하지 않음");

        //부분 수정이니, 값이 null이면 그냥 pass/값이 존재하면 수정
        if(patchtodo.getTitle() != null) change.setTitle(patchtodo.getTitle());
        if(patchtodo.getDescription() != null) change.setDescription(patchtodo.getDescription());
        if(patchtodo.getCompleted() != null) change.setCompleted(patchtodo.getCompleted());

        return change;
    }

    public Todo put(Long id, putTodo newOne) {
        Todo todo = todoRepository.findById(id);
        if (todo == null || newOne.checkNull()) {
            throw new NoSuchElementException("todo 존재하지 않음");
        }
        todo.setTitle(newOne.getTitle());
        todo.setDescription(newOne.getDescription());
        todo.setCompleted(newOne.getCompleted());
        return todoRepository.save(todo);
    }

    public Todo delete(Long id) {
        Todo todo = todoRepository.findById(id);
        if (todo == null) throw new NoSuchElementException("해당 ID의 할 일이 없습니다: " + id);

        todoRepository.delete(id);
        return todo;
    }



}
