package com.example.springsession.controller;


import com.example.springsession.domain.Todo;
import com.example.springsession.dto.createTodo;
import com.example.springsession.dto.patchTodo;
import com.example.springsession.dto.putTodo;
import com.example.springsession.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public ResponseEntity<Todo> create(@RequestBody createTodo createtodo) {
        Todo newOne = todoService.create(createtodo);
        return ResponseEntity.ok(newOne);
    }

    @GetMapping
    public ResponseEntity<List<Todo>> getAll() {
        return ResponseEntity.ok(todoService.getAll());
    }

    @GetMapping("/{id}") //개별조회
    public ResponseEntity<Todo> getById(@PathVariable Long id) {
        Todo todo = todoService.getById(id);
        if (todo == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(todo);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Todo> patch(@PathVariable Long id, @RequestBody patchTodo patchtodo) {
        Todo todo = todoService.patchTodo(id, patchtodo);
        if(todo == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(todo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> put(@PathVariable Long id, @RequestBody putTodo puttodo) {
        Todo todo = todoService.put(id, puttodo);
        if(todo == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(todo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Todo> delete(@PathVariable Long id) {
        Todo todo = todoService.delete(id);
        if(todo == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(todo);
    }



}
